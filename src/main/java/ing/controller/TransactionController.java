package ing.controller;

import ing.model.AccountDto;
import ing.model.TransactionDto;
import ing.service.interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping
  public ResponseEntity<AccountDto> doTransactionOperation(
      @RequestBody TransactionDto transactionDto) {
    return new ResponseEntity<>(transactionService.doTransaction(transactionDto), HttpStatus.OK);
  }

  @GetMapping("/account/{accountId}")
  public ResponseEntity<List<TransactionDto>> getTransactionsByAccount(
      @PathVariable Long accountId) {
    return new ResponseEntity<>(
        transactionService.getTransactionsByAccountId(accountId), HttpStatus.OK);
  }
}
