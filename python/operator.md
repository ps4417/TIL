# 연산자

## 숫자처리함수

```python
#숫자 처리 함수
print(abs(-5)) #5
print(pow(4,2)) # 4^2 = 4*4=16
print(max(5,12)) #12
print(min(5,12)) #5
print(round(3.14)) #3
print(round(4.99)) #5

from math import*
print(floor(4.99)) #내림. 4
print(ceil(4.99)) #올림. 5
print(sqrt(16)) #제곱근. 4
```



## 랜덤함수

```python
from random import*

print(random()) #0.0~1.0 미만의 임의의 값 생성
print(random()*10) #0.0 ~ 10.0 미만의 임의의 값 생성
print(int(random()*10)) #0 ~ 10 미만의 임의의 값 생성
print(int(random()*10)+1) #1 ~ 10 이하의 임의의 값 생성
```



## 로또값 생성

```python
print(int(random()*45)+1) # 1부터 45이하의 임의의 값 생성
print(int(random()*45)+1)
print(int(random()*45)+1)
print(int(random()*45)+1)
print(int(random()*45)+1)
print(int(random()*45)+1)

print(randrange(1,46)) # 1부터 46미만의 임의의 값 생성

print(randint(1,45)) # 1부터 45 이하의 임의의 값 생성
```



## Q.퀴즈

당신은 최근에 코딩 스터디 모임을 새로 만들었습니다.

월 4회 스터디를 하는데 3번은 온라인으로 하고 1번은 오프라인으로 하기로 했습니다.

아래 조건에 맞는 오프라인 모임 날자를 정해주는 프로그램을 작성하시오.



조건1 : 랜덤으로 날짜를 뽑아야 함

조건2 : 월별 날짜는 다름을 감안해 최소 일수인 28 이내로 정함

조건3 : 매월 1~3일은 스터디 준비를 해야하므로 제외



(출력문 예제)

오프라인 스터디 모임 날짜는 매월 x 일로 선정 되었습니다.

```python
from random import *
studyDay = randrange(4,29)
print("오프라인 스터디 모임 날짜는 매월"+ str(studyDay) +"일로 선정되었습니다.")
```

