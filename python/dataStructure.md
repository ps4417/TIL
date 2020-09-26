# 자료구조

## 1. 리스트["",""]

* **append** , **pop** , **sort** , **reverse** 

```python
subwqy = ["유재석", "조세호", "박명수"]
print(subway)

# 조세호씨가 몇번째 칸에 타고 있는가?
print(subway.index("조세호"))  # 1

# 하하씨가 다음 정류장에서 다음 칸에 탐
subway.append("하하")
print(subway)

# 정형돈씨를 유재석 / 조세호 사이에 태워봄
subway.insert(1,"정형돈") # subway[1] 에 태우는 것

# 지하철에 있는 사람을 한명씩 뒤에서 꺼냄
print(subway.pop())

# 같은 이름의 사람이 몇명 있는지 확인
print(subway.count("유재석"))

# 정렬
num_list = [5,2,4,3,1]
num_list.sort()
print(num_list)

# 순서 뒤집기 가능
num_list.reverse()

# 모두 지우기
num_list.clear()

#리스트 확장
num_list = [5,2,4,3,1]
mix_list = ["조세호",20,True]

num_list.extend(mix_list)
print(num_list)
# [5,2,4,3,1,'조세호',20,True]
```



## 2. 사전 {key:value}

```python
cabinet = {3:"유재석", 100:"김태호"}
print(cabinet[3])
print(cabinet[100])

print(cabinet.get(3))

# 만약 없는 값을 print하면?
# 1번 경우
print(cabinet[5])  # 오류가 나고 프로그램 종료

# 2번 경우
print(cabinet.get(5)) # none으로 출력된다.
	# 다음과 같이 쓰면 
print(cabinet.get(5,"사용가능"))  # 사용가능 으로출력


print(3 in cabinet) # True
print(5 in cabinet) # False

cabinet = {"A-3":"유재석", "B-100":"김태호"}
# 새 손님
cabinet["A-3"] = "김종국"   # A-3가 김종국으로 대체됨
cabinet["C-20"] = "조세호"  # C-20 : 조세호 추가된다.

# 손님이 갔을 경우
del cabinet["A-3"]

# key 들만 출력
print(cabinet.keys())

# value 들만 출력
print(cabinet.values())

# key, valuse 쌍으로 출력
print(cabinet.items())

# 폐점
cabinet.clear()
```



## 3. 튜플("","")

* 변경하지 않는 목록을 사용할 때 튜플 사용
* 값을 추가하거나 변경 불가능
* ~~menu.add("생선가스") 불가능!!!!~~

```python
menu = ("돈까스","치즈까스")
print(menu[0])
print(menu[1])
```

```python
# name = "김종국"
# age = 20
# hobby = "코딩"

(name,age,hobby)=("김종국",20,"코딩")
print(name,age,hobby)
```



## 4. 세트(set) {1,2,3,4,5}

* 집합 (값만 나열한다.)
* `중복 안됨`, `순서 없음`

```python
my_set = {1,2,3,3,3}
print(my_set)  # {1,2,3} # 중복허용 안되기 때문에
```

* **add** ,**remove**

```python
java = {"유재석","김태호","양세형"}
python = set(["유재석","박명수"])

# 교집합 (java와 python 모두 할 수 있는 사람)
print(java & python)  # {'유재석'}
print(java.intersection(python))

# 합집합 (java 할 수 있거나, python 할 수 있는 개발자)
print(java | python)  # {'김태호','박명수','유재석','양세형'}
print(java.union(python))

# 차집합 (java는 할 수 있지만 python은 못하는 개발자)
print(java-python)
print(java.difference(python))

# python 할줄 아는 사람 추가됨(add)
python.add("김태호")
print(python)  # {'박명수','김태호','유재석'}

# java를 잊었어요 (remove)
java.remove("김태호")
```



## 자료구조의 변경

```python
menu = {"커피","우유","주소"}
print(menu, type(menu))  # <class 'set'>

menu = list(menu)
print(menu, type(menu))  # <class 'list'>

menu = tuple(menu)
print(menu, type(menu))  # <class 'tuple'>

menu = set(menu)
print(menu, type(menu))  # <class 'set'>
```

