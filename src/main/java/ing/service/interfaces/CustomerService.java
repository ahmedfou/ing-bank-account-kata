package ing.service.interfaces;

import ing.model.CustomerDto;

import java.util.List;

public interface CustomerService {
  List<CustomerDto> getAllCustomers();
}
