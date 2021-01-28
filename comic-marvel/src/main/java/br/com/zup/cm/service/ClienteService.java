package br.com.zup.cm.service;

import java.util.List;
import java.util.Optional;

import br.com.zup.cm.dto.ClienteDTO;
import br.com.zup.cm.dto.MensagemDTO;
import br.com.zup.cm.dto.alteraClienteDTO;
import br.com.zup.cm.entity.Cliente;
import br.com.zup.cm.exception.GlobalException;



public interface ClienteService {

	public Optional<Cliente> buscaCliente(String cpf) throws GlobalException;

	public List<Cliente> listaCientes();

	public Cliente adicionaCliente(ClienteDTO clienteDTO) throws GlobalException;

	public Optional<Cliente> alteraCliente(String cpf, alteraClienteDTO clienteDTO) throws GlobalException;

	public MensagemDTO removeCliente(String cpf) throws GlobalException;
}
