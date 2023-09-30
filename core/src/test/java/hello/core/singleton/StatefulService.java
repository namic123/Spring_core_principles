package hello.core.singleton;


// 상태를 유지하는 필드의 문제점
public class StatefulService {
    private int price;  // 상태를 유지하는 필드
    public void order(String name, int price){
        System.out.println("name = " + name + " price = "+ price );
        this.price = price;  // 이 부분이 문제점
    }
    public int getPrice(){
        return price;
    }
}
