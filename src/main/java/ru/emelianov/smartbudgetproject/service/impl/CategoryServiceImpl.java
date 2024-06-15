package ru.emelianov.smartbudgetproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.emelianov.smartbudgetproject.database.model.Category;
import ru.emelianov.smartbudgetproject.database.model.CategoryType;
import ru.emelianov.smartbudgetproject.database.repository.CategoryRepository;
import ru.emelianov.smartbudgetproject.dto.CategoryDTO;
import ru.emelianov.smartbudgetproject.exception.CategoryDeletionException;
import ru.emelianov.smartbudgetproject.mapper.CategoryMapper;
import ru.emelianov.smartbudgetproject.service.CategoryService;

import java.util.List;

import static ru.emelianov.smartbudgetproject.message.category.CategoryErrorMessage.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public void create(String name, String color, CategoryType type) {
        Category category = new Category();

        category.setName(name);
        category.setColor(color);
        category.setType(type);

        categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (DataIntegrityViolationException ex) {
            throw new CategoryDeletionException(CATEGORY_DELETION_FAILED.getMessage());
        }
    }

    @Override
    public List<CategoryDTO> findByType(CategoryType type) {
        return categoryRepository.findByType(type).stream()
                .map(categoryMapper::convertToDTO)
                .toList();
    }

}
