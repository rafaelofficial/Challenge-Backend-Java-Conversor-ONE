package br.com.one.conversor.moeda.exception;

public class ConversaoNaoExiste extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConversaoNaoExiste(String mensagem) {
		super(mensagem);
	}
}
