package ing.service.interfaces;

import ing.model.AccountDto;
import ing.model.TransactionDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

  List<AccountDto> getAllAccounts();

  List<AccountDto> getAllAccountsByCustomerId(Long customerId);

  AccountDto getAccountById(Long accountId);

  BigDecimal getAccountBalance(Long accountId);

}
