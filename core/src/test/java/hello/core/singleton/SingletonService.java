package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {
    // 1. 자기 자신을 내부 priavte으로 가지고 있음. (static) - 클래스 레벨에 올라가기 때문에 딱 하나만 존재하게 됨.
    private static final SingletonService instance = new SingletonService();
    // getter를 public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){}    // 3. 기본 생성자를 private으로 만들어서 실행 코드에서 객체를 생성하지 못하도록 막음.

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
    // 그렇다면 이 클래스를 어떻게 만들 수 있을까? getInstance로 호출이 가능하며,
    // 이 메서드의 반환값은 static이므로 항상 같은 객체가 반환된다
    // 생성자를 privat으로 막아서 외부에서 new 키워드롤 사용하며 객체 인스턴스를 만들 수가 없음.

}
