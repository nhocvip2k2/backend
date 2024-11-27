package code.controller.admin;

import code.model.request.ChatRequest;
import code.service.admin.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("AdminChatController")
@RequestMapping("/api/admin/chat")
public class ChatController {

  private ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

//  Lấy tất cả hôi thoại : dành cho phần tin nhắn bên ngoài
/*
* ----------------------------
| Ưu tiên   | Khác        |
-----------------------------------------
| Cloud của tôi            |            |
| Bạn: chỉ riêng mình ta   | 20/11      |
----------------------------            |
| Btl HCSDL ĐPT            |            |
| Bạn: @All làm r.          | 13 phút   |
----------------------------            |
| D20CNTT                  |            |
| Thanh Vũ: Ai mở link xét | 10 giờ     |
----------------------------            |
| Zalopay - Đặt vé phim    |            |
| 🎉 CGV CULTURE DAY...    | 2 ngày     |
----------------------------            |
| CodeGym - Chuỗi sự kiện  |            |
| Hồng Ân: Chào buổi sáng  | 3 ngày     |
----------------------------            |
| Hưng                     |            |
| Bạn: ok                  | 3 ngày     |
----------------------------            |
| Zalopay                  |            |
| Nguyễn Thái Hòa ơi...    | 19/11      |
----------------------------            |
| vinh hoàn hảo huy        |            |
| Con Tôm Trên Đồng: Cho.. | 22/11      |
---------------------------------------
  * */
  @GetMapping("/")
  public ResponseEntity<?> getConversations(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(chatService.getConversations(page, size));
  }

// Lấy phân trang 20 tin đầu của tất cả thông tin Message cuộc trò chuyện với customer có id
//  là customerId

  @GetMapping("/{conversationId}")
  public ResponseEntity<?> getMessagesByConversationId(
      @PathVariable long conversationId,
      @RequestParam(defaultValue = "0") int page) {
    return ResponseEntity.ok(chatService.getMessagesByConversation(conversationId,page));
  }

//  Gửi tin nhắn tới customer có id là customerId
  @PostMapping("/")
  public ResponseEntity<?> chatToCustomer(
      @RequestParam long customerId,
      @RequestBody  ChatRequest request
  ){
    return ResponseEntity.ok(chatService.chatToCustomer(customerId,request));
  }
}
