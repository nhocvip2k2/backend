package code.controller.admin;

import code.model.entity.Category;
import code.model.request.CreateCategoryRequest;
import code.model.request.UpdateCategoryRequest;
import code.service.admin.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AdminCategoryController")
@RequestMapping("/api/admin")
public class CategoryController {
  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService){
    this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public ResponseEntity<Page<Category>> getCategories(){
    return ResponseEntity.ok(this.categoryService.getCategories(0,10));
  }

  @PostMapping("/categories")
  public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
    return ResponseEntity.ok(this.categoryService.createCategory(createCategoryRequest));
  }

  @PutMapping("/categories")
  public ResponseEntity<Category> createCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest){
    return ResponseEntity.ok(this.categoryService.updateCategory(updateCategoryRequest));
  }
}
