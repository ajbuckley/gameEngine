package network;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Server extends Thread {

	Socket cSock;
	static int clients = 0;
	private static ArrayList<Server> clientList;
	int cNum;

	public Server(Socket cSock) {
		this.cSock = cSock;
		if (cSock != null) {
			cNum = clients;
			clients++;
		}

	}

	public void run() {

		System.out.println("Client connected");

		try {

			PrintWriter outputStream = new PrintWriter(cSock.getOutputStream(),
					true);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(cSock.getInputStream()));

			String message = inputStream.readLine();

			while (message != null) {
				System.out.println("Message recieved from client num: " + cNum
						+ ":\n" + message);
				outputStream.println("Your message \"" + message
						+ "\" has been recieved.");

				if (message.equalsIgnoreCase("Quit")) {
					System.out.println("Connection to client num: " + cNum
							+ " closed");
					break;
				}
				message = inputStream.readLine();
			}

			outputStream.close();
			inputStream.close();
			cSock.close();
		} catch (IOException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		clientList = new ArrayList<Server>();
		ServerSocket sSock = null;
		int i = 10000;
		Server l = new Server(null);
		Listener list = l.new Listener();
		(new Thread(list)).start();

		

	}

	private class Listener extends Thread {
		public Listener() {
			

		}
		
		public void run(){
			ServerSocket sSock = null;
			int i = 10000;

			try {

				sSock = new ServerSocket(i);

				try {

					while (true) {
						System.out.println("Listening for clients");
						Server listener = new Server(sSock.accept());
						listener.start();
						clientList.add(listener);
						i++;

					}
				} catch (IOException e) {
					System.out.println(e.toString());
					System.exit(1);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}

			try {
				sSock.close();
			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}
			
		}
	}
	
	

}
