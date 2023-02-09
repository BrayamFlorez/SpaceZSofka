package com.sofka.spaceZ.services;

import com.sofka.spaceZ.models.Nave;
import com.sofka.spaceZ.models.TipoNave;

import java.util.List;

public interface TipoNaveServices {

    public List<TipoNave> findAll();
    public TipoNave findById(Long Id);
    public TipoNave save(TipoNave tipoNave);
    public void delete(Long Id);
}
