package com.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

// ������ �ִ� �����͸� �����ͺ���!(project�� �ִ� network��� ������Ʈ�� json�� �Է��س���)
public class Test1 {

	public static void main(String[] args) {
		String urlstr = "http://192.168.0.100/network/users.jsp";
		URL url = null;
		URLConnection con = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		try {
			// ���� ����
			url = new URL(urlstr);
			con = url.openConnection();
			
			
			// �о� ������
			is = con.getInputStream();  
			isr = new InputStreamReader(is,"UTF-8");  // �о�� �� �ѱ� �ȱ������� UTF-8�� �ٲ��ش�.
			br = new BufferedReader(isr);
			
			String str = "";
			while((str = br.readLine()) != null) {  // �� ���ξ� �о���δ�.
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
