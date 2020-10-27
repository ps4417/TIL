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
// ������ �����ؼ� ������ ��������!
public class Test2 {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/mp.mp3";
		URL url = null;
		URLConnection con = null;
		
		InputStream is = null;
		BufferedInputStream bis = null;  // ������ �����ö��� �̰� ����.
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;  // ������ �� ��ǻ�Ϳ� �����Ұž�
		
		try {
			// ���� ����
			url = new URL(urlstr);
			con = url.openConnection();
			
			is = con.getInputStream();
			bis = new BufferedInputStream(is,10240000);
			
			fos = new FileOutputStream("newmp.mp3");
			bos = new BufferedOutputStream(fos);
			
			
			// �����ʹ�  ������ int�̴�! 
			int data = 0;
			while((data = bis.read())!= -1) {
				bos.write(data); // ���� �����͸� ����!
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
