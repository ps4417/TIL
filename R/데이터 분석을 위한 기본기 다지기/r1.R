sh <- read.csv("shop.txt",
               header=F,
               stringsAsFactors = F,
               fileEncoding = "UTF-8")
#colnames(sh) <- c("ID","NAME","AGE","TEMP","PRICE","QT")
tt <- sh$PRICE*sh$QT
sh$TOTAL <- tt
write.csv(
  sh,
  file="shoptotal.csv",
  row.names = T
  
)
save(sh,file="shoptotal.rda")
sht <- load("shoptotal.rda")
tt <- rowSums(sh[,c(4:7)],na.rm=T)

ls(sh)
