package code.controller.customer;

import code.service.customer.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("customerProductController")
@RequestMapping("/api/customer")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService){
    this.productService = productService;
  }

//  Lấy tất cả các product
  @GetMapping("/products")
  public ResponseEntity<?> getProducts(){
    return ResponseEntity.ok(this.productService.getProductDTOs(0,10));
  }
//  Lấy tất cả các product theo danh mục
@GetMapping("/categories/{categoryId}/products")
public ResponseEntity<?> getProductsByCategory(@PathVariable long categoryId){
  return ResponseEntity.ok(this.productService.getProductDTOsByCategoryId(categoryId,0,10));
}

//  Lấy các thuôc tính type của product có product_id (Từ đó giá sẽ đi theo màu sắc)
@GetMapping("/products/{productId}/types")
public ResponseEntity<?> getTypesByProductId(@PathVariable long productId){
  return ResponseEntity.ok(this.productService.getTypesByProductId(productId));
}

//  Lấy tất cả các ProductDetail dựa trên productId và type
@GetMapping("/products/{productId}")
public ResponseEntity<?> getProductDetaisByProductIdAndType(@PathVariable long productId,String type){
  return ResponseEntity.ok(this.productService.getProductDetaisByProductIdAndType(productId,type));
}
}
