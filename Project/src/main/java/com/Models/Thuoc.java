package com.Models;

public class Thuoc {
    String maThuoc;
    String tenThuoc;
    String donVi;
    int giaBan;
    int soLuong;
    String cachDung;
    
    public Thuoc(String tenThuoc, String donVi, int soLuong, int giaBan, String cachDung) {
		super();
		this.tenThuoc = tenThuoc;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.giaBan = giaBan;
		this.cachDung = cachDung;
	}

    public Thuoc(String maThuoc, String tenThuoc, String donVi, int giaBan, int soLuong, String cachDung) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.donVi = donVi;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.cachDung = cachDung;
    }
    public Thuoc(){}

    //main function and method
    public int getTongTienThuoc(){
        return this.giaBan*this.soLuong;
    }


    //Getter and setter
    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
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

    public String getCachDung() {
        return cachDung;
    }

    public void setCachDung(String cachDung) {
        this.cachDung = cachDung;
    }
	@Override
	public String toString() {
		return "Thuoc [ tenThuoc=" + tenThuoc + ", donVi=" + donVi + ", giaBan=" + giaBan
				+ ", soLuong=" + soLuong + ", cachDung=" + cachDung + "]";
	}
}
