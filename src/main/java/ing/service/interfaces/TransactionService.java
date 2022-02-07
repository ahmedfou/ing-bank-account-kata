package ing.service.interfaces;

import ing.model.AccountDto;
import ing.model.TransactionDto;

import java.util.List;

public interface TransactionService {

  List<TransactionDto> getTransactionsByAccountId(Long accountId);

  AccountDto doTransaction(TransactionDto transactionDto);
}
