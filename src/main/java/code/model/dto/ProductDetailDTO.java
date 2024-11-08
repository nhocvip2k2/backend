package code.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDetailDTO {
  private Long id;
  private String type;
  private String color;
  private long price;
  private String condition;
  private long inventory;
  private boolean status;
}
