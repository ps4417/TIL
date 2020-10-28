package com.tcpip;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	ServerSocket serverSocket;
	Socket socket;
	
	// 생성자
	public Server() {}
	public Server(int port) {
		this.port = port;
	}
	
	// client 가 들어올 때마다 이 Receiver 가 작동 되도록 할 것
	class Receiver extends Thread{
		// 오늘은 파일을 주고 받지는 않으니까 buffer안쓰고 이걸로만 할 것
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
		public void run() { // DataInputStream 받아서 처리
			while(dis != null) {
				String msg = "";
				try {
					msg = dis.readUTF();  // readLine대신 이번엔 readUTF 로 해보자
					if(msg.equals("q")) {
						System.out.println("client가 나갔습니다.");
						break;
					}
					System.out.println(msg);  // msg 들어오는거 확인용도
				} catch (IOException e) {
					System.out.println("client가 나갔습니다.");
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
			// 서버는 죽지않고 살아야 하기 때문에 while
			while(true) {
				System.out.println("Ready Server..");
				socket = serverSocket.accept();  // 시작하면 여기서 일단 대기(client 들어올 때까지)
				System.out.println("Connected..");
				new Receiver(socket).start(); // 소켓은 클라이언트 당 1개가 생겨야한다. 100명이면 100개가 생겨야해
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
