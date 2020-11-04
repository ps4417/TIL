package com.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import com.msg.Msg;

public class Client {

	int port;
	String address;
	String id;

	Socket socket;
	Sender sender;

	public Client() {
	}

	public Client(String address, int port, String id) {
		this.address = address;
		this.port = port;
		this.id = id;
	}

	// socket을 만든다.
	// exception
	public void connect() throws IOException {
		try {
			socket = new Socket(address, port);
		} catch (Exception e) {
			while (true) {
				try {
					Thread.sleep(2000);
					socket = new Socket(address, port);
					break;
				} catch (Exception e1) {
					System.out.println("Retry ...");
				}
			}
		}
		System.out.println("Connected Server:" + address);
		
		// 서버로 보내자
		sender = new Sender(socket);
		
		// 서버에서 뿌리는거 받자
		new Receiver(socket).start();

	}

	// 스캐너에서 입력받아서 보내는 것
	// 귓속말을 하고자하는 유저 한 명의 IP를  "" 안에 직접 적어준다.
	// 여러명의 유저에게 귓속말을 하고자 하는 경우 일단 Msg부터 바꾼다.
	// 그 후 ArrayList를 만들어 그 안에 IP를 담는다.
	public void sendMsg() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Input msg");
			String ms = sc.nextLine();
			// 1을 보내면 서버에서는 사용자 리스트틀 보낸다.
			Msg msg = null;
			if(ms.equals("1")) {
				msg = new Msg(id,ms);
			}else {			
				ArrayList<String> ips = new ArrayList<>();
				ips.add("/192.168.0.19");
				msg = new Msg(null,id,ms);
			}				
			sender.setMsg(msg);				
			new Thread(sender).start();		
			if (ms.equals("q")) {
				break;
			}
		}
		sc.close();
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("BYE ...");
	}

	class Sender implements Runnable {
		Socket socket;
		ObjectOutputStream oo;
		Msg msg;

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			oo = new ObjectOutputStream(socket.getOutputStream());
		}

		public void setMsg(Msg msg) {
			this.msg = msg;
		}

		@Override
		public void run() {
			if (oo != null) {
				try {
					oo.writeObject(msg);
				} catch (IOException e) {
//					e.printStackTrace();
					try {
						if (socket != null) {
							socket.close();
						}
						
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						// 재접속
						Thread.sleep(2000);
						connect();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}

	// server에서 보내는 메시지를 받는 역할
	class Receiver extends Thread {
		ObjectInputStream oi;
		public Receiver(Socket socket) throws IOException {
			oi = new ObjectInputStream(socket.getInputStream());
		}
		@Override
		public void run() {
			while(oi != null) {
				Msg msg  = null;
				try {
					msg = (Msg) oi.readObject();
					if(msg.getMaps() != null) {
						HashMap<String,Msg> hm = msg.getMaps();
						Set<String> keys = hm.keySet();
						for(String k : keys) {
							System.out.println(k);
						}
						continue;
					}
					System.out.println(msg.getId()+msg.getMsg());
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
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

	public static void main(String[] args) {
		Client client = new Client("192.168.0.4", 5555, "[Comet]");
		try {
			client.connect();
			client.sendMsg();
		} catch (IOException e) {
			e.printStackTrace();
		}
		send();
	}
	
	public static void send() {
		while(true) {
			try {
				Thread.sleep(4000);
				IotD td = new IotD();
				td.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
class IotD extends Thread{

	@Override
	public void run() {
		String urlstr = "http://192.168.0.4/network/car.jsp";
		URL url = null;
		
		// URLConnection 보다 기능이 더 많은 것! 앞으로는 이걸 사용할 것
		HttpURLConnection con = null;
		
		// 반복문을 사용해서 주기적으로 좌표가 전송되도록 한다.
		
			try {
				// Random 함수를 사용하면 좌표값이 랜덤하게 설정되어 전송된다.
				double lat = Math.random()*90+1;
				double lng = Math.random()*90+1;
				url = new URL(urlstr+"?lat="+lat+"&lng="+lng);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000);  // 5초동안 답이 없으면 
				con.setRequestMethod("POST"); // POST 방식으로 요청해라
				con.getInputStream();  // 이거 작성해줘야 서버에 찍힌다. 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.disconnect(); // 끝나면 연결을 끊어줘야해!
			}
		
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		
	}
	
	
}
