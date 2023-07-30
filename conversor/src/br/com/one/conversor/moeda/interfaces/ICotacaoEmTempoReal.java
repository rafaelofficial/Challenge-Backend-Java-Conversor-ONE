package br.com.one.conversor.moeda.interfaces;

import java.io.IOException;

import br.com.one.conversor.moeda.model.MoedaModel;

public interface ICotacaoEmTempoReal {

	MoedaModel buscarCotacaoEmTempoReal(String moeda) throws IOException;
}
