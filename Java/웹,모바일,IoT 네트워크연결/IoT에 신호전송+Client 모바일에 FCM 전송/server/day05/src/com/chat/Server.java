package com.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.msg.Msg;

// 실시간 채팅(통신)기능 구현해보자!
// IoT 장비를 작동시키는 기능을 할 수도 있다.
public class Server {
	
	
	int port;
	
	// 사용자의 IP 주소를 string으로 할 예쩡
	HashMap<String,ObjectOutputStream> maps;
	
	ServerSocket serverSocket;
	
	
	
	public Server() {}
	public Server(int port) {
		this.port = port;
		maps = new HashMap<>();
	}
	
	// client 가 접속할 수 있도록 준비한다 - socket
	// 클라이언트에서 들어온 정보를 확인해서 처리한다.
	public void startServer() throws Exception {
		serverSocket = new ServerSocket(port);
		System.out.println("Start Server ...");
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Socket socket = null;
						System.out.println("Ready Server ...");
						socket = serverSocket.accept();
						System.out.println(socket.getInetAddress());  //접속한 클라이언트의 IP address를 찍어본다.
						makeOut(socket);
						new Receiver(socket).start();
					}catch(Exception e) {
						e.printStackTrace();
					}					
				}// end While
				
			}
		};
		new Thread(r).start();
	}
	
	// 각각의 클라이언트에서 발생한 outputStream을 해시맵에 담는 역할을 한다. 
	public void makeOut(Socket socket) throws IOException {
		ObjectOutputStream oo;
		oo = new ObjectOutputStream(socket.getOutputStream());
		maps.put(socket.getInetAddress().toString(),oo); // 해시맵에 저장~
		System.out.println("접속자수: "+maps.size());
	}
	
	
	
	// 각각의 클라이언트마다 각각의 쓰레드가 생긴다. 받기만 한다.
	class Receiver extends Thread {
		Socket socket;
		ObjectInputStream oi;
		
		
		
		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			oi = new ObjectInputStream(socket.getInputStream());
			
		}

		@Override
		public void run() {
			while(oi != null) {
				Msg msg = null;
				try {			
					msg = (Msg) oi.readObject(); // 여기서 exception이 나는 거면 server에서 client의 정보를 더 갖고 있을 필요가 없다.
					if(msg.getMsg().equals("q")) {
						throw new Exception();
					}else if(msg.getMsg().equals("1")) {
						String ip = socket.getInetAddress().toString();
						ArrayList<String> ips = new ArrayList<>();
						ips.add(ip);
						msg.setIps(ips);
						
						Set<String> keys = maps.keySet();
						HashMap<String,Msg> hm = new HashMap<>();
						for(String k : keys) {
							hm.put(k,null);
						}
						// 1을 보낸 client 정보
						// 서버의 접속자 IP 들
						msg.setMaps(hm);
						
					}
					
					System.out.println(msg.getId()+msg.getMsg()); //서버에 메시지 찍히도록 
					sendMsg(msg);
					
				} catch (Exception e) {
					maps.remove(socket.getInetAddress().toString()); // 고로 삭제해버린다.
					System.out.println(socket.getInetAddress()+"...Exit");
					System.out.println("접속자수 :"+maps.size());
					break;
				}
			} // end While
			
			// while문이 끝나면 다 닫아줘야 한다.
			try {
				if(oi != null) {
					oi.close();
				}
				if(socket != null) {
					socket.close();
				}
			}catch(Exception e) {
				
			}
		}
		
	}
	
	
	// Receiver가 메시지를 받으면 이 메서드가 Sender 쓰레드에게 전송시켜준다. 
	public void sendMsg(Msg msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}
	
	// HashMap에 있는 클라이언트둘에게 보내준다.
	class Sender extends Thread {
		Msg msg;
		public void setMsg(Msg msg) {
			this.msg = msg;
		}
		
		@Override
		public void run() {
			Collection<ObjectOutputStream> cols = maps.values();
			Iterator<ObjectOutputStream> it = cols.iterator();
			while(it.hasNext()) {
				try {
					
					if(msg.getIps()!=null) {
						for(String ip: msg.getIps()) {
							
							if(!msg.getMsg().equals("1")&&!maps.containsKey(ip)) {
								System.out.println("없는 사용자");
							}
							else maps.get(ip).writeObject(msg);
						}
						break;
					}
					it.next().writeObject(msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Server server = new Server(5555);
		try {
			server.startServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
	}

}
