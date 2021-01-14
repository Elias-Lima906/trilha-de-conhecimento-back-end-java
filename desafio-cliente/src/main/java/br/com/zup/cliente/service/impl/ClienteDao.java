package br.com.zup.cliente.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.zup.cliente.entity.Cliente;
import br.com.zup.cliente.exception.ClienteException;

public class ClienteDao {

	private Connection connection;

	public void adicionaCliente(Cliente novoCliente) throws ClienteException {

		String sqlInsert = "insert into clientes" + "(nome, idade, cpf, email, telefone, endereco) "
				+ "values (?, ?, ?, ?, ?, ?);";

		try {

			PreparedStatement stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, novoCliente.getNome());
			stmt.setInt(2, novoCliente.getIdade());
			stmt.setString(3, novoCliente.getCpf());
			stmt.setString(4, novoCliente.getEmail());
			stmt.setString(5, novoCliente.getTelefone());
			stmt.setString(6, novoCliente.getEndereco());

			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClienteException("CLIENTE J� EXISTE NO BANCO DE DADOS!");
		}

	}

	public void alteraCliente(String cpf, Cliente clienteASerAlterado) throws ClienteException, SQLException {

		Cliente cliente = verificaExistenciaCliente(cpf);

		if (cliente != null) {

			String sqlConsulta = "update clientes set nome=?, idade=?, cpf=?, email=?, telefone=?, endereco=? where cpf = ?";

			PreparedStatement stmt = connection.prepareStatement(sqlConsulta);

			stmt.setString(1, clienteASerAlterado.getNome());
			stmt.setInt(2, clienteASerAlterado.getIdade());
			stmt.setString(3, cpf);
			stmt.setString(4, clienteASerAlterado.getEmail());
			stmt.setString(5, clienteASerAlterado.getTelefone());
			stmt.setString(6, clienteASerAlterado.getEndereco());
			stmt.setString(7, cpf);

			stmt.execute();
			stmt.close();

			return;
		}

		throw new ClienteException("CLIENTE N�O ENCONTRADO!");
	}

	public Cliente buscaCliente(String cpf) throws ClienteException, SQLException {

		Cliente cliente = this.verificaExistenciaCliente(cpf);

		if (cliente != null) {
			return cliente;
		}

		throw new ClienteException("\nCLIENTE N�O ENCONTRADO!\n");
	}

	public List<Cliente> listaClientes() throws SQLException {

		String sqlConsulta = "select * from clientes";

		PreparedStatement stmt = connection.prepareStatement(sqlConsulta);

		List<Cliente> clientes = populaListaDeClientesASerListada(stmt);

		return clientes;
	}

	public void removeCliente(String cpf) throws ClienteException, SQLException {

		if (this.verificaExistenciaCliente(cpf) != null) {

			String sqlConsulta = "delete from clientes where cpf = ?;";

			PreparedStatement stmt = connection.prepareStatement(sqlConsulta);

			stmt.setString(1, cpf);

			stmt.execute();
			stmt.close();
			return;
		}

		throw new ClienteException("\nCLIENTE N�O ENCONTRADO PARA REMOVER!\n");
	}

	private Cliente verificaExistenciaCliente(String cpf) throws SQLException {

		String sqlConsulta = "select * from clientes where cpf = ?";

		PreparedStatement stmt = connection.prepareStatement(sqlConsulta);

		stmt.setString(1, cpf);

		Cliente cliente = this.populaInformacoesClienteBuscadoPorId(stmt);

		return cliente;
	}

	private Cliente populaInformacoesClienteBuscadoPorId(PreparedStatement stmt) throws SQLException {

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {

			Cliente cliente = new Cliente();

			cliente.setNome(rs.getString("nome"));
			cliente.setIdade(rs.getInt("idade"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setEmail(rs.getString("email"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEndereco(rs.getString("endereco"));

			return cliente;
		}

		return null;
	}

	private List<Cliente> populaListaDeClientesASerListada(PreparedStatement stmt) throws SQLException {

		List<Cliente> clientes = new ArrayList<Cliente>();

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			Cliente cliente = new Cliente();

			cliente.setNome(rs.getString("nome"));
			cliente.setIdade(rs.getInt("idade"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setEmail(rs.getString("email"));
			cliente.setTelefone(rs.getString("telefone"));
			cliente.setEndereco(rs.getString("endereco"));

			clientes.add(cliente);
		}

		return clientes;
	}
}