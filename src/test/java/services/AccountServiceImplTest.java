package services;

import factory.AccountFactory;
import ing.domain.Account;
import ing.model.AccountDto;
import ing.repository.AccountRepository;
import ing.service.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

class AccountServiceImplTest {

  @Mock private AccountRepository accountRepository;

  private AccountServiceImpl accountService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    accountService = new AccountServiceImpl(accountRepository);
  }

  @Test
  void getAllAccounts() {
    // Given
    List<Account> accounts = List.of(AccountFactory.buildAccount(), AccountFactory.buildAccount());

    // When
    Mockito.when(accountRepository.findAll()).thenReturn(accounts);

    // Then
    List<AccountDto> accountDtos = accountService.getAllAccounts();

    assertThat(accountDtos).isNotNull();
    assertThat(accountDtos.size()).isEqualTo(2);
  }

  @Test
  void getAllAccountsByCustomerId() {
    // Given
    Long customerId = 1L;
    List<Account> accounts = List.of(AccountFactory.buildAccount());

    // When
    Mockito.when(accountRepository.findAllByCustomerId(any())).thenReturn(accounts);

    // Then
    List<AccountDto> accountDtos = accountService.getAllAccountsByCustomerId(customerId);

    assertThat(accountDtos).isNotNull();
    assertThat(accountDtos.size()).isEqualTo(1);
  }

  @Test
  void getAccountById() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();

    // When
    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));

    // Then
    AccountDto accountDto = accountService.getAccountById(accountId);

    assertThat(accountDto).isNotNull();
    assertThat(accountDto.getId()).isEqualTo(account.getId());
    assertThat(accountDto.getCode()).isEqualTo(account.getCode());
    assertThat(accountDto.getBalance()).isEqualTo(account.getBalance());
    assertThat(accountDto.getAccountType()).isEqualTo(account.getAccountType());
    assertThat(accountDto.getCustomerId()).isEqualTo(account.getCustomer().getId());
    assertThat(accountDto.getTransactions().size()).isEqualTo(account.getTransactions().size());
  }

  @Test
  void getAccountBalance() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();

    // When
    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));

    // Then
    BigDecimal balance = accountService.getAccountBalance(accountId);

    assertThat(balance).isNotNull();
    assertThat(balance).isEqualTo(account.getBalance());
  }
}
