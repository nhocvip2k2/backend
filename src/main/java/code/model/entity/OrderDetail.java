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

  @Column(name = "current_price",nullable = false)
  private long currentPrice;

  @Column(name = "rental_day",nullable = false)
  private int rentalDay;

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
//  2 : Đã thanh toán đơn hàng xong chờ giao - có thể hủy được
//  3 : Đang giao
//  4 : Đã giao đến nơi  - Không thể hủy đơn -> Tạo 1 OrderReturn
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

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_return_id", referencedColumnName = "id")
  private OrderReturn orderReturn;
}
