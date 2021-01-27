package com.qfedu.util;

public class PageUtil {
    private int pageNo;//当前页码
    private int pageSize;//每一个的容量
    private int dataCount;//总数量
    private int pageCount;//总页数

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getPageCount() {
        if (dataCount%pageSize==0){
            pageCount = dataCount / pageSize;
        }else{
            pageCount = dataCount / pageSize + 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public PageUtil() {
    }

//    public PageUtil(int pageNo, int pageSize, int dataCount, int pageCount) {
//        this.pageNo = pageNo;
//        this.pageSize = pageSize;
//        this.dataCount = dataCount;
//        this.pageCount = pageCount;
//    }
//
//    @Override
//    public String toString() {
//        return "PageUtil{" +
//                "pageNo=" + pageNo +
//                ", pageSize=" + pageSize +
//                ", dataCount=" + dataCount +
//                ", pageCount=" + pageCount +
//                '}';
//    }
}
