package com.sofka.spaceZ.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipos")
public class TipoNave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
    @Column(length = 50)
    private String nombre;

    private String Descripcion;

    @OneToMany(mappedBy = "tipo")
    private List<Nave> naves;


}
