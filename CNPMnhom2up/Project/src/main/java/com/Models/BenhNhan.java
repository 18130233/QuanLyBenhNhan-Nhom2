package com.Models;

import java.util.ArrayList;

public class BenhNhan {
    String maBenhNhan;
    String tenBenhNhan;
    Date ngayKham;
    String diaChi;
    String dienThoai;
    String chuanDoan;
    String trieuChung;
    String tienSu;
    ChiSo dauHieuSinhTon;
    ArrayList<Thuoc> danhSachThuoc;
    ArrayList<DichVu> danhSachDichVu;
    boolean thanhToan;
    PhongKham phongKham;
    

    public BenhNhan(String maBenhNhan, String tenBenhNhan,  String trieuChung,String chuanDoan, String tienSu) {
		super();
		this.maBenhNhan = maBenhNhan;
		this.tenBenhNhan = tenBenhNhan;
		this.trieuChung = trieuChung;
		this.chuanDoan = chuanDoan;
		
		this.tienSu = tienSu;
	}
	public BenhNhan(String maBenhNhan, String tenBenhNhan, String diaChi, String dienThoai) {
		super();
		this.maBenhNhan = maBenhNhan;
		this.tenBenhNhan = tenBenhNhan;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}
	public BenhNhan(String maBenhNhan, String tenBenhNhan, Date ngayKham, String diaChi, String dienThoai, String chuanDoan, String trieuChung, String tienSu, ChiSo dauHieuSinhTon, PhongKham phongKham) {
        this.danhSachThuoc = new ArrayList<>();
        this.danhSachDichVu = new ArrayList<>();
        this.thanhToan = false;
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        this.ngayKham = ngayKham;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.chuanDoan = chuanDoan;
        this.trieuChung = trieuChung;
        this.tienSu = tienSu;
        this.dauHieuSinhTon = dauHieuSinhTon;
        this.phongKham = phongKham;
    }
    public BenhNhan(){

    }
    //main function and method

    public void updateTrieuChung(String trieuChung){
//        Code here
    }



    //Getter and setter
    public String getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(String maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public Date getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(Date ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getTienSu() {
        return tienSu;
    }

    public void setTienSu(String tienSu) {
        this.tienSu = tienSu;
    }

    public ChiSo getDauHieuSinhTon() {
        return dauHieuSinhTon;
    }

    public void setDauHieuSinhTon(ChiSo dauHieuSinhTon) {
        this.dauHieuSinhTon = dauHieuSinhTon;
    }

    public ArrayList<Thuoc> getDanhSachThuoc() {
        return danhSachThuoc;
    }

    public void setDanhSachThuoc(ArrayList<Thuoc> danhSachThuoc) {
        this.danhSachThuoc = danhSachThuoc;
    }

    public ArrayList<DichVu> getDanhSachDichVu() {
        return danhSachDichVu;
    }

    public void setDanhSachDichVu(ArrayList<DichVu> danhSachDichVu) {
        this.danhSachDichVu = danhSachDichVu;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }

    public PhongKham getPhongKham() {
        return phongKham;
    }

    public void setPhongKham(PhongKham phongKham) {
        this.phongKham = phongKham;
    }
	@Override
	public String toString() {
		return "BenhNhan [maBenhNhan=" + maBenhNhan + ", tenBenhNhan=" + tenBenhNhan + ", diaChi=" + diaChi
				+ ", dienThoai=" + dienThoai + "]";
	}
    
}

