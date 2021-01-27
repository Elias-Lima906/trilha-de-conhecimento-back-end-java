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

	private static final String PROCESSO_FINALIZADO = "Processo Finalizado!";

	private static final String INICIANDO_PROCESSO = "Iniciando Processo!";

	private static final String CLIENTE_JÁ_EXISTE_COM_CPF = "JÁ EXISTE UM CADASTRO NO BANCO DE DADOS COM O CPF: ";

	private static final String CLIENTE_REMOVIDO_COM_SUCESSO = "CLIENTE REMOVIDO COM SUCESSO";

	private static final String CLIENTE_NÃO_ENCONTRADO = "CLIENTE NÃO ENCONTRADO PELO CPF: ";

	@Autowired
	private ClienteRepository clienteRepository;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public Optional<Cliente> buscaCliente(String cpf) throws GlobalException {

		logger.info(INICIANDO_PROCESSO);

		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO + cpf);
		}

		logger.info(PROCESSO_FINALIZADO);

		return clienteRepository.findById(cpf);
	}

	@Override
	public List<Cliente> listaCientes() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Cliente adicionaCliente(ClienteDTO clienteDTO) throws GlobalException {

		logger.info(INICIANDO_PROCESSO);

		String cpf = clienteDTO.getCpf();

		if (clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_JÁ_EXISTE_COM_CPF + cpf);
		}

		Cliente cliente = new Cliente();

		BeanUtils.copyProperties(clienteDTO, cliente);

		clienteRepository.save(cliente);

		logger.info(PROCESSO_FINALIZADO);

		return cliente;
	}

	@Override
	public Optional<Cliente> alteraCliente(String cpf, alteraClienteDTO clienteDTO) throws GlobalException {

		logger.info(INICIANDO_PROCESSO);

		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO + cpf);
		}

		Optional<Cliente> cliente = clienteRepository.findById(cpf);

		BeanUtils.copyProperties(clienteDTO, cliente.get());

		clienteRepository.save(cliente.get());

		logger.info(PROCESSO_FINALIZADO);

		return cliente;
	}

	@Override
	public MensagemDTO removeCliente(String cpf) throws GlobalException {

		logger.info(INICIANDO_PROCESSO);

		if (!clienteRepository.existsById(cpf)) {
			throw new GlobalException(CLIENTE_NÃO_ENCONTRADO + cpf);
		}

		logger.info(PROCESSO_FINALIZADO);

		clienteRepository.deleteById(cpf);
		return new MensagemDTO(CLIENTE_REMOVIDO_COM_SUCESSO);
	}
}