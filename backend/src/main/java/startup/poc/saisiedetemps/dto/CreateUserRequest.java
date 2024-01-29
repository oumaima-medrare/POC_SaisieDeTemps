package startup.poc.saisiedetemps.dto;


import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import startup.poc.saisiedetemps.securtiy.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Instant createdAt;
    private Role role;
    private Long managerId;

}