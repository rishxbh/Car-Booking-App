package com.zemoso.springboot.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "colour")
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colour_id")
    private int colourId;

    @Column(name = "colour_name")
    private String colourName;

    public Colour() { }

    public Colour(int colourId, String colourName) {
        this.colourId = colourId;
        this.colourName = colourName;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    @Override
    public String toString() {
        return "Colour{" +
                "colourId=" + colourId +
                ", colourName='" + colourName + '\'' +
                '}';
    }
}
