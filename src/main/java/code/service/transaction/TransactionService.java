package code.service.transaction;

import code.model.more.Transaction;
import code.model.request.WebHookRequest;
import code.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  private TransactionRepository transactionRepository;
  public TransactionService(TransactionRepository transactionRepository){
    this.transactionRepository = transactionRepository;
  }

//  lấy giá trị webhook lưu vào db
  public Transaction addTransactionFromWebHook(WebHookRequest request){
    Transaction transaction = new Transaction();
    BeanUtils.copyProperties(request,transaction);
    transaction.setIdSepay(request.getId());
    transaction.setId(null);
    transactionRepository.save(transaction);
//    Tạo đơn hàng
//    createOrderDetail
    return transaction;
  }
//  mở QR
//  ng dùng quét -> lưu đc giao dịch vào db -> socket gửi về fe để xác nhận thành công ->đóng QR
//  -> chuyển đến orders

//  Mã nội dung ck : yêu cầu chứa "SEVQR" : SEVQR + Mã KH + Mã Order
//diện thoại + máy chiếu
//  Tạo 1 Order -> orderDetail
}
