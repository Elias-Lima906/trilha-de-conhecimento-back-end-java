package br.com.zup.cliente;

public class Cliente {

	private String nome;
	private int idade;
	private String cpf;
	private String email;
	private String telefone;
	private String endereco;

	public Cliente() {
	}

	@Override
	public String toString() {
		return "\nNOME: " + nome + "\nIDADE: " + idade + "\nCPF: " + cpf + "\nEMAIL: " + email + "\nTELEFONE: "
				+ telefone + "\nENDEREÇO: " + endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
