package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 * 1. 파라미터 전송 기능
 */
@WebServlet(name = "requestParamServlet" , urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");
        System.out.println("전체 파라미터 조회 - start");
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            System.out.println("key = " + parameterName);
            System.out.println("value = " + request.getParameter(parameterName));
        }
        System.out.println("전체 파라미터 조회 - end");
        System.out.println();
        System.out.println("단일 파라미터 조회 - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println(username + age);
        System.out.println("단일 파라미터 조회 - end");
        System.out.println();
        System.out.println("이름이 같은 복수 파라미터 조회 - start");
        String[] usernames = request.getParameterValues("username");
        for(String username2 : usernames){
            System.out.println(username2);
        }
        System.out.println("이름이 같은 복수 파라미터 조회 - end");
        System.out.println();
    }
}
