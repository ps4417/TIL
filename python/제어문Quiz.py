from random import*
    ctn = 0 
    for i in range(1,51):
        time = randrange(5,51)
        if 5 <= time <= 15:
            print("[0] {0}번째 손님(소요시간 : {1}분)".format(i,time))
