package ru.emelianov.smartbudgetproject.message.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Перечисление сообщений об ошибках, связанных с категориями</p>
 */
@Getter
@AllArgsConstructor
public enum CategoryErrorMessage {

    /**
     * Ошибка, указывающая на невозможность удаления категории из-за связанных с ней транзакций
     */
    CATEGORY_DELETION_FAILED("Невозможно удалить категорию из-за связных с ней транзакций");

    /**
     * Строка сообщения об ошибке, связанная с константой перечисления
     */
    private final String message;

}
