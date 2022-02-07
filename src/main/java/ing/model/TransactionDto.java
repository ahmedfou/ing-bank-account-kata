package ing.model;

import com.sun.istack.NotNull;
import ing.domain.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class TransactionDto {

  Long id;

  @NotNull BigDecimal amount;

  @NotNull TransactionType transactionType;

  Date createdAt;

  Long accountId;
}
