package code.service.admin;

import code.exception.NotFoundException;
import code.model.more.Conversation;
import code.model.more.Message;
import code.repository.ConversationRepository;
import code.repository.MessageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("AdminChatService")
public class ChatService {

  private ConversationRepository conversationRepository;
  private MessageRepository messageRepository;

  public ChatService(ConversationRepository conversationRepository,
      MessageRepository messageRepository) {
    this.conversationRepository = conversationRepository;
    this.messageRepository = messageRepository;
  }

//  Lấy tất cả cuộc trò chuyện sắp xếp theo thứ tự cập nhật : mới đến cũ
  public Page<Conversation> getConversations(int page, int size){
//    Lấy phân trang và sắp xếp từ mới đến cũ
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
    return conversationRepository.findAll(pageable);
  }

//  Lấy cuộc trò chuyện theo id
  public Conversation getConversationById(long id){
    return conversationRepository.findById(id)
        .orElseThrow(()-> new NotFoundException("Không thấy conversation có id : " + id));
  }

//  Lấy nội dung tin nhắn : load cố định 20 tin nhắn trên Page
//  public Page<Message> getMessageByConversation(int page){
//    Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "updatedAt"));
//    return messageRepository.
//  }

}
