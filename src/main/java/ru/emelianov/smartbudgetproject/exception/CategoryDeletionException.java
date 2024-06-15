package ru.emelianov.smartbudgetproject.exception;

/**
 * <p>Исключение, выбрасываемое при невозможности удаления категории</p>
 */
public class CategoryDeletionException extends RuntimeException {

    /**
     * Создает новое исключение с указанным сообщением
     *
     * @param message сообщение об ошибке
     */
    public CategoryDeletionException(String message) {
        super(message);
    }

}
