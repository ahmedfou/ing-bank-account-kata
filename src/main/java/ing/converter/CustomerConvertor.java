package ing.converter;

import ing.domain.Customer;
import ing.model.CustomerDto;

public final class CustomerConvertor {

  public static CustomerDto ToCustomerDto(Customer customer) {
    if (customer == null) {
      return null;
    }
    return CustomerDto.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .accounts(AccountConvertor.toAccountDtos(customer.getAccounts()))
        .build();
  }
}
