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

// �ǽð� ä��(���)��� �����غ���!
// IoT ��� �۵���Ű�� ����� �� ���� �ִ�.
public class Server {
	
	
	int port;
	
	// ������� IP �ּҸ� string���� �� ����
	HashMap<String,ObjectOutputStream> maps;
	
	ServerSocket serverSocket;
	
	
	
	public Server() {}
	public Server(int port) {
		this.port = port;
		maps = new HashMap<>();
	}
	
	// client �� ������ �� �ֵ��� �غ��Ѵ� - socket
	// Ŭ���̾�Ʈ���� ���� ������ Ȯ���ؼ� ó���Ѵ�.
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
						System.out.println(socket.getInetAddress());  //������ Ŭ���̾�Ʈ�� IP address�� ����.
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
	
	// ������ Ŭ���̾�Ʈ���� �߻��� outputStream�� �ؽøʿ� ��� ������ �Ѵ�. 
	public void makeOut(Socket socket) throws IOException {
		ObjectOutputStream oo;
		oo = new ObjectOutputStream(socket.getOutputStream());
		maps.put(socket.getInetAddress().toString(),oo); // �ؽøʿ� ����~
		System.out.println("�����ڼ�: "+maps.size());
	}
	
	
	
	// ������ Ŭ���̾�Ʈ���� ������ �����尡 �����. �ޱ⸸ �Ѵ�.
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
					msg = (Msg) oi.readObject(); // ���⼭ exception�� ���� �Ÿ� server���� client�� ������ �� ���� ���� �ʿ䰡 ����.
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
						// 1�� ���� client ����
						// ������ ������ IP ��
						msg.setMaps(hm);
						
					}
					
					System.out.println(msg.getId()+msg.getMsg()); //������ �޽��� �������� 
					sendMsg(msg);
					
				} catch (Exception e) {
					maps.remove(socket.getInetAddress().toString()); // ��� �����ع�����.
					System.out.println(socket.getInetAddress()+"...Exit");
					System.out.println("�����ڼ� :"+maps.size());
					break;
				}
			} // end While
			
			// while���� ������ �� �ݾ���� �Ѵ�.
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
	
	
	// Receiver�� �޽����� ������ �� �޼��尡 Sender �����忡�� ���۽����ش�. 
	public void sendMsg(Msg msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}
	
	// HashMap�� �ִ� Ŭ���̾�Ʈ�ѿ��� �����ش�.
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
								System.out.println("���� �����");
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
