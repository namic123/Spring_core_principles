package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private MyLogger myLogger;
    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger>;

    @RequestMapping("log-demo")
    @ResponseBody   // view 없이 문자 그대로 응답할 수 있음.
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString(); // 요청 url을 불러옴.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
