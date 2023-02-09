package com.sofka.spaceZ.repositories;

import com.sofka.spaceZ.models.TipoNave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNaveRep extends JpaRepository<TipoNave,Long> {
}
