# 프로토타입 스코프


## 싱글톤 스코프
* 싱글톤 스코프의 빈을 조회하면, 스프링 컨테이너는 매번 같은 인스턴스의 빈을 반환

## 프로토타입 스코프
* 프로토 타입 스코프는 스프링 컨테이너가 매번 새로운 인스턴스를 생성해서 반환

---------------------------------------------

# 클라이언트 요청 스코프 흐름

## 싱글톤
1. 클라이언트가 싱글톤 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 본인이 관리하는 스프링 빈을 반환
3. 다른 클라이언트의 요청이 와도 같은 인스턴스의 스프링 빈 반환

## 프로토타입
1. 클라이언트가 프로토타입 스코프의 빈을 스프링 컨테이너에 요청한다.
2. 스프링 컨테이너는 프로토타입 빈을 생성하고, 필요한 의존관계를 주입한다.
3. 프로토타입 빈을 클라이언트에게 반환한다.
4. 다른 클라이언트의 요청이 올때마다 해당 빈의 새로운 인스턴스를 반환한다.


# 정리
## * 프로토타입의 핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존 관계 주입, 초기화까지만 관여하는 것.
## * 클라이언트에게 빈을 반환하고 더 이상 해당 빈을 관리하지 않는다.
## * 즉, 프로토타입 빈의 관리 책임 대상은 클라이언트에게 넘어간 것.
## * 따라서, @PreDestroy 같은 종료 시점 메서드는 호출되지 않음

```java
public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeTestBean.class);
        
        PrototypeTestBean prototypeTestBean1 = ac.getBean(PrototypeTestBean.class);
        PrototypeTestBean prototypeTestBean2 = ac.getBean(PrototypeTestBean.class);

        System.out.println("prototypeTestBean1 = " + prototypeTestBean1);
        System.out.println("prototypeTestBean2 = " + prototypeTestBean2);
        assertThat(prototypeTestBean1).isNotSameAs(prototypeTestBean2);

        ac.close(); // prototype은 클라이언트에게 빈을 전달하고 그 이후에는 관리 안하기 때문에 호출되지 않음
    }

    @Scope("prototype")
    static class PrototypeTestBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeTestBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeTestBean.destroy");
        }
    }
}
```

## 예제를 실행해보면, 같은 타입 빈이 요청마다 새로운 객체를 생성하고 @PreDestroy를 실행하지 않는다. 

### 프로토타입 빈의 정리
* 스프링 컨테이너 요청마다 인스턴스 새로 생성
* 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화(@PostConstrcut)까지만 관여
* @PreDestroy는 호출되지 않음
* 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야한다. 따라서, 종료 메서드에 대한 호출도 클라이언트 책임.

# 언제 프로토타입 빈을 사용할까?
* 대부분 싱글톤으로 해결 가능하지만 매번 사용할 때마다 의존관계 주입이 완료된 새 객체가 필요한 시점에 잘 고려해서 사용하면된다.