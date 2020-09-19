## 리뷰 다양한 소리 내기

> Sounding 인터페이스를 구현하는 Dog, Baby, Tiger, Robot 클래스를 작성하고, 메소드 오버라이딩을 통해 출력 예와 같은 결과를 만드시오.



* 출력 예

  ```java
  Dog: 멍멍!
  Baby: 응애!
  Tiger: 어흥!
  Robot: 삐빕!
  ```

* 정답

  ```java
  public class InterfaceReview {
    public static void main(String[] args) {
      // 객체 생성
      Sounding dog = new Dog();
      Sounding baby = new Baby();
      Sounding tiger = new Tiger();
      Sounding robot = new Robot();
      
      // 인터페이스 배열 생성
      Sounding[] arr = {dog, baby, tiger, robot};
      // 소리내기
      for(int i=0; i<arr.length;i++){
        arr[i].sound();
      }
    }
  }
  
  /* 인터페이스 및 클래스를 작성하시오. */
  interface Sounding{
    public void sound();
  }
  class Dog implements Sounding {
    public void sound(){
      System.out.println("Dog: 멍멍!");
    }
  }
  class Baby implements Sounding{
    public void sound(){
      System.out.println("Baby: 응애!");
    }
  }
  class Tiger implements Sounding{
    public void sound(){
      System.out.println("Tiger: 어흥!");
    }
  }
  class Robot implements Sounding{
    public void sound(){
      System.out.println("Robot: 삐빕!");
    }
  }
  ```

* ArrayList로 리팩토링 해보기

  ```java
  // 인터페이스 배열 생성
      ArrayList<Sounding> list = new ArrayList<Sounding>();
      list.add(dog);
      list.add(baby);
      list.add(tiger);
      list.add(robot);
  
      // 소리내기    
      for(int i=0; i<list.size();i++){
        list.get(i).sound();
      }
  ```

  

