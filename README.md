# SchoolBusReservationForUser-java

이 앱은 학생들이 **스쿨버스를 예약할 수 있도록 만든 Android 애플리케이션(Java 버전)** 입니다.  
Firebase와 연동하여 실시간 예약 기능을 제공하며, 간편한 UI로 사용자가 빠르게 예약할 수 있도록 설계되었습니다.
- **[🟦 사용자 앱 시연 영상 보기](https://youtube.com/shorts/VGlsEBxsMDQ?feature=share)**
- 2024 대구가톨릭대학교 캡스톤디자인 🥉장려상 수상작입니다.

---

## 주요 기능

### 1. 로그인 / 회원가입
- [로그인 레이아웃](userImages/userlogin.PNG)
- [회원가입 레이아웃](userImages/userregister.PNG)
- 이메일, 비밀번호 기반 회원가입 및 로그인 기능
- Firebase Authentication 연동
- 로그인 성공 시 메인화면으로 이동

---

### 2. 노선 선택 화면
- [레이아웃](userImages/userroutechoose.PNG)
- Spinner를 통해 노선 선택
- 선택한 노선에 따라 정류장 및 시간 선택 화면으로 이동
- 노선에 따라 XML 화면이 따로 구성되어 있음

---

### 3. 정류장 및 시간 선택
- [레이아웃](userImages/usertimeplace.PNG)
- Spinner를 통해 시간 및 정류장 선택
- 선택한 예약 정보를 Firebase에 저장
- 예약 완료 후 Toast 메시지로 안내

---

### 4. 예약 내역 확인
- [레이아웃](userImages/userselectbuslist.PNG)
- 사용자가 예약한 내역을 리스트로 확인 가능
- RecyclerView를 통해 예약 리스트 출력
- 예약 항목 삭제 기능 제공 (Firebase에서 실시간 삭제)

---

### 5. 기타 기능
- 로그아웃 기능
- 간단한 디자인 기반의 정적 UI
- fab 버튼 기능 [레이아웃](userImages/userfab.PNG)
- 예약 시 중복 체크 없음 (※ 리팩토링 전 버전)

---

##  사용 기술

| 항목 | 내용 |
|------|------|
| 언어 | Java |
| UI | Android XML |
| 백엔드 | Firebase Firestore |
| 인증 | Firebase Authentication |
| 주요 컴포넌트 | Spinner, RecyclerView, Intent, Toast 등 |

---
