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
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

  @Column(name = "rental_type",nullable = false)
  private int rentalType;
//  1 : thuê theo lượt : thời gian < 48h(1 lượt = 2 ngày)
//  2 : thuê theo ngày > 2 ngày

  @Column(name = "current_price",nullable = false)
  private long currentPrice;

  @Column(name = "rental_day",nullable = false)
  private int rentalDay;
//  Nếu thuê theo lượt thì bỏ trống
//  Nếu thuê theo ngày thì giá trị > 2

  @Column(name = "current_phone",nullable = false)
  private String currentPhone;

  @Column(name = "current_address",nullable = false)
  private String currentAddress;

  @Column(name = "current_condition",nullable = false)
  private String currentCondition;

  @Column(name = "note",nullable = false)
  private String note;
  
  @Column(name = "status",nullable = false)
  private int status;
//  1 : Đã tạo đơn hàng xong chờ admin xác nhận - có thể hủy được
//  2 : Admin xác nhận - Không thể hủy đơn
//  3 : Đang giao hàng
//  4 : Đã nhận được hàng
//  5 :

  @Column(name = "created_at",nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at",nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "order_id",nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_ORDER-DETAIL"))
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_detail_id",nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT-DETAIL_ORDER-DETAIL"))
  private ProductDetail productDetail;
}
