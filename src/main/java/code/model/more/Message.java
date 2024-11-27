package code.model.more;

import code.model.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="messages")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Message {
  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sender_id", nullable = false)
//  Nếu là customer thì senderId là customerId
//  Nếu là admin thì senderId là 0
  private long senderId ;

  @Column(name = "sender_role", nullable = false)
  private String senderRole;

  @Column(name = "content",nullable = false)
  private String content;

  @Column(name = "seen",nullable = false)
  private boolean seen = false;

  @Column(name = "created_at",nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at",nullable = false)
  private LocalDateTime updatedAt;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "conversation_id",nullable = false, foreignKey = @ForeignKey(name = "FK_CONVERSATION_MESSAGE"))
  private Conversation conversation;
}
