package br.com.zup.cliente.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
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

	private static final String INICIANDO_PROCEDIMENTO_DE_BUSCA = "Iniciando processo de busca!";

	private static final String CLIENTE_ALTERADO = "Cliente alterado!";

	private static final String INICIANDO_PEOCESSO_DE_ALTERAÇÃO = "Iniciando processo de alteração!";

	private static final String CADASTRO_EFETUADO = "Cadastro efetuado!";

	private static final String INICIANDO_PEOCESSO_DE_CADASTRO = "Iniciando processo de cadastro!";

	private static final String CLIENTE_ENCONTADO = "Cliente encontado!";

	private static final String INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA = "Iniciando procedimento de verificação de existência!";

	private static final String CLIENTE_JÁ_EXISTE = "CLIENTE JÁ EXISTE NO BANCO DE DADOS!";

	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "CLIENTE REMOVIDO COM SUCESSO";

	private static final String CLIENTE_NÃO_ENCONTRADO = "CLIENTE NÃO ENCONTRADO!";

	@Autowired
	private ClienteRepository clienteRepository;
	
	private Logger logger = Logger.getLogger(ClienteServiceImpl.class);

	@Override
	public Optional<Cliente> buscaCliente(String cpf) throws GlobalException {

		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
		}

		logger.info(CLIENTE_ENCONTADO);
		
		return clienteRepository.findById(cpf);
	}

	@Override
	public List<Cliente> listaCientes() {
		
		logger.info(INICIANDO_PROCEDIMENTO_DE_BUSCA);
		
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente adicionaCliente(ClienteDTO clienteDTO) throws GlobalException {
		
		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (clienteRepository.existsById(clienteDTO.getCpf())) {
			throw new GlobalException(CLIENTE_JÁ_EXISTE);
		}
		
		logger.info(INICIANDO_PEOCESSO_DE_CADASTRO);

		Cliente cliente = new Cliente();

		BeanUtils.copyProperties(clienteDTO, cliente);

		clienteRepository.save(cliente);
		
		logger.info(CADASTRO_EFETUADO);

		return cliente;
	}

	@Override
	public Optional<Cliente> alteraCliente(String cpf, alteraClienteDTO clienteDTO) throws GlobalException {

		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
		}
		
		logger.info(INICIANDO_PEOCESSO_DE_ALTERAÇÃO);

		Optional<Cliente> cliente = clienteRepository.findById(cpf);

		BeanUtils.copyProperties(clienteDTO, cliente.get());

		clienteRepository.save(cliente.get());

		logger.info(CLIENTE_ALTERADO);
		
		return cliente;
	}

	@Override
	public MensagemDTO removeCliente(String cpf) throws GlobalException {

		logger.info(INICIANDO_PROCEDIMENTO_DE_VERIFICAÇÃO_DE_EXISTÊNCIA);
		
		if (clienteRepository.existsById(cpf)) {

			clienteRepository.deleteById(cpf);
			return new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);
		}

		logger.info("Cliente deletado!");
		
		throw new GlobalException(CLIENTE_NÃO_ENCONTRADO);
	}
}