library(readxl)
library(psych)
library(descr)
y16 <- read_excel("C:/R/day02/y16.xlsx")
y17 <- read_excel("C:/R/day02/y17.xlsx")

y16 <- as.data.frame(y16)
y17 <- as.data.frame(y17)

bind <- left_join(y17,y16, by="ID")
bind2 <- left_join(y16,y17, by="ID")
bind_inner <- inner_join(y17,y16,by = "ID")
bind_full <- full_join(y17,y16,by="ID")


bind_full$SUM_AMT <- rowSums(bind_full %>% select(AMT17,AMT16),na.rm = T)
bind_full$SUM_CNT <- rowSums(bind_full %>% select(Y17_CNT,Y16_CNT),na.rm = T)

r4 <- bind_full %>% group_by(AREA) %>% summarise(AVG_AMT = mean(SUM_AMT),AVG_CNT=mean(SUM_CNT))
r4 <- as.data.frame(r4)

r5 <- bind_inner
r4$AREA <- ifelse(is.na(r4$AREA),'NONE',r4$AREA)

r4 <- r4 %>% arrange(desc(AVG_AMT))
summary(r4)

describe(r4)

hist(r5$AGE)
boxplot(r5$AGE)


