package com.senai.apiblamovi.controllers;

import com.senai.apiblamovi.dtos.ComentarioDto;
import com.senai.apiblamovi.models.ComentarioModel;
import com.senai.apiblamovi.repositories.ComentarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/comentario", produces = {"application/json"})
public class ComentarioController {

    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<List<ComentarioModel>> listarComentarios() {
        return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.findAll());
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<Object> buscarComentarioId(@PathVariable(value = "idComentario") UUID id) {
        Optional<ComentarioModel> comentarioBuscado = comentarioRepository.findById(id);

        if (comentarioBuscado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(comentarioBuscado.get());
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarComentario(@RequestBody @Valid ComentarioDto dadosRecebidos) {

        ComentarioModel comentarioModel = new ComentarioModel();
        BeanUtils.copyProperties(dadosRecebidos, comentarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentarioModel));
    }

    @PutMapping(value = "/{idComentario}")
    public ResponseEntity<Object> editarComentario(@PathVariable(value = "idComentario") UUID id, @Valid ComentarioDto comentarioDto){
        Optional<ComentarioModel> comentarioBuscado = comentarioRepository.findById(id);

        if (comentarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado");
        }

        ComentarioModel comentarioModel = comentarioBuscado.get();
        BeanUtils.copyProperties(comentarioDto, comentarioModel);

        return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.save(comentarioModel));
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Object> deletarComentario(@PathVariable(value = "idComentario") UUID id){
        Optional<ComentarioModel> comentarioBuscado = comentarioRepository.findById(id);

        if (comentarioBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado");
        }
        comentarioRepository.delete(comentarioBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Comentário deletado com sucesso");
    }
}