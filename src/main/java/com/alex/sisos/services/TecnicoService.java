package com.alex.sisos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alex.sisos.domain.Pessoa;
import com.alex.sisos.domain.Tecnico;
import com.alex.sisos.dtos.TecnicoDTO;
import com.alex.sisos.repository.PessoaRepository;
import com.alex.sisos.repository.TecnicoRepository;
import com.alex.sisos.services.excepitions.ObjectNotFoundExceptions;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
				"Objeto não encontrado! ID: " + id + " Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCpf(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF Já cadastrado na base de dados");
		}
		return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}

	public Tecnico update(Integer id, TecnicoDTO objDTO) {
		Tecnico oldOBJ = findById(id);
		if(findByCpf(objDTO) != null && findByCpf(objDTO).getId() != id) {
			throw new DataIntegrityViolationException("CPF Já cadastrado na base de dados");
		}
		
		oldOBJ.setNome(objDTO.getNome());
		oldOBJ.setCpf(objDTO.getCpf());
		oldOBJ.setTelefone(objDTO.getTelefone());
		
		return repository.save(oldOBJ);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		
		if(obj.getList().size() > 0) {
			throw new DataIntegrityViolationException("Técnico pssui ordem de serviço, não pode ser deletado");
		}
		
		repository.deleteById(id);
	}
	
	private Pessoa findByCpf(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj != null) {
			return obj;
		}
		return null;
	}

	

}
