package br.com.one.conversor.moeda.util;

import java.io.BufferedReader;
import java.io.IOException;

public class CotacaoEmTempoRealUtil {

	private static String responseBody;

	public static String deserializar(BufferedReader bufferedReader) throws IOException {	
		String jsonString = "";
		
		while ((setResponseBody(bufferedReader.readLine())) != null) {
			setResponseBody(getResponseBody() + jsonString);
		}
		
		return jsonString;
	}

	public static String getResponseBody() {
		return responseBody;
	}

	public static String setResponseBody(String responseBody) {
		CotacaoEmTempoRealUtil.responseBody = responseBody;
		return responseBody;
	}
}
