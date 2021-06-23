package com.Models;

public class BacSi {
    String maBacSi;
    String tenBacSi;
    String chuyenKhoa;

    public BacSi(String maBacSi, String tenBacSi, String chuyenKhoa) {
        this.maBacSi = maBacSi;
        this.tenBacSi = tenBacSi;
        this.chuyenKhoa = chuyenKhoa;
    }
    public BacSi(){}

    public String getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(String maBacSi) {
        this.maBacSi = maBacSi;
    }

    public String getTenBacSi() {
        return tenBacSi;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }
}
