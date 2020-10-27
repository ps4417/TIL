package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// 로그인 데이터를 서버에서 확인해서 맞으면 1, 틀리면 0이 출력되도록 하자
// Test1, Test3 을 같이 사용해서 해야해!
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
