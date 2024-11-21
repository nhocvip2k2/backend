package code.model.more;

import code.model.entity.Category;
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
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  nếu id = 0 là admin - > customer
  @Column(name = "sender_id", nullable = false)
  private long senderId = 0;

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

  @ManyToOne
  @JoinColumn(name = "conversation_id",nullable = false, foreignKey = @ForeignKey(name = "FK_CONVERSATION_MESSAGE"))
  private Conversation conversation;
}
