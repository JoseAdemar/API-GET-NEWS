package com.noticia.api.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noticia.api.exception.NegocioExecption;
import com.noticia.api.exception.NoticiaException;
import com.noticia.api.model.Noticia;
import com.noticia.api.service.NoticiaService;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

	@Autowired
	NoticiaService noticiaService;

	@PostMapping
	public ResponseEntity<List<Noticia>> salvaUmaNoticia(@RequestBody Noticia noticia) throws IOException {

		try {
			List<Noticia> noticiaList = noticiaService.noticiaCapturada(noticia);

			return ResponseEntity.status(HttpStatus.CREATED).body(noticiaList);

		} catch (NoticiaException e) {
			throw new NegocioExecption(e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<?> listaTodasNoticias() {

		List<Noticia> noticias = noticiaService.listarTodasAsNoticias();
		return ResponseEntity.status(HttpStatus.OK).body(noticias);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> listaNoticaPorId(@PathVariable Long id) {

		Noticia noticias = noticiaService.listarNoticiaPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(noticias);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletaNoticaPorId(@PathVariable Long id) {

		noticiaService.deletarNoticiaPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaNoticia(@RequestBody @Valid Noticia noticia, @PathVariable Long id){
		
		      try {
		     Noticia noticias = noticiaService.atualizarNoticia(noticia, id);
		      return ResponseEntity.status(HttpStatus.OK).body(noticias);
		      
		      }catch (EmptyResultDataAccessException e) {
				throw new NoticiaException(e.getMessage());
			}
	}
}
