package ing.service.services;

import ing.converter.AccountConvertor;
import ing.converter.TransactionConvertor;
import ing.domain.Account;
import ing.domain.enums.TransactionType;
import ing.exception.AccountNotFoundException;
import ing.exception.TransactionOperationException;
import ing.model.AccountDto;
import ing.model.TransactionDto;
import ing.repository.AccountRepository;
import ing.repository.TransactionRepository;
import ing.service.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  private final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(0.01);

  @Override
  public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
    return transactionRepository.findAllByAccountId(accountId).stream()
        .map(TransactionConvertor::toTransactionDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public AccountDto doTransaction(TransactionDto transactionDto) {
    Account account =
        accountRepository
            .findById(transactionDto.getAccountId())
            .orElseThrow(() -> new AccountNotFoundException());

    return transactionDto.getTransactionType().equals(TransactionType.DEPOSIT)
        ? depositAmount(account, transactionDto)
        : withdrawAmount(account, transactionDto);
  }

  private AccountDto withdrawAmount(Account account, TransactionDto transactionDto) {
    BigDecimal newBalance = account.getBalance().subtract(transactionDto.getAmount());
    if (BigDecimal.ZERO.compareTo(newBalance) > 0) {
      throw new TransactionOperationException();
    }
    account.setBalance(newBalance);
    transactionRepository.save(TransactionConvertor.toTransaction(transactionDto));
    return AccountConvertor.toAccountDto(accountRepository.save(account));
  }

  private AccountDto depositAmount(Account account, TransactionDto transactionDto) {
    if (MIN_AMOUNT.compareTo(transactionDto.getAmount()) >= 0) {
      throw new TransactionOperationException();
    }
    BigDecimal newBalance = account.getBalance().add(transactionDto.getAmount());
    account.setBalance(newBalance);
    transactionRepository.save(TransactionConvertor.toTransaction(transactionDto));
    return AccountConvertor.toAccountDto(accountRepository.save(account));
  }
}
