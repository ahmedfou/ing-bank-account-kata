package ing.model;

import com.sun.istack.NotNull;
import ing.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class AccountDto {

  Long id;

  @NotNull String code;

  @NotNull BigDecimal balance;

  @NotNull AccountType accountType;

  Long customerId;

  Set<TransactionDto> transactions = new HashSet<>();
}
