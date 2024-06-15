package ru.emelianov.smartbudgetproject.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelianov.smartbudgetproject.database.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>Репозиторий для управления сущностями {@link Transaction} в базе данных</p>
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Находит список транзакций для указанного банковского счета в заданном диапазоне дат
     *
     * @param bankAccountId ID банковского счета
     * @param startDate     начальная дата диапазона
     * @param endDate       конечная дата диапазона
     * @return список транзакций
     */
    List<Transaction> findByBankAccountIdAndDateBetween(
            Long bankAccountId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Находит самую раннюю дату транзакции для указанного банковского счета
     *
     * @param bankAccountId ID банковского счета
     * @return опционально самая ранняя дата транзакции
     */
    Optional<LocalDateTime> findEarliestTransactionDate(Long bankAccountId);

    /**
     * Находит самую позднюю дату транзакции для указанного банковского счета
     *
     * @param bankAccountId ID банковского счета
     * @return опционально самая поздняя дата транзакции
     */
    Optional<LocalDateTime> findLatestTransactionDate(Long bankAccountId);

}
