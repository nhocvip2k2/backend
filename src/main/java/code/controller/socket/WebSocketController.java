package code.controller.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  // Phương thức để gửi thông báo qua WebSocket
  public void sendChatNotification(long customerId, Object message) {
    String destination = "/topic/customer/" + customerId;
    messagingTemplate.convertAndSend(destination, message);
  }
}
