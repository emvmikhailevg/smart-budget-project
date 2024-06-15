package ru.emelianov.smartbudgetproject.message.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Перечисление сообщений об ошибках транзакций</p>
 */
@Getter
@AllArgsConstructor
public enum TransactionErrorMessage {

    /**
     * Ошибка, указывающая на то, что транзакция не найдена
     */
    TRANSACTION_NOT_FOUND("Транзакция не найдена");

    /**
     * Строка сообщения об ошибке, связанная с константой перечисления
     */
    private final String message;

}
