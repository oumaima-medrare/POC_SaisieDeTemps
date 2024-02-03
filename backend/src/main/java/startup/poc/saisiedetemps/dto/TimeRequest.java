package startup.poc.saisiedetemps.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeRequest {

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    @Schema(defaultValue = "1")
    private Long projectId;


}