package code.service.customer;

import code.exception.BadRequestException;
import code.exception.NotFoundException;
import code.model.entity.Order;
import code.model.entity.OrderDetail;
import code.model.entity.ProductDetail;
import code.model.entity.User;
import code.model.request.CreateOrderDetailRequest;
import code.model.request.ProductItem;
import code.repository.OrderDetailRepository;
import code.repository.OrderRepository;
import code.repository.ProductDetailRepository;
import code.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("CustomerOrderService")
public class OrderDetailService {

  private OrderDetailRepository orderDetailRepository;
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private ProductDetailRepository productDetailRepository;

  public OrderDetailService(OrderDetailRepository orderDetailRepository,
      UserRepository userRepository,
      ProductDetailRepository productDetailRepository,
      OrderRepository orderRepository) {
    this.orderDetailRepository = orderDetailRepository;
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.productDetailRepository = productDetailRepository;
  }

  //  Lấy tất cả các đơn hàng chi tiết (OrderDetail)
  public Page<OrderDetail> getOrderDetailsByUser(User user, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderDetailRepository.findAllByUserId(user.getId(), pageable);
  }

  //  Lấy các đơn hàng theo trạng thái
  public Page<OrderDetail> getAllByUserAndProductDetailStatus(User user, int status, int page,
      int size) {
    Pageable pageable = PageRequest.of(page, size);
    return orderDetailRepository.findAllByUserIdAndProductDetailStatus(user.getId(), status, pageable);
  }

  // Xem đơn hàng cụ thể có id là orderDetailId
  public OrderDetail getByUserIdAndId(long userId, long orderDetailId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id : " + userId));
    OrderDetail orderDetail = orderDetailRepository.findByOrderDetailIdAndUserId(orderDetailId,
            userId)
        .orElseThrow(() -> new NotFoundException(
            "Không tìm thấy đơn hàng tương ứng "));
    return orderDetail;
  }

  //  Tạo mới đơn hàng : Tạo đối tượng order -> xửa lí List<productDetailId> và trạng thái 1 là chưa thanh toán
  public List<OrderDetail> createOrderDetail(User user, CreateOrderDetailRequest request) {
  //    1 - Tạo Order mới
    Order order = new Order();
    order.setUser(user);
    order.setPayment(request.getPayment());
    order.setShipment(request.getShipment());
    orderRepository.save(order);
  //      Xử lý danh sách các ProductDetail x Quantity
    List<OrderDetail> orderDetails = new ArrayList<>();
    for (ProductItem productItem : request.getProductItems()) {
      long productDetailId = productItem.getProductDetailId();
      ProductDetail productDetail = productDetailRepository.findById(productDetailId)
          .orElseThrow(
              () -> new NotFoundException("Không thấy ProductDetail có id : " + productDetailId));
      OrderDetail orderDetail = new OrderDetail();

      orderDetail.setRentalDay(productItem.getRentalDay());
      orderDetail.setCurrentPrice(productDetail.getPrice());

  //      Check số lượng đặt hàng với số trong kho
      if (productDetail.getInventory() < productItem.getQuantity()) {
        throw new BadRequestException("Số lượng quá giới hạn");
      }
      orderDetail.setQuantity(productItem.getQuantity());
      orderDetail.setCurrentPhone(request.getCurrentPhone());
      orderDetail.setCurrentAddress(request.getCurrentAddress());
      orderDetail.setCurrentCondition(productDetail.getCondition());
      orderDetail.setNote(productItem.getNote());
      orderDetail.setStatus(1);
      orderDetail.setOrder(order);
      orderDetail.setProductDetail(productDetail);
      orderDetails.add(orderDetail);
      orderDetailRepository.save(orderDetail);
    }
    return orderDetails;
  }

//  Thanh toán đon hàng
//  public OrderDetail purchaseOrderDetail(){
//
//  }
//  Hủy đơn hàng

  public OrderDetail cancelOrderDetail(User user, long orderDetailId) {
    OrderDetail orderDetail = orderDetailRepository.findByOrderDetailIdAndUserId(orderDetailId,
            user.getId())
        .orElseThrow(() -> new NotFoundException(
            "Không tìm thấy đơn hàng tương ứng "));
    if (orderDetail.getStatus() == 1 ) {
      orderDetail.setStatus(0);
    } else {
      throw new BadRequestException("Không thể hủy đơn hàng ở trạng thái này.");
    }
    return orderDetail;
  }

}
