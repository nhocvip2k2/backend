package code.repository;

import code.model.entity.Category;
import code.model.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
  Optional<Product> findByName(String name);
  boolean existsBySlug(String slug);
  List<Product> findByCategory(Category category);

}
