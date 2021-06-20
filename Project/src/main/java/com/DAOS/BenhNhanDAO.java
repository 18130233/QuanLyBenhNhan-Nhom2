package com.DAOS;

import com.DBUtils.ConnectionDB;
import com.Models.BenhNhan;
import com.Models.Thuoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public void checkId(String id){
//        Code here
    }
    public void addBenhNhan(BenhNhan benhNhan){
//        code here
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
