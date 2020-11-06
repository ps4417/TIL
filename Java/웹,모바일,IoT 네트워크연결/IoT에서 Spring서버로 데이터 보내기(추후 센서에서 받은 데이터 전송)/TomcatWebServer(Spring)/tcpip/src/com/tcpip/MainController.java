package com.tcpip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.chat.Client;

import ftest.Ftest;

@Controller
public class MainController {
	Client client;
	Ftest ftest;
	
	public MainController() {
		client = new Client("52.78.89.147", 5555, "[WEB]");
		try {
			client.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@RequestMapping("/main.mc")
	public ModelAndView main() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping("/iot.mc")
	public void iot(HttpServletResponse res) throws IOException {
		System.out.println("IoT Send Start ...");
		client.sendTarget("/192.168.0.34","100");
		PrintWriter out = res.getWriter();
		out.print("ok");
		out.close();
	}

	@RequestMapping("/phone.mc")
	public void phone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		Ftest ft = new Ftest();
		try {
			ft.service(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
				
		
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.close();
	}
}
