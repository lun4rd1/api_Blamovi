package com.senai.apiblamovi.dtos;

import java.util.UUID;

public record ComentarioDto(

        UUID idComentario,

        UUID id_usuario,

        UUID idPost,

        String texto

        ) {}