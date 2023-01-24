package com.zemoso.springboot.demo.service;

import com.zemoso.springboot.demo.dao.CarRepository;
import com.zemoso.springboot.demo.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }
    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(int id) {
        Optional<Car> result = carRepository.findById(id);
        Car car = null;
        if(result.isPresent()){
            car = result.get();
        }else{
            throw new RuntimeException("Car with " + id + " is not present");
        }
        return car;
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }

}
