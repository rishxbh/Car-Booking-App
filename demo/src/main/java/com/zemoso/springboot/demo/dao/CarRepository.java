package com.zemoso.springboot.demo.dao;

import com.zemoso.springboot.demo.entity.Car;
import com.zemoso.springboot.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<Car, Integer> {
}
