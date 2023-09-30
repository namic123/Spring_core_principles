package hello.core;

import lombok.Getter;
import lombok.Setter;

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
