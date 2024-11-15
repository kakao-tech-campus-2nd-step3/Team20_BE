# Team20_BE

---

# 프로젝트 소개

## 프로젝트 기획 의도

봉사활동 참여 정보를 다양한 정렬, 필터링 기능과 위치정보, 기관에 대한 정성적인 평가와 함께 제공하므로써 봉사활동 참여에 대한 접근성을 높이고자 기획하게 된 프로젝트입니다.

## 주요 기능

### 1. 카카오 로그인 및 아바타 생성

- Interceptor와 JWT를 통한 회원가입/로그인 기능
- 로그인 후, 아바타 게이미피케이션을 위한 아바타 생성 기능

### 2. 1365 공공데이터 API를 통한 데이터 수집 및 가공

- Spring Scheduler와 1365 공공데이터 API을 통해 주기적으로 봉사활동 데이터를 수집
- DB 형식에 맞게 데이터를 가공 및 저장
- - 가독성이 높은 단순한 카테고리로 변경
  - 활동 기관과 활동 정보를 분리하여 저장
- 활동 기관에 경우 좌표 정보가 제공되지 않아 Kakao Local API를 사용하여 좌표 정보를 획득 및 저장
- - 불가능한 경우 시도군구 코드의 지역 중심점 좌표를 활용

### 3. 봉사활동 조회

- Offset 방식의 Infinity Scroll 기능
- 필터링 기능
  - 카테고리별
  - 시도, 군구를 통한 지역별
  - 청소년 참여 가능한 활동만 제공
  - 모집이 마감되지 않은 활동만 제공
- 정렬 기능
  - 등록 최신순 (id순)
  - 모집 마감 날짜 가까운순
  - 좌표 기반 가까운 거리순

### 4. 키워드 기반 검색 기능

- 키워드가 포함된 제목의 봉사활동 검색 제공
- 필터링, 정렬 기능을 적용한 상태로 검색 가능하도록 지원

### 5. 카카오맵 API를 통한 지도와 주변 봉사활동의 마커 제공

- 조회된 봉사활동들의 좌표정보를 바탕으로 지도 마커로 제공

### 6. 봉사활동에 대한 리뷰 기능

- 사전에 정의된 질문에 대한 별점을 통해 활동 기관처에 대한 평가를 남길 수 있음
- 참여 신청한 봉사활동의 상태를 구분
  - 접수 -> 활동 대기 -> 활동 -> 활동 완료
- "활동 완료" 상태일 때 리뷰를 남길 수 있도록 제공
  - 활동 완료 -> 리뷰 완료 로 변경됨

### 7. 아바타 육성 게이미피케이션 기능

- 봉사활동 참여 시간에 따른 경험치와 레벨을 통해 아바타 육성 기능 제공

## 주안점을 두고 개발한 부분

- 서비스 의도에 적절한 공공데이터 API를 찾고 테스트하여, 서비스의 핵심인 데이터 수집/가공 방향성을 수립하고자 노력
- Infinity Scroll, 필터링, 정렬, 검색 기능들을 하나의 API로 제공하도록 개발
- 최소한 메서드의 단위테스트는 작성하도록 지향
- PR시 Github actions CI를 트리거하여 조금 더 안정적으로 기능 추가/수정
- 배포 CI/CD를 구축하여 요구사항/수정사항을 빠르게 고치고 배포하여, 클라이언트측의 원활한 테스트가 가능하도록 구성

---

# ERD
![최종ERD](https://github.com/user-attachments/assets/82e23c03-7d9c-4063-8353-0b84d3b0e622)

---

# 배포된 API 목록

## 1. 카카오 회원가입/로그인

``` 
GET http://3.37.32.242:8080/api/v1/users/login/kakao

header {
  token: kakao-access-token
}
```

## 2. 아바타

### 아바타 생성

```
POST http://3.37.32.242:8080/api/v1/avatars
header {
  Authorization: Bearer jwt-access-token
}

body {
  "nickname": "nickname",
  "ageRange": "청소년",
  "experienced": "중급자"
}
```

### 아바타 정보 조회

```
GET http://3.37.32.242:8080/api/v1/avatars
header {
  Authorization: Bearer jwt-access-token
}
```

## 3. 봉사활동

### 봉사활동 목록 조회

```
// 최신 공고순 정렬 (id순)
GET http://3.37.32.242:8080/api/v1/activities?page=0
GET http://3.37.32.242:8080/api/v1/activities?page=0&sort=actId

// 모집 마감 날짜 가까운순 정렬
GET http://3.37.32.242:8080/api/v1/activities?page=0&sort=noticeEndDate,actId

// 가까운 거리순 정렬
GET http://3.37.32.242:8080/api/v1/activities?page=0&sort=distance&latitude=126.846647&longitude=37.495585&distanceKm=5

// 카테고리별 필터링 조회
GET http://3.37.32.242:8080/api/v1/activities?page=0&category=교육 및 멘토링

// 시도 지역별 필터링 조회 (서울특별시)
GET http://3.37.32.242:8080/api/v1/activities?page=0&sidoCode=6110000

// 시군구 지역별 필터링 조회 (서울특별시 광진구)
GET http://3.37.32.242:8080/api/v1/activities?page=0&sidoGunguCode=3040000

// 청소년 참여 가능한 활동만 조회
GET http://3.37.32.242:8080/api/v1/activities?page=0&teenPossibleOnly=true

// 모집이 마감되지 않은 활동만 조회 
GET http://3.37.32.242:8080/api/v1/activities?page=0&beforeDeadlineOnly=true

// 키워드 검색 조회
GET http://3.37.32.242:8080/api/v1/activities?page=0&keyword=연탄

// 복합 조회 (모집 마감 날짜 가까운순 정렬 + 시도 필터링 + 카테고리 필터링 + 키워드 검색)
GET http://3.37.32.242:8080/api/v1/activities?page=0&sort=noticeEndDate,actId&sidoCode=6110000&category=기타 활동&keyword=연탄
```

### 봉사활동 상세 조회

```
GET http://3.37.32.242:8080/api/v1/activities/{actId}
```

### 봉사활동 카테고리 목록 조회

```
GET http://3.37.32.242:8080/api/v1/activities/categories
```

### 시도 지역 목록 조회

```
GET http://3.37.32.242:8080/api/v1/districts/sido
```

### 시군구 지역 목록 조회

```
GET http://3.37.32.242:8080/api/v1/districts/gungu
```

## 4. 참여 활동

### 사용자가 참여한 봉사활동 리스트 조회

```
GET http://3.37.32.242:8080/api/v1/histories
header {
  Authorization: Bearer JWT-access-token
}
```

### 봉사활동 참여 정보 추가

```
POST http://3.37.32.242:8080/api/v1/histories
header {
  Authorization: Bearer JWT-access-token
}

body {
  "actId": 1234
}

```

## 5. 리뷰

### 질문 리스트 조회

```
GET http://3.37.32.242:8080/api/v1/reviews/questions
```

### 리뷰 저장

```
POST http://3.37.32.242:8080/api/v1/reviews
header
{
  Authorization : Bearer JWT-access-token
}

body
{
  "historyId": 1,
  "answers": [
    {
            "questionId": 1,
            "score": 5
    },
    {
            "questionId": 2,
            "score": 3
    }
  ]
}
```

### 리뷰 답변 내용 확인

```
GET http://3.37.32.242:8080/api/v1/reviews/{historyId}
```
