package br.com.zup.cliente.exception;

public class ClienteException extends Exception {

	private static final long serialVersionUID = 4592620498402206441L;

	private String mensagemErro;

	public ClienteException(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
