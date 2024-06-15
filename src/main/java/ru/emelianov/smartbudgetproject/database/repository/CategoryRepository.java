package ru.emelianov.smartbudgetproject.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.emelianov.smartbudgetproject.database.model.Category;
import ru.emelianov.smartbudgetproject.database.model.CategoryType;

import java.util.List;

/**
 * <p>Репозиторий для управления сущностями {@link Category} в базе данных</p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Находит список категорий по указанному типу
     *
     * @param type тип категории
     * @return список категорий соответствующего типа
     */
    List<Category> findByType(CategoryType type);

}
