package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое при переводе суммы на тот же счет</p>
 */
public class SameAccountTransferException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public SameAccountTransferException(String message) {
        super(message);
    }

}
