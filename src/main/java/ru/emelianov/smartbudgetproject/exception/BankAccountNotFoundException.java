package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое, когда банковский счет не найден</p>
 */
public class BankAccountNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public BankAccountNotFoundException(String message) {
        super(message);
    }

}
