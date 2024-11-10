package code.seeder;

import code.model.entity.Category;
import code.model.entity.Product;
import code.model.entity.ProductDetail;
import code.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@AllArgsConstructor
public class ProductSeeder implements CommandLineRunner {
  private ProductRepository productRepository;
  private ProductDetailRepository productDetailRepository;
  private CategoryRepository categoryRepository;
  @Override
  public void run(String... args) throws Exception {
    // Kiểm tra nếu bảng User rỗng
    if (categoryRepository.count() * productRepository.count() * productDetailRepository.count() == 0) {
//      Taọ sẵn 1 danh mục mới là Điện thoại
      Category category = new Category();
      category.setName("Điện thoại");
      categoryRepository.save(category);

//      Tạo sẵn 1 sản phẩm là Iphone 15
      Product product = new Product();
      product.setCategory(category);
      product.setBrand("Apple");
      product.setName("Iphone 15");
      product.setSlug("iphone-15");
      product.setDescription("iPhone 15 Pro Max đã chính thức được ra mắt trong sự kiện Wonderlust"
          + " tại nhà hát Steve Jobs, California (Mỹ) vào lúc 10h sáng 12/9 tức 0h ngày 13/9 (giờ Việt Nam). "
          + "Chiếc iPhone mới trong series mới đem đến cho người dùng trải nghiệm ổn định hơn với thế hệ chip A17 Pro mới nhất, "
          + "cùng công nghệ Wi-Fi 6E. Bộ camera của iPhone 15 Pro Max cũng được nâng cấp thêm "
          + "với ống kính tele với khả năng zoom quang học 5x với thiết kế tetraprism hiện đại.");
      productRepository.save(product);

//      Tạo sẵn các thuộc tính sản phẩm đã có
// ProductDetail 1
      ProductDetail productDetail1 = new ProductDetail();
      productDetail1.setProduct(product);
      productDetail1.setType("256G");
      productDetail1.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail1.setInventory(20);
      productDetail1.setCondition("90% like new");
      productDetail1.setColor("Titan trắng");
      productDetailRepository.save(productDetail1);

// ProductDetail 2
      ProductDetail productDetail2 = new ProductDetail();
      productDetail2.setProduct(product);
      productDetail2.setType("1T");
      productDetail2.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail2.setInventory(12);
      productDetail2.setCondition("95% like new");
      productDetail2.setColor("Titan tự nhiên");
      productDetailRepository.save(productDetail2);

// ProductDetail 3
      ProductDetail productDetail3 = new ProductDetail();
      productDetail3.setProduct(product);
      productDetail3.setType("512G");
      productDetail3.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail3.setInventory(15);
      productDetail3.setCondition("98% like new");
      productDetail3.setColor("Đen");
      productDetailRepository.save(productDetail3);

// ProductDetail 4
      ProductDetail productDetail4 = new ProductDetail();
      productDetail4.setProduct(product);
      productDetail4.setType("128G");
      productDetail4.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail4.setInventory(30);
      productDetail4.setCondition("85% like new");
      productDetail4.setColor("Bạc");
      productDetailRepository.save(productDetail4);

// ProductDetail 5
      ProductDetail productDetail5 = new ProductDetail();
      productDetail5.setProduct(product);
      productDetail5.setType("1T");
      productDetail5.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail5.setInventory(8);
      productDetail5.setCondition("99% like new");
      productDetail5.setColor("Vàng");
      productDetailRepository.save(productDetail5);

// ProductDetail 6
      ProductDetail productDetail6 = new ProductDetail();
      productDetail6.setProduct(product);
      productDetail6.setType("256G");
      productDetail6.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail6.setInventory(25);
      productDetail6.setCondition("92% like new");
      productDetail6.setColor("Xanh lá");
      productDetailRepository.save(productDetail6);

// ProductDetail 7
      ProductDetail productDetail7 = new ProductDetail();
      productDetail7.setProduct(product);
      productDetail7.setType("512G");
      productDetail7.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail7.setInventory(18);
      productDetail7.setCondition("97% like new");
      productDetail7.setColor("Xanh dương");
      productDetailRepository.save(productDetail7);

// ProductDetail 8
      ProductDetail productDetail8 = new ProductDetail();
      productDetail8.setProduct(product);
      productDetail8.setType("64G");
      productDetail8.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail8.setInventory(10);
      productDetail8.setCondition("80% like new");
      productDetail8.setColor("Đỏ");
      productDetailRepository.save(productDetail8);

// ProductDetail 9
      ProductDetail productDetail9 = new ProductDetail();
      productDetail9.setProduct(product);
      productDetail9.setType("1T");
      productDetail9.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail9.setInventory(6);
      productDetail9.setCondition("99% like new");
      productDetail9.setColor("Xám");
      productDetailRepository.save(productDetail9);

// ProductDetail 10
      ProductDetail productDetail10 = new ProductDetail();
      productDetail10.setProduct(product);
      productDetail10.setType("128G");
      productDetail10.setStatus(true);
      productDetail1.setPricePerUse(500000);
      productDetail1.setPricePerDay(500000);
      productDetail10.setInventory(22);
      productDetail10.setCondition("88% like new");
      productDetail10.setColor("Hồng");
      productDetailRepository.save(productDetail10);


      System.out.println("Default productDetail created.");
    }
  }
}

