package com.noticia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noticia.api.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}

