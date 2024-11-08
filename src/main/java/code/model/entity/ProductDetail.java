package code.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="product_details",uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "color"})})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "color",nullable = false)
  private String color;

  @Column(name = "type",nullable = false)
  private String type;

  @Column(name = "price",nullable = false)
  private long price;

  @Column(name = "product_condition",nullable = false)
  private String condition;

  @Column(name = "inventory",nullable = false)
  private long inventory;

  @Column(name = "status",nullable = false)
  private boolean status;

  @Column(name = "created_at",nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at",nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "product_id",nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_PRODUCT-DETAIL"))
  private Product product;

  @JsonIgnore
  @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderDetail> orderDetails;

  @JsonIgnore
  @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Review> reviews;
}
