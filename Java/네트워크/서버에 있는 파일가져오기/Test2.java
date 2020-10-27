package com.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
// 서버에 접속해서 파일을 가져오자!
public class Test2 {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/mp.mp3";
		URL url = null;
		URLConnection con = null;
		
		InputStream is = null;
		BufferedInputStream bis = null;  // 파일을 가져올때는 이걸 쓴다.
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;  // 파일을 내 컴퓨터에 저장할거야
		
		try {
			// 서버 연결
			url = new URL(urlstr);
			con = url.openConnection();
			
			is = con.getInputStream();
			bis = new BufferedInputStream(is,10240000);
			
			fos = new FileOutputStream("newmp.mp3");
			bos = new BufferedOutputStream(fos);
			
			
			// 데이터는  단위가 int이다! 
			int data = 0;
			while((data = bis.read())!= -1) {
				bos.write(data); // 받은 데이터를 쓴다!
//				System.out.println(data);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
