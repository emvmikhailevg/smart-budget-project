package ru.emelianov.smartbudgetproject.database.repository;

import org.springframework.data.repository.CrudRepository;
import ru.emelianov.smartbudgetproject.database.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
