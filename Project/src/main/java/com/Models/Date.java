package com.Models;

public class Date {
    int ngay;
    int thang;
    int nam;

    public Date(int ngay, int thang, int nam) {
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    public Date() {
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getStringDate() {
        return this.nam + "-" + this.thang + "-" + this.ngay;
    }

    public void setStringDate(String date) {
        this.nam = Integer.parseInt(date.substring(0, 4));//chỉ lay dau k lay cuoi
        this.thang = Integer.parseInt(date.substring(5, 7));
        this.ngay = Integer.parseInt(date.substring(8));
    }


}
