package co.edu.uniquindio.subastaQuinidio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Appservidor {
	
	
	ServerSocket serverComunicacion;
	ServerSocket serverTranferencia;
	private ObjectInputStream flujoEntradaObjeto;
	private ObjectOutputStream flujoSalidaObjeto;
	private DataInputStream flujoEntradaComunicacion;
	private DataOutputStream flujoSalidaComunicacion;
	private DataInputStream flujoEntradaLoginComunicacion;
	private DataOutputStream flujoSalidaLoginComunicacion;
	

	public void correrServer() throws IOException {
		// TODO Auto-generated method stub
		serverComunicacion = new ServerSocket(9998);
		serverTranferencia = new ServerSocket(9999);
		
		while(true) {
			System.out.println("SERVIDOR INCIADO");
			
			Socket socketComunicacion = null;
			Socket socketTranferencia = null;
			socketComunicacion = serverComunicacion.accept();
			socketTranferencia = serverTranferencia.accept();
			
			System.out.println("conexion establecida");
			flujoEntradaComunicacion = new DataInputStream(socketComunicacion.getInputStream());
			flujoSalidaComunicacion = new DataOutputStream(socketComunicacion.getOutputStream());
			flujoSalidaObjeto = new ObjectOutputStream(socketTranferencia.getOutputStream());
			flujoEntradaObjeto = new ObjectInputStream(socketTranferencia.getInputStream());			
			
			
			iniciarHiloClienteServidor();	
		}
	}
	
	
	private void iniciarHiloClienteServidor() {
		// TODO Auto-generated method stub
		HiloDesdeClienteAlServidor hiloClienteS = new HiloDesdeClienteAlServidor();
		hiloClienteS.inicializar(flujoEntradaComunicacion, flujoSalidaComunicacion, flujoEntradaObjeto, flujoSalidaObjeto, this);
		hiloClienteS.start();
	}


}

