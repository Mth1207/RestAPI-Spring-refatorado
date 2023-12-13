package com.example.Aula1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Aula1.Exception.RecursoNaoEncontrado;
import com.example.Aula1.Exception.ValidaDadosException;
import com.example.Aula1.entidades.Local;
import com.example.Aula1.repositories.LocalRepository;

@Service
public class LocalService {

	@Autowired
	LocalRepository repo;
	
	public Local consultar(Long idlocal) {
		Optional<Local> opt = repo.findById(idlocal);
		Local lc = opt.orElseThrow(()-> new RecursoNaoEncontrado("Local não encontrado"));
		return lc;
	}
	
	public Local salvar(Local local) {
		validaCampos(local);
		return repo.save(local);
	}
	
	public Local alterar(Long idLocal, Local local) {
		Local lc = consultar(idLocal);
		validaCampos(local);
		return repo.save(lc);
	}
	
	public void excluir(Long idLocal) {
		Local lc = consultar(idLocal);
		repo.delete(lc);
	}
	
	private void validaCampos(Local local) {
		if(local.getNome().equals("")) {
			throw new ValidaDadosException("O nome deve ser informado");
		}
		
		if(local.getRua().equals("")) {
			throw new ValidaDadosException("A rua deve ser informada");
		}

		if(local.getNumero().equals("")) {
			throw new ValidaDadosException("O numero deve ser informado");
		}
	
		if(local.getBairro().equals("")) {
			throw new ValidaDadosException("O bairro deve ser informado");
		}
		
		if(local.getCidade().equals("")) {
			throw new ValidaDadosException("A cidade deve ser informada");
		}
		
		if(local.getEstado().equals("")) {
			throw new ValidaDadosException("O estado deve ser informado");
		}
		
		if(local.getCep().equals("")) {
			throw new ValidaDadosException("O CEP deve ser informado");
		}
	}
}
