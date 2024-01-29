package startup.poc.saisiedetemps.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import startup.poc.saisiedetemps.dto.ChangeUserRequest;
import startup.poc.saisiedetemps.dto.ChangeUserRoleRequest;
import startup.poc.saisiedetemps.dto.CreateUserRequest;
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



    public User createUser(CreateUserRequest createUserRequest, Long idUser) {

        // save data's coming from inputs
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setFirstname(createUserRequest.getFirstname());
        user.setLastname(createUserRequest.getLastname());

        // Set the user's role
        user.setRole(createUserRequest.getRole());


        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User changeAffectationForUser(ChangeUserRequest changeUserRequest) {

        // get user by id and manager by id

        User user = userRepository.findById(changeUserRequest.getUserId()).get();
        User manager = userRepository.findById(changeUserRequest.getManagerId()).get();

        //user.getRole().getId() if roleId == 1 then do
        // user.setManager(manager)
        //userRepository.save(User)

        if(user.getRole() == Role.USER && manager.getRole() == Role.MANAGER) {
            user.setManager(manager);
        }

        return userRepository.save(user);
    }


    @Override
    public User changeUserRole(ChangeUserRoleRequest changeUserRoleRequest) {

        User user = userRepository.findById(changeUserRoleRequest.getUserId()).get();

        //if the current role is manager
        //we should delete manager for affected users
        //manager -> admin
        if (user.getRole() == Role.MANAGER && changeUserRoleRequest.getRole() != Role.MANAGER) {

            List <User> usersOfManager = findUsersofManager(user.getId());
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
    public List<User> findUsersofManager(Long idManager) {
        return userRepository.findAllByManagerId(idManager);
    }

    @Override
    public User editUser(User user_) {
        System.out.println(user_.getEmail());
        System.out.println(user_.getFirstname());
        System.out.println(user_.getRole());
        System.out.println("**************");
        User user = userRepository.findById(user_.getId()).get();

        //changing infos
        user.setFirstname(user_.getFirstname());
        user.setLastname(user_.getLastname());
        user.setEmail(user_.getEmail());
        user.setUsername(user_.getUsername());


        // changing role
        if (user.getRole() == Role.MANAGER && user_.getRole() != Role.MANAGER) {

            List <User> usersOfManager = findUsersofManager(user.getId());
            for (User u :usersOfManager) {
                u.setManager(null);
                userRepository.save(u);
            }
        }

        //employee -> manager or employee -> admin
        if (user.getRole() == Role.USER && (user_.getRole() == Role.MANAGER || user_.getRole() == Role.ADMIN)) {
            user.setManager(null);

        }

        user.setRole(user_.getRole());


        //changing affectation
        if (user_.getManager()!=null)
        {   User manager = userRepository.findById(user_.getManager().getId()).get();
            if(user.getRole() == Role.ADMIN && manager.getRole() == Role.MANAGER) {
                user.setManager(manager);
            }}


        return userRepository.save(user);
    }



}