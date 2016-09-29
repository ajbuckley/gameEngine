package network;

import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws IOException {

		String IP = new String("127.0.0.1");

		/**
		 * Setup input and output stream for the socket
		 */
		Socket sSock = null;
		PrintWriter toServer = null;
		BufferedReader in = null;
		int i = 10000;
		boolean connected = false;
		while (!connected) {
			try {
				sSock = new Socket(IP, i);
				connected = true;
				toServer = new PrintWriter(sSock.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						sSock.getInputStream()));

			} catch (UnknownHostException e) {
				i++;
				if (i > 11000){
					break;
				}

			} catch (IOException e) {
				System.out.println(e.toString());
				System.exit(1);
			}
		}
		if (!connected){
			System.out.println("Unable to connect");
			System.exit(1);
		}

		/**
		 * Setup input stream for the user
		 */
		BufferedReader user = new BufferedReader(new InputStreamReader(
				System.in));

		/**
	 * 
	 * 
	 */
		System.out.println("Send message or \"quit\"");
		String message = user.readLine();

		while (message != null) {

			toServer.println(message);

			if (message.equalsIgnoreCase("quit")) {
				break;
			}

			System.out.println(in.readLine());
			System.out.println("Send message or \"quit\"");
			message = user.readLine();
		}

		toServer.close();
		in.close();
		user.close();
		sSock.close();
	}
}
