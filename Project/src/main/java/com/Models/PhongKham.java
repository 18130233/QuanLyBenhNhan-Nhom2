package com.Models;

public class PhongKham {
String maPhongKham;
String tenPhongKham;
BacSi bacSiTruc;
Date ngayTruc;
int gioTruc;

    public PhongKham(String maPhongKham, String tenPhongKham, BacSi bacSiTruc, Date ngayTruc, int gioTruc) {
        this.maPhongKham = maPhongKham;
        this.tenPhongKham = tenPhongKham;
        this.bacSiTruc = bacSiTruc;
        this.ngayTruc = ngayTruc;
        this.gioTruc = gioTruc;
    }
    public  PhongKham(){}

//    Main function and method
    public void capNhatBacSiTruc(BacSi bacSi, Date ngayTruc, Date gioTruc){
//        Code here
    }

//Getter and setter
    public String getMaPhongKham() {
        return maPhongKham;
    }

    public void setMaPhongKham(String maPhongKham) {
        this.maPhongKham = maPhongKham;
    }

    public String getTenPhongKham() {
        return tenPhongKham;
    }

    public void setTenPhongKham(String tenPhongKham) {
        this.tenPhongKham = tenPhongKham;
    }

    public BacSi getBacSiTruc() {
        return bacSiTruc;
    }

    public void setBacSiTruc(BacSi bacSiTruc) {
        this.bacSiTruc = bacSiTruc;
    }

    public Date getNgayTruc() {
        return ngayTruc;
    }

    public void setNgayTruc(Date ngayTruc) {
        this.ngayTruc = ngayTruc;
    }

    public int getGioTruc() {
        return gioTruc;
    }

    public void setGioTruc(int gioTruc) {
        this.gioTruc = gioTruc;
    }
}
