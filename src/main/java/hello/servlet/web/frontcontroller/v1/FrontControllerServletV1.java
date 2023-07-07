package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // v1하위에 어떤 url들어와도 일단 이 서블릿이 실행됨
public class FrontControllerServletV1 extends HttpServlet {

    //매핑정보 -> url실행될때 매핑해 놓은 controllerV1사용하기 위한
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet 프론트 컨트롤러");

        System.out.println("uri: "+request.getRequestURI());
        System.out.println("url: "+request.getRequestURL());

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        controller.process(request, response);
    }
}
