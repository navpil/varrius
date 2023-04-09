package io.github.navpil.varrius.oldschool;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OldSchoolServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("Hello from old school Servlet");
            pw.flush();
        }
    }
}
