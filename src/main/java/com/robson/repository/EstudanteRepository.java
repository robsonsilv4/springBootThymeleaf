package com.robson.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.robson.domain.Estudante;

@Repository
public interface EstudanteRepository extends CrudRepository<Estudante, Long> {

	List<Estudante> findByMatricula(Long matricula);

	List<Estudante> findByNome(String nome);
}