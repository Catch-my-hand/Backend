# Backend

This repository is the backend repository for the project and is responsible for the overall API of the application.

## 💁‍♂️ Features

내손을잡아 서버에서는 다음과같은 기능을 제공합니다. 
~~~~~~~~~~~~~~~~~
1. 유저 - 회원가입, 로그인, id찾기
2. 가정분양 - 게시물 작성, 게시물 삭제, 게시물 수정, 최신 게시물로 UP하기, 게시물 타이틀 검색 
3. 유기견분양 - 경기도 데이터드림의 유기동물보호 센터의 OPEN API를 이용한, 최신 데이터 제공 
~~~~~~~~~~~~~~~~~~

## 💻 Tech Stack 
~~~~~~~~~~~~~~~~~~~~~~~
Backend : Spring Boot [ JdbcTemplate, lombok, MySQL-Connector, slack-api, springdoc-openapi, okhttp ]
Database : MySQL 
Server : RDS 
DevOps : Docker, Grafana, Prometheus, AlertManager 
API Docs : Swagger 
Etc : Slack, Github 
~~~~~~~~~~~~~~~~~~~~~~~~

## 📕 Initialization

- clone repository
~~~~~~~~~~
$ git clone https://github.com/Catch-my-hand/Backend.git
$ cd Backend
~~~~~~~~~~~~

- Docker build up 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
docker-compose up --build
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

## Member 

|         👨‍💻 김의빈          |          👩🏻‍💻 홍수경            |   
| ------------------------| ------------------------ | 
|       Backend           |          Backend         |    
|        DB                  |         RDS Setting            |  
|        DevOps                 |                          |     


