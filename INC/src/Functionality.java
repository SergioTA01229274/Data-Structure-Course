import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Functionality {

	public void saveLogInData(SignUp signUp, String file, String path) {
		try {
			FileWriter fw = new FileWriter(path + "\\" + file);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(signUp.getHName() + "/" + signUp.getHPhoneNumber() + "/" + signUp.getHAddress() + "/" + signUp.getHPostalCode() + "/" + signUp.getHUserId() + "/" + signUp.getHPassword());
			pw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Hubo un error al intentar guardar los datos de registro");
		}
	}
	
	public boolean verifyDocument(String document, String path) throws IOException{
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(path + "\\" + document));  
			linea = br.readLine();
			if(linea == null) {
				br.close();
				return false;
			}
			br.close();
			return true;
		}
		catch(IOException e) {
			throw new IOException();
		}
	}
	public String obtainDonorData(String document, String path) throws IOException{
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(path + "\\" + document));  
			linea = br.readLine();
			String[] Donors = linea.split(" ");
			String DonorId1 = Donors[0];
			br.close();
			String phoneNumber = DonorId1.substring(0, 11);
			return phoneNumber;
		}catch(IOException e) {
			throw new IOException();
		}
	}
	public String obtainAddress(String document, String path) throws IOException{
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(path + "\\" + document));  
			linea = br.readLine();
			String[] HospitalData = linea.split("/");
			String address = HospitalData[2];
			br.close();
			return address;
		}catch(IOException e) {
			throw new IOException();
		}
	}
	public String obtainHospitalName(String document, String path) throws IOException{
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(path + "\\" + document));  
			linea = br.readLine();
			String[] HospitalData = linea.split("/");
			String name = HospitalData[0];
			br.close();
			return name;
		}catch(IOException e) {
			throw new IOException();
		}
	}
	public String obtainPatientCurp(String PatientID) throws IOException{
		String curp = PatientID.substring(0, 19);
		return curp;
	}
	public String obtainUrgency(String PatientID) throws IOException{
		String urgency = PatientID.substring(19, 21);
		return urgency;
	}
	
	
	public void savePerson(String document, String path, String curp, String urgency, String bloodType) throws IOException{
		try {
			FileWriter fw = new FileWriter(path + "\\" + document, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(curp + urgency + bloodType + " ");
			pw.close();
		} catch (IOException e) {
			throw new IOException();
		}
	}
	public void savePerson2(String document, String path, String phoneNumber, String sex, String bloodType) throws IOException{
		try {
			FileWriter fw = new FileWriter(path + "\\" + document, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(phoneNumber + sex + bloodType + " ");
			pw.close();
		} catch (IOException e) {
			throw new IOException();
		}
	}
	
	public boolean verifyAccount(String document, String path, String UserID, String Password) throws IOException{
		try {
			String linea;
			BufferedReader br = new BufferedReader(new FileReader(path + "\\" + document));  
			linea = br.readLine();
			if(linea != null) {
				String[] elements = linea.split("/");
				if(UserID.compareTo(elements[4]) == 0 && Password.compareTo(elements[5]) == 0) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		catch(IOException e) {
			throw new IOException();
		}
	}
	
	public void sendMessage(String donorPhoneNumber, String patient, String address, ProjectWindow window, String hospitalName, String bloodType, String Urgency, String Quantity) throws Exception{
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
		parametersList.add(new BasicNameValuePair("dest", "523317536289"));
		parametersList.add(new BasicNameValuePair("msg", "El hospital " + hospitalName + " solicita sangre tipo " + bloodType + " para el paciente " + patient));
		parametersList.add(new BasicNameValuePair("senderId", "TestAltiria"));
		
		try {
			 //Se fija la codificacion de caracteres de la peticion POST
			 post.setEntity(new UrlEncodedFormEntity(parametersList,"UTF-8"));
			}
			catch(UnsupportedEncodingException uex) {
			 throw new Exception();
			}
			 
			CloseableHttpResponse response = null;
			 
			try {
			 System.out.println("Enviando petición");
			 //Se envía la petición
			 response = httpClient.execute(post);
			 //Se consigue la respuesta
			 String resp = EntityUtils.toString(response.getEntity());
			//Error en la respuesta del servidor
			 if (response.getStatusLine().getStatusCode()!=200){
			 System.out.println("ERROR: Código de error HTTP: " + response.getStatusLine().getStatusCode());
			 System.out.println("Compruebe que ha configurado correctamente la direccion/url ");
			 System.out.println("suministrada por Altiria");
			 return;
			 }else {
			 //Se procesa la respuesta capturada en la cadena 'response'
			 if (resp.startsWith("ERROR")){
			 System.out.println(resp);
			 System.out.println("Código de error de Altiria. Compruebe las especificaciones");
			 } else
			 System.out.println(resp);
			 }
			 }
			 catch (Exception e) {
				 e.printStackTrace();
				 throw new Exception();
			 
			 //return;
			 }
			finally {
				 //En cualquier caso se cierra la conexión
				 post.releaseConnection();
				 if(response!=null) {
				 try {
				 response.close();
				 }
				 catch(IOException ioe) {
					 throw new Exception();
				 //System.out.println("ERROR cerrando recursos");
				 }
				 }
				 }
	}
	
	public static void main(String[] args) {
		
	}
}
