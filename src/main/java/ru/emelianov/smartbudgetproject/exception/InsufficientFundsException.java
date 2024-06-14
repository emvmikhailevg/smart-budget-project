package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое при недостатке средств на банковском счете для выполнения операции</p>
 */
public class InsufficientFundsException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

}
