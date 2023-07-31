package br.com.one.conversor.moeda.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import br.com.one.conversor.moeda.exception.MoedaNaoExisteException;
import br.com.one.conversor.moeda.interfaces.ICotacaoEmTempoReal;
import br.com.one.conversor.moeda.model.Cotacao;
import br.com.one.conversor.moeda.util.CotacaoEmTempoRealUtil;

/**
 * precisa rever a entender o que acontece aqui nessa classe
 */
public class CotacaoTempoRealService extends Cotacao implements ICotacaoEmTempoReal {
	private static final long serialVersionUID = 1L;
	// endpoint: "https://economia.awesomeapi.com.br/json/last/:moedas"
	// parametros: "BRL-USD,BRL-EUR,USD-BRL,EUR-BRL,GBP-BRL,ARS-BRL,CLP-BRL"
	private static final String webservice = "https://economia.awesomeapi.com.br/json/last/";
	private static final int statusCode = 200;
	private HttpURLConnection urlConexao;
	private Gson gson = new Gson();

	public JsonObject buscarCotacao() {
		return buscarCotacaoEmTempoReal("BRL-USD,BRL-EUR,BRL-GBP,USD-BRL,EUR-BRL,GBP-BRL,ARS-BRL,CLP-BRL");
	}

	/**
	 * Deve retornar a última ocorrência das moedas selecionadas em tempo real.
	 */
	@Override
	public JsonObject buscarCotacaoEmTempoReal(String avaliacoes) {
		String api = webservice + avaliacoes;

		try {
			URL url = new URL(api);
			this.urlConexao = (HttpURLConnection) url.openConnection();
			this.urlConexao.setRequestMethod("GET");

			if (this.urlConexao.getResponseCode() != statusCode) {
				throw new MoedaNaoExisteException("Código de Resposta: " + this.urlConexao.getResponseCode());
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.urlConexao.getInputStream()));

			return deserializarResponseBody(bufferedReader);
		} catch (IOException exception) {
			exception.printStackTrace();
			throw new IllegalStateException("Erro ao acessar os dados. " + exception.getMessage());

		}
	}

	private JsonObject deserializarResponseBody(BufferedReader bufferedReader) throws IOException {
		String jsonString = CotacaoEmTempoRealUtil.deserializar(bufferedReader);
		return this.gson.fromJson(jsonString, JsonObject.class);
	}
}
