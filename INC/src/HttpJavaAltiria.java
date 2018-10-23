
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpJavaAltiria {
	public static void main(String[] args) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(60000).build();
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultRequestConfig(config);
		CloseableHttpClient httpClient = builder.build();
		HttpPost post = new HttpPost("http://www.altiria.net/api/http");
		List parametersList = new ArrayList ();
		parametersList.add(new BasicNameValuePair("cmd", "sendsms"));
		parametersList.add(new BasicNameValuePair("domainId", "test"));
		parametersList.add(new BasicNameValuePair("login", "alfonso.reyes199x"));
		parametersList.add(new BasicNameValuePair("passwd", "8jrbhme9"));
		parametersList.add(new BasicNameValuePair("dest", "523317536289")); //celular de cristy
		//parametersList.add(new BasicNameValuePair("dest", "523339528687")); //celular de cristy
		parametersList.add(new BasicNameValuePair("msg", "Como estas"));
		parametersList.add(new BasicNameValuePair("senderId", "TestAltiria"));
		
		try {
			 //Se fija la codificacion de caracteres de la peticion POST
			 post.setEntity(new UrlEncodedFormEntity(parametersList,"UTF-8"));
			}
			catch(UnsupportedEncodingException uex) {
			 System.out.println("ERROR: codificaci�n de caracteres no soportada");
			}
			 
			CloseableHttpResponse response = null;
			 
			try {
			 System.out.println("Enviando petici�n");
			 //Se env�a la petici�n
			 response = httpClient.execute(post);
			 //Se consigue la respuesta
			 String resp = EntityUtils.toString(response.getEntity());
			//Error en la respuesta del servidor
			 if (response.getStatusLine().getStatusCode()!=200){
			 System.out.println("ERROR: C�digo de error HTTP: " + response.getStatusLine().getStatusCode());
			 System.out.println("Compruebe que ha configurado correctamente la direccion/url ");
			 System.out.println("suministrada por Altiria");
			 return;
			 }else {
			 //Se procesa la respuesta capturada en la cadena 'response'
			 if (resp.startsWith("ERROR")){
			 System.out.println(resp);
			 System.out.println("C�digo de error de Altiria. Compruebe las especificaciones");
			 } else
			 System.out.println(resp);
			 }
			 }
			 catch (Exception e) {
			 System.out.println("Excepci�n");
			 e.printStackTrace();
			 return;
			 }
			finally {
				 //En cualquier caso se cierra la conexi�n
				 post.releaseConnection();
				 if(response!=null) {
				 try {
				 response.close();
				 }
				 catch(IOException ioe) {
				 System.out.println("ERROR cerrando recursos");
				 }
				 }
				 }
	}
}
