package com.alex.sisos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.sisos.domain.Cliente;
import com.alex.sisos.domain.OS;
import com.alex.sisos.domain.Tecnico;
import com.alex.sisos.domain.enuns.Prioridade;
import com.alex.sisos.domain.enuns.Status;
import com.alex.sisos.dtos.OSDTO;
import com.alex.sisos.repository.OSRepository;
import com.alex.sisos.services.excepitions.ObjectNotFoundExceptions;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteServices clienteServices;

	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundExceptions(
				"Objeto não encontrado Id: " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacao(obj.getObservacao());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente clt = clienteServices.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(clt);

		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(newObj);
	}

}
