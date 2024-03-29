package startup.poc.saisiedetemps.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import startup.poc.saisiedetemps.securtiy.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Schema(defaultValue = "John")
    private String firstname;
    @NotBlank
    @Schema(defaultValue = "Doe")
    private String lastname;
    @NotBlank
    @Schema(defaultValue = "John.Doe@gmail.com")
    private String email;
    @NotBlank
    @Schema(defaultValue = "Change_This")
    private String password;
    @NotBlank
    @Schema(defaultValue = "USER")
    private Role role;
}