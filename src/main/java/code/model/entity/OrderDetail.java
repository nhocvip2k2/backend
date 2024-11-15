package code.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;
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
//  1 : Chưa thanh toán - Có thể hủy
//  2 : Đã tạo đơn hàng xong chờ admin xác nhận - có thể hủy được
//  3 : Admin xác nhận + Giao hàng luôn - Không thể hủy đơn
//  4 : Đã giao đến nơi  - Không thể hủy đơn
//  5 : Đã hoàn thành - Không thể hủy đơn
//  0 : Đã hủy


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
