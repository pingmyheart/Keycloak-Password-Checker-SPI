package io.github.pingmyheart.keycloakpasswordcheckerspi.factory;

import io.github.pingmyheart.keycloakpasswordcheckerspi.provider.CredentialCheckerResourceProvider;
import io.github.pingmyheart.keycloakpasswordcheckerspi.service.CredentialCheckerService;
import org.keycloak.Config;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.services.resource.RealmResourceProvider;
import org.keycloak.services.resource.RealmResourceProviderFactory;

public class CredentialCheckerResourceProviderFactory implements RealmResourceProviderFactory {
    public static final String ID = "credential-checker"; // URL segment

    @Override
    public RealmResourceProvider create(KeycloakSession session) {
        CredentialCheckerService service = new CredentialCheckerService(session);
        return new CredentialCheckerResourceProvider(service);
    }

    @Override
    public void init(Config.Scope config) {


    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return ID;
    }
}
