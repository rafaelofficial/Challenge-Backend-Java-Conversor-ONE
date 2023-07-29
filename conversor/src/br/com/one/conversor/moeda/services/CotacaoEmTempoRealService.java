package br.com.one.conversor.moeda.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import br.com.one.conversor.moeda.exception.MoedaNaoExisteException;
import br.com.one.conversor.moeda.interfaces.ICotacaoEmTempoReal;
import br.com.one.conversor.moeda.model.ConversorDeMoeda;
import br.com.one.conversor.moeda.util.CotacaoEmTempoRealUtil;

/**
 * precisa rever a entender o que acontece aqui nessa classe
 */
public class CotacaoEmTempoRealService implements ICotacaoEmTempoReal {
	// "https://economia.awesomeapi.com.br/json/last/:moedas"
	// "USD-BRL,EUR-BRL,GBP-BRL,ARS-BRL,CLP-BRL"
	private static final String webservice = "https://economia.awesomeapi.com.br/json/last/";
	private static final int statusCode = 200;
	private HttpURLConnection urlConexao;

	public ConversorDeMoeda buscarCotacao(String tipoDeMoeda) throws IOException {
		return buscarCotacaoEmTempoReal(tipoDeMoeda);
	}

	/**
	 * Deve retornar a última ocorrência das moedas selecionadas em tempo real.
	 */
	@Override
	public ConversorDeMoeda buscarCotacaoEmTempoReal(String avaliacao) throws IOException {

		String api = webservice + avaliacao;

		try (BufferedReader responseBody = new BufferedReader(
				new InputStreamReader(this.urlConexao.getInputStream()))) {

			URL url = new URL(api);
			this.urlConexao = (HttpURLConnection) url.openConnection();

			if (this.urlConexao.getResponseCode() != statusCode) {
				throw new MoedaNaoExisteException("Código de Resposta: " + this.urlConexao.getResponseCode());
			}

			return deserializarResponseBody(responseBody);
		}
	}

	private ConversorDeMoeda deserializarResponseBody(BufferedReader responseBody) throws IOException {
		Gson gson = new Gson();
		String jsonString = CotacaoEmTempoRealUtil.deserializar(responseBody);

		return gson.fromJson(jsonString, ConversorDeMoeda.class);
	}
}
