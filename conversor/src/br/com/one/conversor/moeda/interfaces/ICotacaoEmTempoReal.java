package br.com.one.conversor.moeda.interfaces;

import java.io.IOException;

import br.com.one.conversor.moeda.model.ConversorDeMoeda;

public interface ICotacaoEmTempoReal {

	ConversorDeMoeda buscarCotacaoEmTempoReal(String moeda) throws IOException;
}
