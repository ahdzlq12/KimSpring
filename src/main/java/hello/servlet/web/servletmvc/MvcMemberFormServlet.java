package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    //mvc패턴에서는 컨트롤러를 거쳐서 뷰로 들어가야 함(입력이 컨트롤러로). 컨트롤러는 비즈니스 로직을 처리하지 않음 서비스로직 호출만함
    //뷰는 출력만
    //모델은 데이터 저장. 뷰가 필요한 데이터를 모델에 담아서(DTO)

    //서비스(레포지토리)에서 비즈니스로직 처리. 데이터 접근
    //mvc에서는 dao가 없어도 될듯? -> dao에서 jdbc쿼리 날렸는데, orm으로 할테니까 -> 맞나??
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //컨트롤러에서 뷰로 이동 하기 위한 작업

        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); //뷰로 이동
    }
}
