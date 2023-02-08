package com.sofka.spaceZ.repositories;

import com.sofka.spaceZ.models.Nave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NaveRep extends JpaRepository<Nave,Long>{
}


