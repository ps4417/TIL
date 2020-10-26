package com.io;

import java.io.*;

// 파일을 Read and Write - 바이트 기반 스트림
public class Test1 {  

	public static void main(String[] args) {
		String file = "C:\\network\\day01\\src\\test.txt";
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream("test2.txt");  // file을 읽어서  test2.txt에 입력하고 day01에 txt파일을 만듦
			bos = new BufferedOutputStream(fos);  // Buffer을 이용하면 더 빠르다!
			int data = 0;
			while((data=bis.read())!= -1) {
//				fos.write(data);
				bos.write(data);
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
