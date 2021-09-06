## [SB_Project] Bank

Team Project

* email address : sh007100@naver.com
* Demo Video : 
* development date : 2021.09.04
* Backend code : https://github.com/softpeanut/SB_Bank_Backend

  


### Introduction

---

SB Project의 은행 서비스 SB Bank입니다.

1. 은행 서비스를 쉽게 이용할 수 있습니다.
2. 어드민 기능이 구현되어 있어 유저를 손쉽게 관리할 수 있습니다.




### Key Features

---

1. Auth
  * 로그인 및 회원가입 기능
  * 보안을 위한 2차 로그인 기능
  * 손쉬운 회원 탈퇴 기능
 
2. Account
  * 계좌 생성 기능
  * 입출금 기능
  * 계좌이체 기능
  * 대출 기능 (금리 구현)
 
3. MainPage & UserPage

  * 잔액 표시 기능
  * 대출 현황 기능
  * 거래 내역 기능(ALL, RECEIVE, SEND)

4. AdminPage
  * 유저 조회
  * 유저 계좌 정보 조회
  * 유저 잔액 설정





### Development Environment

---

* Android Studio @4.2.2
* Figma
* PhotoShop CC
* PostMan




### Application Version

---

* minSdkVersion : 28
* targerSdkVersion : 31




### APIS

---

* SB_Project Auth API : 로그인 & 회원가입을 도와주는 계정 관련 API
* SB_Project Bank API : 은행 관련 서비스를 도와주는 API


### Libraries

---

* Retrofit : HTTP API를 Java 인터페이스로 변환함
* Gson : Json의 자바 오브젝트의 직력화, 역직렬화를 해줌
* RecyclerView : 메모리 사용량을 최소화하면서 UI에 많은 양의 데이터를 표시함




### ScreenShot

---

<img src="https://user-images.githubusercontent.com/80076029/132150441-0a322ade-d1fc-46b2-802e-a026dbc030f8.png" width="180px" height="350px" title="Start" alt="시작"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149455-351d37fd-42b0-4570-ab79-175c2cf8ff15.png" width="180px" height="320px" title="Login" alt="로그인"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149475-99698ba4-f8c7-4b45-bc73-c1efb1bde617.png" width="180px" height="320px" title="Register" alt="회원가입"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149464-3439c684-d672-48c0-acd8-a50f2daac08b.png" width="180px" height="320px" title="Main" alt="메인 페이지"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149504-15c93c61-0936-4833-a79a-ee1e1e7fcd9a.png" width="180px" height="320px" title="Side Menu" alt="메인 페이지 메뉴"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149423-aac1c11f-69e2-4af6-b958-41c2ce47a7ed.png" width="180px" height="320px" title="createAccount" alt="계좌 생성"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149492-53b09753-1f00-4920-9205-f8053e4aebf1.png" width="180px" height="320px" title="Sec Password" alt="2차 로그인"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149504-15c93c61-0936-4833-a79a-ee1e1e7fcd9a.png" width="180px" height="320px" title="deposit" alt="입금"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149529-5319c01b-4fc2-44b4-9912-c1760ea3ddaf.png" width="180px" height="320px" title="withdraw" alt="출금"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149545-65e122ed-d99f-4de7-a144-23171a290ee5.png" width="180px" height="320px" title="withdraw Number" alt="출금 승인번호"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149426-f82cabe5-8f26-49e3-942f-40ccc5e8d26b.png" width="180px" height="320px" title="InputAccountNum" alt="계좌번호 입력"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149481-d99da97b-ced6-4f99-9f16-c240f384ed5a.png" width="180px" height="320px" title="transfer" alt="송금"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149522-bd9ccfbd-4cfa-4290-8491-69e670f5975d.png" width="180px" height="320px" title="transferList" alt="거래내역"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149509-8dfa2958-5364-4e17-9d5f-3ed31b494c31.png" width="180px" height="320px" title="transferList Menu" alt="거래내역 카테고리"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149428-3bf85dfe-f94e-415e-abe0-5bb8a421993d.png" width="180px" height="320px" title="loan Money" alt="대출 금액입력"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149444-6f06baa5-a857-4a59-a18b-d626ec2c8679.png" width="180px" height="320px" title="loan info" alt="대출 정보"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149450-c915fb36-babc-49e3-ba2b-41d615e86736.png" width="180px" height="320px" title="loan list" alt="대출 리스트"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149468-7007dea4-6be8-4af1-acb8-2197e0e54d7e.png" width="180px" height="320px" title="myPage" alt="마이페이지"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149405-9b803721-55b1-4cb2-af2c-763cbe957bb8.png" width="180px" height="320px" title="Admin Page" alt="관리자 페이지"></img>
<img src="https://user-images.githubusercontent.com/80076029/132149412-364e2270-e420-41bb-9574-12039b959b7b.png" width="180px" height="320px" title="Admin set money" alt="관리자 유저 잔액설정"></img>




