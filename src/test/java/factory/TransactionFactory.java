package factory;

import ing.domain.Account;
import ing.domain.Transaction;
import ing.domain.enums.TransactionType;
import ing.model.TransactionDto;

import java.math.BigDecimal;

public class TransactionFactory {

  public static Transaction buildTransaction() {
    return Transaction.builder()
        .id(1L)
        .transactionType(TransactionType.DEPOSIT)
        .amount(new BigDecimal(10))
        .account(Account.builder().build())
        .build();
  }

    public static TransactionDto buildTransactionDto() {
        return TransactionDto.builder()
                .id(1L)
                .transactionType(TransactionType.DEPOSIT)
                .amount(new BigDecimal(10))
                .accountId(1L)
                .build();
    }

}
