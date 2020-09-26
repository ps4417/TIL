# LIst

## 배열

```python
a=[23,12,36,53,19]
print(a[:3])
```

> a[0], a[1], a[2] 값을 구할 수 있다.



```python
a=[23,12,36,53,19]
print(a[1:4])
```

> a[0], a[1] , a[2], a[3] 값을 구할 수 있다.



## 배열을 이용한 for문

```python
a=[23,12,36,53,19]
for i in range(len(a)):
    print(a[i], end=' ')
```

> len(a)=5 이므로  a[0]~ a[4]까지 출력



```python
a=[23,12,36,53,19]
for x in a:
    print(x, end=' ')
```

> for x in a 는 a 리스트에 있는 값들을 a[0]번부터 끝까지 출력



### 조건문 사용해 홀수 값 구하기

```python
a=[23,12,36,53,19]
for x in a:
    if x%2==1:
        print(x, end=' ')
```



## enumerate

```python
a=[23,12,36,53,19]
for x in enumerate(a):
    print(x)
```

> 튜플 값으로 출력된다.
>
> (0 ,23)
>
> (0 ,12)
>
> ...
>
> (0,19)

```python
for x in enumerate(a):
    print(x[0], x[1])
```

> 