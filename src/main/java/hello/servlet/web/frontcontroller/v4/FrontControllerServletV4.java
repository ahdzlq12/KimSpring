package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // v1하위에 어떤 url들어와도 일단 이 서블릿이 실행됨
public class FrontControllerServletV4 extends HttpServlet {

    //매핑정보 -> url실행될때 매핑해 놓은 controllerV1사용하기 위한
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet 프론트 컨트롤러");

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        Map<String,String> paramMap = createParamMap(request); //계층 맞춰주기 위해서 함수로 -> service이 부분에서 세부적인 작업을 하고 있지 않음. 세부적인 애들은 따로 빼서 계층 맞춰주기
        Map<String,Object> model = new HashMap<>();
        String viewName = controller.process(paramMap,model); //model 주소 넘겨 주기 때문에 유지가 되는걸로 보임

        MyView view = viewResolver(viewName); //계층 맞춰주기 위해서 함수로
        view.render(model, request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String,String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
