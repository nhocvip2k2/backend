package code.controller.admin;

import code.model.entity.OrderReturn;
import code.service.admin.OrderReturnService;
import code.service.admin.OrderService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.util.RecyclerPool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AdminOrderReturnController")
@RequestMapping("/api/admin")
public class ReturnController {

  private OrderReturnService orderReturnService;

  public ReturnController(OrderReturnService orderReturnService) {
    this.orderReturnService = orderReturnService;
  }

  //Lấy tất cả các đơn trả
  @GetMapping("/order_returns")
  public ResponseEntity<?> getOrderReturnS(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(orderReturnService.getOrderReturns(page, size));
  }

  //  Lấy đơn trả theo id
  @GetMapping("/order_returns/{idOrderReturn}")
  public ResponseEntity<?> getOrderReturnById(@PathVariable long idOrderReturn) {
    return ResponseEntity.ok(orderReturnService.getOrderReturnById(idOrderReturn));
  }

  //  Lấy tất cả các đơn trả theo trạng thái statusFee
  @GetMapping("/order_returns/")
  public ResponseEntity<?> getOrderReturnsByStatus(
      @RequestParam boolean status,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(orderReturnService.getOrderReturnsByStatus(page, size, status));
  }
}
