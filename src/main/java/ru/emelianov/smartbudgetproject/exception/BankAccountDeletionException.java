package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое при невозможности удаления банковского счета из-за связанных с ним транзакций</p>
 */
public class BankAccountDeletionException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public BankAccountDeletionException(String message) { super(message); }

}
