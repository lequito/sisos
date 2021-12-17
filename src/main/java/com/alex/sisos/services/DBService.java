package com.alex.sisos.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.sisos.domain.Cliente;
import com.alex.sisos.domain.OS;
import com.alex.sisos.domain.Tecnico;
import com.alex.sisos.domain.enuns.Prioridade;
import com.alex.sisos.domain.enuns.Status;
import com.alex.sisos.repository.ClienteRepository;
import com.alex.sisos.repository.OSRepository;
import com.alex.sisos.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public void instaciadb() {
		Tecnico t2 = new Tecnico(null, "Linus", "586.138.922-56", "46545651231");
		Tecnico t1 = new Tecnico(null, "asdf", "378.086.810-59", "123456");
		Cliente c3 = new Cliente(null, "cliente", "678.927.782-09", "4444464654");
		OS os3 = new OS(null, Prioridade.BAIXA, "hkgl", Status.ABERTO, t1, c3);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c3));
		osRepository.saveAll(Arrays.asList(os3));

	}

}
