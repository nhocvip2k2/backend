package code.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="returns")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Return {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
}
