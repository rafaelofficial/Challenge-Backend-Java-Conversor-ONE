package br.com.one.conversor.moeda.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import br.com.one.conversor.moeda.exception.MoedaNaoExisteException;
import br.com.one.conversor.moeda.interfaces.ICotacaoEmTempoReal;
import br.com.one.conversor.moeda.model.MoedaModel;
import br.com.one.conversor.moeda.util.CotacaoEmTempoRealUtil;

/**
 * precisa rever a entender o que acontece aqui nessa classe
 */
public class CotacaoEmTempoRealService implements ICotacaoEmTempoReal {
	// endpoint: "https://economia.awesomeapi.com.br/json/last/:moedas"
	// parametros: "BRL-USD,BRL-EUR,USD-BRL,EUR-BRL,GBP-BRL,ARS-BRL,CLP-BRL"
	private static final String webservice = "https://economia.awesomeapi.com.br/json/last/";
	private static final int statusCode = 200;
	private HttpURLConnection urlConexao;

	public MoedaModel buscarCotacao(String paresDeMoedas) {
		return buscarCotacaoEmTempoReal(paresDeMoedas);
	}

	/**
	 * Deve retornar a última ocorrência das moedas selecionadas em tempo real.
	 */
	@Override
	public MoedaModel buscarCotacaoEmTempoReal(String avaliacao) {
		String api = webservice + avaliacao;
		
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

	private MoedaModel deserializarResponseBody(BufferedReader bufferedReader) throws IOException {
		Gson gson = new Gson();
		String jsonString = CotacaoEmTempoRealUtil.deserializar(bufferedReader);

		return gson.fromJson(jsonString, MoedaModel.class);
	}
}
