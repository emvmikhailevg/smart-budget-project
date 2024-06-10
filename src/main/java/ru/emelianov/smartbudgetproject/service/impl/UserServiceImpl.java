package ru.emelianov.smartbudgetproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.emelianov.smartbudgetproject.database.model.Role;
import ru.emelianov.smartbudgetproject.database.model.User;
import ru.emelianov.smartbudgetproject.database.repository.UserRepository;
import ru.emelianov.smartbudgetproject.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(String email, String username, String password) {
        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);

        userRepository.save(user);
    }

}
