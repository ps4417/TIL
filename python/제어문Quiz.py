from random import *
customers = range(1,51)

cnt = 0
for customer in customers:
    time = randrange(5,51)
    if 5<= time <= 15:  # 5~15이내의 손님(매칭 성공), 탑승 승객 수 증가 처리
        print("[0] {0}번째 손님 (소요시간 : {1}분)".format(customer,time))
        cnt +=1
    else: # 매칭 실패한 경우
        print("[ ] {0}번째 손님 (소요시간 : {1}분)".format(customer,time))

print("총 탑승 승객 : {0} 분".format(cnt))


