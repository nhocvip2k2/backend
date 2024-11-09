package code.model.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateOrderDetailRequest {
  private  OrderRequest orderRequest;
  private String currentAddress;
  private String currentPhone;
  private String note;
  private long currentPrice;
  private long quantity;
  private List<Long> productDetailIds;

}
