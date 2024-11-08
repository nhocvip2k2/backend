package code.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name",nullable = false,length = 200)
  private String name;

  @Column(name = "brand",nullable = false)
  private String brand;

  @Lob
  @Column(name = "description",nullable = false,columnDefinition = "TEXT")
  private String description;

  @Column(name = "slug", nullable = false, unique = true)
  private String slug;

  @Column(name = "created_at",nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at",nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "category_id",nullable = false, foreignKey = @ForeignKey(name = "FK_CATEGORY_PRODUCT"))
  private Category category;

  @JsonIgnore
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductDetail> productDetails;
}
