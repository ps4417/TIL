package kr.co.softcampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test1")
	public String test1() {
		
		return "redirect:/sub1";  // sub1을 요청하라 라는 정보를 응답정보로 꾸며 브라우저로 전달한다. 즉, 화면은 sub1으로 이동됨(주소값도 변경)
									// redirect는 서버가 브라우저에게 요청정보를 전달하는 것이고, 브라우저는 그것을 받아 직접 요청한다.
									// 응답결과가 도착했기 때문에 request 객체는 소멸이 되고, 다시 요청이 발생했기 때문에 새로운 request 객체가 생성된다.
	}
	
	@GetMapping("/sub1")
	public String sub1() {
		return "sub1"; //return하게되면 sub1이라는 이름을 가진 jsp를 찾아서 실행하고 그것을 화면에 보여주게된다.
	}
	
	@GetMapping("/test2")
	public String test2() {
		return "forward:/sub2"; //forward는 코드의 흐름이 서버상에서 이동된다.(이 때문에 웹브라우저는 이를 알 수 없고-> 주소창의 주소는 변경되지 않는다.)
								// request 객체가 그대로 전달된다.
	}
	
	@GetMapping("/sub2")
	public String sub2() {
		return "sub2";
	}
}
