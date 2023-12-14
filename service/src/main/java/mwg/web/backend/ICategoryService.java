package mwg.web.backend;

import mwg.web.backend.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> findCategories(String categoryName);

}
