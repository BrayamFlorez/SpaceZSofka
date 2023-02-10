package com.sofka.spaceZ.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import com.sofka.spaceZ.models.TipoNave;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "naves")
public class Nave {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(length = 50)
    private String nombre;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
   // @ManyToOne
    //@JoinColumn(name = "tipo")
   @Column(length = 1)
    private int tipo;

}


