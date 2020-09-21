# 람다 함수(lambda)

```python
def plus_one(x):
    return x+1

print(plus_one(1))
# 2
```



```python
plus_two=lambda x: x+2
print(plus_two(1))
# 3
```



* lambda 사용

> lambda 는 다음과 같이 사용되는 경우가 多

```python
def plus_one(x):
    return x+1

a=[1,2,3]
print(list(map(plus_one, a)))
#[2,3,4]

print(list(map(lambda x:x+1, a)))
#[2,3,4]
```

