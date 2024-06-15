package ru.emelianov.smartbudgetproject.dto;

import ru.emelianov.smartbudgetproject.database.model.CategoryType;

/**
 * <p>DTO для сущности категории</p>
 *
 * @param id    ID категории
 * @param name  имя категории
 * @param color цвет категории
 * @param type  тип категории
 */
public record CategoryDTO(
        Long id,
        String name,
        String color,
        CategoryType type) { }
