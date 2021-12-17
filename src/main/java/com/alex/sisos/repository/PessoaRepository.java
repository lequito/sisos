package com.alex.sisos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alex.sisos.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query("select obj from Pessoa obj where obj.cpf =:cpf")
	Pessoa findByCpf(@Param("cpf") String cpf);
	
}
