package br.com.one.conversor.moeda.model;

import java.io.Serializable;

public class ConversorDeMoedaModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String moedas;
		
	public String getMoedas() {
		return moedas;
	}
	
	public void setMoedas(String moedas) {
		this.moedas = moedas;
	}
	
	@Override
	public String toString() {
		return "Moeda: " + this.moedas;
	}
}
