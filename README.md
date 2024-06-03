# Planiverse(플래니버스)

### 📌 Summary
이 스케줄러는 카카오와 구글 로그인을 지원하며, 달력 공유용 토큰을 통해 유저 간 달력 공유가 가능합니다.
사용자는 원하는 달력만 필터링하여 확인할 수 있으며, 화면 전환 없이 모든 요소를 하나의 JSP 파일에서 관리하여 구현했습니다.

---

### 💡 주요기능
1. 회원가입 / 일반, 구글, 카카오 로그인 / 로그아웃 / 계정 찾기 / 탈퇴
   - 구글 OAuth2 리퀘스트 사용으로, 프로필 데이터 호출
   - 카카오 토큰 리퀘스트를 통해, 프로필 데이터 호출
2. 구글 캘린더 일정 가져오기
   - 보유한 토큰으로 사용자 구글 일정 데이터 호출
3. 캘린더 생성 및 공유
     - 회원 고유의 캘린더를 생성, 토큰을 공유하여 캘린더를 공유/사용 가능
4. 일정 생성 / 호출 / 수정 / 삭제
     - FullCalendar 함수를 이용하여 DB 연동
     - 드래그 앤 드롭으로 일정 수정 가능
     - 일정 hover시 팝오버창으로 일정 요약 정보 확인
5. 캘린더별 일정 필터링 조회 가능
     - 캘린더 체크에 따라 캘린더별 일정을 원하는대로 확인 가능

---

### 🔨 Tech Stack
- Java(Servlet/JSP)
- HTML
- CSS
- Javascript
- SQL(Oracle)

---

### 🔎 사용한 API
1. [FullCalendar API](https://fullcalendar.io/)

---

### 📜 프로젝트 결과물 - Documents (클릭하시면 문서화면으로 넘어갑니다!)
1. [요구 분석서](https://drive.google.com/file/d/14Lm7erYE8a2qr-gxUjx04CpnqMyuYtLN/view?usp=drive_link)
2. [ERD](https://drive.google.com/file/d/1kN_l4FE4hDO8FZUd4Az9qic_At0ca037/view?usp=drive_link) 
3. [순서도](https://drive.google.com/file/d/1OFIma4hOac0xDd93Weemenalk-kl2XLU/view?usp=drive_link) 
4. [개발문서](https://drive.google.com/file/d/1Ab-X3NkVe9DucibBVmcxva7yXrU7C0f2/view?usp=drive_link) 
