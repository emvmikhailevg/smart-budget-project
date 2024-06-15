package ru.emelianov.smartbudgetproject.dto;

import ru.emelianov.smartbudgetproject.database.model.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>DTO для сущности транзакции</p>
 *
 * @param id            ID транзакции
 * @param bankAccountId ID банковского счета
 * @param category      категория транзакции
 * @param amount        сумма транзакции
 * @param date          дата транзакции
 */
public record TransactionDTO(
        Long id,
        Long bankAccountId,
        Category category,
        BigDecimal amount,
        LocalDateTime date) { }
