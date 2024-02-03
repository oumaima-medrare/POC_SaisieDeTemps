package startup.poc.saisiedetemps.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import startup.poc.saisiedetemps.dto.ChangeUserRequest;
import startup.poc.saisiedetemps.dto.ChangeUserRoleRequest;
import startup.poc.saisiedetemps.dto.CreateUserRequest;
import startup.poc.saisiedetemps.dto.UpdateUserRequest;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.repositories.UserRepository;
import startup.poc.saisiedetemps.securtiy.Role;
import startup.poc.saisiedetemps.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;



    public User createUser(CreateUserRequest createUserRequest) {

        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setFirstname(createUserRequest.getFirstname());
        user.setLastname(createUserRequest.getLastname());

        user.setRole(createUserRequest.getRole());

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User changeManagerOfUser(ChangeUserRequest changeUserRequest) {

        // get user by id and manager by id

        User user = userRepository.findById(changeUserRequest.getUserId()).get();
        User manager = userRepository.findById(changeUserRequest.getManagerId()).get();

        user.setManager(manager);

        return userRepository.save(user);
    }


    @Override
    public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest) {

        User user = userRepository.findById(changeUserRoleRequest.getUserId()).get();

        //if the current role is manager
        //we should delete manager for affected users
        //manager -> admin
        if (user.getRole() == Role.MANAGER && changeUserRoleRequest.getRole() != Role.MANAGER) {

            List <User> usersOfManager = findUsersOfManager(user.getId());
            for (User u :usersOfManager) {
                u.setManager(null);
                userRepository.save(u);
            }
        }

        //employee -> manager or employee -> admin
        if (user.getRole() == Role.USER && (changeUserRoleRequest.getRole() == Role.MANAGER || changeUserRoleRequest.getRole() == Role.ADMIN)) {
            user.setManager(null);

        }

        //affect the new role
        user.setRole(changeUserRoleRequest.getRole());

        return userRepository.save(user);
    }

    @Override
    public User findUser(Long idUser) {
        return userRepository.findById(idUser).get();
    }


    @Override
    public List<User> findUsersOfManager(Long idManager) {
        return userRepository.findAllByManagerId(idManager);
    }

    @Override
    public User updateUser(Long idUser, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(idUser).get();

        //changing infos
        user.setFirstname(updateUserRequest.getFirstname());
        user.setLastname(updateUserRequest.getLastname());
        user.setEmail(updateUserRequest.getEmail());
        user.setUsername(updateUserRequest.getUsername());

        return userRepository.save(user);
    }



}