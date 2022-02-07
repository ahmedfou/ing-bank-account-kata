package converter;

import factory.AccountFactory;
import factory.CustomerFactory;
import factory.TransactionFactory;
import ing.converter.CustomerConvertor;
import ing.converter.TransactionConvertor;
import ing.domain.Customer;
import ing.domain.Transaction;
import ing.model.CustomerDto;
import ing.model.TransactionDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionConvertorTest {

  @Test
  void toTransaction() {
      // Given
      Transaction transaction = TransactionFactory.buildTransaction();

      // When
      TransactionDto transactionDto = TransactionConvertor.toTransactionDto(transaction);

      //Then
      assertThat(transactionDto).isNotNull();
      assertThat(transactionDto.getId()).isEqualTo(transaction.getId());
      assertThat(transactionDto.getAmount()).isEqualTo(transaction.getAmount());
      assertThat(transactionDto.getTransactionType()).isEqualTo(transaction.getTransactionType());
      assertThat(transactionDto.getAccountId()).isEqualTo(transaction.getAccount().getId());
  }

  @Test
  void toTransactionDto() {
      // Given
      TransactionDto transactionDto = TransactionFactory.buildTransactionDto();

      // When
      Transaction transaction = TransactionConvertor.toTransaction(transactionDto);

      //Then
      assertThat(transaction).isNotNull();
      assertThat(transaction.getAmount()).isEqualTo(transactionDto.getAmount());
      assertThat(transaction.getTransactionType()).isEqualTo(transactionDto.getTransactionType());
      assertThat(transactionDto.getAccountId()).isEqualTo(transaction.getAccount().getId());
  }
}
