package code.service.admin;

import code.exception.BadRequestException;
import code.exception.NotFoundException;
import code.model.entity.Order;
import code.model.entity.OrderDetail;
import code.model.entity.User;
import code.repository.OrderDetailRepository;
import code.repository.OrderRepository;
import code.repository.ProductDetailRepository;
import code.repository.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("AdminOrderService")
public class OrderService {

  private OrderDetailRepository orderDetailRepository;
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private ProductDetailRepository productDetailRepository;

  public OrderService(OrderDetailRepository orderDetailRepository,
      UserRepository userRepository,
      ProductDetailRepository productDetailRepository,
      OrderRepository orderRepository) {
    this.orderDetailRepository = orderDetailRepository;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.productDetailRepository = productDetailRepository;
  }

  //  Lấy tất cả đơn hàng
  public Page<OrderDetail> getOrderDetails(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderDetailRepository.findAll(pageable);
  }
//  Lấy đơn hàng theo id
  public Object getOrderDetailById(long orderDetailId){
    OrderDetail orderDetail =  orderDetailRepository.findById(orderDetailId)
        .orElseThrow(()-> new NotFoundException("Không thấy OrderDetail có id : "+ orderDetailId));
    Order order = orderDetail.getOrder();
    User user = order.getUser();
    Map<String, Object> response = new HashMap<>();
    response.put("orderDetail",orderDetail);
    response.put("user",user);
    return response;
  }

//  Thay đổi trạng thái đơn hàng
public Object updateStatusOrderDetailById(long orderDetailId,int status){
  OrderDetail orderDetail =  orderDetailRepository.findById(orderDetailId)
      .orElseThrow(()-> new NotFoundException("Không thấy OrderDetail có id : "+ orderDetailId));
  if(status < 0 || status > 6){
    throw new BadRequestException("Trạng thái không hợp lệ");
  }
//  Xác nhận đơn hàng
  if(status == 3){
    if(orderDetail.getStatus() == 2){
      orderDetail.setStatus(3);
    }
    else throw new BadRequestException("Không thể chuyển trạng thái");
  }
//  Xác nhận đã giao đến nơi
  if(status == 4){
    if(orderDetail.getStatus() == 3){
      orderDetail.setStatus(4);
    }
    else throw new BadRequestException("Không thể chuyển trạng thái");
  }
//  Xác nhận đã hoàn thành - nếu có lỗi, quá hạn thì đã xử lý lỗi, quá hạn
  if(status == 5){
    if(orderDetail.getStatus() == 4){
      orderDetail.setStatus(5);
    }
    else throw new BadRequestException("Không thể chuyển trạng thái");
  }
  Order order = orderDetail.getOrder();
  User user = order.getUser();
  Map<String, Object> response = new HashMap<>();
  response.put("orderDetail",orderDetail);
  response.put("user",user);
  return response;
}
}
