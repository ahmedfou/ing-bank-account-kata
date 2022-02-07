package factory;

import ing.domain.Account;
import ing.domain.Customer;
import ing.domain.enums.AccountType;
import ing.model.AccountDto;

import java.math.BigDecimal;
import java.util.Collections;

public final class AccountFactory {

  public static Account buildAccount() {
    return Account.builder()
        .id(1L)
        .code("code")
        .accountType(AccountType.SALARY_ACCOUNT)
        .transactions(Collections.emptySet())
        .balance(new BigDecimal(100))
        .customer(Customer.builder().build())
        .build();
  }

  public static AccountDto buildAccountDto() {
    return AccountDto.builder()
        .id(1L)
        .code("code")
        .accountType(AccountType.SALARY_ACCOUNT)
        .transactions(Collections.emptySet())
        .customerId(1L)
        .build();
  }
}
