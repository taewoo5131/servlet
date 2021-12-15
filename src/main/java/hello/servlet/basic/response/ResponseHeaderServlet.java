package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="responseHeaderServlet" , urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // status - line
        response.setStatus(302);

        // response header
        response.setHeader("Content-Type","text/plain");
        response.setHeader("Cache-Control" ,"no-cache");
        response.setHeader("Pragma","no-cache");
        response.setHeader("my-haeder","hello");
        response.setHeader("Location","/basic");
        //

    }
}
