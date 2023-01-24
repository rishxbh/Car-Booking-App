package com.zemoso.springboot.demo.service;

import com.zemoso.springboot.demo.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CarService {
    public List<Car> findAll();

    public Car findById(int id);

    public void save(Car car);

    public void deleteById(int id);

}
