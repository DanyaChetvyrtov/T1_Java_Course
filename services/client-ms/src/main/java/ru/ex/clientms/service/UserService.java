package ru.ex.clientms.service;

import ru.ex.clientms.dto.UserDto;
import ru.ex.clientms.dto.request.UserLoginRequest;
import ru.ex.clientms.dto.request.UserRegisterRequest;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserRegisterRequest request);

    void loginUser(UserLoginRequest request);

    List<UserDto> getAllUsers();

    UserDto getUser(Long userId);

    void deleteUser(Long userId);
}

