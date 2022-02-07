package ing.domain;

import com.sun.istack.NotNull;
import ing.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "customer")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull String firstName;

  @NotNull String lastName;

  @OneToMany(
      mappedBy = "customer",
      cascade = { CascadeType.PERSIST, CascadeType.MERGE},
      orphanRemoval = true,
      fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  Set<Account> accounts = new HashSet<>();
}
