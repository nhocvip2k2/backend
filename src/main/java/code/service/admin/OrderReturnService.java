package code.service.admin;

import code.repository.OrderReturnRepository;
import org.springframework.stereotype.Service;

@Service("AdminOrderReturnService")
public class OrderReturnService {
  private OrderReturnRepository orderReturnRepository;
  public OrderReturnService(OrderReturnRepository orderReturnRepository){
    this.orderReturnRepository = orderReturnRepository;
  }
//  Thực hiện thao tác trả hàng

}
