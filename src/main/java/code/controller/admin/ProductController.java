package code.controller.admin;

import code.model.entity.Product;
import code.model.request.CreateProductRequest;
import code.service.admin.CategoryService;
import code.service.admin.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("adminProductController")
@RequestMapping("/api/admin")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService){
    this.productService = productService;
  }

  @GetMapping("/products")
  public ResponseEntity<?> getProducts(){
    return ResponseEntity.ok(this.productService.getProducts(0,10));
  }

  @GetMapping("/products/{productId}")
  public ResponseEntity<Product> getProductById(@PathVariable long productId){
    return ResponseEntity.ok(this.productService.getProductById(productId));
  }

  @PostMapping("/products")
  public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
    return ResponseEntity.ok(this.productService.createProduct(createProductRequest));
  }
}
