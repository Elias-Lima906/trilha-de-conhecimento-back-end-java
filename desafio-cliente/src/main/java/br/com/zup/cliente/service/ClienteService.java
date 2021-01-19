package br.com.zup.cliente.service;

import java.util.List;
import java.util.Optional;

import br.com.zup.cliente.dto.ClienteDTO;
import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.dto.alteraClienteDTO;
import br.com.zup.cliente.entity.Cliente;
import br.com.zup.cliente.exception.GlobalException;

public interface ClienteService {

	public Optional<Cliente> buscaCliente(String cpf) throws GlobalException;

	public List<Cliente> listaCientes();

	public Cliente adicionaCliente(ClienteDTO clienteDTO) throws GlobalException;

	public Optional<Cliente> alteraCliente(String cpf, alteraClienteDTO clienteDTO) throws GlobalException;

	public MensagemDTO removeCliente(String cpf) throws GlobalException;
}
