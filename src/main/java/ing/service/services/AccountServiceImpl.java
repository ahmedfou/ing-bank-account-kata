package ing.service.services;

import ing.converter.AccountConvertor;
import ing.exception.AccountNotFoundException;
import ing.model.AccountDto;
import ing.repository.AccountRepository;
import ing.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public List<AccountDto> getAllAccounts() {
    return accountRepository.findAll().stream()
        .map(AccountConvertor::toAccountDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<AccountDto> getAllAccountsByCustomerId(Long customerId) {
    return accountRepository.findAllByCustomerId(customerId).stream()
        .map(AccountConvertor::toAccountDto)
        .collect(Collectors.toList());
  }

  @Override
  public AccountDto getAccountById(Long accountId) {
    return accountRepository
        .findById(accountId)
        .map(account -> AccountConvertor.toAccountDto(account))
        .orElse(null);
  }

  @Override
  public BigDecimal getAccountBalance(Long accountId) {
    return accountRepository
        .findById(accountId)
        .map(account -> account.getBalance())
        .orElseThrow(() -> new AccountNotFoundException());
  }
}
