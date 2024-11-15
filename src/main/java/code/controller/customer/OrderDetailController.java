package code.controller.customer;

import code.model.request.CreateOrderDetailRequest;
import code.security.CheckUserAccess;
import code.service.customer.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("customerOrderDetailController")
@RequestMapping("/api/customer/{userId}")
public class OrderDetailController {

  private OrderDetailService orderDetailService;

  public OrderDetailController(OrderDetailService orderDetailService) {
    this.orderDetailService = orderDetailService;
  }

  //  Lấy tất cả các đơn hàng
  @CheckUserAccess
  @GetMapping("/orders")
  public ResponseEntity<?> getOrderDetails(@PathVariable long userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(orderDetailService.getOrderDetailsByUserId(userId, page, size));
  }

  //  Lấy tất cả các đơn hàng theo trạng thái : status
  @CheckUserAccess
  @GetMapping("/orders/")
  public ResponseEntity<?> getOrderDetailsByStatus(
      @PathVariable long userId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam int status) {
    return ResponseEntity.ok(
        orderDetailService.getAllByUserIdAndProductDetailStatus(userId, status, page, size));
  }


  // Tạo mới đơn hàng dựa trên : List<productDetailId>, userId,
  @CheckUserAccess
  @PostMapping("/orders")
  public ResponseEntity<?> createOrderDetail(@PathVariable long userId,
      @RequestBody CreateOrderDetailRequest request) {
    return ResponseEntity.ok(orderDetailService.createOrderDetail(userId, request));
  }

  @CheckUserAccess
  @PutMapping("/orders/{orderDetailId}")
  public ResponseEntity<?> cancelOrderDetail(@PathVariable long userId,
      @PathVariable Long orderDetailId) {
    return ResponseEntity.ok(orderDetailService.cancelOrderDetail(userId,orderDetailId));
  }

  @CheckUserAccess
  @GetMapping("/orders/{orderDetailId}")
  public ResponseEntity<?> getOrderDetailById(@PathVariable long userId,
      @PathVariable Long orderDetailId) {
    return ResponseEntity.ok(orderDetailService.cancelOrderDetail(userId,orderDetailId));
  }
}
