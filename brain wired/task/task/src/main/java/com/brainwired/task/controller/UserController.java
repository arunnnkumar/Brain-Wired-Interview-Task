package com.brainwired.task.controller;

import com.brainwired.task.Service.UserService;
import com.brainwired.task.customException.UserNotAvailableException;
import com.brainwired.task.model.User;
import com.brainwired.task.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserResponse<User> addUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public UserResponse<List<User>> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public UserResponse<User> getUser(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @PutMapping("/users")
    public UserResponse<User> updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public UserResponse<Boolean> deleteUser(@PathVariable Long id) throws Exception {
        return userService.deleteUser(id);
    }


    @ExceptionHandler(value = UserNotAvailableException.class)
    public UserResponse userIdNotAvailableHandler(UserNotAvailableException e) {
        return UserResponse
                .builder()
                .message(e.getMessage())
                .statusCode(404)
                .build();
    }
}
