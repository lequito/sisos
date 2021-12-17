package com.alex.sisos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alex.sisos.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("select obj from Cliente obj where obj.cpf =:cpf")
	Cliente findByCpf(@Param("cpf") String cpf);
}
