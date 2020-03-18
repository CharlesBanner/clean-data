package com.charles.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class MaybeEnterPeopleExcelVO extends BaseRowModel {

    @ExcelProperty(value = {"姓名"}, index = 0)
    private String name;

    @ExcelProperty(value = {"出入境时间"}, index = 1)
    private String eeTime;

    @ExcelProperty(value = {"出入口岸名称"}, index = 2)
    private String eePort;

    @ExcelProperty(value = {"身份证号"}, index = 3)
    private String idCard;

    @ExcelProperty(value = {"手机号"}, index = 4)
    private String phoneNum;

    @ExcelProperty(value = {"户口所在地"}, index = 5)
    private String hometown;

    @ExcelProperty(value = {"来自国家代码"}, index = 6)
    private String countryCode;

    @ExcelProperty(value = {"来自国家名称"}, index = 7)
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEeTime() {
        return eeTime;
    }

    public void setEeTime(String eeTime) {
        this.eeTime = eeTime;
    }

    public String getEePort() {
        return eePort;
    }

    public void setEePort(String eePort) {
        this.eePort = eePort;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MayBeGoherePeopleExcelVO{" +
                "name='" + name + '\'' +
                ", eeTime='" + eeTime + '\'' +
                ", eePort='" + eePort + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", hometown='" + hometown + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
