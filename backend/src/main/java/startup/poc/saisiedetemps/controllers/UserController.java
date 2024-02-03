package startup.poc.saisiedetemps.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import startup.poc.saisiedetemps.dto.ChangeUserRequest;
import startup.poc.saisiedetemps.dto.ChangeUserRoleRequest;
import startup.poc.saisiedetemps.dto.CreateUserRequest;
import startup.poc.saisiedetemps.dto.UpdateUserRequest;
import startup.poc.saisiedetemps.models.User;
import startup.poc.saisiedetemps.services.UserService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH })
@Tag(name="Users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/users")
    @Operation(summary = "Create a user")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<User>(userService.createUser(createUserRequest),HttpStatus.CREATED);
    }

    @PostMapping("/users/change-role")
    @Operation(summary = "Change user role")
    public ResponseEntity<User> changeUserRole(@RequestBody ChangeUserRoleRequest changeUserRoleRequest){
        return new ResponseEntity<User>(userService.changeUserRole(changeUserRoleRequest),HttpStatus.OK);
    }


    @PostMapping("/users/change")
    @Operation(summary = "Change manager of user")
    public ResponseEntity<User> changeManagerOfUser(@RequestBody ChangeUserRequest changeUserRequest){
        return new ResponseEntity<User>(userService.changeManagerOfUser(changeUserRequest),HttpStatus.OK);
    }

    @PutMapping("/users/{idUser}")
    @Operation(summary = "Update user info ")
    public ResponseEntity<User> updateUser(@PathVariable Long idUser, @RequestBody UpdateUserRequest updateUserRequest){
        return new ResponseEntity<User>(userService.updateUser(idUser,updateUserRequest),HttpStatus.OK);
    }

    @GetMapping("/users/{idUser}")
    @Operation(summary = "Get user info")
    public ResponseEntity<User> getUserInfos(@PathVariable Long idUser){
        return new ResponseEntity<User>(userService.findUser(idUser),HttpStatus.OK);
    }

    @GetMapping("/users/manager/{managerId}")
    @Operation(summary = "Find all users of manager")
    public ResponseEntity<List<User>> findAllUsersOfManager(@PathVariable Long managerId){
        return new ResponseEntity<List<User>>(userService.findUsersOfManager(managerId),HttpStatus.OK);
    }

}