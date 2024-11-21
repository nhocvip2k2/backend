package code.model.more;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name="transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "id_sepay", nullable = false)
  private Long idSepay; // Id giao dịch trên sepay

  @Column(name = "gateway", nullable = false)
  private String gateway; // Brand name của ngân hàng

  @Column(name = "transaction_date", nullable = false)
  private LocalDateTime transactionDate; // Thời gian xảy ra giao dịch phía ngân hàng

  @Column(name = "account_number", nullable = false)
  private String accountNumber; // Số tài khoản ngân hàng

  @Column(name = "code")
  private String code; // Mã code thanh toán

  @Column(name = "content", length = 500)
  private String content; // Nội dung chuyển khoản

  @Column(name = "transfer_type", nullable = false)
  private String transferType; // Loại giao dịch (in hoặc out)

  @Column(name = "transfer_amount", nullable = false)
  private Long transferAmount; // Số tiền giao dịch

  @Column(name = "accumulated", nullable = false)
  private Long accumulated; // Số dư tài khoản (lũy kế)

  @Column(name = "sub_account")
  private String subAccount; // Tài khoản ngân hàng phụ (tài khoản định danh)

  @Column(name = "reference_code", nullable = false)
  private String referenceCode; // Mã tham chiếu của tin nhắn SMS

  @Column(name = "description", columnDefinition = "TEXT")
  private String description; // Toàn bộ nội dung tin nhắn SMS

}
