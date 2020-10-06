library(readxl)
# 01
mid_exam <- read_excel("mid_exam.xlsx")
mid_exam <- as.data.frame(mid_exam)
mid_exam <- rename(mid_exam,MATH_MID=MATH,ENG_MID=ENG)

# 02
final_exam <- read_excel("final_exam.xlsx")
final_exam <- as.data.frame(final_exam)
final_exam <- rename(final_exam,MATH_FINAL=MATH,ENG_FINAL=ENG)

# 03
total_exam <- inner_join(mid_exam,final_exam,by="ID")

# 04
total_exam$MATH_AVG <- rowMeans(total_exam %>% select(MATH_MID,MATH_FINAL),na.rm = T) 
total_exam$ENG_AVG <- rowMeans(total_exam %>% select(ENG_MID,ENG_FINAL),na.rm = T) 

# 05
total_exam$TOTAL_AVG <- rowMeans(total_exam %>% select(MATH_AVG,ENG_AVG),na.rm = T)  

#06
math <- mean(total_exam$MATH_AVG)
eng <- mean(total_exam$ENG_AVG)

# 07
total_exam %>% filter(MATH_MID>=80 & ENG_MID>=90)

# 08
boxplot(total_exam$MATH_AVG,total_exam$ENG_AVG)
