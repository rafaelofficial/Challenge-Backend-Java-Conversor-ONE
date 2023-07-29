package br.com.one.conversor.moeda.interfaces;

import java.io.IOException;

import br.com.one.conversor.moeda.model.ConversorDeMoedaModel;

public interface ICotacaoEmTempoReal {

	ConversorDeMoedaModel buscarCotacaoEmTempoReal(String moeda) throws IOException;
}
