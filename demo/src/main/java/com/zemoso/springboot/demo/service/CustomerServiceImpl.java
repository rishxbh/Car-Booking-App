package com.zemoso.springboot.demo.service;


import com.zemoso.springboot.demo.dao.CustomerRepository;
import com.zemoso.springboot.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        Customer customer = null;
        if(result.isPresent()) {
            customer = result.get();
        }
        else{
            throw new RuntimeException("Did not find Customer Id- " + id);
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    public boolean findByEmailId(Customer c){
        return customerRepository.findByEmail(c.getEmail()) != null;
    }

    public Customer findByEmailId(String str){
        return customerRepository.findByEmail(str);
    }
}
