package ru.emelianov.smartbudgetproject.service;

/**
 * <p>Сервис для управления пользователями</p>
 */
public interface UserService {

    /**
     * Регистрирует нового пользователя
     *
     * @param email    электронная почта пользователя
     * @param username имя пользователя
     * @param password пароль пользователя
     */
    void register(String email, String username, String password);

}
