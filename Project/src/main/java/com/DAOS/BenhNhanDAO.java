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
    public void updateTrieuChung(String maKhamBenh, String trieuChung){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "update khambenh set trieuchung = ? where maKhamBenh like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,trieuChung);
            preparedStatement.setString(2,maKhamBenh);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public BenhNhan getBenhNhanByMaKhamBenh(String id){
        BenhNhan benhNhan = new BenhNhan();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "select * from khambenh where makhambenh like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while (rs.next()){
                benhNhan = getBenhNhanById(rs.getString(1));
                benhNhan.setTrieuChung(rs.getString(4));
                benhNhan.setChuanDoan(rs.getString(5));
                benhNhan.setTienSu(rs.getString(6));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return benhNhan;
    }

    public BenhNhan getBenhNhanById(String id){
        BenhNhan benhNhan = new BenhNhan();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "select * from BenhNhan where MaBenhNhan like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,id);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while (rs.next()){
                benhNhan.setMaBenhNhan(rs.getString(1));
                benhNhan.setTenBenhNhan(rs.getString(2));
                benhNhan.setDiaChi(rs.getString(3));
                benhNhan.setDienThoai(rs.getString(4));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return benhNhan;
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
        } catch (Exception e) {
            e.printStackTrace();
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

        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
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


    public ArrayList<BenhNhan> getDanhSachBenhNhanTheoPhongKham(String maPhongKham){
    ArrayList<BenhNhan> danhSachBenhNhan = new ArrayList<>();
        Connection  con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
       try {
           String maHangDoi = getMaHangDoiFrom(maPhongKham);
            con = ConnectionDB.getConnection();
            String sql = "select * from cthangdoi where mahangdoi like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,maHangDoi);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                String maBenhNhan = rs.getString(2);
                danhSachBenhNhan.add(getBenhNhanById(maBenhNhan));
            }
       }catch (Exception e){
           e.printStackTrace();
       }
        return danhSachBenhNhan;
    }

//    Sub method, function

    private String getMaHangDoiFrom(String maPhongKham){
        String result = "";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
         try{

             con =  ConnectionDB.getConnection();
             String sql= "select * from HangDoi where maPhongKham like ? ";
             preparedStatement = con.prepareStatement(sql);
             preparedStatement.setString(1,maPhongKham);
             rs = preparedStatement.executeQuery();
             rs.beforeFirst();
            while (rs.next()){
                result = rs.getString(1);
            }


        }catch (Exception e){
             e.printStackTrace();
}
        return result;
    }

public String getMaKhamBenh(String benhNhanId){
    String result = "";
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    try{

        con =  ConnectionDB.getConnection();
        String sql= "select kb.MaKhamBenh from Khambenh kb join  CtHangDoi ct on kb.Mahangdoi = ct.mahangdoi where ct.mabenhnhan like ? and kb.mabenhnhan like ? ";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,benhNhanId);
        preparedStatement.setString(2,benhNhanId);
        rs = preparedStatement.executeQuery();
        rs.beforeFirst();
        while (rs.next()){
            result = rs.getString(1);
        }


    }catch (Exception e){
        e.printStackTrace();
    }
    return result;
}
//Test
    public static void main(String[] args) {
        BenhNhanDAO c = new BenhNhanDAO();
        System.out.println(c.getMaKhamBenh("bn4"));
    }


}
