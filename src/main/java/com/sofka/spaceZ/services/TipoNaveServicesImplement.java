package com.sofka.spaceZ.services;

import com.sofka.spaceZ.models.Nave;
import com.sofka.spaceZ.models.TipoNave;
import com.sofka.spaceZ.repositories.NaveRep;
import com.sofka.spaceZ.repositories.TipoNaveRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoNaveServicesImplement implements TipoNaveServices{

    @Autowired
    private TipoNaveRep repository;
    @Override
    public List<TipoNave> findAll() {
        return (List<TipoNave>) repository.findAll();
    }

    @Override
    public TipoNave findById(Long Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public TipoNave save(TipoNave tipoNave) {
        return repository.save(tipoNave);
    }

    @Override
    public void delete(Long Id) {
        repository.deleteById(Id);
    }
}
