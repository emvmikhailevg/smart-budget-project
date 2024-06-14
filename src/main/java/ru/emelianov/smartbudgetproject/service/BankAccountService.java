package ru.emelianov.smartbudgetproject.service;

import ru.emelianov.smartbudgetproject.database.model.User;
import ru.emelianov.smartbudgetproject.dto.BankAccountDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Сервис для управления банковскими счетами</p>
 */
public interface BankAccountService {

    /**
     * Добавляет новый банковский счет
     *
     * @param user   пользователь, владелец счета
     * @param name   имя счета
     * @param amount начальная сумма на счете
     * @param color  цвет счета в формате Hex
     */
    void add(User user, String name, BigDecimal amount, String color);

    /**
     * Находит все банковские счета по идентификатору пользователя
     *
     * @param userId идентификатор пользователя
     * @return список DTO банковских счетов
     */
    List<BankAccountDTO> findAllByUserId(Long userId);

    /**
     * Удаляет банковский счет по его идентификатору
     *
     * @param accountId идентификатор банковского счета
     */
    void delete(Long accountId);

    /**
     * Проверяет, существует ли банковский счет с данным идентификатором у указанного пользователя
     *
     * @param accountId идентификатор банковского счета
     * @param userId    идентификатор пользователя
     * @return true, если счет существует, иначе false
     */
    boolean hasBankAccountWithId(Long accountId, Long userId);

    /**
     * Редактирует банковский счет
     *
     * @param accountId идентификатор банковского счета
     * @param name      новое имя счета
     * @param color     новый цвет счета в формате Hex
     */
    void editBankAccount(Long accountId, String name, String color);

    /**
     * Переводит сумму с одного банковского счета на другой
     *
     * @param fromAccountId идентификатор исходного банковского счета
     * @param toAccountId   идентификатор целевого банковского счета
     * @param amount        сумма перевода
     */
    void transferAmountBetweenAccounts(Long fromAccountId, Long toAccountId, BigDecimal amount);

}