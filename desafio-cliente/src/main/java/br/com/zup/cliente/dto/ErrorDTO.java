package br.com.zup.cliente.dto;

public class ErrorDTO {

	private String mensagemErro;

	public ErrorDTO(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

}
