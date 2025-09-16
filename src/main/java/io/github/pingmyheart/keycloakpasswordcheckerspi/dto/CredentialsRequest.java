package io.github.pingmyheart.keycloakpasswordcheckerspi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.credential.CredentialInput;
import org.keycloak.models.UserCredentialModel;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CredentialsRequest {
    private String username;
    private String password;

    public CredentialInput toCredentialInput() {
        return UserCredentialModel.password(this.password);
    }
}
