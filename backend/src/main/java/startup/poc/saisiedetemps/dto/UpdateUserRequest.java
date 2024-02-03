package startup.poc.saisiedetemps.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotBlank
    @Schema(defaultValue = "John")
    private String firstname;
    @Schema(defaultValue = "Doe")
    private String lastname;
    @Schema(defaultValue = "JohnDoe")
    private String username;
    @Schema(defaultValue = "John.Doe@gmail.com")
    private String email;
}
