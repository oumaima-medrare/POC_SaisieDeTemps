package startup.poc.saisiedetemps.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import startup.poc.saisiedetemps.securtiy.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserRoleRequest {

    @NotBlank
    private Long userId;
    @NotBlank
    private Role role;

}