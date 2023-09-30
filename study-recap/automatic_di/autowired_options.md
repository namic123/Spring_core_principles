# @Autowired의 옵션처리

@Autowired는 required default가 true이므로, 자동 주입 대상이 없으면 오류가 발생
* 자동 주입할 대상이 없으면 null이 됨.

@Autowired(required = false)
* required 는 필수 주입 여부를 결정하는 옵션.
* false로 하는 경우, 제외 대상이 될 수 있음


@Nullable 
* 이것을 파라미터에 넣어주면 null로 처리를 해줌.


Optional<?>
* Optional.empty로 넣어줌

```java
public class AutowiredTest {

    @Test
    @DisplayName("Autowired의 옵션 처리")
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    @Autowired(required = false) // 필수 주입 제외
    public void setNoBean1(Client noBean1) {
                System.out.println("noBean1 = " + noBean1);
    }
    @Autowired
    public void setNoBean2(@Nullable Client noBean2){
        System.out.println("noBean2 = " + noBean2);
    }

    @Autowired
    public void setNoBean1(Optional<Client> noBean3){
        System.out.println("noBean3 = " + noBean3);
    }
}
}
```

