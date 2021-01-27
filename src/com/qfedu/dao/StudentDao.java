package com.qfedu.dao;

import com.qfedu.entity.Students;

import java.util.List;

public interface StudentDao {
    public List<Students> studentGetAll();
    public List<Students> studentGetAllByPage(int pageNo,int pageSize);
}

