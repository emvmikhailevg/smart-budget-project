package ru.emelianov.smartbudgetproject.service;

import ru.emelianov.smartbudgetproject.dto.TransactionDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>Сервис для управления транзакциями, связанными с банковскими счетами</p>
 */
public interface TransactionService {

    /**
     * Добавляет транзакцию начисления на указанный банковский счет
     *
     * @param bankAccountId ID банковского счета
     * @param categoryId    ID категории
     * @param amount        сумма для начисления
     * @param date          дата транзакции
     */
    void accrual(Long bankAccountId, Long categoryId, BigDecimal amount, LocalDateTime date);

    /**
     * Добавляет транзакцию снятия с указанного банковского счета
     *
     * @param bankAccountId ID банковского счета
     * @param categoryId    ID категории
     * @param amount        сумма для снятия
     * @param date          дата транзакции
     */
    void withdraw(Long bankAccountId, Long categoryId, BigDecimal amount, LocalDateTime date);

    /**
     * Редактирует существующую транзакцию
     *
     * @param transactionId ID транзакции
     * @param newCategoryId новый ID категории
     * @param newAmount     новая сумма
     * @param newDate       новая дата транзакции
     */
    void editTransaction(Long transactionId, Long newCategoryId, BigDecimal newAmount, LocalDateTime newDate);

    /**
     * Удаляет транзакцию по ID
     *
     * @param transactionId ID транзакции
     */
    void deleteTransactionById(Long transactionId);

    /**
     * Возвращает мапу транзакций, сгруппированных по дате, для указанного банковского счета.
     * Ключом является дата, значением - список транзакций
     *
     * @param bankAccountId ID банковского счета
     * @return мапа транзакций, сгруппированных по дате
     */
    Map<LocalDate, List<TransactionDTO>> findAndGroupByDate(
            Long bankAccountId);

    /**
     * Возвращает мапу транзакций, сгруппированных по дате, для указанного банковского счета в заданном диапазоне дат.
     * Ключом является дата, значением - список транзакций
     *
     * @param bankAccountId ID банковского счета
     * @param startDate     начальная дата диапазона
     * @param endDate       конечная дата диапазона
     * @return мапа транзакций, сгруппированных по дате
     */
    Map<LocalDate, List<TransactionDTO>> findAndGroupByDate(
            Long bankAccountId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Возвращает список транзакций для указанного банковского счета
     *
     * @param bankAccountId ID банковского счета
     * @return список транзакций
     */
    List<TransactionDTO> findBetweenDates(Long bankAccountId);

    /**
     * Возвращает список транзакций для указанного банковского счета в заданном диапазоне дат
     *
     * @param bankAccountId ID банковского счета
     * @param startDate     начальная дата диапазона
     * @param endDate       конечная дата диапазона
     * @return список транзакций
     */
    List<TransactionDTO> findBetweenDates(Long bankAccountId, LocalDateTime startDate, LocalDateTime endDate);

}
