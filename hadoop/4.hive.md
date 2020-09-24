# 하이브

실제 데이터는 datanode에 넣어놓고 분석은 sql로 한다.

Mariadb에는 구조만 넣어 놓는다.

![하이브](md-images/%ED%95%98%EC%9D%B4%EB%B8%8C.PNG)

## 마리아 DB 설치

들어가서 다운로드 https://downloads.mariadb.com/MariaDB/mariadb-10.0.15/yum/centos7-amd64/rpms/

(다운로드 및 환경설정 참고 링크 https://www.notion.so/Maria-DB-d96d3ec34d7e488ebe1328da57f31153)

hive_db database 생성

hive 계정 및 비번 111111

## 하이브 설치

wget https://archive.apache.org/dist/hive/hive-1.0.1/apache-hive-1.0.1-bin.tar.gz

[root@mainserver 다운로드]# mv apache-hive-1.0.1-bin hive [root@mainserver 다운로드]# cp -r hive /usr/local

cd hive/usr/local→ cd hive → cd conf 파일로 들어가

ls

vi hive-site.xml 치고 밑에 거 입력하고 wq로 저장해

- hive-site.xml

```xml
<?xml version="1.0"?>

<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

<configuration>

<property>

<name>hive.metastore.local</name>

<value>false</value>

<description>controls whether to connect to remove metastore server or open a new metastore server in Hive Client JVM</description>

</property>

<property>

<name>javax.jdo.option.ConnectionURL</name>

<value>jdbc:mariadb://localhost:3306/hive_db?createDatabaseIfNotExist=true</value>

<description>JDBC connect string for a JDBC metastore</description>

</property>

<property>

<name>javax.jdo.option.ConnectionDriverName</name>

<value>org.mariadb.jdbc.Driver</value>

<description>Driver class name for a JDBC metastore</description>

</property>

<property>

<name>javax.jdo.option.ConnectionUserName</name>

<value>hive</value>

<description>username to use against metastore database</description>

</property>

<property>

<name>javax.jdo.option.ConnectionPassword</name>

<value>111111</value>

<description>password to use against metastore database</description>

</property>

</configuration>
```

p.569

[root@mainserver ~]# hadoop fs -mkdir /tmp [root@mainserver ~]# hadoop fs -mkdir /user/root/warehouse [root@mainserver ~]# hadoop fs -chmod 777 /tmp [root@mainserver ~]# hadoop fs -chmod 777 /user/root/warehouse [root@mainserver ~]# hadoop fs -mkdir /tmp/hive [root@mainserver ~]# hadoop fs -chmod 777 /tmp/hive

### HIVE DIRECTORY SETTING

hadoop fs -mkdir /tmp

hadoop fs -mkdir user/root/warehouse

hadoop fs -chmod 777 /tmp

hadoop fs -chmod 777 /user/root/warehouse

hadoop fs -mkdir /tmp/hive

hadoop fs -chmod 777 /tmp/hive

**hive 치고 들어가!**

(cmd창 두개 띄워서 해! 여기선 설명을 위해 cmd1, cmd2라고 설명할게)

HIVE에 데이터 테이블 작성 및 파일 업로드 (cmd1)

CREATE TABLE HDI(id INT, country STRING, hdi FLOAT, lifeex INT, mysch INT,eysch INT, gni INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

카페 게시판에서 파일 hive예제에 있는 hdi-data.csv 다운받고

(cmd2에서 해)

cd 다운로드 파일에서

→ mv hdi-data.csv hdi.txt

→ mv hdi.txt /root

(cmd1에서)

hive>load data local inpath '/root/hdi.txt' into table HDI;

hive> select * from hdi limit 5;

네이버 카페에서 air data 복사해서 (cmd1 hive> 에 붙여넣기)

- air data

***hive>***

LOAD DATA LOCAL INPATH '/root/2006.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2006');

***hive>***

LOAD DATA LOCAL INPATH '/root/2007.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2007');

***hive>***

LOAD DATA LOCAL INPATH '/root/2008.csv' OVERWRITE INTO TABLE airline_delay PARTITION (delayYear='2008');

예제1)

년도 별 출발지연시간, 도착지연 시간의 평균을 구하시오

SELECT Year, avg(ArrDelay), avg(DepDelay) FROM airline_delay GROUP BY Year;

예제2)

년도, 월별 출발, 도착 지연시간의 평균을 구하시오

SELECT Year, Month, avg(ArrDelay), avg(DepDelay) FROM airline_delay WHERE delayYear=2006 GROUP BY Year, Month ORDER BY YEAR,MONTH;

예제3)

SELECT Year, Month, avg(ArrDelay), avg(DepDelay) FROM airline_delay WHERE delayYear=2006 OR delayYear=2007 GROUP BY Year, Month ORDER BY Year,Month;