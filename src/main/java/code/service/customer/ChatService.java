package code.service.customer;

import code.exception.*;
import code.model.entity.User;
import code.model.more.Conversation;
import code.model.more.Message;
import code.model.request.ChatRequest;
import code.repository.ConversationRepository;
import code.repository.MessageRepository;
import code.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("CustomerChatService")
public class ChatService {

  private MessageRepository messageRepository;
  private ConversationRepository conversationRepository;
  private UserRepository userRepository;

  public ChatService(MessageRepository messageRepository,
                     ConversationRepository conversationRepository,
                     UserRepository userRepository) {
    this.conversationRepository = conversationRepository;
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
  }
//  Lấy phân trang các tin nhắn đã nhắn
  public Page<Message> getMessages(User user, int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    //    Lấy cuộc trò chuyện ra theo customerId
    Conversation conversation = conversationRepository.findByCustomerId(user.getId())
        .orElseThrow(() -> new NotFoundException(
            "Không thấy Conversation tương ứng với CustomerId : " + user.getId()));
//    Lấy ra (size) tin nhắn mới nhất trong cuộc trò chuyện
    return messageRepository.findALlByConversation(pageable,conversation);
  }
//  Tạo 1 tin nhắn mới tới admin
  @Transactional
  public Message chatToAdmin(ChatRequest request,User customer) {
//    Lấy cuộc trò chuyện ra theo customerId
    Conversation conversation = conversationRepository.findByCustomerId(customer.getId())
        .orElseThrow(() -> new NotFoundException(
            "Không thấy Conversation tương ứng với CustomerId : " + customer.getId()));
//    Tạo đối tượng tin nhắn mới
    Message message = new Message();
    message.setSenderId(customer.getId());
    message.setSenderRole("customer");
    message.setContent(request.getContent());
    message.setConversation(conversation);
//    Cập nhật dữ liệu cho Conversation như tin nhắn cuối, thời gian cập nhật, ....
    LocalDateTime now = LocalDateTime.now();
    message.setCreatedAt(now);
    message.setUpdatedAt(now);
    conversation.setUpdatedAt(now);
    conversation.setLastSenderId(customer.getId());
    conversation.setLastMessageContent(message.getContent());
//    Lưu tin nhắn và cập nhật thời gian Conversation cùng lúc
    messageRepository.save(message);
    conversationRepository.save(conversation);

    return message;
  }

// đánh dấu là đã seen tất cả các tin nhắn của admin
public Message seenMessageFromAdmin(long customerId,long messageId){
  Message message = messageRepository.findById(messageId)
      .orElseThrow(()-> new NotFoundException("Không thấy Message có id : " + messageId));
//  Kiểm tra nếu tin nhắn này của customer này thì mới được gọi api
  if(message.getConversation().getCustomerId() != messageId){
    throw new ForbiddenException("Forbidden!");
  }
  else{
    message.setSeen(true);
    Conversation conversation = message.getConversation();
    messageRepository.save(message);
    conversationRepository.save(conversation);
    return message;
  }
}
}
