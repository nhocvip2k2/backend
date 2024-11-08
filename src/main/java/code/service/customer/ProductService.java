package code.service.customer;

import code.exception.NotFoundException;
import code.model.dto.ProductDTO;
import code.model.dto.ProductDetailDTO;
import code.model.entity.Category;
import code.model.entity.Product;
import code.model.entity.ProductDetail;
import code.repository.CategoryRepository;
import code.repository.ProductDetailRepository;
import code.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("CustomerProductService")
public class ProductService {

  private ProductRepository productRepository;
  private ProductDetailRepository productDetailRepository;
  private CategoryRepository categoryRepository;

  public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
      ProductDetailRepository productDetailRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
    this.productDetailRepository = productDetailRepository;
  }

  public Page<ProductDTO> getProductDTOs(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    List<Product> products = productRepository.findAll();
    List<ProductDTO> productDTOs = new ArrayList<>();
    for (Product product : products) {
      ProductDTO productDTO = new ProductDTO();
      BeanUtils.copyProperties(product, productDTO);
      productDTO.setCategory(product.getCategory());
//      Tính số lượt đã thuê

//      Tính số sao đánh giá

      productDTOs.add(productDTO);
    }
//    Chuyển List ProductDTO sang phân trang
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), productDTOs.size());
    List<ProductDTO> paginatedDTOs = productDTOs.subList(start, end);

    // Trả về Page<ProductDTO> bằng cách sử dụng PageImpl
    return new PageImpl<>(paginatedDTOs, pageable, productDTOs.size());
  }

  //  Lấy product theo category_id
  public Page<ProductDTO> getProductDTOsByCategoryId(long categoryId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Category category = categoryRepository.findById(categoryId)
        .orElseThrow(() -> new NotFoundException("Không thấy category có id : " + categoryId));
    List<Product> products = productRepository.findByCategory(category);
    List<ProductDTO> productDTOs = new ArrayList<>();
    for (Product product : products) {
      ProductDTO productDTO = new ProductDTO();
      BeanUtils.copyProperties(product, productDTO);
      productDTO.setCategory(product.getCategory());
//      Tính số lượt đã thuê

//      Tính số sao đánh giá

      productDTOs.add(productDTO);
    }
//    Chuyển List ProductDTO sang phân trang
    int start = (int) pageable.getOffset();
    int end = Math.min((start + pageable.getPageSize()), productDTOs.size());
    List<ProductDTO> paginatedDTOs = productDTOs.subList(start, end);

    // Trả về Page<ProductDTO> bằng cách sử dụng PageImpl
    return new PageImpl<>(paginatedDTOs, pageable, productDTOs.size());
  }

  //  Lấy các thuôc tính type của product có product_id (Từ đó giá sẽ đi theo màu sắc)
  public List<String> getTypesByProductId(long productId) {
    return productDetailRepository.findDistinctTypeByProductId(productId);
  }

//  Lấy ProductDetail dựa theo productId và type
public List<ProductDetailDTO> getProductDetaisByProductIdAndType(long productId, String type) {
  List<ProductDetail> productDetails = productDetailRepository.findByProductIdAndType(productId, type);
  List<ProductDetailDTO> productDetailDTOs = new ArrayList<>();
  for(ProductDetail productDetail : productDetails){
    ProductDetailDTO productDetailDTO = new ProductDetailDTO();
    BeanUtils.copyProperties(productDetail, productDetailDTO);
    productDetailDTOs.add(productDetailDTO);
  }
  return productDetailDTOs;
}

}
