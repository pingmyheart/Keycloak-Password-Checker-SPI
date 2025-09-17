package io.github.pingmyheart.keycloakpasswordcheckerspi.provider;

import io.github.pingmyheart.keycloakpasswordcheckerspi.service.CredentialCheckerService;
import org.keycloak.services.resource.RealmResourceProvider;

public class CredentialCheckerResourceProvider implements RealmResourceProvider {
    private final CredentialCheckerService service;

    public CredentialCheckerResourceProvider(CredentialCheckerService service) {
        this.service = service;
    }

    @Override
    public Object getResource() {
        return service;
    }

    @Override
    public void close() {

    }
}
