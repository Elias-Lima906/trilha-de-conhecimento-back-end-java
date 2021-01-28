package br.com.zup.cm.exception;

public class GlobalException extends Exception {

	private static final long serialVersionUID = 4592620498402206441L;

	private String mensagemErro;

	public GlobalException(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
}
