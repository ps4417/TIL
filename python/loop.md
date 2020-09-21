# 반복문을 이용한 문제풀이

## while, for, break, continue

```python

i=1
while i<=10:
    print(i)
    i=i+1 

i=10
while i>=1:
    print(i)
    i=i-1


i=1
while True:
    print(i)
    if i==10:
        break
    i+=1


for i in range(1,11):
    if i%2==0:
        continue
    print(i)


for i in range(1,11):
    print(i)
    if i>15:
        break
else:
    print(11)
  
```



##  홀수 값 구하기

```python
n=int(input())
for i in range(1,n+1):
    if i%2==1:
        print(i)
```



## 합 구하기

```python

n=int(input())
sum=0
for i in range(1,n+1):
    sum=sum+i
print(sum)
```



## 약수 구하기

```python
n=int(input())
for i in range(1,n+1):
    if n%i==0:
        print(i, end=' ')
```



## 중첩반복문

> 별 찍기

```python
for i in range(5):
    for j in range(i+1):
        print('*',end=' ')
    print()
    
* 
* * 
* * * 
* * * * 
* * * * *     
    
    
for i in range(5):
    for j in range(5-i):
        print('*', end=' ')
    print()    
    
* * * * * 
* * * * 
* * * 
* * 
* 

```

