package com.zemoso.springboot.demo.controller;


import com.zemoso.springboot.demo.entity.Customer;
import com.zemoso.springboot.demo.exceptionHandlers.UserNameAlreadyExists;
import com.zemoso.springboot.demo.service.ColourService;
import com.zemoso.springboot.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customerApi")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @GetMapping("")
    public String viewHome(){
        return "index";
    }

    @GetMapping("/showFormForRegistration")
    public String addFunc(Model model){
        Customer c = new Customer();
        model.addAttribute("customer", c);
        return "register";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute Customer c){
        if (customerService.findByEmailId(c)) {
            throw new UserNameAlreadyExists("User Already exists with this username");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        c.setRole("User");
        customerService.save(c);
        return "redirect:/customerApi/success";
    }

    @GetMapping("/success")
    public String registerSuccess(){
        return "register-success";
    }

    @GetMapping("/customers")
    public String getCustomers(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "list-customers";
    }

    @GetMapping("/customers/{custId}")
    public Customer getCustomer(@PathVariable int custId){

        Customer c = customerService.findById(custId);
        if(c == null){
            throw new RuntimeException("Customer Id not found- " + custId);
        }
        return c;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateFunc(@PathVariable(value = "id") int id, Model model){
        Customer c = customerService.findById(id);
        model.addAttribute("customer",c);
        return "update-a-customer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer c){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(c.getPassword());
        c.setPassword(encoded);
        customerService.save(c);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int id, Model model){
        Customer c = customerService.findById(id);

        if(c == null){
            throw new RuntimeException("Customer Id not found- " + id);
        }
        customerService.deleteById(id);
        return "redirect:/customerApi/customers";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/customerApi/showFormForRegistration";
    }

    @GetMapping("/adminPage")
    public String adminHome(){
        return "redirect:/carApi/cars";
    }

    @GetMapping("/errorPage")
    public String error(){
        return "errorPage";
    }

}
