package converter;

import factory.AccountFactory;
import factory.CustomerFactory;
import ing.converter.CustomerConvertor;
import ing.domain.Customer;
import ing.model.CustomerDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerConvertorTest {

  @Test
  void toCustomerDto() {
    // Given
    Customer customer = CustomerFactory.buildCustomer();
    customer.setAccounts(Set.of(AccountFactory.buildAccount()));

    // When
    CustomerDto customerDto = CustomerConvertor.ToCustomerDto(customer);

    // Then
    assertThat(customerDto).isNotNull();
    assertThat(customerDto.getId()).isEqualTo(customer.getId());
    assertThat(customerDto.getFirstName()).isEqualTo(customer.getFirstName());
    assertThat(customerDto.getLastName()).isEqualTo(customer.getLastName());
    assertThat(customerDto.getAccounts().size()).isEqualTo(customer.getAccounts().size());
  }
}
