package code.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatToAdminRequest {
  private String content;
}
