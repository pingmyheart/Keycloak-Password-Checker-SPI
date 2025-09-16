package io.github.pingmyheart.keycloakpasswordcheckerspi.service;

import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsRequest;
import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsResponse;
import jakarta.ws.rs.core.Response;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;

import static java.util.Objects.isNull;

public class CredentialCheckerService {
    private final KeycloakSession session;

    public CredentialCheckerService(KeycloakSession session) {
        this.session = session;
    }

    public Response checkCredentials(CredentialsRequest request) {
        UserModel user = session.users().getUserByUsername(session.getContext().getRealm(), request.getUsername());
        if (isNull(user)) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(CredentialsResponse.builder()
                            .valid(Boolean.FALSE)
                            .build())
                    .build();
        }
        return Response.status(Response.Status.OK)
                .entity(CredentialsResponse.builder()
                        .valid(user.credentialManager().isValid(request.toCredentialInput()))
                        .build())
                .build();
    }
}
