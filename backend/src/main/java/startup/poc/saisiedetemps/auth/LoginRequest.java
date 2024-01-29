package startup.poc.saisiedetemps.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @Schema(defaultValue = "John.Doe@gmail.com")
    private String email;
    @NotBlank
    @Schema(defaultValue = "Change_This")
    private String password;
}