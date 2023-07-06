package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodySpringServlet extends HttpServlet {
//htttp api을 통한 요청. //서버에서 서버, 앱 -> 서버
//HTTP message body에 데이터 직접 담아서 요청
//post, put, patch
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();//메시지 바디 내용을 바이트 코드로 얻을 수 있음

        //스프링에서 제공하는 유틸
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//바이트를 문자(또는 문자를 바이트로)로 변환할때는 인코딩(디코딩) 정보를 꼭 알려줘야 함.

        System.out.println("포스트맨에서 Post요청한 데이터를 request를 통해서 바이트 코드로 읽은 후 변환: " + messageBody);

        response.getWriter().write("ok");

    }
}
