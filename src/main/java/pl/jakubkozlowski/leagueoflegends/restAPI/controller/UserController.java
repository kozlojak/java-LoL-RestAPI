package pl.jakubkozlowski.leagueoflegends.restAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.leagueoflegends.restAPI.controller.descriptor.UserControllerDescriptor;
import pl.jakubkozlowski.leagueoflegends.restAPI.dto.UserDTO;
import pl.jakubkozlowski.leagueoflegends.restAPI.service.UserService;

@RestController
@RequestMapping(path = UserControllerDescriptor.USER_BASE_PATH)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = UserControllerDescriptor.BY_ID)
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        return userService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping(value = UserControllerDescriptor.USERNAME)
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping(value = UserControllerDescriptor.BY_ID)
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        userService.update(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @DeleteMapping(value = UserControllerDescriptor.BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
