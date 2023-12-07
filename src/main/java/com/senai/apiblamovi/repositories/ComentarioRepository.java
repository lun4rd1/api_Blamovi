package com.senai.apiblamovi.repositories;

import com.senai.apiblamovi.models.ComentarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID> {
    ComentarioModel findByTexto(String texto);

}