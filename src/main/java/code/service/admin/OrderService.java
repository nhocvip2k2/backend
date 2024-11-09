package code.service.admin;

import code.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service("AdminOrderService")
public class OrderService {
  private OrderRepository orderRepository;

}
