package code.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChangePasswordRequest {
  private long id;
  private String oldPassword;
  private String newPassword;
}
