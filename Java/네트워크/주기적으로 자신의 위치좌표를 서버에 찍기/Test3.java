package com.http;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test3 {
// 주기적으로 자신의 위치를 서버에 쏠거야
	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/car.jsp";
		URL url = null;
		
		// URLConnection 보다 기능이 더 많은 것! 앞으로는 이걸 사용할 것
		HttpURLConnection con = null;
		
		// 반복문을 사용해서 주기적으로 좌표가 전송되도록 한다.
		while(true) {
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

}
