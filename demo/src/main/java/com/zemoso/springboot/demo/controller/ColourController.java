package com.zemoso.springboot.demo.controller;

import com.zemoso.springboot.demo.entity.Car;
import com.zemoso.springboot.demo.entity.Colour;
import com.zemoso.springboot.demo.exceptionHandlers.ProductNotFoundException;
import com.zemoso.springboot.demo.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ComponentScan("com.zemoso.springboot.demo.service")
@RequestMapping("/colourApi")
public class ColourController {

    @Autowired
    private ColourService colourService;

    @Autowired
    public ColourController(ColourService colourService){
        this.colourService = colourService;
    }

    @GetMapping("/add")
    public String addFunc(Model model){
        List<Colour> list = colourService.findAll();
        Colour c = new Colour();
        model.addAttribute("colour", c);
        model.addAttribute("list", list);
        return "add-colour";
    }

    @PostMapping("/save")
    public String saveFunc(@ModelAttribute Colour c){
        colourService.save(c);
        return "redirect:/carApi/addCar";
    }

    @GetMapping("/delete/{id}")
    public String deleteColour(@PathVariable(value = "id") int id,Model model){
        Colour b = colourService.findById(id);

        if(b == null){
            throw new ProductNotFoundException("Product Id not found- " + id);
        }
        colourService.deleteById(id);
        return "redirect:/carApi/cars";
    }
}
