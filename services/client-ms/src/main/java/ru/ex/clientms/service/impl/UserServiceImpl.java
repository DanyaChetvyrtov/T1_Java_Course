package ru.ex.clientms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ex.clientms.dto.UserDto;
import ru.ex.clientms.dto.request.UserLoginRequest;
import ru.ex.clientms.dto.request.UserRegisterRequest;
import ru.ex.clientms.exception.custom.PasswordMismatchException;
import ru.ex.clientms.exception.custom.UserAlreadyExist;
import ru.ex.clientms.exception.custom.UserNotFound;
import ru.ex.clientms.mapepr.UserMapper;
import ru.ex.clientms.models.User;
import ru.ex.clientms.repository.UserRepository;
import ru.ex.clientms.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto registerUser(UserRegisterRequest request) {
        validateRegisterRequest(request);

        User user = userMapper.toEntity(request);
        user = userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public void loginUser(UserLoginRequest request) {
        var user = userRepository.findByLogin(request.getLogin());

        if (user.isEmpty())
            throw new UserNotFound("User with '" + request.getLogin() + "' login not found");

        // TODO: добавить поле isActive и сделать его валидацию также добавить ручку, чтоб заблочить юзера
        if (!user.get().getPassword().equals(request.getPassword()))
            throw new PasswordMismatchException("Invalid password");
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(UserNotFound::new);
    }

    private void validateRegisterRequest(UserRegisterRequest request) {
        if (!request.getPassword().equals(request.getPasswordConfirmation()))
            throw new PasswordMismatchException("Passwords do not match");

        if (userRepository.existsByLogin(request.getLogin()))
            throw new UserAlreadyExist("User with '" + request.getLogin() + "' login already exists");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new UserAlreadyExist("User with '" + request.getEmail() + "' email already exists");
    }
}
