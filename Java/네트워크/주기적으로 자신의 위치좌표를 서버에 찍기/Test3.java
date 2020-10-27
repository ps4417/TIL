package com.http;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test3 {
// �ֱ������� �ڽ��� ��ġ�� ������ ��ž�
	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/car.jsp";
		URL url = null;
		
		// URLConnection ���� ����� �� ���� ��! �����δ� �̰� ����� ��
		HttpURLConnection con = null;
		
		// �ݺ����� ����ؼ� �ֱ������� ��ǥ�� ���۵ǵ��� �Ѵ�.
		while(true) {
			try {
				// Random �Լ��� ����ϸ� ��ǥ���� �����ϰ� �����Ǿ� ���۵ȴ�.
				double lat = Math.random()*90+1;
				double lng = Math.random()*90+1;
				url = new URL(urlstr+"?lat="+lat+"&lng="+lng);
				con = (HttpURLConnection) url.openConnection();
				con.setReadTimeout(5000);  // 5�ʵ��� ���� ������ 
				con.setRequestMethod("POST"); // POST ������� ��û�ض�
				con.getInputStream();  // �̰� �ۼ������ ������ ������. 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.disconnect(); // ������ ������ ���������!
			}
		
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

}
