package com.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/tsc")
public class TestServletClass extends HttpServlet { 
	
	
	//servlet 실행
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("--doGet()--");		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}
	// init 보다 먼저 호출됨
	@PostConstruct
	private void funPC() {
		System.out.println("--@PostConstruct--");

	} 
	
	//servlet 생성
	@Override
	public void init() throws ServletException { 
		System.out.println("--init()--");
	}
	
	//servlet 종료
	@Override
	public void destroy() { 
		System.out.println("--destory()--");
	}
	
	//servlet 종료후 호출됨
	@PreDestroy
	private void funPd() {
		System.out.println("--PreDestroy--");

	}

}
