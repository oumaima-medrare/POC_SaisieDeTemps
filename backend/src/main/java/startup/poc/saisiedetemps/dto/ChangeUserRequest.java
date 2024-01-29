package startup.poc.saisiedetemps.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserRequest {

    private Long userId;
    private Long managerId;
}