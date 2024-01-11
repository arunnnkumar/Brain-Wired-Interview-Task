package com.brainwired.task.Service;

import com.brainwired.task.customException.UserNotAvailableException;
import com.brainwired.task.model.User;
import com.brainwired.task.repository.UserRepository;
import com.brainwired.task.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse<User> addUser(User user) {
        return UserResponse
                .<User>builder()
                .object(userRepository.save(user))
                .statusCode(200)
                .message("successfully added")
                .build();
    }

    public UserResponse<List<User>> getUsers() {

        List<User> users = userRepository.findAll().stream().filter(u -> u.isEnable()).collect(Collectors.toList());
        String message = users.isEmpty() ? "User not available" : "";
        return UserResponse
                .<List<User>>builder()
                .object(users)
                .message(message)
                .statusCode(200)
                .build();
    }

    public UserResponse<User> getUser(Long id) throws UserNotAvailableException {
        User u = userRepository
                .findById(id)
                .orElseThrow(()-> new UserNotAvailableException("User id not available..."));
        return UserResponse
                .<User>builder()
                .object(u.isEnable() ? u : null)
                .statusCode(200)
                .build();
    }

    public UserResponse<User> updateUser(User user) {
        return UserResponse
                .<User>builder()
                .object(userRepository.save(user))
                .statusCode(200)
                .build();
    }

    @Transactional
    public UserResponse<Boolean> deleteUser(Long id) throws UserNotAvailableException{
        getUser(id);
        userRepository.updateEnableById(id, false);
        return UserResponse
                .<Boolean>builder()
                .object(1 > 0)
                .statusCode(200)
                .message("Deletion compeleted successfully")
                .build();
    }


}
