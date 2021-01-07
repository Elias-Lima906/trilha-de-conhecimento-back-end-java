package br.com.zup.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.cliente.Cliente;
import br.com.zup.exception.ClienteException;

public class ClienteDao {
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public ClienteDao() {

	}

	public void adicionaCliente(Cliente novoCliente) throws ClienteException {

		for (Cliente cliente : clientes) {
			if (cliente != null && cliente.getCpf().equals(novoCliente.getCpf())) {
				throw new ClienteException("\nCLIENTE JÁ CADASTRADO\n!");
			}
		}

		clientes.add(novoCliente);
	}

	public boolean verificaCliente(String cpf) throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("\nNÃO HÁ CADASTROS NO BANCO DE DADOS!\n");
		}

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				return true;
			}
		}
		return false;
	}

	public void alteraCliente(String cpf, Cliente cliente) throws ClienteException {

		Cliente clienteAlterado = new Cliente();

		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getCpf().equals(cpf)) {
				clienteAlterado = clientes.get(i);

				clienteAlterado.setEmail(cliente.getEmail());
				clienteAlterado.setNome(cliente.getNome());
				clienteAlterado.setIdade(cliente.getIdade());
				clienteAlterado.setTelefone(cliente.getTelefone());
				clienteAlterado.setEndereco(cliente.getEndereco());
				clienteAlterado.setCpf(cpf);

				clientes.remove(clientes.get(i));
				clientes.add(clienteAlterado);
			}
		}
	}

	public Cliente buscaCliente(String cpf) throws ClienteException {

		if (clientes.size() == 0) {
			throw new ClienteException("\nNÃO HÁ CADASTROS NO BANCO DE DADOS!\n");
		}

		for (int i = 0; i < clientes.size(); i++) {

			if (clientes.get(i).getCpf().equals(cpf)) {
				return clientes.get(i);
			}
		}

		throw new ClienteException("\nCLIENTE NÃO ENCONTRADO!\n");
	}

	public List<Cliente> listaClientes() {
		return clientes;
	}

	public void removeCliente(String cpf) throws ClienteException {

		for (int i = 0; i < clientes.size(); i++) {

			if (cpf.equals(clientes.get(i).getCpf())) {
				clientes.remove(clientes.get(i));
				return;
			}
		}

		throw new ClienteException("\nCLIENTE NÃO ENCONTRADO PARA REMOVER!\n");
	}
}