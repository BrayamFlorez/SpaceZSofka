package com.sofka.spaceZ.services;

import com.sofka.spaceZ.models.Nave;

import java.util.List;

public interface NaveServices {
    public List<Nave> findAll();
    public Nave findById(Long Id);
    public Nave save(Nave nave);
    public void delete(Long Id);
}

