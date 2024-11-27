package code.controller.customer;

import code.model.request.ChatRequest;
import code.security.CheckUserAccess;
import code.service.customer.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("CustomerChatController")
@RequestMapping("/api/customer/{user_id}")
public class ChatController {
  private ChatService chatService;
  public ChatController(ChatService chatService){
    this.chatService = chatService;
  }

  @GetMapping("/chat")
  @CheckUserAccess
  public ResponseEntity<?> getMessages(@PathVariable long user_id,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size){
    return ResponseEntity.ok(chatService.getMessages(user_id,page,size ));
  }

  @PostMapping("/chat")
  @CheckUserAccess
  public ResponseEntity<?> chatToAdmin(@PathVariable long user_id,@RequestBody
  ChatRequest request){
    return ResponseEntity.ok(chatService.chatToAdmin(request,user_id));
  }

//  @PutMapping("/chat/{chatId}")
//  @CheckUserAccess
//  public ResponseEntity<?> seenMessageFormAdmin(){
//
//  }
}
