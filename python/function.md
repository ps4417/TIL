# 함수만들기

* 기본 함수 

```python
def add(a,b):
    c=a+b
    print(c)

add(3,2)
# 5
-------------
def add(a,b):
    c=a+b
    return c


x=add(3,2)
print(x)
# 5
-------------

def add(a,b):
    c=a+b
    d=a-b
    return c,d

print(add(3,2))
# (5, 1)
```







* 소수만 출력하는 함수 만들기

```python
def isPrime(x):
    for i in range(2,x):
        if x%i==0:
            return False
    return True


a=[12,13,7,9,19]
for y in a:
    if isPrime(y):
        print(y, end=' ' )
        
        
# 13 7 19 
```

