package ru.ex.clientms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ex.clientms.dto.UserDto;
import ru.ex.clientms.dto.request.UserLoginRequest;
import ru.ex.clientms.dto.request.UserRegisterRequest;
import ru.ex.clientms.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Validated @RequestBody UserRegisterRequest request) {
        UserDto user = userService.registerUser(request);
        return ResponseEntity.created(URI.create("/api/users/" + user.getId())).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@Validated @RequestBody UserLoginRequest request) {
        userService.loginUser(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }
}

