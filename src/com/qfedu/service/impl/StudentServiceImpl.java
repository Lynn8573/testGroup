package com.qfedu.service.impl;

import com.qfedu.dao.StudentDao;
import com.qfedu.dao.impl.StudentDaoImpl;
import com.qfedu.entity.Students;
import com.qfedu.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Students> getAll() {
        StudentDao studentDao = new StudentDaoImpl();
        List<Students> students = studentDao.studentGetAll();
        return students;
    }

    @Override
    public List<Students> studentGetAllByPage(int pageNo, int pageSize){
        StudentDao studentDao = new StudentDaoImpl();
        List<Students> students = studentDao.studentGetAllByPage(pageNo, pageSize);
        return students;
    }

    @Override
    public int getDataCount() {
        StudentDao studentDao = new StudentDaoImpl();
        List<Students> students = studentDao.studentGetAll();
        return students.size();
    }
}
