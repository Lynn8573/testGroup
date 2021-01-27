package com.qfedu.service;

import com.qfedu.entity.Students;

import java.util.List;

public interface StudentService {
    public List<Students> getAll();
    public List<Students> studentGetAllByPage(int pageNo,int pageSize);
    public int getDataCount();
}
