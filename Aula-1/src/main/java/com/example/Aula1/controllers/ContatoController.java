package com.example.Aula1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Aula1.entidades.Contato;
import com.example.Aula1.repositories.ContatoRepository;
import com.example.Aula1.services.ContatoService;

@RestController
@RequestMapping("/contatos")
@CrossOrigin
public class ContatoController {
	List<Contato> contatos = new ArrayList<>();

	@Autowired
	ContatoRepository repo;

	@Autowired
	ContatoService service;

	@PostMapping
	public ResponseEntity<Contato> salvar(@RequestBody Contato contato) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(contato));
	}

	@PutMapping("/{idcontato}")
	public ResponseEntity<Object> alterar(@PathVariable("idcontato") Long idcontato, @RequestBody Contato contato) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idcontato, contato));

	}

	@DeleteMapping("/{idcontato}")
	public ResponseEntity<Object> excluir(@PathVariable("idcontato") Long idcontato) {
		service.excluir(idcontato);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping
	public ResponseEntity<List<Contato>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}

	@GetMapping("/{idcontato}")
	public ResponseEntity<Object> consultar(@PathVariable("idcontato") Long idcontato) {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar(idcontato));
	}
}
