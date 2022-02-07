package factory;

import ing.domain.Account;
import ing.domain.Customer;
import ing.model.AccountDto;
import ing.model.CustomerDto;

import java.util.Set;

public final class CustomerFactory {

  public static Customer buildCustomer() {
    return Customer.builder()
        .id(1L)
        .firstName("first name")
        .lastName("last name")
        .accounts(Set.of(Account.builder().build()))
        .build();
  }

  public static CustomerDto buildCustomerDto() {
    return CustomerDto.builder()
        .id(1L)
        .firstName("first name")
        .lastName("last name")
        .accounts(Set.of(AccountDto.builder().build()))
        .build();
  }
}
