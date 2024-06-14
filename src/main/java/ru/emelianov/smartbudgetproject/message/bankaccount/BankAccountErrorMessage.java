package ru.emelianov.smartbudgetproject.message.bankaccount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BankAccountErrorMessage {
    TARGET_ACCOUNT_NOT_FOUND("Целевой банковский счет не найден"),
    SOURCE_ACCOUNT_NOT_FOUND("Исходный банковский счет не найден"),
    INSUFFICIENT_FUNDS("Недостаточно средств для перевода"),
    ACCOUNT_DELETION_FAILED("Невозможно удалить банковский счет из-за связных с ним транзакций"),
    ACCOUNT_NOT_FOUND("Банковский счет не найден");

    private final String message;

}
