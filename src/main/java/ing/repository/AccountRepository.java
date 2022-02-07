package ing.repository;

import ing.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

  List<Account> findAllByCustomerId(Long customerId);
}
