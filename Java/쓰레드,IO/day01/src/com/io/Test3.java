package com.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Object �ְ� �ޱ� �ϱ�
public class Test3 implements Serializable{ // Serializable �� implements ����� �Ѵ�. 

	public static void main(String[] args) {
		User user = new User("id01","�̸���");
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("user.serial");  // Ȯ���ڸ��� �����Ӱ� �ᵵ ��� ����.
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(bos);
			oos.writeObject(user);
			System.out.println("Wrtie Complete");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			ObjectInputStream ois = null;
			
			try {
				fis = new FileInputStream("user.serial");
				bis = new BufferedInputStream(fis);
				ois = new ObjectInputStream(bis);
				User readUser = null;
				readUser = (User)ois.readObject();
				System.out.println(readUser);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			
	} //end Main

}
