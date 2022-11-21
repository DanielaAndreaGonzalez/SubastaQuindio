package co.edu.uniquindio.subastaQuinidio;

import java.io.IOException;

public class main {

	public static void main(String [] args)
	{
		
		
		Appservidor servidor = new Appservidor();
		
		try {
			servidor.correrServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	
	}
		
}
