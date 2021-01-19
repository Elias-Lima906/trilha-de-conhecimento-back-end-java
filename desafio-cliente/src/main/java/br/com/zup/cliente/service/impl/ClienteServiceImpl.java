package br.com.zup.cliente.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.cliente.dto.ClienteDTO;
import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.dto.alteraClienteDTO;
import br.com.zup.cliente.entity.Cliente;
import br.com.zup.cliente.exception.GlobalException;
import br.com.zup.cliente.repository.ClienteRepository;
import br.com.zup.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final String CLIENTE_JÁ_EXISTE = "CLIENTE JÁ EXISTE NO BANCO DE DADOS!";

	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "CLIENTE REMOVIDO COM SUCESSO";

	private static final String CLIENTE_NÃO_ENCONTRADO = "CLIENTE NÃO ENCONTRADO!";

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Optional<Cliente> buscaCliente(String cpf) throws GlobalException {

		if (clienteRepository.existsById(cpf)) {
			return clienteRepository.findById(cpf);
		}

		throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
	}

	@Override
	public List<Cliente> listaCientes() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente adicionaCliente(ClienteDTO clienteDTO) throws GlobalException {

		if (clienteRepository.existsById(clienteDTO.getCpf())) {
			throw new GlobalException(CLIENTE_JÁ_EXISTE);
		}

		Cliente cliente = new Cliente();

		BeanUtils.copyProperties(clienteDTO, cliente);

		clienteRepository.save(cliente);

		return cliente;
	}

	@Override
	public Optional<Cliente> alteraCliente(String cpf, alteraClienteDTO clienteDTO) throws GlobalException {

		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
		}

		Optional<Cliente> cliente = clienteRepository.findById(cpf);

		BeanUtils.copyProperties(clienteDTO, cliente.get());

		clienteRepository.save(cliente.get());

		return cliente;
	}

	@Override
	public MensagemDTO removeCliente(String cpf) throws GlobalException {

		if (clienteRepository.existsById(cpf)) {
			
			clienteRepository.deleteById(cpf);
			return new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);
		}

		throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
	}
}