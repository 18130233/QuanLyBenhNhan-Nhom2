package com.Models;

public class DichVu {
    String maDichVu;
    String tenDichVu;
    String donVi;
    int giaBan;
    int soLuong;
    String ketQua;
    public DichVu(String tenDichVu, String donVi, int giaBan, int soLuong) {
  		super();
  		this.tenDichVu = tenDichVu;
  		this.donVi = donVi;
  		this.soLuong = soLuong;
  		this.giaBan = giaBan;
  		
  	
  	}

    public DichVu(String maDichVu, String tenDichVu, String donVi, int giaBan, int soLuong, String ketQua) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.ketQua = ketQua;
    }
    public DichVu(){

    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }
    @Override
	public String toString() {
		return "DichVu [tenDichVu=" + tenDichVu + ", donVi=" + donVi + ", giaBan=" + giaBan + ", soLuong=" + soLuong
				+ "]";
	}
}
