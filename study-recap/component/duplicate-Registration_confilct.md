# 컴포넌트 스캔에서 빈을 중복으로 등록하는 상황과 충돌


## 자동 빈 등록 (ComponentScan)
## 수동 빈 등록 (@Bean)

### 컴포넌트 스캔에 의해 자동으로 중복된 이름의 스프링 빈이 등록되면?
* ConflictingBeanDefinitionException 예외가 발생

### 수동 빈 등록과 자동 빈 등록이 충돌 되었다면?
- 예외가 발생하지 않는다.
- 이 경우, 수동 빈 등록이 우선권을 가진다.
- 수동 빈이 자동 빈을 오버라이딩 해버림.
- 테스트 코드를 보면 오버라이딩 했고 replace됐다는 것을 확인 할 수 있음.
- 그러나 이 것은 큰 버그를 야기할 수 있다.
- 따라서 최근의 스프링 부트는 수동 빈 등록과 자동 빈의 충돌 발생 시 오류가 발생하도록 기본 값을 바꿈.
- @SpringBootApplication 클래스를 실행해 보면 오류 발생.


### 만약 충돌이 일어났고 오버라이딩을 하고 싶은 상황이라면?
- 스프링 부트 애플리케이션 시작 코드를 실행하면 아래와 같은 결과가 나옴
A bean with that name has already been defined in file [C:\Users\박재성\OneDrive\바탕 화면\study\core\core\out\production\classes\hello\core\member\MemoryClientRepository.class] and overriding is disabled.
- 즉, overriding이 disable 됐다는 것인데, 그 출력 아래 보면 pring.main.allow-bean-definition-overriding=true
라는 코드도 볼 수 있다.
- 위 코드를 application.properties에 넣으면 오버라이딩을 할 수 있도록 설정이 가능
- 그러나 이 방법은 코드의 복잡성을 야기하므로, 하지 않는 것이 권장된다.


### 실무에서는 어설픈 것보다 명확하게 코드를 설계하는 것이 좋다.
### 즉, 실무에서는 혼자 개발하는 것이 아니므로 최대한 기본 값을 기반으로 코드를 설계하자.


