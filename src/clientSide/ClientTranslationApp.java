package clientSide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTranslationApp {
	public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 4229);

            //Get text from user
            String text = getText();

            //Get language from user
            int language = getLanguage();

            //Send data to the server
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(text);
            dos.writeInt(language);

            //Get data from the server
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String translatedText = dis.readUTF();
            System.out.println("\nTranslated text: " + translatedText);

            // Close everything
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Client choose the language
    private static int getLanguage() {
    	
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type of language");
        System.out.println("1. Bahasa Malaysia");
        System.out.println("2. Arabic");
        System.out.println("3. Korean");
        System.out.print("Type of translated language: ");

        int language = scanner.nextInt();
        scanner.close();
        return language;
    }

    //Client choose which text to be translate
    private static String getText() {
 
        System.out.println("Text to translate");
        System.out.println("1. Good morning");
        System.out.println("2. Good night");
        System.out.println("3. How are you?");
        System.out.println("4. Thank you");
        System.out.println("5. Goodbye");
        System.out.println("6. What's up?");
        System.out.print("Text to be translated: ");

        Scanner scanner = new Scanner(System.in);

        String text = "";
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                text = "Good morning";
                break;
            case 2:
                text = "Good night";
                break;
            case 3:
                text = "How are you?";
                break;
            case 4:
                text = "Thank you";
                break;
            case 5:
                text = "Goodbye";
                break;
            case 6:
                text = "What's up?";
                break;
            default:
                break;
        }

        scanner.close();
        return text;
    }
}
