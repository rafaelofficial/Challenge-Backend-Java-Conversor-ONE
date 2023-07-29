package br.com.one.conversor.moeda.exception;

public class MoedaNaoExisteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MoedaNaoExisteException(String mensagem) {
		super("Moeda não existe. " + mensagem);
	}
}
