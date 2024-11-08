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
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_details")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantity",nullable = false)
  private long quantity;

  @Column(name = "current_price",nullable = false)
  private long currentPrice;

  @Column(name = "current_phone",nullable = false)
  private long currentPhone;

  @Column(name = "current_address",nullable = false)
  private long currentAddress;

  @Column(name = "current_condition",nullable = false)
  private String currentCondition;

  @Column(name = "status",nullable = false)
  private int status;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "order_id",nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_ORDER-DETAIL"))
  private Order order;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "product_detail_id",nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT-DETAIL_ORDER-DETAIL"))
  private ProductDetail productDetail;
}
