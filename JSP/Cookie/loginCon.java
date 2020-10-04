package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginCon")
public class loginCon extends HttpServlet {
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//잘 받아왔는지 확인하기 위해 PrintWriter생성
		PrintWriter out = response.getWriter(); 
		
		String mId = request.getParameter("mID");
		String mPw = request.getParameter("mPW");
		
		// id, pw 출력
		out.print("mId: "+mId);
		out.print("mPw: "+mPw);
		// 쿠키는 여러 정보가 있을 수 있기 때문에 배열로 관리함
		// 사용자로 부터 가져오기 때문에 request!
		Cookie[] cookies =request.getCookies();
		Cookie cookie = null;
		
		//사용자로부터 가져온 쿠키를 for문을 돌려 검색한다.
		for(Cookie c:cookies) {
			System.out.println("c.getName():"+c.getName()+", c.getValue() :"+c.getValue());
			
			//만약 가져온 아이디가 memberId와 같다면(접속한 이력이 있다는 의미!) c를 cookie에 담아라
			if(c.getName().equals("memberId")) {
				cookie = c;
			}
		}
		// 쿠키가 없다면 cookie is null을 출력하고 쿠키를 만든다.--> name은 memberId, value는 mId(사용자가 가져온 아이디 사용)
		if(cookie ==null) {
			System.out.println("cookie is null");
			cookie = new Cookie("memberId", mId);
		}
		// 사용자에게 응답해준다.
		response.addCookie(cookie);
		cookie.setMaxAge(60*60);  //쿠키 유효시간 설정
		
		//servlet에서는 view를 만들지 않고 sendRedirect를 통해 jsp화면을 보내준다.(sendRedirect)
		response.sendRedirect("loginOk.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
