# 변수

## 변수명 정하기

1. 영문과 숫자, _ 로 이루어진다.
2. 대소문자를 구분한다.
3. 문자나, _ 로 시작한다.
4. 특수문자를 사용하면 안된다. (&, %등)
5. 키워드를 사용하면 안된다.(if, for 등)

> `#` 은 한 줄 주석, ```로 감싸주면 단락 주석



## 변수와 출력함수

```python
a=1
A=2
A1=3
_b=4
print(a,A,A1,_b)  # => 1,2,3,4
a,b,c = 3,2,1
print(a,b,c)  # => 3 2 1

```

* 값 교환

```python
a, b=10, 20
print(a,b)  # => 10 20
a,b =b,a
print(a,b) # => 20 10
```

* 변수 타입

``` python
a=12345
print(type(a))

# => <class 'int'>
```

> int 정수형
>
> float 실수형
>
> str 문자열



* 출력방식

```python
print("number") # => number
a,b,c = 1,2,3
print(a,b,c, sep=',') # => 1,2,3
print(a,b,c, sep='') # => 1 2 3
print(a,b,c, sep='\n') 
# => 1
# => 2
# => 3

print(a)  # print에는 자동적으로 줄바꿈 기능이 포함
print(b)
print(c)
# => 1
# => 2
# => 3

print(a, end=' ')  
print(b, end=' ')
print(c, end=' ')
# => a b c
  
```





## 변수입력과 연산자

* 변수입력

```python
a=input("숫자를 입력하세요 :")
print(a)
# => 숫자를 입력하세요 :

a, b=input("숫자를 입력하세요 : ").split()
a=int(a)
b=int(b)
print(a+b)
# => 숫자를 입력하세요 :   (2 3 입력)
# => 5

a, b=map(int,input("숫자를 입력하세요 : ").split())
print(a+b)
# => 숫자를 입력하세요 :   (2 3 입력)
# => 5
```

* 연산자

```python
a, b=map(int,input("숫자를 입력하세요 : ").split())
print(a+b)
print(a-b)
print(a*b)
print(a/b)
print(a//b) # // 는 몫을 구하는 것 
print(a%b)	# %  는 나머지를 구하는것
print(a**b) # ** 은 거듭제곱
```





