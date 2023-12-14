package mwg.web.backend.controller;

import mwg.web.backend.APIResponse;
import mwg.web.backend.dto.CategoryDTO;
import mwg.web.backend.impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<APIResponse<List<CategoryDTO>>> getAllCategories() {
        APIResponse<List<CategoryDTO>> response = APIResponse.<List<CategoryDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all categories successfully!")
                .metadata(categoryService.getAllCategories())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<APIResponse<CategoryDTO>> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.addCategory(categoryDTO);

        APIResponse<CategoryDTO> response = APIResponse.<CategoryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Category #%d is created successfully!", category.getCategoryId()))
                .metadata(category)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<APIResponse<String>> deleteCategory(@PathVariable(name = "id") Long categoryId) {
        categoryService.deleteCategory(categoryId);

        APIResponse<String> response = APIResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Category #%d is deleted successfully!", categoryId))
                .metadata("DELETED")
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/categories")
    public ResponseEntity<APIResponse<CategoryDTO>> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.updateCategory(categoryDTO);

        APIResponse<CategoryDTO> response = APIResponse.<CategoryDTO>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Category #%d is updated successfully!", category.getCategoryId()))
                .metadata(category)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories/{name}")
    public ResponseEntity<APIResponse<List<CategoryDTO>>> findCategories(@PathVariable(name = "name") String categoryName) {
        APIResponse<List<CategoryDTO>> response = APIResponse.<List<CategoryDTO>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(String.format("Get all categories with name %s successfully!", categoryName))
                .metadata(categoryService.findCategories(categoryName))
                .build();

        return ResponseEntity.ok(response);
    }
}
