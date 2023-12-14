package mwg.web.backend.impl;

import mwg.web.backend.entity.Category;
import mwg.web.backend.CategoryRepository;
import mwg.web.backend.ICategoryService;
import mwg.web.backend.dto.CategoryDTO;
import mwg.web.backend.dto.Mapper;
import mwg.web.backend.exception.NotFoundException.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final Mapper mapper;

    public CategoryService(CategoryRepository categoryRepository, Mapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toCategoryDTO)
                .toList();
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .amount(categoryDTO.getAmount())
                .voluntary(categoryDTO.isVoluntary())
                .startDate(categoryDTO.getStartDate())
                .endDate(categoryDTO.getEndDate())
                .build();

        return mapper.toCategoryDTO(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getCategoryId()).orElseThrow(() -> new CategoryNotFoundException(categoryDTO.getCategoryId()));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setAmount(categoryDTO.getAmount());
        category.setVoluntary(category.isVoluntary());
        category.setStartDate(category.getStartDate());
        category.setEndDate(category.getEndDate());

        return mapper.toCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDTO> findCategories(String categoryName) {
        List<Category> categories = categoryRepository.findAllByCategoryNameContainsIgnoreCase(categoryName);
        if (categories.isEmpty()) return new ArrayList<>();
        return categories.stream().map(mapper::toCategoryDTO).toList();
    }
}
