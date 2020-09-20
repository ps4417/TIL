# 조건문

## if 분기문, 중첩문

* `==`, `!=` 

```python
x=7
if x==7:    # x가 7이면 Lucky 출력하세요.
    print("Lucky")
# => Lucky


x=7
if x!=7:   # x가 7이 아니면 Lucky 출력하세요.
    print("Lucky")
#=> (출력 x)

```



* 중첩문

```python
x=15
if x>=10:
    if x%2==1:
        print("10이상의 홀수")
# => 10이상의 홀수 
```



* 논리연산자 사용

```python
x=7
if x>0 and x<10:
    print("10보다 작은 자연수")
# => 10보다 작은 자연수

x=7
if 0<x<10:			# <- 파이썬은 이렇게도 가능
    print("10보다 작은 자연수")   
# => 10보다 작은 자연수
```



* if, else (분기문)

```python
x=10
if x>0:
    print("양수")
else:
    print("음수")  
```



* 다중 if문

```python
x=93
if x>=90:
    print("A")
elif x>80:
    print("B")
elif x>=70:
    print("C")
elif x>=60:
    print("D")
else:
    print("F")

# => A
```

