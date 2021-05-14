package com.tweetapp.app.demo.controller;

import com.tweetapp.app.demo.domain.ResetPassword;
import com.tweetapp.app.demo.domain.User;
import com.tweetapp.app.demo.exception.InvalidDetailsException;
import com.tweetapp.app.demo.exception.UserAlreadyExists;
import com.tweetapp.app.demo.exception.UserDoesNotExists;
import com.tweetapp.app.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<List<User>> getSearchUsers(@PathVariable String username) {
        return new ResponseEntity<>(userService.getSearchUsers(username), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody ResetPassword restPassword) throws UserDoesNotExists, InvalidDetailsException {
        return new ResponseEntity<>(userService.loginUser(restPassword.getLoginID(), restPassword.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user) throws UserAlreadyExists {
        return new ResponseEntity<>(userService.registerNewUser(user), HttpStatus.OK);
    }

    @PutMapping("/forgot")
    public ResponseEntity<User> forgotPassword(@RequestBody ResetPassword restPassword) throws UserDoesNotExists {
        return new ResponseEntity<>(userService.forgotPassword(restPassword), HttpStatus.OK);
    }

    @PutMapping("/addBIO")
    public ResponseEntity<User> addBIO(@RequestBody User user) {
        return new ResponseEntity<>(userService.addBIO(user), HttpStatus.OK);
    }

    @PutMapping("/logout/{loginID}")
    public ResponseEntity<String> logout(@PathVariable String loginID) throws UserDoesNotExists {
        return new ResponseEntity<>(userService.logout(loginID), HttpStatus.OK);
    }

}
