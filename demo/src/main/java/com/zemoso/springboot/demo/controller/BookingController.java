package com.zemoso.springboot.demo.controller;


import com.zemoso.springboot.demo.entity.Car;
import com.zemoso.springboot.demo.entity.Customer;
import com.zemoso.springboot.demo.entity.Reader;
import com.zemoso.springboot.demo.service.CarService;
import com.zemoso.springboot.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/bookApi")
public class BookingController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CarService carService;

    @GetMapping("/book")
    public String bookingFunc(@ModelAttribute Reader r, Model model, Principal principal){

//        int id = r.getId();
//        Customer c = customerService.findById(id);
//        if(c == null){
//            throw new RuntimeException("Customer Id not found- " + id);
//        }
        String email = principal.getName();
        Customer c = customerService.findByEmailId(email);
        List<Car> carList = carService.findAll();
        model.addAttribute("customer",  c);
        model.addAttribute("list", carList);
        return "booking-page";
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Customer c){
        customerService.save(c);
        return "redirect:/carApi/carsView";
    }
}
