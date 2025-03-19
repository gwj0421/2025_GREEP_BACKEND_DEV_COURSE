# 2025_GREEP_BACKEND_DEV_COURSE

# Troubleshooting
<details>

<summary>AssertJ 호환 문제</summary>
문제 : gradle/maven없이 AssertJ와 같은 외부 라이브러리 사용에 차질
<br>
해결 : Assertj jar파일을 직접 넣어 외부 라이브러리 활성화 진행
</details>
<details>
<summary>Url 리팩토링</summary>
문제 : Url 특성상 [ /구분/기능?파라미터... ] 구조로 되어있고 구분만 값이 존재할 수있고 구분과 기능만 값이 존재할 경우가 있어 오버로딩으로 구현하면 불필요한 코드가 생김
<br>
해결 : 빌더 패턴 활용
</details>

