package ing.domain;

import com.sun.istack.NotNull;
import ing.domain.enums.TransactionType;
import ing.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction extends AuditModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(name = "amount")
  @NotNull
  BigDecimal amount;

  @Column(name = "transaction_type")
  @Enumerated(EnumType.STRING)
  @NotNull
  TransactionType transactionType;

  @ManyToOne
  @JoinColumn(name = "account_id")
  Account account;
}
