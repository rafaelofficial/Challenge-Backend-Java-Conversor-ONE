package br.com.one.conversor.moeda.interfaces;

import java.io.IOException;

import com.google.gson.JsonObject;

public interface ICotacaoEmTempoReal {

	JsonObject buscarCotacaoEmTempoReal(String moeda) throws IOException;
}
