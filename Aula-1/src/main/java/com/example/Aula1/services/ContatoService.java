package com.example.Aula1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Aula1.Exception.RecursoJaExistente;
import com.example.Aula1.Exception.RecursoNaoEncontrado;
import com.example.Aula1.Exception.ValidaDadosException;
import com.example.Aula1.entidades.Contato;
import com.example.Aula1.repositories.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	ContatoRepository repo;
	
	public Contato consultar(Long idcontato) {
		Optional<Contato> opt = repo.findById(idcontato);
		Contato ct = opt.orElseThrow(()-> new RecursoNaoEncontrado("Contato não encontrado"));
		return ct;
	}
	
	public Contato salvar(Contato contato) {
		validaCampos(contato);
		if(repo.findByEmail(contato.getEmail()) != null) {
			throw new RecursoJaExistente("Contato já cadastrado para este email");
		}
		return repo.save(contato);
	}
	
	public Contato alterar(Long idContato, Contato contato) {
		Contato ct = consultar(idContato);
		validaCampos(contato);
		ct.setNome(contato.getNome());
		ct.setEmail(contato.getEmail());
		ct.setFone(contato.getFone());
		return repo.save(ct);
	}
	
	public void excluir(Long idContato) {
		Contato ct = consultar(idContato);
		repo.delete(ct);
	}
	
	private void validaCampos(Contato contato) {
		if(contato.getNome().equals("")) {
			throw new ValidaDadosException("O nome deve ser informado");
		}
		if(contato.getEmail().equals("")) {
			throw new ValidaDadosException("O email deve ser informado");
		}
		
		if(contato.getFone() == null ) {
			throw new ValidaDadosException("O fone deve ser informado");
		}
		else {
			if(contato.getFone().equals("")) {
				throw new ValidaDadosException("O fone deve ser informado");
			}
		}
	}
}
