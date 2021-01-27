package com.qfedu.test;

import com.qfedu.entity.Students;
import com.qfedu.service.StudentService;
import com.qfedu.service.impl.StudentServiceImpl;

import java.util.List;

public class Demo1 {
    public static void main(String [] args){
        StudentService studentService = new StudentServiceImpl();
        List<Students> students = studentService.getAll();
        for (Students student : students) {
            System.out.println(student);
        }
    }
}
