package io.github.navpil.varrius.oldschool;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/oldschool/annotation/*")
public class AnnotationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("Hello from Old School Servlet configured by annotation");
            pw.flush();
        }
    }
}
