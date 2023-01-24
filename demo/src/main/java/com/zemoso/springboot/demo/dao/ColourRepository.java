package com.zemoso.springboot.demo.dao;

import com.zemoso.springboot.demo.entity.Colour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColourRepository extends JpaRepository<Colour, Integer> {
}
