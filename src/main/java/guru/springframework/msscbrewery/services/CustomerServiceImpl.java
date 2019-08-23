package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("Liviu").build();
    }

    @Override
    public void saveNewCustomer(CustomerDto customerDto) {
        log.info("cusmer saved");
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        log.info("cusmer updated");

    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("cusmer deleted");

    }
}
