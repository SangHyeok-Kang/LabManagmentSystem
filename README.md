# 실습실 관리 시스템

<div align = "center">
  <img src = "https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/banner.png" width="80%">
</div>

<br>

## 💡 주제

- 동의대학교 컴퓨터소프트웨어공학과 실습실 관리 시스템
- 실습실 등록, 예약 기능 제공

<br>

## 📝 설명

- **실습실 관리 시스템**은 기존에 정해져있지 않던 **실습실 이용 체계**를 확립하고 사용자들이 편리하게 실습실을 사용할 수 있도록 도와주는 시스템 입니다.
- **시간표 입력, 실습실 예약, 실습실 사용 현황 조회** 등  현재의 실습실 정보에 대한 정보를 제공하여 관리자가 실습실을 관리함에 있어 용이하도록 시스템을 제공합니다.

<br>

<!--
## 🖥️ 시스템 구조도

<div align = "center">
  <img src = "https://github.com/SangHyeok-Kang/DataRepository/blob/29a3c9595cba646c1ac0ff9333aaf904987aa05f/%EC%98%A4%ED%94%84%EB%9D%BC%EC%9D%B8%20%EA%B5%90%EC%9C%A1%20%EB%B3%B4%EC%A1%B0%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EC%8B%9C%EC%8A%A4%ED%85%9C%EA%B5%AC%EC%A1%B0%EB%8F%84.png">
</div>
-->

## 🖥️ 기술 스택
- **Language** : JAVA JDK 11(Swing)
- **Database** : MySQL 8.0.29
- **Tools** : Apache NetBeans IDE, Git

<br>

## ✨ 주요 기능

- **회원 관리**
    - 관리자는 학기마다 새로운 토큰을 발급할 수 있습니다.
    - 사용자들은 발급한 토큰을 통해서만 회원가입을 진행하며, 관리자의 승인 하에 시스템을 사용 가능합니다.
    - 부적절한 사용자에 대해서 관리자는 이용제한 조치를 가할 수 있습니다.
- **실습실 관리**
    - 관리자와 교수는 실습실별 정규 시간표를 입력 해야하고, 특강 또는 세미나를 신청할 수 있습니다.
    - 실습실 사용현황 조회를 하여 현재 실습실 사용 현황을 조회할 수 있습니다.
- **실습실 조회 및 예약**
    - 학생은 현재의 실습실 잔여 좌석을 조회할 수 있으며 예약을 할 수 있습니다.
    - 17시 이전의 예약건의 경우 자동으로 예약 완료 처리가 되며, 17시 이후 예약건의 경우 관리사의 예약 승인 하에 예약 완료가 됩니다.
    - 학생은 본인이 예약한 좌석에 대해 위치와 남은시간, 연장 가능 시간을 조회 가능하며, 사용 종료 30분 전까지 사용 연장이 가능합니다.
- **게시판 관리**
    - 관리자는 공지사항을 등록할 수 있습니다.
    - 학생은 부적절한 사용자에 대해 신고를 할 수 있습니다.
 
<br>

## 📙 WBS
<div align = "center">
  <img src = "https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/wbs.png">
</div>

## 📗 Class Diagram
<div align = "center">
  <img src = "https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/classdiagram.png">
</div>

## 📘 ERD
> **테이블 구조도**
<div align="center">
   <img src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%ED%85%8C%EC%9D%B4%EB%B8%94%20%EA%B5%AC%EC%A1%B0%EB%8F%84.png"  width="70%"/>&nbsp;&nbsp;
</div>

<br>

> **ER 다이어그램**
<div align="center">
   <img src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/erd.png"  width="70%"/>&nbsp;&nbsp;
</div>

## 📲 UI

<div align = "center">
  
| 메인 페이지 | 강의 시간표 등록 |
| :---: | :---: |
| <img width="350" alt="메인 페이지" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EB%A9%94%EC%9D%B8%ED%8E%98%EC%9D%B4%EC%A7%80.png"> | <img width="350" alt="강의 시간표 등록" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EA%B0%95%EC%9D%98%20%EC%8B%9C%EA%B0%84%ED%91%9C%20%EB%93%B1%EB%A1%9D.png"> |

| 실습실 예약 | 예약 승인 |
| :---: | :---: |
| <img width="350" alt="실습실 예약" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EC%98%88%EC%95%BD.png"> | <img width="350" alt="예약 승인" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EC%98%88%EC%95%BD%20%EC%8A%B9%EC%9D%B8.png"> |

| 예약 연장 | 학생명단 관리 |
| :---: | :---: |
| <img width="350" alt="예약 연장" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%EC%98%88%EC%95%BD%20%EC%97%B0%EC%9E%A5.png"> | <img width="350" alt="학생명단 관리" src="https://github.com/SangHyeok-Kang/DataRepository/blob/9959cfde99871c59014f9cb0ef0bd35689999b63/%EC%8B%A4%EC%8A%B5%EC%8B%A4%20%EA%B4%80%EB%A6%AC%20%ED%94%8C%EB%9E%AB%ED%8F%BC/%ED%95%99%EC%83%9D%20%EB%AA%85%EB%8B%A8%20%EA%B4%80%EB%A6%AC.png"> |

</div>

<br>

## 👨‍👦팀 구성
<div align="center">

|김부성 ``` Fullstack Dev ```| 강상혁 ```Backend Dev``` | 송준섭 ```Backend Dev``` |
|:-:|:-:|:-:|
|<img src="https://avatars.githubusercontent.com/u/93811002?v=4" width=130>| <img src="https://avatars.githubusercontent.com/u/104892909?s=400&v=4" width=130> | <img src="https://avatars.githubusercontent.com/u/115469010?v=4" width=130> |
|[@Bu-Sung](https://github.com/Bu-Sung)| [@SangHyeok-Kang](https://github.com/SangHyeok-Kang)| [@JunSeop-Song](https://github.com/Junseop-Song)|

</div>
