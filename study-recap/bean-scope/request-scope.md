# request 스코프

## 라이브러리 추가
* implementation 'org.springframework.boot:spring-boot-starter-web'

### 참고 : 스프링 부트에서 웹 라이브러리가 없으면 AnnotationConfigApplicationContext 기반으로 애플리케이션을 구동함. 웹 라이브러리가 추가되면 웹 관련 추가 설정과 환경들이 필요하므로, AnnotationConfigServletWebServerApplicationContext를 기반으로 애플리케이션을 구도한다.

## request 스코프의 활용
* HTTP 동시 요청이 왔을 때 정확히 어떤 요청이 남긴 로그인지 구분하기 어려움
* 이때 request 스코프를 사용하기 좋음
```java
 @Scope(value = "request")
```
* HTTP 요청 당 하나씩 생성되며, 요청이 끝나는 시점에 소멸되는 스코프
* 즉, 스프링 앱 실행 시점이 아닌 HTTP 요청이 들어와야만 생성되기 때문에 요청이 들어올때까지 생성을 지연시켜야한다.
* 이때 필요한 것이 DL(의존성)

## 스코프와 Provider

## 스코프와 프록시



