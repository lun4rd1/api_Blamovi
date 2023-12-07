package com.senai.apiblamovi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "comentario")

public class ComentarioModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCmentario", nullable = false)

    private UUID idComentario;
    private UUID id_usuario;
    private UUID idPost;
    private String texto;

}