package ing.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Optional;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(TransactionOperationException.class)
    public ResponseEntity<Problem> handleTransactionOperationException() {
        return ResponseEntity.of(
                Optional.of(
                        Problem.builder()
                                .withTitle("Transaction Operation Exception")
                                .withDetail("Bad transaction Operation")
                                .withStatus(Status.FORBIDDEN)
                                .build()
                ));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Problem> handleAccountNotFoundException() {
        return ResponseEntity.of(
                Optional.of(
                        Problem.builder()
                                .withTitle("Account Not Found Exception")
                                .withDetail("The target not found")
                                .withStatus(Status.BAD_REQUEST)
                                .build()
                ));
    }
}
