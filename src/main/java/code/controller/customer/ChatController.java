package code.controller.customer;

import code.model.request.ChatRequest;
import code.security.CustomUserDetails;
import code.service.customer.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CustomerChatController")
@RequestMapping("/api/customer")
public class ChatController {
  private ChatService chatService;
  public ChatController(ChatService chatService){
    this.chatService = chatService;
  }

  @GetMapping("/chat")
  public ResponseEntity<?> getMessages(@AuthenticationPrincipal CustomUserDetails userDetail,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size){
    return ResponseEntity.ok(chatService.getMessages(userDetail.getUser(),page,size ));
  }

  @PostMapping("/chat")
  public ResponseEntity<?> chatToAdmin(@AuthenticationPrincipal CustomUserDetails userDetail,@RequestBody
  ChatRequest request){
    return ResponseEntity.ok(chatService.chatToAdmin(request,userDetail.getUser()));
  }

//  @PutMapping("/chat/{chatId}")
//  @CheckUserAccess
//  public ResponseEntity<?> seenMessageFormAdmin(){
//
//  }
}
