package hello.servlet.web.frontController.v3;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontController.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontController.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3" , urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerNap = new HashMap();

    public FrontControllerServletV3() {
        controllerNap.put("/front-controller/v3/members/new-form" , new MemberFormControllerV3());
        controllerNap.put("/front-controller/v3/members/save" , new MemberSaveControllerV3());
        controllerNap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerNap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);
        MyView myView = viewResolver(mv);
        myView.render(mv.getModel() , request,response);
    }

    // 계층을 맞춰주기 위해서 메소드화
    private MyView viewResolver(ModelView mv) {
        return new MyView("/WEB-INF/views/" + mv.getViewName() + ".jsp");
    }

    // 계층을 맞춰주기 위해서 메소드화
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            paramMap.put(paramName , request.getParameter(paramName));
        }
        return paramMap;
    }
}
