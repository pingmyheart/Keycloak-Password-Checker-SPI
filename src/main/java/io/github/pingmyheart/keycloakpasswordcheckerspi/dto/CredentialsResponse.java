package io.github.pingmyheart.keycloakpasswordcheckerspi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CredentialsResponse {
    private Boolean valid;

}
