package code.controller.customer;

import code.model.more.Transaction;
import code.model.request.WebHookRequest;
import code.service.transaction.TransactionService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
  private TransactionService transactionService;
  public PaymentController(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  @PostMapping("/webhook")
  public ResponseEntity<?> addTransaction(@RequestBody WebHookRequest request) {
    // Gọi service để xử lý request
    Object result = transactionService.addTransactionFromWebHook(request);

    // Tạo JSON trả về
    Map<String, Object> response = new HashMap<>();
    response.put("success", true);
    response.put("data", result); // Chèn dữ liệu kết quả từ service

    // Trả về HTTP Status 201 với JSON
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

}
