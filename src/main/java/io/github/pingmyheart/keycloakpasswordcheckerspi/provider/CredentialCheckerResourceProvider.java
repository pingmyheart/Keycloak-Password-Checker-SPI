package io.github.pingmyheart.keycloakpasswordcheckerspi.provider;

import io.github.pingmyheart.keycloakpasswordcheckerspi.dto.CredentialsRequest;
import io.github.pingmyheart.keycloakpasswordcheckerspi.service.CredentialCheckerService;
import org.keycloak.services.resource.RealmResourceProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
