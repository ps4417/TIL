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

public class Server2 {
	int port;

	HashMap<String, ObjectOutputStream> maps;

	ServerSocket serverSocket;

	public Server2() {
	}

	public Server2(int port) {
		this.port = port;
		maps = new HashMap<>();
	}

	/*
	 * Server start Socket ���� ������ ����ΰ� �������� ó���� �Ѵ�.
	 */
	public void startServer() throws Exception {
		serverSocket = new ServerSocket(port);
		System.out.println("Start Server2 ...");
		Runnable r = new Runnable() {			
			@Override
			public void run() {
				while (true) {
					try {
						Socket socket = null;
						System.out.println("Ready Server2");
						socket = serverSocket.accept();
						System.out.println(socket.getInetAddress() + "���� �����߽��ϴ�.");
						makeOut(socket);
						new Receiver(socket).start();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(r).start();
		
	}

	/*
	 * ������ Client�� �������� �� ������ Socket���� ���� �����͸� HashMap�� �����͸� �����ϴ¿���
	 */
	public void makeOut(Socket socket) throws IOException {
		ObjectOutputStream oo;
		oo = new ObjectOutputStream(socket.getOutputStream());
		maps.put(socket.getInetAddress().toString(), oo);
		System.out.println("���� ������ ��: " + maps.size());
	}

	/* �����͸� �޴� Thread */
	class Receiver extends Thread {
		Socket socket = null;
		ObjectInputStream oi;

		public Receiver() {
		}

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			oi = new ObjectInputStream(socket.getInputStream());
		}

		@Override
		public void run() {
			while (oi != null) {
				Msg msg = null;
				try {
					msg = (Msg) oi.readObject();
					if(msg.getMsg().equals("q")) {
						throw new Exception();
					}else if(msg.getMsg().equals("1")) {
						String ip = socket.getInetAddress().toString();
						ArrayList<String> ips = new ArrayList<>();
						ips.add(ip);
						msg.setIps(ips);
						
						Set<String> keys = maps.keySet();
						HashMap<String,Msg> hm = new HashMap<String, Msg>();
						for(String k: keys) {
							hm.put(k,null);
						}
						msg.setMaps(hm);
					}
					sendMsg(msg);
					System.out.println(msg.getId()+":"+msg.getMsg());
				} catch (Exception e) {
					/*������ Ŭ���̾�Ʈ������ ������ ���*/
					maps.remove(socket.getInetAddress().toString());
					System.out.println(socket.getInetAddress()+"�� �������ϴ�.");
					System.out.println("���� ������ ��: " + maps.size());
					break;
				}
			}
			try {
				if(oi != null) {
					oi.close();
				}
			}catch (Exception e) {
			}
			try {
				if(socket!=null) {
					socket.close();
				}
			}catch (Exception e) {
			}
		}
	}

	/* ���� �޽����� Ŭ���̾�Ʈ�鿡�� �������ش�. */
	public void sendMsg(Msg msg) {
		Sender sender = new Sender();
		sender.setMsg(msg);
		sender.start();
	}

	/* �����͸� ������ ������ (�ּҺ��� �������ұ�?) */
	class Sender extends Thread {
		Msg msg;
		public Sender() {
		}
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
			e.printStackTrace();
		}
	}

}