package code.service.admin;

import code.exception.*;
import code.model.entity.*;
import code.repository.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service("AdminOrderService")
public class OrderService {

  private OrderDetailRepository orderDetailRepository;
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private ProductDetailRepository productDetailRepository;
  private OrderReturnRepository orderReturnRepository;

  public OrderService(OrderDetailRepository orderDetailRepository,
      UserRepository userRepository,
      ProductDetailRepository productDetailRepository,
      OrderRepository orderRepository,
      OrderReturnRepository orderReturnRepository) {
    this.orderDetailRepository = orderDetailRepository;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.productDetailRepository = productDetailRepository;
    this.orderReturnRepository = orderReturnRepository;
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
      orderDetailRepository.save(orderDetail);
    }
    else throw new BadRequestException("Không thể chuyển trạng thái");
  }
//  Xác nhận đã giao đến nơi + Tạo trạng thái trả hàng : OrderReturn
  if(status == 4){
    if(orderDetail.getStatus() == 3){
      orderDetail.setStatus(4);
      orderDetailRepository.save(orderDetail);
      OrderReturn orderReturn = new OrderReturn();
      orderReturn.setOrderDetail(orderDetail);
      orderReturnRepository.save(orderReturn);
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
