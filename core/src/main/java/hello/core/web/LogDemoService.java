package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // 생성자 생성과 의존관계 자동 주입
public class LogDemoService {
    private final MyLogger myLogger;
    public void logic(String id){
        myLogger.log("service id = "+ id);
    }
}
