package io.github.pingmyheart.keycloakpasswordcheckerspi.service;

import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsRequest;
import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class CredentialCheckerService {
    private final KeycloakSession session;

    @Path("/check")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCredentials(CredentialsRequest request) {
        UserModel user = this.getUser(request.getUsername());
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

    private UserModel getUser(String username) {
        assert session != null;
        UserModel userModel = session.users().getUserByUsername(session.getContext().getRealm(), username);
        if (nonNull(userModel)) {
            return userModel;
        }
        userModel = session.users().getUserByEmail(session.getContext().getRealm(), username);
        if (nonNull(userModel)) {
            return userModel;
        }
        userModel = session.users().getUserById(session.getContext().getRealm(), username);
        if (nonNull(userModel)) {
            return userModel;
        } else {
            return null;
        }
    }
}
