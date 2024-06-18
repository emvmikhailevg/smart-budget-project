package ru.emelianov.smartbudgetproject.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.emelianov.smartbudgetproject.database.model.User;

import java.util.Optional;

/**
 * <p>Репозиторий для управления сущностями {@link User} в базе данных</p>
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Находит пользователя по имени пользователя
     *
     * @param username имя пользователя
     * @return объект {@link Optional}, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByUsername(String username);

    /**
     * Находит пользователя по электронной почте
     *
     * @param email электронная почта
     * @return объект {@link Optional}, содержащий найденного пользователя, если он существует
     */
    Optional<User> findByEmail(String email);

}
