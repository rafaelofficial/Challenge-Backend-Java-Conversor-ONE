package br.com.one.conversor.moeda.services;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

import br.com.one.conversor.moeda.exception.ConversaoNaoExiste;

public class ConversorMoedaService {

	private JsonObject taxaCambio;

	public ConversorMoedaService() {
		CotacaoTempoRealService cotacaoService = new CotacaoTempoRealService();
		this.taxaCambio = cotacaoService.buscarCotacao();
	}

	private String formatadorMoeda(Locale locale, double moeda) {
		return NumberFormat.getCurrencyInstance(locale).format(moeda);
	}

	private double converterMoedas(String paresDeMoedas, double valor) {
		if (Objects.isNull(paresDeMoedas)) {
			JOptionPane.showMessageDialog(null, "Conversão não existe, tente novamente", null,
					JOptionPane.ERROR_MESSAGE);
			throw new ConversaoNaoExiste("Conversão não existe");
		}

		double taxaCambioApi = this.taxaCambio.getAsJsonObject(paresDeMoedas.replace("-", "")).get("ask").getAsDouble();
		return Math.round((valor * taxaCambioApi * 100d)) / 100;
	}

	/** converte Real para outras moedas */

	public void conversorRealDolar(double valor) {
		double realParaDolar = valor / converterMoedas("BRL-USD", valor);
		String formatadorMoeda = formatadorMoeda(Locale.US, realParaDolar);

		JOptionPane.showMessageDialog(null, "O valor da conversão é de " + formatadorMoeda + " Dólar Americano",
				"Menssagem", JOptionPane.INFORMATION_MESSAGE);
	}

	public void conversorRealEuro(double valor) {
		double realEuro = valor / converterMoedas("BRL-EUR", valor);
		String formatadorMoeda = formatadorMoeda(Locale.FRANCE, realEuro);

		JOptionPane.showMessageDialog(null, "O valor da conversão é de " + formatadorMoeda + " Euro", "Menssagem",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void conversorRealLibra(double valor) {
		double realLibra = valor / converterMoedas("BRL-GBP", valor);
		String formatadorMoeda = formatadorMoeda(Locale.UK, realLibra);

		JOptionPane.showMessageDialog(null, "O valor da conversão é de " + formatadorMoeda + " Libras Britânica",
				"Menssagem", JOptionPane.INFORMATION_MESSAGE);

	}

	/** converte outras moedas para Real */

	public void conversorDolarReal(double valor) {
		double dolarReal = valor / converterMoedas("USD-BRL", valor);
		String formatadorMoeda = formatadorMoeda(Locale.US, dolarReal);

		JOptionPane.showMessageDialog(null, "O valor da conversão é de R" + formatadorMoeda + " Real Brasileiro",
				"Menssagem", JOptionPane.INFORMATION_MESSAGE);
	}

}
