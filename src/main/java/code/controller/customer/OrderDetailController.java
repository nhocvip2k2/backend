package code.controller.customer;

import code.model.request.CreateOrderDetailRequest;
import code.security.CustomUserDetails;
import code.service.customer.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController("customerOrderDetailController")
@RequestMapping("/api/customer")
public class OrderDetailController {

  private OrderDetailService orderDetailService;

  public OrderDetailController(OrderDetailService orderDetailService) {
    this.orderDetailService = orderDetailService;
  }

  //  Lấy tất cả các đơn hàng
  @GetMapping("/orders")
  public ResponseEntity<?> getOrderDetails(@AuthenticationPrincipal CustomUserDetails userDetail,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(orderDetailService.getOrderDetailsByUser(userDetail.getUser(), page, size));
  }

  //  Lấy tất cả các đơn hàng theo trạng thái : status
  @GetMapping("/orders/")
  public ResponseEntity<?> getOrderDetailsByStatus(
      @AuthenticationPrincipal CustomUserDetails userDetail,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam int status) {
    return ResponseEntity.ok(
        orderDetailService.getAllByUserAndProductDetailStatus(userDetail.getUser(), status, page, size));
  }


  // Tạo mới đơn hàng dựa trên : List<productDetailId>, userId,
  @PostMapping("/orders")
  public ResponseEntity<?> createOrderDetail(@AuthenticationPrincipal CustomUserDetails userDetail,
      @RequestBody CreateOrderDetailRequest request) {
    return ResponseEntity.ok(orderDetailService.createOrderDetail(userDetail.getUser(), request));
  }

//  Huy don hang
  @PutMapping("/orders/{orderDetailId}")
  public ResponseEntity<?> cancelOrderDetail(@AuthenticationPrincipal CustomUserDetails userDetail,
      @PathVariable Long orderDetailId) {
    return ResponseEntity.ok(orderDetailService.cancelOrderDetail(userDetail.getUser(),orderDetailId));
  }

//  Lay don hang theo orderId
  @GetMapping("/orders/{orderDetailId}")
  public ResponseEntity<?> getOrderDetailById(@AuthenticationPrincipal CustomUserDetails userDetail,
      @PathVariable Long orderDetailId) {
    return ResponseEntity.ok(orderDetailService.cancelOrderDetail(userDetail.getUser(),orderDetailId));
  }
}
