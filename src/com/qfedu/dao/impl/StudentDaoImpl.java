package com.qfedu.dao.impl;

import com.qfedu.dao.StudentDao;
import com.qfedu.entity.Students;
import com.qfedu.util.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {
    @Override
    public List<Students> studentGetAll() {
        String sql = "select * from student";
        Object [] object = {};
        try {
            List<Students> students = super.query(sql,object,Students.class);
            System.out.println(students);
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Students> studentGetAllByPage(int pageNo, int pageSize){
        //定义sql语句，第一个问好为每次查询的位置，第二个问好为，每次查询几个
        String sql = "select * from student limit ? , ?";
        //通过父类数组对象来接受
        Object [] object = {(pageNo-1)*pageSize,pageSize};
        List<Students> students = null;
        try {
            students = super.query(sql,object, Students.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return students;
    }

}
