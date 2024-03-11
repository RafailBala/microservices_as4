package com.example.userservice.controller;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserNameDto;
import com.example.userservice.dto.UserUpdateDto;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "names")
    public List<UserNameDto> getUsersName() throws IOException {
        return userService.getUsersName();
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() throws IOException {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "change_user_status/{userId}")
    public ResponseEntity<?> changeUserStatus(@PathVariable Long userId) throws IOException {
        try {
            return new ResponseEntity<>(userService.changeUserStatus(userId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "create_user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws IOException {
        try {
            return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Компания не существует!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "update_user")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDto userUpdateDto) throws IOException {
        try {
            return new ResponseEntity<>(userService.updateUser(userUpdateDto), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(userUpdateDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "exists-by-id/{userId}")
    public ResponseEntity<?> existsById(@PathVariable(value = "userId") long userId) throws IOException {
        try {
            if (userService.existsById(userId)) {
                return new ResponseEntity<>(userService.existsById(userId), HttpStatus.OK);
            } else  return new ResponseEntity<>(userService.existsById(userId), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(userId, HttpStatus.BAD_REQUEST);
        }
    }
}

