package com.example.Aula1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/ola2")
	public String olaMundo() {
		return "Hello World";
	}
	
	@GetMapping("/ola/{nome}")
	public String olaMundo(@PathVariable("nome")String nome) {
		return "Hello World " + nome;
	}
	
	@PostMapping("/contatos2")
	public String salvar(@RequestBody String contato) {
		return contato;
	}
	
	@PutMapping("/contatos2/{idcontato}")
	public String alterar(@PathVariable("idcontato") int idcontato,
			@RequestBody String contato) {
		return contato;
	}
}
