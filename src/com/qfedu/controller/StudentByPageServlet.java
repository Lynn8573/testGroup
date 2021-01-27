package com.qfedu.controller;

import com.qfedu.entity.Students;
import com.qfedu.service.StudentService;
import com.qfedu.service.impl.StudentServiceImpl;
import com.qfedu.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/studentByPageServlet")
public class StudentByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置定义编码格式
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //声名service层对象，方便接下来的代码调用service层方法
        StudentService studentService = new StudentServiceImpl();
        //声明分页工具类对象，方便接下来的代码调用分页工具类的方法
        PageUtil pageUtil = new PageUtil();
        //定义字符串格式的  页 码  ，用来接受前端传回的页码数据
        String pNo = request.getParameter("pageNo");
        //判断页码是否为空，如果为空则需要赋予默认值。
        if(pNo == null){
            pNo = "1";
        }

        //定义和前端定义相同的数据用来接受转换好的数据
        int pageNo = Integer.parseInt(pNo);
        //自己定义每一页显示的容量
        int pageSize = 2;
        //调用service层方法获取总数据条数
        int dataCount = studentService.getDataCount();

        //将数据的值传给分页工具类，用来求出总页数
        pageUtil.setPageNo(pageNo);
        pageUtil.setPageSize(pageSize);
        pageUtil.setDataCount(dataCount);

        //通过分页工具的方法得出总页数的数据
        int pageCount = pageUtil.getPageCount();
        //调用service层方法将数据传给定义的集合
        List<Students> students = studentService.studentGetAllByPage(pageNo,pageSize);
        //设置参数，给前端传送数据
        request.setAttribute("students",students);
        request.setAttribute("pageNo",pageNo);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("pageSize",pageSize);
        //转发到固定位置，将数据给前端
        request.getRequestDispatcher("/after/student.jsp").forward(request,response);
    }
}
