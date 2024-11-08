package code.service.admin;

import code.exception.ConflictException;
import code.exception.NotFoundException;
import code.model.entity.Product;
import code.model.request.CreateProductRequest;
import code.repository.CategoryRepository;
import code.repository.ProductRepository;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("AdminProductService")
public class ProductService {
  private ProductRepository productRepository;
  private CategoryRepository categoryRepository;
  public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  private String createSlug(String name) {
    String baseSlug = toSlug(name);
    String uniqueSlug = baseSlug;
    int count = 1;

    // Kiểm tra sự tồn tại của slug trong database
    while (productRepository.existsBySlug(uniqueSlug)) {
      uniqueSlug = baseSlug + "-" + count;
      count++;
    }
    return uniqueSlug;
  }

  // Chuyển đổi name thành slug cơ bản
  private String toSlug(String input) {
    String nowhitespace = Pattern.compile("[\\s]").matcher(input).replaceAll("-");
    String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
    return Pattern.compile("[^\\w-]").matcher(normalized).replaceAll("").toLowerCase(Locale.ENGLISH);
  }

  public Page<Product> getProducts(int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    return this.productRepository.findAll(pageable);
  }
  public Product getProductById(long product_id){
    return this.productRepository.findById(product_id)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy product có id : " + product_id));
  }
  public Product createProduct(CreateProductRequest createProductRequest){
    if(this.productRepository.findByName(createProductRequest.getName()).isPresent()){
      throw new ConflictException("Tên sản phẩm đã tồn tại!");
    }
    Product product = new Product();
    product.setName(createProductRequest.getName());
    product.setBrand(createProductRequest.getBrand());
    product.setDescription(createProductRequest.getDescription());
    product.setCategory(this.categoryRepository.findById(createProductRequest.getCategory_id())
        .orElseThrow(() -> new NotFoundException("Không tìm thấy category có id : " + createProductRequest.getCategory_id()))
    );
    String uniqueSlug = createSlug(createProductRequest.getName());
    product.setSlug(uniqueSlug);
    return productRepository.save(product);
  }
}
