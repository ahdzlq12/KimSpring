package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override //서블릿이 실행되면 이 서비스 메서드가 호출됨. 클라이언트에서 서블릿에 http요청이 올때 was가 request, response 객체 만들어서 전달
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //   super.service(req, resp);

        String userRequest = request.getParameter("user");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("responseMessage: "+ userRequest );
    }
}
