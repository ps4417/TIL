package com.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// 서버에 있는 데이터를 가져와보자!(project에 있는 network라는 프로젝트에 json을 입력해놓음)
public class Test1 {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/users.jsp";
		URL url = null;
		URLConnection con = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			// 서버 연결
			url = new URL(urlstr);
			con = url.openConnection();
			
			
			// 읽어 들이자
			is = con.getInputStream();  
			isr = new InputStreamReader(is,"UTF-8");  // 읽어올 때 한글 안깨지도록 UTF-8로 바꿔준다.
			br = new BufferedReader(isr);
			
			String str = "";
			while((str = br.readLine()) != null) {  // 한 라인씩 읽어들인다.
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
