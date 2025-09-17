package io.github.pingmyheart.keycloakpasswordcheckerspi.service;

import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsRequest;
import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;

import static java.util.Objects.isNull;

@Path("/check")
public class CredentialCheckerService {
    private final KeycloakSession session;

    public CredentialCheckerService(KeycloakSession session) {
        this.session = session;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCredentials(CredentialsRequest request) {
        UserModel user = session.users().getUserByUsername(session.getContext().getRealm(), request.getUsername());
        if (isNull(user)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(CredentialsResponse.builder()
                            .valid(Boolean.FALSE)
                            .build())
                    .build();
        }
        boolean valid = user.credentialManager().isValid(request.toCredentialInput());
        return Response.status(valid ?
                        Response.Status.OK :
                        Response.Status.UNAUTHORIZED)
                .entity(CredentialsResponse.builder()
                        .valid(valid)
                        .build())
                .build();
    }

}
