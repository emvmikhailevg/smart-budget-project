package ru.emelianov.smartbudgetproject.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.emelianov.smartbudgetproject.database.model.Category;
import ru.emelianov.smartbudgetproject.dto.CategoryDTO;

/**
 * <p>Маппер для преобразования объектов {@link Category} в объекты {@link CategoryDTO}</p>
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    /**
     * Преобразует объект {@link Category} в объект {@link CategoryDTO}
     *
     * @param category объект категории
     * @return объект DTO категории
     */
    CategoryDTO convertToDTO(Category category);

}
