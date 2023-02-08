package com.sofka.spaceZ.services;

import com.sofka.spaceZ.models.Nave;
import com.sofka.spaceZ.repositories.NaveRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class NaveServicesImplement implements NaveServices{
    @Autowired
    private NaveRep repository;

    @Override
    public List<Nave> findAll() {

        return (List<Nave>) repository.findAll();
    }

    @Override
    public Nave findById(Long Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public Nave save(Nave nave) {
        return repository.save(nave);
    }

    @Override
    public void delete(Long Id) {
        repository.deleteById(Id);
    }
}

