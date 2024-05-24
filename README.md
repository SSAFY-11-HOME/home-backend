# 🏠 집공략 [ 주택 매물 검색 서비스 ]

<div align="center">
  <img src="https://github.com/ZipGongLyag/.github/assets/85926257/74682b95-7faa-437d-9b8b-230be70d903a" style="width: 240px; height: 240px">
  <h2>집공략</h2>
</div>

## 🗃️ 프로젝트 소개
**집공략**은 공공데이터 포털의 [국토교통부_아파트매매 실거래 상세 자료](https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15057511)와 [Kakao Map API](https://apis.map.kakao.com/web/sample/)를 활용한 주택 매물 검색 서비스입니다.

## 📽️ 시연 영상

🔗 [🏠집공략! - 주택 매물 검색 서비스🏠 시연 영상](https://youtu.be/hOWh54xfU3M)

![image](https://github.com/ZipGongLyag/.github/assets/85926257/9b36e2af-d93b-4cbf-b5a7-6256af45eb10)

## 멤버
|Role|Front-End|Back-End|
|:-:|:-:|:-:|
|Member|[김상현](github.com/isyaksh)|[이강산](github.com/clintkslee)|

## 🛠️ 기술 스택

### 🔧 프론트엔드

<div flex="float" align="center">
  <h3>⚡️ SKILLS </h3>
  <img alt="Html" src ="https://img.shields.io/badge/HTML5-E34F26.svg?&style=for-the-badge&logo=HTML5&logoColor=white"/>
  <img alt="Css" src ="https://img.shields.io/badge/CSS3-1572B6.svg?&style=for-the-badge&logo=CSS3&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/JavaScriipt-F7DF1E.svg?&style=for-the-badge&logo=JavaScript&logoColor=black"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/Vue.js-4FC08D.svg?&style=for-the-badge&logo=Vue.js&logoColor=black"/>
</div>

### 📄 라이브러리
```json
{
  "axios": "^1.6.8",
  "bootstrap": "^5.3.3",
  "dotenv": "^16.4.5",
  "js-base64": "^3.7.7",
  "pinia": "^2.1.7",
  "vue": "^3.4.21",
  "vue-router": "^4.3.2"
}
```

### 🔧 백엔드

<div flex="float" align="center">
  <h3>⚡️ SKILLS </h3>
  <img alt="JavaScript" src ="https://img.shields.io/badge/Spring-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/Spring MVC-6DB33F.svg?&style=for-the-badge&logo=Spring&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/Spring Boot-6DB33F.svg?&style=for-the-badge&logo=Spring Boot&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/Maven-C71A36.svg?&style=for-the-badge&logo=Apache Maven&logoColor=white"/>
  
  <img alt="JavaScript" src ="https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MySQL&logoColor=white"/>
</div>

### 📄 라이브러리

```json
{
  "spring-boot-starter-web": "@latest",
  "mybatis-spring-boot-starter": "3.0.3",
  "spring-boot-devtools": "@latest",
  "mysql-connector-j": "@latest",
  "lombok": "@latest",
  "spring-boot-starter-tomcat": "@latest",
  "spring-boot-starter-test": "@latest",
  "mybatis-spring-boot-starter-test": "3.0.3",
  "springdoc-openapi-starter-webmvc-ui": "2.5.0",
  "jjwt-api": "0.11.5",
  "jjwt-impl": "0.11.5",
  "jjwt-jackson": "0.11.5",
}
```

### 🔧 인프라

<div flex="float" align="center">
  <img alt="JavaScript" src ="https://img.shields.io/badge/AWS-232F3E.svg?&style=for-the-badge&logo=Amazon AWS&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/EC2-FF9900.svg?&style=for-the-badge&logo=Amazon EC2&logoColor=white"/>
  <img alt="JavaScript" src ="https://img.shields.io/badge/GitLab-FC6D26.svg?&style=for-the-badge&logo=GitLab&logoColor=white"/>
</div>

## 🖥️ 화면구성

|메인 페이지|
|-|
|![image](https://github.com/ZipGongLyag/.github/assets/85926257/ff626db1-83b7-4109-929a-92be84d92534)|
|- **카카오 지도 API**를 기반으로 **사이드 네비게이션**을 통해 **주변 실거래가 정보 내역**을 조회할 수 있습니다.|

<br/>

|검색 페이지|
|-|
|![캡처](https://github.com/ZipGongLyag/.github/assets/85926257/232d502a-672f-421a-a0b3-5a6b4e513173)|
| - **검색 키워드** 및 **지도의 중앙 위.경도값**을 기준으로 **주변 실거래가 정보 내역**을 조회할 수 있습니다.|

<br/>

|로그인 페이지|
|-|
|![image](https://github.com/ZipGongLyag/.github/assets/85926257/c12a4b61-e015-4752-a8ee-6a35b4ea0778)|
| - **ID**와 **Password** 기반의 로그인 기능을 구현하였습니다. <br/> - 로그인 성공 시, **JWT**를 발급하여 **사용자의 Role**에 따라 API 사용을 제한하였습니다.|

<br/>

|회원가입 페이지|
|-|
|![image](https://github.com/ZipGongLyag/.github/assets/85926257/165d11ee-282d-4d2d-b93c-de750811f069)|
| - 사용자로부터 아이디, 비밀번호, 이름, 이메일을 제공받아 회원가입 기능을 구현하였습니다. |

<br/>

|Q&A 페이지|
|-|
|![캡처](https://github.com/ZipGongLyag/.github/assets/85926257/b1127468-7bfa-4d3a-8f4b-3474bb618848)|
| - **사용자와 관리자가 소통할 수 있는 창구**인 Q&A 게시판 기능을 구현하였습니다.|

<br/>

|부동산 뉴스 API|
|-|
|![캡처](https://github.com/ZipGongLyag/.github/assets/85926257/f14705c5-ac6b-412e-833f-1287fe81fc24)|
| - **네이버 API**를 통해 네이버 검색의 뉴스 검색 결과를 제공하는 기능을 구현하였습니다.|

<br/>

## 📄 Diagram

### ERD
![image](https://github.com/ZipGongLyag/.github/assets/85926257/a33cb4f8-f801-4a24-95bc-e65f858c47b9)

### Usecase Diagram

#### 1. 거래정보 조회 유스케이스
![image](https://github.com/ZipGongLyag/.github/assets/85926257/f92b5aa0-4c7e-4f54-893a-423632ef9879)

#### 2. 회원관리 유스케이스
![image](https://github.com/ZipGongLyag/.github/assets/85926257/af17519c-f866-4526-9d4c-cafc1e093545)

#### 3. QnA 게시판 유스케이스
![image](https://github.com/ZipGongLyag/.github/assets/85926257/9bca162f-9771-430e-bd02-09cc5fad62f4)



