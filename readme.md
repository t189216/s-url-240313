# 요구사항

---
- 실제주소로 이동시 302 사용, 301 사용금지
- 단축주소는 테이블의 주키(숫자)를 사용한다.

작동예시 1
- 실제주소 : https://app.bitly.com/Bd432SOuuNz/links/bit.ly/3Vd5qLc/details
- 단축주소 : https://short.io/433
  - 여기서 433 은 surl 테이블의 주키(id long)

- REST API로 구현
- 단축주소가 생성되면 제목만 수정할 수 있고, 다른 부분은 수정할 수 없다.