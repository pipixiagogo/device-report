package com.zh.device.excelTest.poiExcel;

public class Shop {
    @ExcelAnnotation(headName = "店铺名字",order = 0,columnWidth = 2000)
    String shopName;

    @ExcelAnnotation(headName = "主账号名字",order = 1,columnWidth = 5000)
    String accountName;

    @ExcelAnnotation(headName = "店铺地址",order = 2,columnWidth = 8000)
    String shopUrl;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }
}
