package startup.poc.saisiedetemps.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRequest {
    @NotBlank
    @Schema(defaultValue = "Project's title")
    private String title;
    @NotBlank
    @Schema(defaultValue = "Project's description")
    private String description;
}
