package io.github.pingmyheart.keycloakpasswordcheckerspi.provider;

import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsRequest;
import io.github.pingmyheart.keycloakpasswordcheckerspi.service.CredentialCheckerService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.keycloak.services.resource.RealmResourceProvider;

@Path("/check")
public class CredentialCheckerResourceProvider implements RealmResourceProvider {
    private final CredentialCheckerService service;

    public CredentialCheckerResourceProvider(CredentialCheckerService service) {
        this.service = service;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCredentials(CredentialsRequest request) {
        return service.checkCredentials(request);
    }

    @Override
    public Object getResource() {
        return this;
    }

    @Override
    public void close() {

    }
}
