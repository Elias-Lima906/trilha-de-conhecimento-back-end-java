//package br.com.zup.main;
//
//import java.util.List;
//import java.util.Scanner;
//
//import br.com.zup.cliente.Cliente;
//import br.com.zup.dao.ClienteDao;
//import br.com.zup.exception.ClienteException;
//
//public class ProgramaPrincipal {
//
//	public static void menuInicial() {
//		System.out.println("\nSeja bem vindo a plataforma de cadastro de clientes!");
//		System.out.println("Para começar escolhar uma das opções abaixo:\n");
//		System.out.println("(1) - Cadastrar cliente;");
//		System.out.println("(2) - Alterar cliente;");
//		System.out.println("(3) - Buscar um cliente;");
//		System.out.println("(4) - Listar clientes;");
//		System.out.println("(5) - Remover cliente;");
//		System.out.println("(0) - Sair;");
//	}
//
//	public static void adicionaCliente(Scanner teclado, ClienteDao clienteDao) {
//		Cliente cliente = new Cliente();
//		System.out.println("\nPreencha as informações abaixo:");
//
//		teclado.nextLine();
//
//		System.out.print("\nNome: ");
//		String nome = teclado.nextLine();
//
//		System.out.print("\nCpf: ");
//		String cpf = teclado.nextLine();
//
//		System.out.print("\nEmail: ");
//		String email = teclado.nextLine();
//
//		System.out.print("\nIdade: ");
//		String telefone = teclado.next();
//
//		System.out.print("\nIdade: ");
//		String endereco = teclado.next();
//
//		System.out.print("\nIdade: ");
//		int idade = teclado.nextInt();
//
//		cliente.setNome(nome);
//		cliente.setCpf(cpf);
//		cliente.setEmail(email);
//		cliente.setTelefone(telefone);
//		cliente.setEndereco(endereco);
//		cliente.setIdade(idade);
//
//		try {
//			clienteDao.adicionaCliente(cliente);
//			System.out.println("\nCLIENTE ADICIONADO COM SUCESSO!\n");
//		} catch (ClienteException e) {
//			System.err.println(e.getMensagemErro());
//		}
//	}
//
//	public static void alteraCliente(Scanner teclado, ClienteDao clienteDao) {
//		Cliente cliente = new Cliente();
//		System.out.println("Digite o cpf do cliente a ser alterado:");
//
//		System.out.print("\nCPF: ");
//		String cpf = teclado.next();
//
//		try {
//			if (clienteDao.verificaCliente(cpf) == false) {
//				System.err.println("\nCLIENTE NÃO EXISTE PARA SER ALTERADO!\n");
//				return;
//			}
//		} catch (ClienteException e) {
//			System.err.println(e.getMensagemErro());
//			return;
//		}
//
//		teclado.nextLine();
//
//		System.out.println("\nAgora Digite as Novas informações Do Cliente:");
//
//		System.out.print("\nNome: ");
//		String nome = teclado.nextLine();
//
//		System.out.print("\n\nEmail: ");
//		String email = teclado.nextLine();
//
//		System.out.print("\nIdade: ");
//		String telefone = teclado.next();
//
//		System.out.print("\nIdade: ");
//		String endereco = teclado.next();
//
//		System.out.print("\n\nIdade: ");
//		int idade = teclado.nextInt();
//
//		cliente.setNome(nome);
//		cliente.setEmail(email);
//		cliente.setTelefone(telefone);
//		cliente.setEndereco(endereco);
//		cliente.setIdade(idade);
//
//		try {
//			clienteDao.alteraCliente(cpf, cliente);
//			System.out.println("\nCadastro alterado com sucesso!\n");
//		} catch (ClienteException e) {
//			System.err.println(e.getMensagemErro());
//		}
//	}
//
//	public static void buscaCliente(Scanner teclado, ClienteDao clienteDao) {
//		Cliente clienteConsultado = new Cliente();
//		System.out.println("\nDigite o cpf referente ao cliente que deseja buscar: ");
//
//		System.out.print("\nCpf: ");
//		String cpf = teclado.next();
//
//		try {
//			clienteConsultado = clienteDao.buscaCliente(cpf);
//		} catch (ClienteException e) {
//			System.err.println(e.getMensagemErro());
//		}
//
//		System.out.println(clienteConsultado);
//	}
//
//	public static void listaCliente(ClienteDao clienteDao) {
//		List<Cliente> clientes = clienteDao.listaClientes();
//
//		for (Cliente cliente : clientes) {
//			System.out.println(cliente);
//		}
//	}
//
//	public static void removeCliente(Scanner teclado, ClienteDao clienteDao) {
//
//		System.out.println("\nDigite o cpf referente ao cliente que deseja remover: ");
//
//		System.out.print("\nCpf: ");
//		String cpf = teclado.next();
//
//		try {
//			clienteDao.removeCliente(cpf);
//			System.out.println("\nCliente removido com sucesso!\n");
//		} catch (ClienteException e) {
//			System.err.println(e.getMensagemErro());
//		}
//
//	}
//
//	public static void main(String[] args) {
//		ClienteDao clienteDao = new ClienteDao();
//		Scanner teclado = new Scanner(System.in);
//		int opcao = 0;
//
//		do {
//			menuInicial();
//
//			System.out.print("\nOpção: ");
//			opcao = teclado.nextInt();
//
//			switch (opcao) {
//			case 1:
//				adicionaCliente(teclado, clienteDao);
//				break;
//
//			case 2:
//				alteraCliente(teclado, clienteDao);
//				break;
//
//			case 3:
//				buscaCliente(teclado, clienteDao);
//				break;
//
//			case 4:
//				listaCliente(clienteDao);
//				break;
//
//			case 5:
//				removeCliente(teclado, clienteDao);
//				break;
//
//			case 0:
//				System.out.println("\nAté a proxima!!!");
//				break;
//
//			default:
//				System.out.println("\nOpção inválida!");
//			}
//
//		} while (opcao != 0);
//	}
//}
