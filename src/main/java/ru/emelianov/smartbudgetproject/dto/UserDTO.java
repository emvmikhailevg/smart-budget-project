package ru.emelianov.smartbudgetproject.dto;

/**
 * <p>DTO для передачи информации о пользователе</p>
 *
 * @param username        имя пользователя
 * @param email           электронная почта пользователя
 * @param password        пароль пользователя
 * @param confirmPassword подтверждение пароля пользователя
 */
public record UserDTO(
        String username,
        String email,
        String password,
        String confirmPassword) { }
