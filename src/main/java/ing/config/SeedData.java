package ing.config;

import ing.domain.Account;
import ing.domain.Customer;
import ing.domain.Transaction;
import ing.domain.enums.AccountType;
import ing.domain.enums.TransactionType;
import ing.repository.AccountRepository;
import ing.repository.CustomerRepository;
import ing.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@AllArgsConstructor
public class SeedData implements CommandLineRunner {

  private final CustomerRepository customerRepository;
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  @Override
  public void run(String... args) throws Exception {
    loadData();
  }

  private void loadData() {
    Customer savedCu_1 =
        customerRepository.save(Customer.builder().firstName("Tom").lastName("Riyo").build());

    Customer savedCu_2 =
            customerRepository.save(Customer.builder().firstName("Dros").lastName("Binlli").build());

    Account savedAccount_Cu_1 =
        accountRepository.save(
            Account.builder()
                .accountType(AccountType.SALARY_ACCOUNT)
                .code("123")
                .balance(new BigDecimal(120))
                .customer(savedCu_1)
                .build());

    Account savedAccount_Cu_1_1 =
            accountRepository.save(
                    Account.builder()
                            .accountType(AccountType.SALARY_ACCOUNT)
                            .code("114")
                            .balance(new BigDecimal(120))
                            .customer(savedCu_1)
                            .build());

    Account savedAccount_Cu_2 =
            accountRepository.save(
                    Account.builder()
                            .accountType(AccountType.SALARY_ACCOUNT)
                            .code("665")
                            .balance(new BigDecimal(120))
                            .customer(savedCu_2)
                            .build());


    transactionRepository.save(
        Transaction.builder()
            .account(savedAccount_Cu_1)
            .transactionType(TransactionType.WITHDRAW)
            .amount(new BigDecimal("100"))
            .build());

    transactionRepository.save(
            Transaction.builder()
                    .account(savedAccount_Cu_1)
                    .transactionType(TransactionType.WITHDRAW)
                    .amount(new BigDecimal("10"))
                    .build());

    transactionRepository.save(
            Transaction.builder()
                    .account(savedAccount_Cu_1_1)
                    .transactionType(TransactionType.DEPOSIT)
                    .amount(new BigDecimal("2"))
                    .build());

    transactionRepository.save(
            Transaction.builder()
                    .account(savedAccount_Cu_2)
                    .transactionType(TransactionType.DEPOSIT)
                    .amount(new BigDecimal("10"))
                    .build());
  }
}
