package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое, когда транзакция не найдена</p>
 */
public class TransactionNotFoundException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public TransactionNotFoundException(String message) {
        super(message);
    }

}
