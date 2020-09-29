# ViewResolver

* 컨트롤러에서 전달받은 View의 이름을 토대로 jsp를 찾아 선택하고, jsp 데이터를 분석해 응답결과를 만들어 전달하는 요소이다.



## HttpServletRequest

* Spring MVC는 jsp를 처리할 때 HttpServletRequest 객체를 jsp 쪽으로 전달한다.
* ViewResolver는 이를 이용해 jsp 작업 시 데이터를 사용할 수 있다.

```java
public String test2(HttpServletRequest request) {
request.setAttribute("value1", 100);
request.setAttribute("value2", 200);
return "test2";
}
```



## Model

* Model 객체를 주입 받아 세팅하면 HttpServletRequest 객체에 담겨 이를 jsp 로 전달 할 수 있다.

```java
public String test3(Model model) {
model.addAttribute("value3", 300);
model.addAttribute("value4", 400);
return "test3";
}
```



## ModelAndView

* ModelAndView는 Model에 값을 세팅하는 기능과 View의 이름을 지정하는 기능을 모두 가지고 있다.

```java
public ModelAndView test5(ModelAndView mv) {
mv.addObject("value3000", 3000);
mv.addObject("value4000", 4000);
mv.setViewName("test5");
return mv;
}
```

