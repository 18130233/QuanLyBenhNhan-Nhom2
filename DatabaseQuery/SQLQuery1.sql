CREATE DATABASE CNPMNHOM2;

CREATE TABLE BacSi(
 maBacSi varchar(10),
 tenBacSi nvarchar(50),
 chuyenKhoa nvarchar(50),
 PRIMARY KEY( maBacSi)
);
CREATE TABLE PhongKham(
 maPhongKham varchar(10),
 tenPhongKham nvarchar(50),
 PRIMARY KEY( maPhongKham)
);
CREATE TABLE CTPhongKham(
 maCTPhongKham varchar(10),
 maPhongKham varchar(10) FOREIGN KEY REFERENCES PhongKham(maPhongKham),
 maBacSi varchar(10) FOREIGN KEY REFERENCES BacSi(maBacSi),
 gioTruc float,
 hetCaTruc float,
 PRIMARY KEY( maCTPhongKham)
);
CREATE TABLE BenhNhan(
 maBenhNhan varchar(50) not null,
 tenBenhNhan nvarchar(100) not null,
 diaChi nvarchar(100) not null,
 dienThoai varchar(10),
 PRIMARY KEY( maBenhNhan)
);
CREATE TABLE ChiSo(
 maBenhNhan varchar(50),
 huyetAp int,
 nhietDo real,
 mach int,
 nhipTho int,
 spO2 real,
 PRIMARY KEY( maBenhNhan)
);
CREATE TABLE Thuoc(
 maThuoc varchar(10),
 tenThuoc nvarchar(100),
 donVi nvarchar(50),
 giaBan int,
 cachDung nvarchar(100),
 PRIMARY KEY( maThuoc)
);

CREATE TABLE HoaDonThuoc(
 maHDThuoc varchar(10),
 maBenhNhan varchar(10),
 tongTien nvarchar(50),
 PRIMARY KEY( maHDThuoc)
);
CREATE TABLE ChiTietThuoc(
 maHDThuoc varchar(10) FOREIGN KEY REFERENCES HoaDonThuoc(maHDThuoc),
 maThuoc varchar(10),
 soLuong int,
 PRIMARY KEY( maHDThuoc, maThuoc)
);
CREATE TABLE DichVu(
 maDichVu varchar(10),
 tenDichVu nvarchar(100),
 donVi nvarchar(50),
 giaBan int,
 PRIMARY KEY( maDichVu)
);
CREATE TABLE HoaDonDichVu(
 maHDDichVu varchar(10),
 maBenhNhan nvarchar(10),
 tongTien real,
 PRIMARY KEY( maHDDichVu)
);
CREATE TABLE ChiTietDichVu(
 maHDDichVu varchar(10) FOREIGN KEY REFERENCES HoaDonDichVu(maHDDichVu),
 maDichVu varchar(10) FOREIGN KEY REFERENCES DichVu(maDichVu),
 soLuong int,
 ketQua nvarchar(100),
 PRIMARY KEY( maHDDichVu, maDichVu)
);


CREATE TABLE KhamBenh(
 maBenhNhan varchar(50) FOREIGN KEY REFERENCES BenhNhan(maBenhNhan),
 maKhamBenh varchar(10) ,
 maCTPhongKham varchar(10) FOREIGN KEY REFERENCES CTPhongKham(maCTPhongKham),
 trieuChung nvarchar(100),
 chuanDoan nvarchar(100),
 tienSu nvarchar(100),
 maHDDichVu varchar(10),
 maHDThuoc varchar(10) FOREIGN KEY REFERENCES HoaDonThuoc(maHDThuoc),
 PRIMARY KEY( maKhamBenh)
);
CREATE TABLE HoaDon(
 maHD varchar(10),
 maKhamBenh varchar(10),
 tongTien real,
 PRIMARY KEY( maHD)
);









