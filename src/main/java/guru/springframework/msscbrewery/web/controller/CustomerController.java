package guru.springframework.msscbrewery.web.controller;


import guru.springframework.msscbrewery.exception.MyException;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId")UUID customerId){
        return  new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/save}")
    public ResponseEntity saveNewCustomer(@RequestBody CustomerDto customerDto)throws MyException{
        try{
            customerService.saveNewCustomer(customerDto);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new MyException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerDto customerDto)throws MyException{
        try{
            customerService.updateCustomer(customerDto);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new MyException(e);
        }
    }
    @PutMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId")UUID customerId)throws MyException{
        try{
            customerService.deleteCustomer(customerId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new MyException(e);
        }
    }
}
