package com.example.Aula1.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Aula1.Exception.RecursoNaoEncontrado;
import com.example.Aula1.Exception.ValidaDadosException;
import com.example.Aula1.entidades.Compromisso;
import com.example.Aula1.entidades.Contato;
import com.example.Aula1.entidades.StatusCompromisso;
import com.example.Aula1.repositories.CompromissoRepository;

@Service
public class CompromissoService {

	@Autowired
	CompromissoRepository repo;
	
	public Compromisso consultar(Long idcompromisso) {
		Optional<Compromisso> opt = repo.findById(idcompromisso);
		Compromisso cp = opt.orElseThrow(()-> new RecursoNaoEncontrado("Compromisso não encontrado"));
		return cp;
	}
	
	public Compromisso salvar(Compromisso compromisso) {
		validaCampos(compromisso);
		return repo.save(compromisso);
	}
	
	public Compromisso alterar(Long idCompromisso, Compromisso compromisso) {
		Compromisso cp = consultar(idCompromisso);
		validaCampos(compromisso);
		cp = compromisso;
		return repo.save(cp);
	}
	
	public void excluir(Long idCompromisso) {
		Compromisso cp = consultar(idCompromisso);
		repo.delete(cp);
	}
	
	private void validaCampos(Compromisso compromisso) {
		if(compromisso.getDescricao().isEmpty()) {
			throw new ValidaDadosException("A descrição deve ser informada");
		}
		
		if(compromisso.getData() == null) {
			throw new ValidaDadosException("A data deve ser informada");
		}
		
		if(compromisso.getHora() == null) {
			throw new ValidaDadosException("A hora deve ser infomada");
		}
		
		if(compromisso.getContato() == null) {
			throw new ValidaDadosException("O contato deve ser informado");
		}
		
		if(compromisso.getLocal() == null){
			throw new ValidaDadosException("O local deve ser informado");
		}
	}

    public Compromisso atualizarStatus(Long idCompromisso, StatusCompromisso novoStatus) {
        Compromisso cp = consultar(idCompromisso);
        cp.setStatus(novoStatus);
        return repo.save(cp);
    }

    public List<Compromisso> pesquisarPorIntervaloData(Date dataInicio, Date dataFim) {
        return repo.findByDataBetween(dataInicio, dataFim);
    }

    public List<Compromisso> pesquisarPorContato(Long idContato) {
		try {
			ContatoService contatoService = new ContatoService();
			Contato contato = contatoService.consultar(idContato);
			return repo.findByContato(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Collections.emptyList();
    }

}
