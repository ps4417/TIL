package com.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mSignUp")
public class MemSignUp extends HttpServlet {
	//3. doGet 실행된다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" -- doGet() -- ");
		// 4. getParmeter의  m_name, m_pass 등등은 form태그에서 input태그 name에 입력한 것을 의미한다.
		String m_name = request.getParameter("m_name"); //하나의 데이터를 받을 때는 getParmeter
		String m_pass = request.getParameter("m_pass");
		String m_gender = request.getParameter("m_gender");
		String[] m_hobbys = request.getParameterValues("m_hobby"); //여러개를 받을때는 getParmeterValues 사용하고 배열로 담는다.
		String m_residence = request.getParameter("m_residence");
		
		System.out.println("m_name : " + m_name);
		System.out.println("m_pass : " + m_pass);
		System.out.println("m_gender : " + m_gender);
		System.out.println("m_hobbys : " + Arrays.toString(m_hobbys));
		System.out.println("m_residence : " + m_residence);
		
		Enumeration<String> names = request.getParameterNames(); //parameter의 이름을 확인해보고 싶을 때
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("name : " + name);
		}
		
	}
	// 1. form 태그의 method를 post로 입력하면 여기로 값이 날아와 request에 들어간다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" -- doPost() -- ");
		
		//2. 여기서 다시 doGet을 호출 했으므로 doGet이 실행된다.
		doGet(request, response);
	}

}
