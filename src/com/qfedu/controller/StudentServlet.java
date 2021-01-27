package com.qfedu.controller;
import com.qfedu.entity.Students;
import com.qfedu.service.StudentService;
import com.qfedu.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        StudentService studentService = new StudentServiceImpl();
        List<Students> students = studentService.getAll();
        req.setAttribute("students",students);
        req.getRequestDispatcher("/after/students.jsp").forward(req,resp);
    }
}
