package code.controller.customer;

import code.model.more.Transaction;
import code.model.request.WebHookRequest;
import code.service.transaction.TransactionService;
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
  public ResponseEntity<?> addTransaction(@RequestBody WebHookRequest request){
    return ResponseEntity.ok(transactionService.addTransactionFromWebHook(request));
  }
}
