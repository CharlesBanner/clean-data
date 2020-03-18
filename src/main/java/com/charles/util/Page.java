//package com.charles.util;
//
//
//import com.alibaba.fastjson.annotation.JSONType;
//import java.io.Serializable;
//import java.util.List;
//
//@JSONType(
//        orders = {"pageSize", "pageNum", "totalCount", "pageCount", "data"}
//)
//public class Page<T> implements Serializable {
//    private static final long serialVersionUID = 5636079738558757591L;
//    private static final int DEFAULT_PAGE_SIZE = 10;
//    private int pageSize = 10;
//    private int pageNum;
//    private int totalCount;
//    private int pageCount;
//    private List<T> data;
//
//    public Page() {
//    }
//
//    public Page(List<T> list) {
//        com.github.pagehelper.Page page = (com.github.pagehelper.Page)list;
//        this.pageNum = page.getPageNum();
//        this.pageSize = page.getPageSize();
//        this.pageCount = page.getPages();
//        this.data = page;
//        this.totalCount = (new Long(page.getTotal())).intValue();
//    }
//
//    public int getPageSize() {
//        return this.pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    public int getPageNum() {
//        return this.pageNum;
//    }
//
//    public void setPageNum(int pageNum) {
//        this.pageNum = pageNum;
//    }
//
//    public int getTotalCount() {
//        return this.totalCount;
//    }
//
//    public void setTotalCount(int totalCount) {
//        this.totalCount = totalCount;
//    }
//
//    public int getPageCount() {
//        if (this.totalCount % this.pageSize == 0) {
//            this.pageCount = this.totalCount / this.pageSize;
//        } else {
//            this.pageCount = this.totalCount / this.pageSize + 1;
//        }
//
//        return this.pageCount;
//    }
//
//    public void setPageCount(int pageCount) {
//        this.pageCount = pageCount;
//    }
//
//    public List<T> getData() {
//        return this.data;
//    }
//
//    public void setData(List<T> data) {
//        this.data = data;
//    }
//}
