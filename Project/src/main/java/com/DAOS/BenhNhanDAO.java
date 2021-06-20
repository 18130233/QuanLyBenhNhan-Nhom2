package com.DAOS;

import com.DBUtils.ConnectionDB;
import com.Models.BenhNhan;
import com.Models.Date;
import com.Models.PhongKham;
import com.Models.Thuoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BenhNhanDAO {
    public void updateTrieuChung(String idBenhNhan, String trieuChung) {
//        Code here
    }

    public void updateThuoc(String idBenhNhan, Thuoc thuoc) {
//        Code here
    }

    public BenhNhan getBenhNhanById(String id) {
//        Code here
        return null;
    }

    public BenhNhan checkId(String id) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        cn = ConnectionDB.getConnection();
        BenhNhan benhnhan = new BenhNhan();
        try {
            ps = cn.prepareStatement("select * from benhnhan where(maBenhNhan=" + id + ");");
            rs = ps.executeQuery();
            while (rs.next()) {
                benhnhan.setMaBenhNhan(rs.getString("maBenhNhan"));
                benhnhan.setTenBenhNhan(rs.getNString("tenBenhNhan"));
                benhnhan.setDiaChi(rs.getNString("diaChi"));
                benhnhan.setDienThoai(rs.getNString("dienThoai"));
                return benhnhan;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        benhnhan.setMaBenhNhan(id);
        return benhnhan;
    }

    public boolean addBenhNhan(BenhNhan benhNhan, int succeedChecked) {
        //succeedChecked=1 -> không cần thêm vô
        //succeedChecked = 0:  thêm bệnh bệnh nhân bình thường
        //succeedChecked=-1: cần generate mã
        //count 2 hoặc 3 thì tiep nhan benh nhan thanh cong!
        int count = 0;
        if (succeedChecked == 0 || succeedChecked == -1) {
            insertDataInBenhNhan(benhNhan, succeedChecked);
            count = count + 1;
        }
        //tìm gia tri tham so phu hop cho CTHangDoi
        //lay ra ma hang doi dựa trên tên phòng khám
        //lay ma chi tiet phong kham dựa trên tên phòng khám
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String maHangDoi = "";
        String maCTPhongKham = "";
        try {
            cn = ConnectionDB.getConnection();
            ps = cn.prepareStatement("select * from hangdoi inner join (phongkham INNER JOIN ctphongkham on phongkham.maPhongKham=ctphongkham.maPhongKham) on hangdoi.maPhongKham = phongkham.maPhongKham where(tenPhongKham='" + benhNhan.getPhongKham().getTenPhongKham() + "')");
            rs = ps.executeQuery();
            while (rs.next()) {
                maHangDoi = rs.getString("mahangdoi");
                System.out.println(maHangDoi);
                maCTPhongKham = rs.getString("maCTPhongKham");
            }
            ps.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //cho stt cho benh nhan
        int stt = gennerateID("select * from cthangdoi where (maHangDoi = '" + maHangDoi + "')");
        if (insertDataInCTHangDoi(maHangDoi, benhNhan.getMaBenhNhan(), stt)) {
            count = count + 1;
        }
        //them bảng chi tiết hàng đợi xong
        String maKhamBenh = gennerateID("select * from khambenh") + "";
        //ma benh nhan
        String mabenhnhan = benhNhan.getMaBenhNhan();
        System.out.println("-----"+mabenhnhan);
        //machiphongkham
        System.out.println("-----"+maCTPhongKham);
        //ngaykham
        String ngayKham = benhNhan.getNgayKham().getStringDate();
        System.out.println("-----"+ngayKham);
        //thêm bảng khám bệnh
        if (insertDataInKhamBenh(mabenhnhan, maKhamBenh, maCTPhongKham, "", "", "", maHangDoi, ngayKham))
            count = count + 1;
        //1 la them 2 bang hai la them 3 bang tuy vao benh nhan da co trong csdl chua?
        System.out.println("count" + count);
        return (count == 2 || count == 3);
    }

    private boolean insertDataInBenhNhan(BenhNhan benhNhan, int succeedChecked) {
        //dùng khi benh nhan chua co trong csdl
        Connection cn = null;
        PreparedStatement ps = null;
        cn = ConnectionDB.getConnection();
        if (succeedChecked == 0) {
            try {
                ps = cn.prepareStatement("INSERT INTO benhnhan(maBenhNhan, tenBenhNhan, diaChi, dienThoai) VALUES (?, ?, ?, ?);");
                ps.setString(1, benhNhan.getMaBenhNhan());
                ps.setString(2, benhNhan.getTenBenhNhan());
                ps.setString(3, benhNhan.getDiaChi());
                ps.setString(4, benhNhan.getDienThoai());
                boolean x = ps.execute();
                System.out.println("insert benhnhan: "+x);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        } else if (succeedChecked == -1) {
            int idBenhNhan = gennerateID("select * from benhnhan");
            benhNhan.setMaBenhNhan(idBenhNhan + "");
            try {
                ps = cn.prepareStatement("INSERT INTO benhnhan(maBenhNhan, tenBenhNhan, diaChi, dienThoai) VALUES (?, ?, ?, ?);");
                ps.setString(1, idBenhNhan + "");
                ps.setString(2, benhNhan.getTenBenhNhan());
                ps.setString(3, benhNhan.getDiaChi());
                ps.setString(4, benhNhan.getDienThoai());
                boolean x = ps.execute();
                System.out.println("insert benhnhan:" +x);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean insertDataInKhamBenh(String maBenhNhan, String maKhamBenh, String maCTPhongKham, String trieuChung, String chuanDoan, String tienSu, String maHangDoi, String ngayKham) {
        Connection cn = null;
        PreparedStatement ps = null;
        cn = ConnectionDB.getConnection();
        try {
            ps = cn.prepareStatement("INSERT INTO khambenh(maBenhNhan, maKhamBenh, maCTPhongKham,trieuChung,chuanDoan,tienSu,maHangDoi,ngayKham) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, maBenhNhan);
            ps.setString(2, maKhamBenh);
            ps.setString(3, maCTPhongKham);
            ps.setString(4, trieuChung);
            ps.setString(5, chuanDoan);
            ps.setString(6, tienSu);
            ps.setString(7, maHangDoi);
            ps.setString(8, ngayKham);
            boolean x = ps.execute();
            System.out.println("insert khambenh:"+x);
           return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    private boolean insertDataInCTHangDoi(String maHangDoi, String maBenhNhan, int stt) {
        Connection cn = null;
        PreparedStatement ps = null;
        cn = ConnectionDB.getConnection();
        try {
            ps = cn.prepareStatement("INSERT INTO cthangdoi(maHangDoi, maBenhNhan, stt) VALUES (?, ?, ?);");
            ps.setString(1, maHangDoi);
            ps.setString(2, maBenhNhan);
            ps.setInt(3, stt);
            boolean x = ps.execute();
            System.out.println("insert cthangdoi"+x);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //ma se bat dau tu 1
    //khi goi ham nay mot lan no se tra ve cho ta mot ma cho dong moi.
    private int gennerateID(String sql) {
        int size = 0;
        Connection cn = null;
        PreparedStatement ps = null;
        cn = ConnectionDB.getConnection();
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                rs.last();
                size = rs.getRow();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return size + 1;
    }


    public ArrayList<BenhNhan> getDanhSachBenhNhan() {
//        Code here
        return null;
    }


}
