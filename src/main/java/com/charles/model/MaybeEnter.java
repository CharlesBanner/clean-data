package com.charles.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import javax.persistence.Table;

@Table(name = "出入境人员全量表(每日更新)")
public class MaybeEnter extends BaseRowModel {

    @ExcelProperty(value = {"姓名"}, index = 0)
    private String 姓名;

    @ExcelProperty(value = {"出入境时间"}, index = 1)
    private String 出入境时间;

    @ExcelProperty(value = {"出入口岸名称"}, index = 2)
    private String 出入口岸名称;

    @ExcelProperty(value = {"身份证号"}, index = 3)
    private String 身份证号;

    @ExcelProperty(value = {"手机号"}, index = 4)
    private String 手机号;

    @ExcelProperty(value = {"户口所在地"}, index = 5)
    private String 户口所在地;

    @ExcelProperty(value = {"来自国家代码"}, index = 6)
    private String 来自国家代码;

    @ExcelProperty(value = {"来自国家名称"}, index = 7)
    private String 来自国家名称;

    public String get姓名() {
        return 姓名;
    }

    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    public String get出入境时间() {
        return 出入境时间;
    }

    public void set出入境时间(String 出入境时间) {
        this.出入境时间 = 出入境时间;
    }

    public String get出入口岸名称() {
        return 出入口岸名称;
    }

    public void set出入口岸名称(String 出入口岸名称) {
        this.出入口岸名称 = 出入口岸名称;
    }

    public String get身份证号() {
        return 身份证号;
    }

    public void set身份证号(String 身份证号) {
        this.身份证号 = 身份证号;
    }

    public String get手机号() {
        return 手机号;
    }

    public void set手机号(String 手机号) {
        this.手机号 = 手机号;
    }

    public String get户口所在地() {
        return 户口所在地;
    }

    public void set户口所在地(String 户口所在地) {
        this.户口所在地 = 户口所在地;
    }

    public String get来自国家代码() {
        return 来自国家代码;
    }

    public void set来自国家代码(String 来自国家代码) {
        this.来自国家代码 = 来自国家代码;
    }

    public String get来自国家名称() {
        return 来自国家名称;
    }

    public void set来自国家名称(String 来自国家名称) {
        this.来自国家名称 = 来自国家名称;
    }
}
