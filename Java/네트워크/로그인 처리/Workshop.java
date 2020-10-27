package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// �α��� �����͸� �������� Ȯ���ؼ� ������ 1, Ʋ���� 0�� ��µǵ��� ����
// Test1, Test3 �� ���� ����ؼ� �ؾ���!
public class Workshop {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/login.jsp";
		URL url = null;
		
		HttpURLConnection con = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		
		try {
			String id = "qqq";
			String pwd = "111";
			url = new URL(urlstr+"?id="+id+"&pwd="+pwd);
			con = (HttpURLConnection) url.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("POST");
			
			is = con.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String str = "";
			while((str=br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		

	}

}
