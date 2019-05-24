package com.mitrais.rms.controller;

import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

/*    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }*/

    @PostMapping
    public ResponseEntity<?> add(@RequestBody User user) {
        boolean status = userService.save(user);
        return ResponseEntity.ok().body("User add status = " + status);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        boolean status = userService.update(user);
        return ResponseEntity.ok().body("User with id=" + user.getId() + " was been successfully updated");
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody User user) {
        boolean status = userService.delete(user);
        return ResponseEntity.ok().body("User" + user.getUserName() + " was successfully deleted");
    }

    @GetMapping("/all")
    public List<User> getAll(){
        System.out.println("here");
        return userService.findAll();
    }

    @GetMapping
    public String test(){
        System.out.println("here");
        return "here";
    }

}
