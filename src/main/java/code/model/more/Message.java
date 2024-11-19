package code.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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

  @Column(name = "message",nullable = false)
  private String message;

  @Column(name = "created_at",nullable = false)
  @CreationTimestamp
  private LocalDateTime createdAt;
}
