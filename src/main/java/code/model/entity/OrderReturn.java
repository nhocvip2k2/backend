package code.model.entity;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="order_returns")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderReturn {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "return_date")
  private Date returnDate ;
//  rỗng : chưua trả
//  trả rồi sẽ cập nhật ngày

  @Column(name = "extra_charge")
  private long extraCharge ;

  @Column(name = "status_fee")
  private boolean statusFee = false ;
//  false : chưa nộp tiền phụ phí
//  true : đã nộp bù phụ phí

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_detail_id", referencedColumnName = "id")
  private OrderDetail orderDetail;
}
