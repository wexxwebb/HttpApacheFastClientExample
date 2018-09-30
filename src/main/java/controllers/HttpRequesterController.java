package controllers;

import service.HttpRequesterService;

import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpRequesterController extends HttpServlet {

    @EJB
    private HttpRequesterService httpRequesterService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uuid = httpRequesterService.exec(
                Integer.valueOf(req.getParameter("threads")),
                Integer.valueOf(req.getParameter("loop")))
                .toString();
        try (PrintWriter body = resp.getWriter()) {
            body.write(uuid);
            body.flush();
        }
    }
}
