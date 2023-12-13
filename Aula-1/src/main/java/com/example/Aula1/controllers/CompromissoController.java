package com.example.Aula1.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.Aula1.entidades.Compromisso;
import com.example.Aula1.repositories.CompromissoRepository;
import com.example.Aula1.services.CompromissoService;

@RestController
@RequestMapping("/compromissos")
public class CompromissoController {
	List<Compromisso> local = new ArrayList<>();

	@Autowired
	CompromissoRepository repo;

	@Autowired
	CompromissoService service;

	@PostMapping
	public ResponseEntity<Compromisso> salvar(@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(compromisso));
	}

	@GetMapping
	public ResponseEntity<List<Compromisso>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}

	@GetMapping("/{idcompromisso}")
	public ResponseEntity<Object> consultar(@PathVariable("idcompromisso") Long idcompromisso) {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar(idcompromisso));
	}

	@PutMapping("/{idcompromisso}")
	public ResponseEntity<Object> alterar(@PathVariable("idcompromisso") Long idcompromisso,
			@RequestBody Compromisso compromisso) {
		return ResponseEntity.status(HttpStatus.OK).body(service.alterar(idcompromisso, compromisso));
	}

	@DeleteMapping("/{idcompromisso}")
	public ResponseEntity<Object> excluir(@PathVariable("idcompromisso") Long idcompromisso) {
		service.excluir(idcompromisso);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}