# User
주차장 관리 프로그램에서 **사용자(User) 관련 시스템**을 담당하는 API입니다.

**기반:** Spring Boot

**주요 기능:**
- **차량 검색**
- **주차비 정보 조회**

---

## 1. 차량 검색
사용자가 차량 번호판으로 차량을 검색할 수 있는 기능입니다.

### 차량 검색 (번호판 부분 검색)
- **URL:** `GET /api/parking/vehicles`
- **기능:** 차량 번호판의 일부를 입력하면 해당 문자열을 포함하는 주차중인 차량을 검색합니다.
- **Query Parameters:**
  - `plate` (String): 검색할 번호판 문자열
- **요청 예시:**
  ```
  GET /api/parking/vehicles?plate=5678
  ```
- **응답 예시:**
  ```json
  [
    {
      "id": 5,
      "plate": "90마5678",
      "entryTime": "2025-05-29T18:02:56.146967",
      "exitTime": null,
      "parked": true
    }
  ]
  ```
- **예제 cURL 요청:**
  ```bash
  curl -X GET "http://localhost:8080/api/parking/vehicles?plate=5678"
  ```

---

## 2. 주차비 정보 조회
특정 차량의 주차비를 계산하여 결제 정보를 제공하는 API입니다.

### 주차비 계산 정보 조회
- **URL:** `GET /api/parking/payment-info`
- **기능:** 차량 ID를 통해 주차 시간을 계산하고 결제해야 할 금액 정보를 반환합니다.
- **Query Parameters:**
  - `id` (Long): 차량 데이터 ID
- **요청 예시:**
  ```
  GET /api/parking/payment-info?id=5
  ```
- **응답 예시:**
  ```json
  {
    "carNumber": "90마5678",
    "entryTime": "2025-05-29 18:02",
    "parkingDuration": "2시간 30분",
    "amount": 5000
  }
  ```
- **예제 cURL 요청:**
  ```bash
  curl -X GET "http://localhost:8080/api/parking/payment-info?id=5"
  ```

---

## API 목록
| 기능 | HTTP Method | Endpoint | 설명 |
|------|------------|----------|------|
| **차량 검색** | `GET` | `/api/parking/vehicles?plate={검색어}` | 번호판 부분 검색으로 차량 조회 |
| **주차비 조회** | `GET` | `/api/parking/payment-info?id={차량ID}` | 특정 차량의 주차비 계산 정보 조회 |

---

## 사용 예시
### 1. 번호판으로 차량 찾기
```bash
# "67"이 포함된 번호판 검색
curl "http://localhost:8080/api/parking/vehicles?plate=67"
```

### 2. 찾은 차량의 주차비 확인
```bash
# ID가 5인 차량의 주차비 조회  
curl "http://localhost:8080/api/parking/payment-info?id=5"
```