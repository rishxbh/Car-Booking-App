package com.zemoso.springboot.demo.controller;


import com.zemoso.springboot.demo.entity.Car;
import com.zemoso.springboot.demo.entity.Colour;
import com.zemoso.springboot.demo.entity.Customer;
import com.zemoso.springboot.demo.entity.Reader;
import com.zemoso.springboot.demo.exceptionHandlers.ProductNotFoundException;
import com.zemoso.springboot.demo.service.CarService;
import com.zemoso.springboot.demo.service.ColourService;
import com.zemoso.springboot.demo.service.CustomUserDetails;
import com.zemoso.springboot.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@Controller
@ComponentScan("com.zemoso.springboot.demo.service")
@RequestMapping("/carApi")
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private ColourService colourService;

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Autowired
    public CarController(CarService carService){
        this.carService = carService;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:cars";
    }


    @GetMapping("/addCar")
    public String addFunc(Model model){
        List<Colour> listColours = colourService.findAll();
        Car b = new Car();
        model.addAttribute("cars", b);
        model.addAttribute("list", listColours);
        return "add-a-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute Car b){
        carService.save(b);
        return "redirect:cars";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value="id") int id, Model model){
        Car b = carService.findById(id);
        List<Colour> listColours = colourService.findAll();
        model.addAttribute("cars", b);
        model.addAttribute("list", listColours);
        return "update-a-car";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable(value="id") int id, Model model){
        Car b = carService.findById(id);

        if(b == null){
            throw new ProductNotFoundException("Product Id not found- " + id);
        }
        carService.deleteById(id);
        return "redirect:/carApi/cars";
    }

    @GetMapping("/cars")
    public String findAll(Model model){
        model.addAttribute("cars", carService.findAll());
        return "listcars";
    }

    @GetMapping("/cars/{carId}")
    public Car getCar(@PathVariable int carId){

        Car b = carService.findById(carId);
        if(b == null){
            throw new ProductNotFoundException("Product Id not found- " + carId);
        }
        return b;
    }

    @GetMapping("/carsView")
    public String carsViewForUser(Model model){
//        String email = principal.getName();
//        Customer c = cus
//        Reader reader = new Reader();
//        model.addAttribute("reader",reader);
        model.addAttribute("cars",carService.findAll());
        return "list-of-cars-for-user";
    }
}
