package io.github.navpil.varrius.oldschool;

import io.github.navpil.guitars.GuitarService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class GuitarServlet extends HttpServlet {

    private final GuitarService guitarService;

    public GuitarServlet(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("Hello from Guitar Servlet: " + guitarService.list());
            pw.flush();
        }
    }

}
