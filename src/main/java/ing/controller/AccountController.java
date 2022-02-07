package ing.controller;

import ing.model.AccountDto;
import ing.service.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping()
  public ResponseEntity<List<AccountDto>> getAllAccounts() {
    return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<AccountDto>> getAllAccountsByCustomerId(
      @PathVariable Long customerId) {
    return new ResponseEntity<>(accountService.getAllAccountsByCustomerId(customerId), HttpStatus.OK);
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountId) {

    return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
  }

  @GetMapping("{accountId}/balance")
  public ResponseEntity<BigDecimal> getTransactionsByAccount(@PathVariable Long accountId) {
    return new ResponseEntity<>(accountService.getAccountBalance(accountId), HttpStatus.OK);
  }
}
