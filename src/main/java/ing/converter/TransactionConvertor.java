package ing.converter;

import ing.domain.Account;
import ing.domain.Transaction;
import ing.model.TransactionDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

public final class TransactionConvertor {

  public static Transaction toTransaction(TransactionDto transactionDto) {
    if (transactionDto == null) {
      return null;
    }
    return Transaction.builder()
        .account(Account.builder().id(transactionDto.getAccountId()).build())
        .amount(transactionDto.getAmount())
        .transactionType(transactionDto.getTransactionType())
        .build();
  }

  public static TransactionDto toTransactionDto(Transaction transaction) {
    if (transaction == null) {
      return null;
    }
    return TransactionDto.builder()
        .id(transaction.getId())
        .amount(transaction.getAmount())
        .transactionType(transaction.getTransactionType())
        .createdAt(transaction.getCreatedAt())
        .accountId(transaction.getAccount().getId())
        .build();
  }

  public static Set<TransactionDto> toTransactionDtos(Collection<Transaction> results) {
    return ofNullable(results).orElseGet(Collections::emptyList).stream()
        .map(TransactionConvertor::toTransactionDto)
        .filter(Objects::nonNull)
        .collect(toSet());
  }

}
