package com.desafios.DesafiosEducativosBackend.web.rest;

import com.desafios.DesafiosEducativosBackend.domain.entities.User;
import com.desafios.DesafiosEducativosBackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("/{username}/{password}")
    public ResponseEntity<User> getUsersByUsernameAndPassword( @PathVariable final String username, @PathVariable final String password){
        return ResponseEntity.ok().body(userService.getUserByUsernameAndPassword(username,password));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody final User user) throws URISyntaxException {
        if (user.getId() != null){
            throw new IllegalArgumentException("The new user shouldn't have Id ");
        }
        user.setCreateAt(LocalDateTime.now());
        User userdb = userService.save(user);
        return ResponseEntity.created(new URI("/v1/users/"+userdb.getId())).body(userdb);
    }
}
