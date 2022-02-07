package converter;

import factory.AccountFactory;
import ing.converter.AccountConvertor;
import ing.domain.Account;
import ing.model.AccountDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountConvertorTest {

  @Test
  void toAccountDto() {
    // Given
    Account account = AccountFactory.buildAccount();

    // When
    AccountDto accountDto = AccountConvertor.toAccountDto(account);

    // Then
    assertThat(accountDto).isNotNull();
    assertThat(accountDto.getId()).isEqualTo(account.getId());
    assertThat(accountDto.getCode()).isEqualTo(account.getCode());
    assertThat(accountDto.getBalance()).isEqualTo(account.getBalance());
    assertThat(accountDto.getTransactions()).isEqualTo(account.getTransactions());
    assertThat(accountDto.getCustomerId()).isEqualTo(account.getCustomer().getId());
  }
}
