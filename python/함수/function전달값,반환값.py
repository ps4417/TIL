def open_account():
    print("새로운 계좌가 생성되었습니다.")

def deposit(balacne,money): # 입금
    print("입급이 완료되었습니다. 잔액은 {0}원 입니다.".format(balacne+money))
    return balacne+money

def withdraw(balance,money):
    if (balance>=money):
        print("출금이 완료되었습니다. 잔액은 {0}원입니다.".format(balance-money))
        return balance-money
    else:
        print("출금이 완료되지 않았습니다. 잔액은 {0}원입니다.".format(balance))
        return balance

def withdraw_night(balance,money): #저녁에 출금(수수료)
    commission = 100
    return commission, balance-money-commission                                         

balance = 0
balance = deposit(balance,1000)
# balance = withdraw(balance,500)
commission,balance = withdraw_night(balance,500)
print("수수료 {0}원이며, 잔액은  {1}원입니다.".format(commission,balance))

