package br.com.zup.cliente.service;

import java.util.List;

import br.com.zup.cliente.dto.ClienteDTO;
import br.com.zup.cliente.dto.MensagemDTO;
import br.com.zup.cliente.dto.alteraClienteDTO;
import br.com.zup.cliente.entity.Cliente;

public interface ClienteService {

	public Cliente buscaCliente(String cpf);

	public List<Cliente> listaCientes();

	public Cliente adicionaCliente(ClienteDTO clienteDTO);

	public Cliente alteraCliente(String cpf, alteraClienteDTO clienteDTO);

	public MensagemDTO removeCliente(String cpf);
}
