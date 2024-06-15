package ru.emelianov.smartbudgetproject.service;

import ru.emelianov.smartbudgetproject.database.model.CategoryType;
import ru.emelianov.smartbudgetproject.dto.CategoryDTO;

import java.util.List;

/**
 * <p>Сервис для управления категориями</p>
 */
public interface CategoryService {

    /**
     * Создает новую категорию
     *
     * @param name  имя категории
     * @param color цвет категории
     * @param type  тип категории
     */
    void create(String name, String color, CategoryType type);

    /**
     * Удаляет категорию по ID
     *
     * @param categoryId ID категории
     */
    void delete(Long categoryId);

    /**
     * Находит категории по типу
     *
     * @param type тип категории
     * @return список категорий соответствующего типа
     */
    List<CategoryDTO> findByType(CategoryType type);

}
