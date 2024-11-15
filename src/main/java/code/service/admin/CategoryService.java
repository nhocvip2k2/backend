package code.service.admin;

import code.model.entity.Category;
import code.exception.*;
import code.model.request.CreateCategoryRequest;
import code.model.request.UpdateCategoryRequest;
import code.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service("AdminCategoryService")
public class CategoryService {
  private CategoryRepository categoryRepository;
  public CategoryService(CategoryRepository categoryRepository){
    this.categoryRepository = categoryRepository;
  }

  public Page<Category> getCategories(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return this.categoryRepository.findAll(pageable);
  }

    public Category createCategory(CreateCategoryRequest createCategoryRequest){
//      check categoryName
      if (createCategoryRequest.getName().trim().length() == 0) {
        throw new BadRequestException("Tên danh mục không được để trống");
      }
      if (categoryRepository.findByName(createCategoryRequest.getName()).isPresent()) {
        throw new ConflictException("Tên danh mục đã tồn tại");
      }
      else{
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        categoryRepository.save(category);
        return category;
      }
    }

  public Category updateCategory(UpdateCategoryRequest updateCategoryRequest){
//      check categoryName
    Category category = this.categoryRepository.findById(updateCategoryRequest.getId())
        .orElseThrow(() -> new NotFoundException("Không tồn tại danh mục có id : " + updateCategoryRequest.getId()));

    if (updateCategoryRequest.getNewName().trim().length() == 0) {
      throw new BadRequestException("Tên danh mục không được để trống");
    }
    if (!updateCategoryRequest.getNewName().equals(category.getName()) && categoryRepository.findByName(updateCategoryRequest.getNewName()).isPresent()) {
      throw new ConflictException("Tên danh mục đã tồn tại");
    }
    else{
      category.setName(updateCategoryRequest.getNewName());
      categoryRepository.save(category);
      return category;
    }
  }


}
