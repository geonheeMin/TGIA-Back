# 📖 부기마켓 - 우리 학교 학생들을 위한 중고거래 앱


<img width="1920" alt="bugi" src="https://github.com/dudtlr/TGIA-Back/assets/95243456/9cd0e417-f3bd-448a-9bf9-1d14782c329c">
<br>

## 📚 목차
- 프로젝트 소개
- 팀원 구성 및 역할
- 개발 환경
- 프로젝트 구조
- ERD 설계도
- 기능 별 화면 및 소개


<br>

## 📝 프로젝트 소개
기존의 중고 거래 어플을 이용해본 경험이 있는 사람들은 본인이 구매하려는 물건이 하자가 있는 불량품은 아닐지, 아니면 사기를 당하여 금전적인 손해를 본다든가 직거래시 폭행이나 협박 등의 범죄에 노출되지 않을까 걱정한 경험이 있다고 답하였고, 이러한 불안과 범죄로부터 한성대학교 학생들의 자유롭고 안전한 중고 거래 환경을 조성하고자 기존에 형성되어있는 중고 거래 어플을 한성대학교에 맞게 한성대학교의 마스코트 캐릭터인 상상부기와 디자인을 접목시킨 “부기마켓' 이라는 어플을 만드는 프로젝트를 진행하였습니다. 

<br>

## 👩‍👩‍👧‍👦 팀원 및 담당 파트
<div sytle="overflow:hidden;">
<table>
   <tr>
      <td colspan="2" align="center"><strong>Front-End Developer</strong></td>
      <td colspan="2" align="center"><strong>Back-End Developer</strong></td>
   </tr>
  <tr>
    <td align="center">
        <a href="https://github.com/LapinMin"><img src="https://avatars.githubusercontent.com/u/130971355?v=4" width="150px;" alt="민건희"/><br/><sub><b>민건희</b></sub></a>
    </td>
     <td align="center">
        <a href="https://github.com/LapinMin"><img src="https://avatars.githubusercontent.com/u/15971355?v=4" width="150px" alt="조용기"/><br/><sub><b>조용기</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/dudtlr"><img src="https://avatars.githubusercontent.com/u/1971154?v=4" width="150px" alt="전영식"/><br/><sub><b>전영식</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/MKSonny"><img src="https://avatars.githubusercontent.com/u/116620246?v=4" width="150px" alt="손민규"/><br/><sub><b>손민규</b></sub></a>
     </td>
  <tr>
</table>
</div>

> 민건희 : React Native 사용 모바일 애플리케이션 공동 개발 <br>
> 조용기 : React Native 사용 모바일 애플리케이션 공동 개발 <br>
> 전영식 : Spring Boot 사용 백엔드 애플리케이션 공동 개발 / Kakao Pay API 사용 결제 서비스 개발 <br>
> 손민규 : Spring Boot 사용 백엔드 애플리케이션 공동 개발 / Deep Learning을 활용한 카테고리 자동 추천 기능 개발 <br>

<br>

## 📌 개발 환경
<div>
    <table>
        <tr>
            <td colspan="2" align="center">
                Language
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/Dart-0175C2?style=for-the-badge&logo=Dart&logoColor=white"> 
                <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=openjdk&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Library & Framework
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/Flutter-02569B?style=for-the-badge&logo=Flutter&logoColor=white">
                <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
                <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                API
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/DATA.go.kr-00529B?style=for-the-badge&logo=D&logoColor=white"> 
                <img src="https://img.shields.io/badge/Kakao Map Api-FFCD00?style=for-the-badge&logo=kakao&logoColor=black"> 
                <img src="https://img.shields.io/badge/Kakao Mobility-FFCD00?style=for-the-badge&logo=kakao&logoColor=black"> 
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Server
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> 
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Database
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
                <img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                Tool
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
                <img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                etc.
            </td>
            <td colspan="4">
                <img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
                <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">
            </td>
        </tr>
    </table>
</div>

<br>

## 🔨 프로젝트 구조

<img width="1068" alt="image" src="https://github.com/dudtlr/TGIA-Back/assets/95243456/d3ebb8f3-230e-49df-b63f-d097955334df">

<br>

## 🔨 ERD 설계도


![Untitled](https://github.com/dudtlr/TGIA-Back/assets/95243456/939d7e28-6f8c-4355-88d8-fb232dada81d)

<br>




