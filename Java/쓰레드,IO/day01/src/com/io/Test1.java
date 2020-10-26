package com.io;

import java.io.*;

// ������ Read and Write - ����Ʈ ��� ��Ʈ��
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
			fos = new FileOutputStream("test2.txt");  // file�� �о  test2.txt�� �Է��ϰ� day01�� txt������ ����
			bos = new BufferedOutputStream(fos);  // Buffer�� �̿��ϸ� �� ������!
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
