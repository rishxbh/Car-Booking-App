package com.zemoso.springboot.demo.dao;

import com.zemoso.springboot.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.email = ?1")
    Customer findByEmail(String email);
}