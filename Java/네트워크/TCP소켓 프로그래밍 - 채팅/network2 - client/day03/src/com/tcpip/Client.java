package com.tcpip;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	int port;
	String address;
	
	// 서버와 접촉할 소켓 필요
	Socket socket;
	

	public Client() {}
	public Client(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	// 접속 시도 함수
	public void connect() throws Exception{
		try {	
			socket = new Socket(address,port);
		}catch(Exception e) {
			while(true) {
				Thread.sleep(2000);
				try {
					socket = new Socket(address,port);
					System.out.println("Connected");
					break;
				} catch (IOException e1) {
					System.out.println("Re-Try...");
				}
				
				
			}
		}
	}
	
	class Sender extends Thread{
		DataOutputStream dos;
		String msg;
		public Sender(String msg) {
			this.msg = msg;
			try {
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		@Override
		public void run() {
			if(dos != null) {
				try {
					dos.writeUTF(msg);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
	
	
	
	// 서버로 문자 보내기
	public void request() throws IOException {
		Scanner sc = new Scanner(System.in);
		try {
			while(true) {
				System.out.println("[Input Msg]");
				String msg = sc.nextLine(); // 받아온 걸 서버로 전송한다.
				if(msg.equals("q")) {
					new Sender("q").start();
					Thread.sleep(1000);
					System.out.println("Exit Client");
					break;
				}
				new Sender(msg).start();  // msg를 받아서 보내준다!!
			}
		}catch(Exception e){
			
		}finally {
			sc.close();
			if(socket != null) {
				socket.close();
			}
		}
	}
	public static void main(String[] args) {
		Client client = new Client("192.168.0.3",7777);
			try {
				client.connect();
				client.request();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

}
