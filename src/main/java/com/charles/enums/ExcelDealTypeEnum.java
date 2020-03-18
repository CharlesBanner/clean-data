package com.charles.enums;

public enum ExcelDealTypeEnum {
    MAYBE_ENTER_PEOPLE(1,"可能如黔人员表");

    private int key;
    private String value;

    ExcelDealTypeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static ExcelDealTypeEnum getEnum(int key){
        for (ExcelDealTypeEnum value : ExcelDealTypeEnum.values()) {
            if (value.getKey() == key){
                return value;
            }
        }
        return null;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
