package com.tcpip;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serverSocket;
	Socket socket;
	
	// ������
	public Server() {}
	public Server(int port) {
		this.port = port;
	}
	
	// client �� ���� ������ �� Receiver �� �۵� �ǵ��� �� ��
	class Receiver extends Thread{
		// ������ ������ �ְ� ������ �����ϱ� buffer�Ⱦ��� �̰ɷθ� �� ��
		DataInputStream dis;
		Socket socket = null;
		
		public Receiver(Socket socket){
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() { // DataInputStream �޾Ƽ� ó��
			while(dis != null) {
				String msg = "";
				try {
					msg = dis.readUTF();  // readLine��� �̹��� readUTF �� �غ���
					if(msg.equals("q")) {
						System.out.println("client�� �������ϴ�.");
						break;
					}
					System.out.println(msg);  // msg �����°� Ȯ�ο뵵
				} catch (IOException e) {
					System.out.println("client�� �������ϴ�.");
					break;
				} 
			}// end while
			
			if(dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void startServer() throws Exception {
		System.out.println("TCP/IP Server Start...");
		
		try {
			serverSocket = new ServerSocket(port);
			// ������ �����ʰ� ��ƾ� �ϱ� ������ while
			while(true) {
				System.out.println("Ready Server..");
				socket = serverSocket.accept();  // �����ϸ� ���⼭ �ϴ� ���(client ���� ������)
				System.out.println("Connected..");
				new Receiver(socket).start(); // ������ Ŭ���̾�Ʈ �� 1���� ���ܾ��Ѵ�. 100���̸� 100���� ���ܾ���
			}
		}catch(Exception e) {
			throw e;
		}
		
	}
	
	
	public static void main(String[] args) {
		Server server = new Server(7777);
		try {
			server.startServer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
