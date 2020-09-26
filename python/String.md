		# 문자열

## 슬라이싱

```python
jumin = '941119-1234567'
print("성별 : "+ jumin[7]) 
print("연 : "+jumin[0:2] )  # 0부터 2 직전까지
print("생년월일 : "+jumin[:6]) # 처음부터 6 직전까지
print("뒤 7자리 : "+jumin[7:]) # 7부터 끝까지
print("뒤 7자리(뒤에부터) : "+jumin[-7:]) # 맨 뒤에서 7번째 부터 끝까지

```



## 문자열 처리함수

```python
python = "Python is Amazing"
print(python.lower()) # 소문자로 변경
print(python.upper()) # 대문자로 변경
print(python[0].isupper()) # 0번째가 대문자니? True 
print(len(python)) # python의 길이는 몇? 6
print(python.replace("python","Java")) # python문자를 Java로 변경

index = python.index("n") # n은 몇번째에 있니?
print(index)
index = python.index("n", index+1) # index+1 번째부터 다음 n은 몇번째에 있니?

print(python.find("Java")) # 찾는 값이 없으면 -1 반환
print(python.index("Java")) # 찾는 값이 없으면 오류
print(python.count("n")) # n은 몇번 등장하니?
```

​	

## 문자열 포맷

* 방법1

```python
print("나는 %d살입니다." %20)
print("나는 %s을 좋아해요." %"파이썬")
print("Apple은 %c로 시작해요." %"A")
# s
print("나는 %s살입니다." %20)
print("나는 %s색과 %s색을 좋아해요." %("파란","빨간"))
```

* 방법2

```python
print("나는 {}살입니다.".format(20))
print("나는 {}색과 {}색을 좋아해요".format("파란","빨간"))
print("나는 {1}색과 {0}색을 좋아해요".format("파란","빨간"))
```

* 방법3

```python
print("나는 {age}살이며 ,{color}색을 좋아해요.".format(age=20,color="빨간"))
print("나는 {age}살이며 ,{color}색을 좋아해요.".format(color="빨간",age=20))
```

* 방법4

```python
age=20
color="빨간"
print(f"나는 {age}살이며 , {color}색을 좋아해요.")
```



## 탈출문자

```python
# \n : 문장 내에서 enter기능(다음 줄로)
print("백문이 불여일견 \n백견이 불여일타")

# \", \' : 문장 내에서 따옴표
# 저는 "나도코딩"입니다. 출력
print("저는 \"나도코딩\"입니다.")


# \\ : 문장 내에서 \

# \r : 커서를 맨 앞으로 이동
print("Red Apple\rPine") # Pine Apple이 출력됨

# \b : 백스페이스 (한글자 삭제)
# \t : tab(탭)

```



## Q.퀴즈

​	사이트별로 비밀번호를 만들어 주는 프로그램을 작성하시오.

예) http://naver.com

규칙1 : http:// 부분은 제외 => naver.com

규칙2 : 처음 만나는 점(.) 이후 부분은 제외 => naver

규칙3: 남은 글자 중 처음 처음 세자리 + 글자 갯수 + 글자 내 'e' 갯수 = "!" 로 구성

​							(nav)									(5)						(1)				(1)		

예) 생성된 비밀번호 : nav51!

* 내 풀이

```python
domain = "http://naver.com"
slash = domain.index("/")+2
rule1 = domain[slash:]
rule2 = rule1.index(".")
rule2 = rule1[:rule2]
rule3 = rule2[:3]
password = rule3+str(len(rule2))+str(rule2.count("e"))+"!"
print(f"{domain}의 비밀번호는 {password}입니다.")
```



* 강의 풀이

```python
url = "http://naver.com"
my_str = url.replace("http//","") # 규칙1
my_str = my_str[:my_str.index(".")] # 규칙2
password = my_str[:3] + str(len(my_str)) + str(my_str.count("e")) + "!"
print("{0} 의 비밀번호는 {1} 입니다.".format(url,password))
```

