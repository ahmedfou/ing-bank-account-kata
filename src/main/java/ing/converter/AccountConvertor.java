package ing.converter;

import ing.domain.Account;
import ing.domain.Customer;
import ing.model.AccountDto;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

public final class AccountConvertor {

  public static AccountDto toAccountDto(Account account) {
    if (account == null) {
      return null;
    }
    return AccountDto.builder()
        .id(account.getId())
        .code(account.getCode())
        .accountType(account.getAccountType())
        .balance(account.getBalance())
        .transactions(TransactionConvertor.toTransactionDtos(account.getTransactions()))
        .customerId(account.getCustomer().getId())
        .build();
  }

  public static Set<AccountDto> toAccountDtos(Collection<Account> results) {
    return ofNullable(results).orElseGet(Collections::emptyList).stream()
        .map(AccountConvertor::toAccountDto)
        .filter(Objects::nonNull)
        .collect(toSet());
  }

}
