package com.tcpip;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
		client = new Client("192.168.0.3", 5555, "[WEB]");
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
		client.sendTarget("/192.168.0.99","100");
		PrintWriter out = res.getWriter();
		out.print("ok");
		out.close();
	}

	@RequestMapping("/phone.mc")
	public void phone(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Ftest 객체가 안불러져와서 Ftest의 service 함수 안에 있는 코드를 복붙함  
		System.out.println("Phone Send Start ...");
		
		URL url = null;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");
		} catch (MalformedURLException e) {
			System.out.println("Error while creating Firebase URL | MalformedURLException");
			e.printStackTrace();
		}
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			System.out.println("Error while createing connection with Firebase URL | IOException");
			e.printStackTrace();
		}
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");

		// set my firebase server key
		conn.setRequestProperty("Authorization", "key="
				+ "AAAAyJAImqU:APA91bEkTxdYYRXoAMrp1vWQODDzAMoYJle5X2qPUq-J_OZJRxYpwOW17Nh-GO13_gA20wDLI8R7voI6UZ2GOaETREuV0Ja9nxEM0R2ALX2XCkEM1BKyyNrysBlGzvI34G_ILKQNdcuG");

		// create notification message into JSON format
		JSONObject message = new JSONObject();
		message.put("to", "/topics/car");
		message.put("priority", "high");
		JSONObject notification = new JSONObject();
		notification.put("title", "IoT프로젝트");
		notification.put("body", "띵동~ 쿠폰이 도착하였습니다.");
		message.put("notification", notification);
		JSONObject data = new JSONObject();
		data.put("control", "controlWS");
		data.put("data", 200);
		message.put("data", data);


		try {
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(message.toString());
			out.flush();
			conn.getInputStream();
			System.out.println("OK...............");

		} catch (IOException e) {
			System.out.println("Error while writing outputstream to firebase sending to ManageApp | IOException");
			e.printStackTrace();
		}	
		
		
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.close();
	}
}
