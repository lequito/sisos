package com.alex.sisos.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alex.sisos.domain.Cliente;
import com.alex.sisos.domain.Pessoa;
import com.alex.sisos.dtos.ClienteDTO;
import com.alex.sisos.repository.ClienteRepository;
import com.alex.sisos.repository.PessoaRepository;
import com.alex.sisos.services.excepitions.ObjectNotFoundExceptions;

@Service
public class ClienteServices {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
				"Objeto não encontrado! ID: " + id + " Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(@Valid ClienteDTO objDTO) {
		if (findByCpf(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF Já cadastrado na base de dados");
		}
		return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Cliente update(Integer id, ClienteDTO objDTO) {
		Cliente oldOBJ = findById(id);
		
		if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id){
			throw new DataIntegrityViolationException("CPF Já cadastrado na base de dados");
		}
		
		oldOBJ.setNome(objDTO.getNome());
		oldOBJ.setCpf(objDTO.getCpf());
		oldOBJ.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldOBJ);
	}
	
	public void delete(Integer id) {
		Cliente obj = findById(id);
		
		if(obj.getList().size() > 0){
			throw new DataIntegrityViolationException("Cliente possui ordem de serviço, não pode ser deletado");
		}
		repository.deleteById(id);
	}
	
	private Pessoa findByCpf(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

	


}
