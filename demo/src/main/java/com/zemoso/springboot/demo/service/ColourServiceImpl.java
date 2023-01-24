package com.zemoso.springboot.demo.service;

import com.zemoso.springboot.demo.dao.ColourRepository;
import com.zemoso.springboot.demo.entity.Colour;
import com.zemoso.springboot.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColourServiceImpl implements ColourService{
    @Autowired
    private ColourRepository colourRepository;

    public ColourServiceImpl(ColourRepository colourRepository) {
        this.colourRepository = colourRepository;
    }

    @Override
    public List<Colour> findAll() {
        return colourRepository.findAll();
    }

    @Override
    public Colour findById(int id) {
        Optional<Colour> result = colourRepository.findById(id);
        Colour colour = null;
        if(result.isPresent()) {
            colour = result.get();
        }
        else{
            throw new RuntimeException("Did not find Colour with Id- "+id);
        }
        return colour;
    }

    @Override
    public void save(Colour colour) {
        colourRepository.save(colour);
    }

    @Override
    public void deleteById(int id) {
        colourRepository.deleteById(id);
    }
}
