# 롬복 
LOMBOK (롬복)
- 자주 사용되는 java 코드를 어노테이션 형태로 간소화하는 라이브러리
-  getter, setter, equals, hashcode, toString, 생성자 등을 내부적으로 구현하여, 어노테이션 형태로 제공하여 반복되는 코드 작성의 부담을 줄여준다.


## 롬복 사용법 
Spring.io에서 프로젝트 만들 때 lombok을 추가하는 것도 가능.

아래는 intellij에서 추가하는 방법을 보여줌

1. build.gradle에서 dependency 안에 아래 코드 추가

   // lombok 시작
   compileOnly 'org.projectlombok:lombok'
   annotationProcessor 'org.projectlombok:lombok'

   testCompileOnly 'org.projectlombok:lombok'
   testAnnotationProcessor 'org.projectlombok:lombok'
   // 끝

2. build.gradle에서 java 밑에 아래 코드 추가

// lombok 설정 추가 시작
configurations {
compileOnly{
extendsFrom annotationProcessor
}
}

3. file > settings > plugin에서 lombok추가
4. settings > compliler > Annotation processor > Enable annotation processing 체크

## 롬복 예제
```java
@Getter
@Setter
// 롬복 예제
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("재성");  // setter 
        String name = helloLombok.getName(); // getter
        System.out.println("name = " + name);
    }
}
```
* @Getter, @Setter와 같이 어노테이션 형태로 자동으로 코드를 만들어준다.
* 실무에서 많이 사용된다.
* 롬복의 종류는 아래 api 참고
  롬복 https://projectlombok.org/features/


# 롬복으로 생성자 주입을 만들어보자
* @RequiredArgsConstructor 어노테이션은 final이 붙은 필드의 생성자를 자동으로 만들어준다.
* 자동 생성자 주입을 사용할 때 이 롬복 에너테이션을 쓰자
* 정리하자면 이 에너테이션을 쓰면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.


## 최신 트렌드
* 최근에는 생성자를 딱 1개를 두고, @Autowired를 생략하여 자동 생성자 주입 방식을 채택한다.
* 여기에 더해 Lombok라이브러리의 사용을 함께하면서 코드를 보다 깔끔하게 사용할 수 있다.