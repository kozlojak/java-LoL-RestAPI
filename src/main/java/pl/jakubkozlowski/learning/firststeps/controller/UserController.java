package pl.jakubkozlowski.learning.firststeps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakubkozlowski.learning.firststeps.dto.UserDTO;
import pl.jakubkozlowski.learning.firststeps.service.UserService;

import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.UserControllerDescriptor.BY_ID;
import static pl.jakubkozlowski.learning.firststeps.controller.descriptor.UserControllerDescriptor.USER_BASE_PATH;

@RestController
@RequestMapping(path = USER_BASE_PATH)
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = BY_ID)
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<UserDTO> findUserByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(userService.findUserByUsername(name));
    }

    @PutMapping(value = BY_ID)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO user) {
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @DeleteMapping(value = BY_ID)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
