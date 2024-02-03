package startup.poc.saisiedetemps.services;

import java.util.List;

import org.springframework.stereotype.Service;
import startup.poc.saisiedetemps.dto.ChangeUserRequest;
import startup.poc.saisiedetemps.dto.ChangeUserRoleRequest;
import startup.poc.saisiedetemps.dto.CreateUserRequest;
import startup.poc.saisiedetemps.dto.UpdateUserRequest;
import startup.poc.saisiedetemps.models.User;
@Service
public interface UserService {

    public User createUser(CreateUserRequest createUserRequest);

    public List<User> findUsersOfManager(Long idUser);

    public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest);

    public User changeManagerOfUser(ChangeUserRequest changeUserRequest);

    public User findUser(Long idUser);

    public User updateUser(Long idUser ,UpdateUserRequest updateUserRequest);



}