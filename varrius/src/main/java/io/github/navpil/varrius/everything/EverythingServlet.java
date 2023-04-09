package io.github.navpil.varrius.everything;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class EverythingServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/myapp/guitars")) {
            req.getRequestDispatcher("/jersey/guitars/list").forward(req, resp);
        } else if (pathInfo.startsWith("/myapp/cars")){
            req.getRequestDispatcher("/spring/cars/all").forward(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("Hello from Everything Servlet");
            pw.flush();
        }
    }
}
