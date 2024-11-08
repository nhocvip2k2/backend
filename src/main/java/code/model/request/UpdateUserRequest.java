package code.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateUserRequest {
  private String name;
  private String phone;
  private String role;
  private String password;
  private int status;
}
