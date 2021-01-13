package br.com.zup.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.zup.cliente.Cliente;
import br.com.zup.dao.ClienteDao;
import br.com.zup.exception.ClienteException;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ClienteDao clienteDao = new ClienteDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		String cpf = request.getParameter("cpf");

		if (cpf != null) {

			try {
				Cliente cliente = clienteDao.buscaCliente(cpf);
				writer.println(cliente);

			} catch (ClienteException e) {
				e.printStackTrace();
				writer.println(e.getMensagemErro());

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (cpf == null) {

			List<Cliente> clientes = new ArrayList<Cliente>();
			try {
				clientes = clienteDao.listaClientes();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			for (Cliente clienteExistente : clientes) {
				writer.println(clienteExistente);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		Cliente cliente = new Cliente();

		cliente.setNome(request.getParameter("nome"));
		cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
		cliente.setCpf(request.getParameter("cpf"));
		cliente.setEmail(request.getParameter("email"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setEndereco(request.getParameter("endereco"));

		try {
			clienteDao.adicionaCliente(cliente);
			writer.println("Cliente Salvo Com Sucesso");

		} catch (ClienteException e) {
			e.printStackTrace();
			writer.println(e.getMensagemErro());
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Cliente cliente = new Cliente();

		String cpf = request.getParameter("cpf");

		cliente.setNome(request.getParameter("nome"));
		cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
		cliente.setEmail(request.getParameter("email"));
		cliente.setTelefone(request.getParameter("telefone"));
		cliente.setEndereco(request.getParameter("endereco"));

		try {
			clienteDao.alteraCliente(cpf, cliente);
			writer.println("Cadastro alterado com sucesso!");

		} catch (ClienteException e) {
			e.printStackTrace();
			writer.println(e.getMensagemErro());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();

		String cpf = request.getParameter("cpf");

		try {
			clienteDao.removeCliente(cpf);
			writer.println("Removido com sucesso!");

		} catch (ClienteException e) {
			e.printStackTrace();
			writer.println(e.getMensagemErro());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
