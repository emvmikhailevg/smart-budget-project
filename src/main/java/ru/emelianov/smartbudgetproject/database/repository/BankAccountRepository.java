package ru.emelianov.smartbudgetproject.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelianov.smartbudgetproject.database.model.BankAccount;

import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления сущностями {@link BankAccount} в базе данных</p>
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    /**
     * Находит все банковские счета по идентификатору пользователя
     *
     * @param id идентификатор пользователя
     * @return список банковских счетов, принадлежащих пользователю
     */
    List<BankAccount> findByUserId(Long id);

    /**
     * Находит банковский счет по его идентификатору и идентификатору пользователя
     *
     * @param accountId идентификатор банковского счета
     * @param userId идентификатор пользователя
     * @return объект {@link Optional}, содержащий найденный банковский счет, если он существует
     */
    Optional<BankAccount> findByIdAndUserId(Long accountId, Long userId);

}
