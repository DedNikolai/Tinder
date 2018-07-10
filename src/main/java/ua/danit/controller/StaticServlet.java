package ua.danit.controller;

import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/css/*")
public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        String out = FileUtils.readFileToString(new File("lib",url),"UTF-8");
        byte[] buffer = out.getBytes();
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(buffer);
        outputStream.close();
    }
}
