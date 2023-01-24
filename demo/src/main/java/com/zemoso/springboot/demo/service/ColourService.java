package com.zemoso.springboot.demo.service;

import com.zemoso.springboot.demo.entity.Colour;

import java.util.List;

public interface ColourService {
    public List<Colour> findAll();

    public Colour findById(int id);

    public void save(Colour colour);

    public void deleteById(int id);
}
