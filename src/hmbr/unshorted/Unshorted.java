package hmbr.unshorted;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Unshorted {

	public static String discover(String url) {
		String retorno = null;
		try {
			retorno = un(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	private static String un(URL url) {
		String retorno = null;
		if (url != null) {
			try {
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				/*
				 * tudo chama o getInputStream e ele atualiza a url no caso de
				 * redirect
				 */
				conn.getInputStream();
				
				if (conn.getURL().equals(url)){
					retorno = conn.getURL().toString();	
					conn.disconnect();
				}else{
					conn.disconnect();
					return un(conn.getURL());
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return retorno;
	}
}
