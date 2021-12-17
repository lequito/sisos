package com.alex.sisos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alex.sisos.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

	@Query("select obj from Tecnico obj where obj.cpf =:cpf")
	Tecnico findByCpf(@Param("cpf") String cpf);
}
