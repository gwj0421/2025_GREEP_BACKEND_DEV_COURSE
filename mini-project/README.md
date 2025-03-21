# 미니 프로젝트 소개 및 요구사항

[프로젝트 소개](homework.pdf)

---

## Troubleshooting

### 1. Junit5 테스트

<details open>
  <summary>문제와 해결책</summary>

**문제:** 코딩 과정에서 Junit 부재로 인해 테스트 환경이 미흡  
**해결:** Junit 관련 jar파일을 직접 넣어 외부 라이브러리 활성화 진행

</details>

---

### 2. 컨테이너 객체 도입에 대한 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** 컨테이너 객체에서 모든 객체를 생성하고 관리하려면 싱글톤 패턴을 기반으로 하나의 객체를 생성하고 공유하는 식으로 진행하는 과정이 필요했음  
**해결:** `public static`을 활용하여 싱글톤 패턴과 유사하게 기능 구현하여 메모리 효율성을 증가시켰지만, 동시성 문제는 더 생각해봐야 할 문제

</details>

---

### 3. MVC 패턴 구현에 대한 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** MVC 패턴을 적용하지 않고 프로그램 구현하였을 때 출력 부분과 서비스 부분, 데이터베이스 처리 과정이 뒤죽박죽되어 유지보수성과 효율성이 안 좋음을 파악  
**해결:** MVC 패턴을 기반으로 Model과 View, Container로 나누어 구조화하였음. 특히 View 부분에서 많은 출력 문자열을 `CustomMessage`라는 Enum 클래스를 생성하여 효율적으로 처리

</details>

---

### 4. MVC 패턴 기반 서비스 객체 구현에 대한 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** 서비스 객체는 사실상 도메인과 달리 여러 확장성이 강하기 때문에 이 부분을 인지하고 해결해줘야 유지보수성이 떨어지지 않음  
**해결:** Service의 공통 부분을 인터페이스화하여 여러 서비스로 확장성을 올렸음

</details>

---

### 5. 에러 핸들링에 대한 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** 기능 구현 과정에서 기능마다 에러를 발생시키거나 핸들링을 하여 코드가 불필요하게 많아지고 유지보수성이 낮아졌음  
**해결:** 컨트롤러에서 서비스마다 그룹화하여 에러 핸들링을 하여 에러의 사각지대를 없앴음

</details>

---

### 6. 매직 리터럴 핸들링

<details open>
  <summary>문제와 해결책</summary>

**문제:** 과제에서 제공하는 조건들에서 매직 리터럴이 많이 사용됨. 이를 그대로 사용하게 되면 유지보수성에 악영향을 끼침  
**해결:** 해당 매직 리터럴을 기능별로 그룹화하고 `Enum`과 `static`을 활용하여 해결

</details>

---

### 7. 빌더 패턴 도입 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** 기능 구현 과정에서 하나의 DTO를 입력할 때도 사용하고 출력할 때도 사용하는 등 다방면으로 사용하는 과정에서 생성자가 우후죽순으로 생겨나 유지보수성이 떨어짐  
```java
public class PostDto {
      private String title;
      private String content;
      private Long boardId;
      private LocalDateTime createAt;
      private LocalDateTime updatedAt;
  
      public PostDto(String title, String content, LocalDateTime createAt, LocalDateTime updatedAt) {
          this.title = title;
          this.content = content;
          this.createAt = createAt;
          this.updatedAt = updatedAt;
      }
  
      public PostDto(String title, String content, LocalDateTime createAt) {
          this.title = title;
          this.content = content;
          this.createAt = createAt;
      }
  
      public PostDto(String title, String content, Long boardId) {
          this.title = title;
          this.content = content;
          this.boardId = boardId;
      }
  
      public PostDto(String title, String content) {
          this.title = title;
          this.content = content;
      }
      // 생략
}
```
**해결:** 빌더 패턴을 활용하여 구현, 해당 클래스의 생성자를 private하게 하여 빌더 패턴을 만들어 두고 사용하지 않는 것을 방지하기 위해 정적 내부 클래스를 활용
```java
public class PostDto {
    private String title;
    private String content;
    private Long boardId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;

    private PostDto(String title, String content, Long boardId, LocalDateTime createAt, LocalDateTime updatedAt) {
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
    }

    public static class PostDtoBuilder implements Builder<PostDto> {
        private String title;
        private String content;
        private Long boardId;
        private LocalDateTime createAt;
        private LocalDateTime updatedAt;

        private PostDtoBuilder() {
        }

        public static PostDtoBuilder builder() {
            return new PostDtoBuilder();
        }

        public PostDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public PostDtoBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostDtoBuilder boardId(Long boardId) {
            this.boardId = boardId;
            return this;
        }

        public PostDtoBuilder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public PostDtoBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @Override
        public PostDto build() {
            return new PostDto(title, content, boardId, createAt, updatedAt);
        }
    }
    // 생략
}
```
</details>

---

### 8. 리팩토링에 대한 고찰

<details open>
  <summary>문제와 해결책</summary>

**문제:** 기능 구현 과정에서 스파게티 코드가 되어가고 있었음  
**해결:** SonarQube와 개인적인 고찰을 통해 많은 리팩토링 수행. 자세한 리팩토링 사항은 커밋 히스토리에서 확인할 수 있음

</details>
