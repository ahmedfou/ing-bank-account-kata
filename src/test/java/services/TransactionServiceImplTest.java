package services;

import factory.AccountFactory;
import factory.TransactionFactory;
import ing.domain.Account;
import ing.domain.Transaction;
import ing.domain.enums.TransactionType;
import ing.exception.TransactionOperationException;
import ing.model.AccountDto;
import ing.model.TransactionDto;
import ing.repository.AccountRepository;
import ing.repository.TransactionRepository;
import ing.service.services.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

class TransactionServiceImplTest {

  @Mock private AccountRepository accountRepository;

  @Mock private TransactionRepository transactionRepository;

  private TransactionServiceImpl transactionService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    transactionService = new TransactionServiceImpl(accountRepository, transactionRepository);
  }

  @Test
  void getTransactionsByAccountId() {
    // Given
    Long accountId = 1L;
    List<Transaction> transactions =
        List.of(TransactionFactory.buildTransaction(), TransactionFactory.buildTransaction());

    Mockito.when(transactionRepository.findAllByAccountId(accountId)).thenReturn(transactions);

    // When
    List<TransactionDto> transactionDtos = transactionService.getTransactionsByAccountId(accountId);

    // Then
    assertThat(transactionDtos).isNotNull();
    assertThat(transactionDtos.size()).isEqualTo(2);
  }

  @Test
  void doTransactionDepositAmount() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();
    account.setId(accountId);
    Transaction transaction = TransactionFactory.buildTransaction();
    TransactionDto transactionDto = TransactionFactory.buildTransactionDto();
    transactionDto.setAccountId(accountId);

    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
    Mockito.when(accountRepository.save(any())).thenReturn(account);
    Mockito.when(transactionRepository.save(any())).thenReturn(transaction);

    // When
    AccountDto accountDtoResult = transactionService.doTransaction(transactionDto);

    // Then
    assertThat(accountDtoResult).isNotNull();
    assertThat(accountDtoResult.getBalance()).isEqualTo(account.getBalance());
  }

  @Test
  void doTransactionDepositAmount_throwException() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();
    account.setId(accountId);
    Transaction transaction = TransactionFactory.buildTransaction();
    TransactionDto transactionDto = TransactionFactory.buildTransactionDto();
    transactionDto.setAccountId(accountId);
    transactionDto.setTransactionType(TransactionType.DEPOSIT);
    transactionDto.setAmount(new BigDecimal(0));

    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
    Mockito.when(accountRepository.save(any())).thenReturn(account);
    Mockito.when(transactionRepository.save(any())).thenReturn(transaction);

    // When & Then
    assertThrows(
        TransactionOperationException.class,
        () -> {
          transactionService.doTransaction(transactionDto);
        });
  }

  @Test
  void doTransactionWithdrawAmount() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();
    account.setId(accountId);
    Transaction transaction = TransactionFactory.buildTransaction();
    transaction.setTransactionType(TransactionType.WITHDRAW);
    TransactionDto transactionDto = TransactionFactory.buildTransactionDto();
    transactionDto.setAccountId(accountId);
    transactionDto.setTransactionType(TransactionType.WITHDRAW);

    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
    Mockito.when(accountRepository.save(any())).thenReturn(account);
    Mockito.when(transactionRepository.save(any())).thenReturn(transaction);

    // When
    AccountDto accountDtoResult = transactionService.doTransaction(transactionDto);

    // Then
    assertThat(accountDtoResult).isNotNull();
    assertThat(accountDtoResult.getBalance()).isEqualTo(account.getBalance());
  }

  @Test
  void doTransactionWithdrawAmount_throwException() {
    // Given
    Long accountId = 1L;
    Account account = AccountFactory.buildAccount();
    account.setId(accountId);
    Transaction transaction = TransactionFactory.buildTransaction();
    transaction.setTransactionType(TransactionType.WITHDRAW);
    transaction.setAmount(new BigDecimal(1000));
    TransactionDto transactionDto = TransactionFactory.buildTransactionDto();
    transactionDto.setAccountId(accountId);
    transactionDto.setTransactionType(TransactionType.WITHDRAW);
    transactionDto.setAmount(new BigDecimal(1000));

    Mockito.when(accountRepository.findById(any())).thenReturn(Optional.of(account));
    Mockito.when(accountRepository.save(any())).thenReturn(account);
    Mockito.when(transactionRepository.save(any())).thenReturn(transaction);

    // When & Then
    assertThrows(
        TransactionOperationException.class,
        () -> {
          transactionService.doTransaction(transactionDto);
        });
  }
}
