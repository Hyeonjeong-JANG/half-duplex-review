package org.example.yourapp;

import com.example.yourapp.RequestMapping;
import com.example.yourapp.controller.BoardController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// http://localhost:8080/*.do
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private String getAction(String uri) {
        return uri
                //.replace("/", "")
                .split("\\.")[0];
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request.getRequestURI());
        BoardController bc = new BoardController();

        Method[] methods = bc.getClass().getDeclaredMethods();

        for (Method mt : methods) {
            Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
            if (anno instanceof RequestMapping) {
                RequestMapping rm = (RequestMapping) anno;
                if (rm.value().equals(action)) {
                    try {
                        String result = (String) mt.invoke(bc);
                        response.getWriter().println(result);
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }

            }
        }
    }
}
