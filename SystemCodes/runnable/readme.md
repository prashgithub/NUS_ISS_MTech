# How to build & run a standalone application

## Prerequisite

```bash
JAVA_HOME is set , JDK 8 or above
Maven is installed.
Mysql 8 is installed and excute NUS_ISS_MTech\SystemCodes\ispm\src\main\resources\data_mysql_v2.sql
Python 3 (below 3.8) is installed and Orange 3 (pip install Orange3)
```

## Build artifacts 
Build workflow

```bash
    cd NUS_ISS_MTech\SystemCodes\Integrated_Shield_Plan_Master
    mvn clean package 
	cp NUS_ISS_MTech\SystemCodes\Integrated_Shield_Plan_Master\target\Integrated_Shield_Plan_Master-1.0.0.jar NUS_ISS_MTech\SystemCodes\runnable\
```
build opta

```bash
    cd NUS_ISS_MTech\SystemCodes\OptaISP
    mvn clean package 
	cp NUS_ISS_MTech\SystemCodes\OptaISP\target\OptaISP-1.0.0.jar NUS_ISS_MTech\SystemCodes\runnable\
```	

build GUI

```bash
    cd NUS_ISS_MTech\SystemCodes\ispm
    cp pom_pack_war.xml.txt pom.xml
    mvn clean package 
	cp NUS_ISS_MTech\SystemCodes\ispm\target\mr-ispm-1.0.0.war NUS_ISS_MTech\SystemCodes\runnable\
```	

## Run application

```bash
   NUS_ISS_MTech\SystemCodes\runnable\startWebApp.bat
```	