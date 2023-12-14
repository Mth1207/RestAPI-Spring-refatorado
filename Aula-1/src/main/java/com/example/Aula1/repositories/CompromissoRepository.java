package com.example.Aula1.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Aula1.entidades.Compromisso;
import com.example.Aula1.entidades.Contato;

@Repository
public interface CompromissoRepository extends JpaRepository<Compromisso, Long>{

	@Query("SELECT c FROM Compromisso c WHERE c.data BETWEEN :dataInicio AND :dataFim")
    List<Compromisso> findByDataBetween(@Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
	
	List<Compromisso> findAllByContato(Contato contato);
	
}
