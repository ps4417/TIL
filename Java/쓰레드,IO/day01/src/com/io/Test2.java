package com.io;

import java.io.*;

// 파일을 Read and Write  - 문자기반 스트림
public class Test2 {  

	public static void main(String[] args) {
		String file = "C:\\network\\day01\\src\\test.txt";
		FileReader fis = null;
		BufferedReader bis = null;
		FileWriter fos = null;
		BufferedWriter bos = null;
		try {
			fis = new FileReader(file);
			bis = new BufferedReader(fis);
			fos = new FileWriter("test2.txt");  
			bos = new BufferedWriter(fos);  
			String data = "";
			while((data=bis.readLine())!= null) {
//				fos.write(data);
				bos.write(data);
				System.out.println(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(bis != null) {
				try {
//					fis.close();
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(bos != null) {
				try {
//					fos.close();
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
