package serverSide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTranslationApp {
	public static void main (String [] args) throws IOException{
		ServerSocket serverSocket = null;
		
		try {
			
			TranslationLanguage translation = new TranslationLanguage();
			
			//Bind Serversocket to a port
			int portNo = 4229;
			serverSocket = new ServerSocket(portNo);

			System.out.println("Waiting for request");
			
			while(true) {
				
				//Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				
				//Process client request - read the text and language
				DataInputStream dis = new DataInputStream(
						clientSocket.getInputStream());
				String text = dis.readUTF();
				int language = dis.readInt();
				
				//Translate the text from client
				String translatedText = translation.translateText(text, language);
				
				String message = "Text get from client: " + text + "\nTranslated: " + translatedText;
				System.out.println(message);
				
				//Create stream to write data on the network
				DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
				
				//Send current date back to the client
				dos.writeUTF(translatedText);
				
				//Close the socket
				clientSocket.close();
				dis.close();
				dos.close();
				
			}
		} catch (IOException ioe) {
			if (serverSocket != null)
				serverSocket.close();
			
			ioe.printStackTrace();
		}
	}
}
