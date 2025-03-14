# User
주차장 관리 프로그램에서 **사용자(User) 관련 시스템**을 담당하는 API입니다.

**기반:** Spring Boot

**주요 기능:**
- **차량 검색**
- **카드 정보 전송 및 결제 데이터 API**

---

## 1. 차량 검색
사용자가 차량 번호 뒷자리로 차량을 검색할 수 있는 기능입니다.

### 차량 검색 (뒷자리 기준 검색)
- **URL:** `GET /api/user/vehicle/search/{plate_suffix}`
- **기능:** 차량 번호 뒷자리를 입력하면 해당 뒷자리를 공유하는 모든 차량을 검색합니다.
- **요청 예시:**
  ```
  GET /api/user/vehicle/search/3456
  ```
- **응답 예시:**
  ```json
  {
    "plate_suffix": "3456",
    "vehicles": [
      {"license_plate": "12가 3456", "entry_time": "2025-02-27T10:30:00"},
      {"license_plate": "34나 3456", "entry_time": "2025-02-27T09:15:00"}
    ]
  }
  ```
- **예제 cURL 요청:**
  ```bash
  curl -X GET "http://localhost:8080/api/user/vehicle/search/3456"
  ```


---

## 2. 카드 정보 전송 및 결제 데이터
사용자가 주차비를 결제할 때 사용하는 API입니다.

### 결제 요청
- **URL:** `POST /api/user/payment`
- **기능:** 차량 주차비 결제를 요청합니다.
- **Body Parameters:**
  ```json
  {
    "license_plate": "12가 3456",        // 차량번호
    "identifier": "ORD123456",            // 결제 식별자
    "orderName": "2시간 주차권",          // 주문명
    "amount": 5000,                       // 결제 금액
    "paymentMethod": "CARD",              // 결제 수단
    "paymentInfo": {
        "type": "CARD",                   // 결제 상세 타입
        "cardNumber": "1234-5678-9012-3456",  // 카드번호
        "expiry": "0525",                 // 유효기간 (MMYY)
        "cvc": "123",                     // CVC
        "installmentMonths": 0            // 할부개월 (optional)
    }
  }
  ```
- **응답 예시:**
  ```json
  {
    "status": "success",
    "message": "결제 완료",
    "identifier": "ORD123456"
    "amount": 5000,
  }
  ```
- **예제 cURL 요청:**
  ```bash
  curl -X POST "http://localhost:8080/api/user/payment" \
       -H "Content-Type: application/json" \
       -d '{
         "license_plate": "12가 3456",
         "identifier": "ORD123456",
         "orderName": "2시간 주차권",
         "amount": 5000,
         "paymentMethod": "CARD",
         "paymentInfo": {
             "type": "CARD",
             "cardNumber": "1234-5678-9012-3456",
             "expiry": "0525",
             "cvc": "123",
             "installmentMonths": 0            // 할부개월 (optional)
       }'
  ```

---

## 정리
### User API 목록
| 기능 | HTTP Method | Endpoint | 설명 |
|------|------------|----------|------|
| **차량 검색** | `GET` | `/api/user/vehicle/search/{plate_suffix}` | 차량 번호 뒷자리 검색 |
| **결제 요청** | `POST` | `/api/user/payment` | 주차비 결제 요청 |

---

