# 프로젝트 이름 : SpringBoot CRUD Community

## 📚프로젝트 목적

SpringBoot를 이용하여 가벼운 CRUD와 로그인 회원가입 및
페이징처리, 파일처리을 연습해보는 목적인
1인 토이 프로젝트

## 목차
  - [ERD구성](#erd구성)
  - [Tools](#tools)
  - [작업 내용](#담당-작업)
    - [메인페이지 WIKI로 이동](#메인페이지--wiki로-이동)
    - [상담사등록 WIKI로 이동](#상담사-등록-wiki로-이동)
    - [상담사 프로필(목록) WIKI로 이동](#상담사-프로필목록-wiki로-이동)
    - [상담사 프로필(상세보) WIKI로 이동](#상담사-프로필목록-wiki로-이동)
    - [상담 예약 회원 관리 WIKI로 이동](#상담예약-회원관리-wiki로-이동)
    - [제휴등록 WIKI로 이동](#제휴등록-wiki로-이동)


## ERD구성

<details open>
<summary>ERD</summary>
  
  <a href='https://github.com/NohEuijin/CRUDcommunity/assets/141835418/261b59ae-d609-462f-932f-2107c32cce2b' target='_blank'>
  <img src='https://github.com/NohEuijin/CRUDcommunity/assets/141835418/261b59ae-d609-462f-932f-2107c32cce2b' border='0'>
  </a>

</details>

 ## Tools
- **Java**
- **Spring Boot**
### DB
- **Oracle**

## 작업 내용

### 메인페이지 <a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EB%A9%94%EC%9D%B8%ED%8E%98%EC%9D%B4%EC%A7%80"> WIKI로 이동</a>
- 로그인시 세션 유지
- 세션에 따른 헤더 메뉴 등 매핑 다름
- 세션의 레벨에 따라 000 님 , 000 상담사 님 , 000 관리자 님 으로 구분
- 세션의 레벨에 따라 드롭다운 목록이 변경
<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EB%A9%94%EC%9D%B8%ED%8E%98%EC%9D%B4%EC%A7%80"> 
 <img src="https://github.com/2teamSpring/RedMedicine/assets/141835418/59c8569b-cd25-48fa-aa52-ad59d1f155aa?type=w580" width="500">
</a>

### 상담사 등록<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%EB%93%B1%EB%A1%9D"> WIKI로 이동</a>
- 상담사는 유료상담사, 무료상담사로 구분
- 상담사 회원은 결제를 완료하면 유료 상담사로 활동이 가능
- 유료 상담사는 등록 페이지에서 비용을 입력 가능한 칸이 나타남
- 상담사의 프로필 이미지를 첨부파일로 등록이 가능

<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%EB%93%B1%EB%A1%9D"> 
<img src="https://github.com/NohEuijin/RedMedicine2/assets/141835418/bca6b628-fc70-43a7-8805-6a2ff7e3d9af?type=w580" width="500">
<img src="https://github.com/NohEuijin/RedMedicine2/assets/141835418/88e6f01e-e3aa-4c21-bcc9-d6220f75e911" width="500">
</a>

### 상담사 프로필(목록)<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%ED%94%84%EB%A1%9C%ED%95%84(%EB%AA%A9%EB%A1%9D%EB%B3%B4%EA%B8%B0)#%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C"> WIKI로 이동</a>
- 비용을 기준으로 찾아와 보여줌.

<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%ED%94%84%EB%A1%9C%ED%95%84(%EB%AA%A9%EB%A1%9D%EB%B3%B4%EA%B8%B0)#%EA%B8%B0%EB%8A%A5%EC%86%8C%EA%B0%9C"> 
 <img src="https://github.com/2teamSpring/RedMedicine/assets/141835418/8da2cb24-0160-4239-b2b9-2022da9132ad?type=w580" width="500">
</a>

### 상담사 프로필(상세보기)<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%ED%94%84%EB%A1%9C%ED%95%84(%EC%83%81%EC%84%B8%EB%B3%B4%EA%B8%B0)"> WIKI로 이동</a>
- 상담사 목록에서 상담사 프로필 클릭시 상세보기로 이동
- 로그인한 본인의 상담사 상세보기 페이지라면 삭제가 가능
- 예약 버튼 클릭 시 예약 페이지로 이동
- 상담 후 댓글이 가능

<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%82%AC%ED%94%84%EB%A1%9C%ED%95%84(%EC%83%81%EC%84%B8%EB%B3%B4%EA%B8%B0)"> 
 <img src="https://github.com/NohEuijin/RedMedicine2/assets/141835418/e34eeebf-bb32-4e68-a7aa-4497a9a10189?type=w580" width="500">
  <img src="https://github.com/NohEuijin/RedMedicine2/assets/141835418/6e8bd8bb-5efd-4eef-aacb-d7b98382b577?type=w580" width="500">
</a>

### 상담예약 회원관리<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%98%88%EC%95%BD-%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC"> WIKI로 이동</a>
- 상담사는 로그인시 드롭다운에 상담예약관리가 생김
- 상담 예약 관리 페이지에서는 이름, 상담날짜, 시간으로 검색이 가능(비동기)
- 해당 페이지는 10을 기준으로 페이징 처리(비동기)
- 상담을 예약한 회원의 정보를 번호와 이메일, 상담 내역을 모달창으로 확인 가능(비동기
- 상담취소 또는 상담완료 버튼으로 관리 가능

<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%83%81%EB%8B%B4%EC%98%88%EC%95%BD-%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC"> 
 <img src="https://github.com/donghyun3193/SpringRedMedicine/assets/131939490/5e4ce5c3-d455-4ebc-a67d-7cf4b76e90ee?type=w580" width="500">
</a>

### 제휴등록<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%A0%9C%ED%9C%B4%EB%93%B1%EB%A1%9D"> WIKI로 이동</a>
- 제휴를 등록하면 유료상담사가 됨
- 결제 버튼을 누르면 카카오페이로 결제가 진행
- 결제 진행은 비동기로 처리

<a href="https://github.com/NohEuijin/RedMedicine2/wiki/%EC%A0%9C%ED%9C%B4%EB%93%B1%EB%A1%9D"> 
 <img src="https://github.com/NohEuijin/RedMedicine2/assets/141835418/5a08dc2d-6aba-415d-8537-ddd1b034ec89?type=w580" width="500">
</a>
