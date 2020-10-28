package com.tcpip2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.msg.Msg;

public class Server {
// Object Serialization �̿��� ��
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
		ObjectInputStream dis;
		Socket socket = null;
		
		public Receiver(Socket socket){
			this.socket = socket;
			try {
				dis = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() { 
			while(dis != null) {
				Msg mo = null;
				try {
					mo = (Msg)dis.readObject();  
					if(mo.getMsg().equals("q")) {
						System.out.println(mo.getId()+"���� �������ϴ�.");
						break;
					}
					System.out.println(
							"["+mo.getId()+"]"+mo.getMsg()); 
				} catch (Exception e) {
					if(mo.getId() != null) {
						System.out.println(mo.getId()+"���� �������ϴ�.");
					}
					
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
