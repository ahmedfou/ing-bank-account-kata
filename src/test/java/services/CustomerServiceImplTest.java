package services;

import factory.AccountFactory;
import ing.domain.Account;
import ing.domain.Customer;
import ing.model.CustomerDto;
import ing.repository.CustomerRepository;
import ing.service.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerServiceImplTest {

  @Mock private CustomerRepository customerRepository;

  private CustomerServiceImpl customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    customerService = new CustomerServiceImpl(customerRepository);
  }

  @Test
  void getAllCustomers() {
    // Given
    Account account1 = AccountFactory.buildAccount();
    Account account2 = AccountFactory.buildAccount();

    Customer customer1 = Customer.builder().build();
    customer1.setAccounts(Set.of(account1));

    Customer customer2 = Customer.builder().build();
    customer1.setAccounts(Set.of(account2));

    List<Customer> customers = List.of(customer1, customer2);

    // When
    Mockito.when(customerRepository.findAll()).thenReturn(customers);

    // Then
    List<CustomerDto> customerDtos = customerService.getAllCustomers();

    assertThat(customerDtos).isNotNull();
    assertThat(customerDtos.size()).isEqualTo(2);
  }
}
