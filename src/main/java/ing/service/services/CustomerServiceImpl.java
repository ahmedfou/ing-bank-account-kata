package ing.service.services;

import ing.converter.CustomerConvertor;
import ing.model.CustomerDto;
import ing.repository.CustomerRepository;
import ing.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public List<CustomerDto> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(CustomerConvertor::ToCustomerDto)
        .collect(Collectors.toList());
  }
}
