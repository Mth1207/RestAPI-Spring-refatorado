package com.example.Aula1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Aula1.entidades.Compromissos;

@Repository
public interface CompromissosRepository extends JpaRepository<Compromissos, Long>{
	
}
