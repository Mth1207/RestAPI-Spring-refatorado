package com.example.Aula1.controllers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Aula1.entidades.Compromisso;
import com.example.Aula1.entidades.StatusCompromisso;
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
	
    @PutMapping("/{idcompromisso}/atualizar-status")
    public ResponseEntity<Compromisso> atualizarStatus(
            @PathVariable("idcompromisso") Long idcompromisso,
            @RequestBody StatusCompromisso novoStatus) {
        Compromisso compromissoAtualizado = service.atualizarStatus(idcompromisso, novoStatus);
        return ResponseEntity.status(HttpStatus.OK).body(compromissoAtualizado);
    }
    
    @GetMapping("/pesquisar-por-intervalo-data")
    public ResponseEntity<List<Compromisso>> pesquisarPorIntervaloData(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim) {
        List<Compromisso> compromissos = service.pesquisarPorIntervaloData(dataInicio, dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
    }

    @GetMapping("/pesquisar-por-contato")
    public ResponseEntity<List<Compromisso>> pesquisarPorContato(
            @RequestParam Long idContato) {
        List<Compromisso> compromissos = service.pesquisarPorContato(idContato);
        return ResponseEntity.status(HttpStatus.OK).body(compromissos);
    }
	
}