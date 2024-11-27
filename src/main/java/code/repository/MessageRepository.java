package code.repository;

import code.model.more.Conversation;
import code.model.more.Message;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

  Page<Message> findALlByConversation(Pageable pageable,Conversation conversation);
}
