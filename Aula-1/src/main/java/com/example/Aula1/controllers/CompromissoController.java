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

import com.example.Aula1.entidades.Compromissos;
import com.example.Aula1.entidades.Local;
import com.example.Aula1.repositories.CompromissosRepository;
import com.example.Aula1.repositories.LocalRepository;

@RestController
@RequestMapping("/compromisso")
public class CompromissoController {
	List<Compromissos> local = new ArrayList<>();
	
	@Autowired
	CompromissosRepository repo;
	
	@PostMapping
	public ResponseEntity<Compromissos> salvar(@RequestBody Compromissos compromisso) {
		repo.save(compromisso);
		return ResponseEntity.status(HttpStatus.CREATED).body(compromisso);
	}
	
	@GetMapping
	public ResponseEntity<List<Compromissos>> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@GetMapping("/{idcompromisso}")
	public ResponseEntity<Object> consultar(@PathVariable("idcompromisso")Long idcompromisso){
		Optional<Compromissos> opt = repo.findById(idcompromisso);
		try {
			Compromissos cp = opt.get();
			return ResponseEntity.status(HttpStatus.OK).body(cp);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado!");
		}
	}
	
	@PutMapping("/{idcompromisso}")
	public ResponseEntity<Object> alterar(@PathVariable("idlocal") Long idcompromisso,
			@RequestBody Compromissos compromisso){
		
		Optional<Compromissos> opt = repo.findById(idcompromisso);
		try {
			Compromissos cp = opt.get();
			cp.setDescricao(compromisso.getDescricao());
			cp.setData(compromisso.getData());
			cp.setHora(compromisso.getHora());
			cp.setContato(compromisso.getContato());

			repo.save(cp);
			return ResponseEntity.status(HttpStatus.OK).body(cp);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado!");
		}
	}
	
	@DeleteMapping("/{idcompromisso}")
	public ResponseEntity<Object> excluir(@PathVariable("idcompromisso") Long idcompromisso) {	
		Optional<Compromissos> opt = repo.findById(idcompromisso);
		try {
			Compromissos cp = opt.get();
			repo.delete(cp);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compromisso não encontrado!");
		}
	}
}