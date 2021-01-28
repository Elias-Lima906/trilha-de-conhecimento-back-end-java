package br.com.zup.cm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {

	@CPF
	private String cpf;

	@NotBlank(message = "O campo NOME precisa ser preenchido.")
	private String nome;

	@NotNull(message = "O campo IDADE precisa ser preenchido.")
	private int idade;

	@NotBlank(message = "O campo EMAIL precisa ser preenchido.")
	@Email(message = "O EMAIL informado precisa ser válido.")
	private String email;

	@NotBlank(message = "O campo TELEFONE precisa ser preenchido.")
	@Size(max = 11, message = "O TELEFONE pode ter no máximo 11 dígitos.")
	private String telefone;

	@NotBlank(message = "O campo ENDEREÇO precisa ser preenchido.")
	private String endereco;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
