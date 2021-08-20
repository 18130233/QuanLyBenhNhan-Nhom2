package com.DAOS;

import com.DBUtils.ConnectionDB;
import com.Models.Thuoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThuocDAO {

    public ArrayList<Thuoc> getThuocByMaKhamBenh(String maKhamBenh){
        ArrayList<Thuoc> danhSachThuoc = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "select * from hoadonthuoc where makhambenh like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,maKhamBenh);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                int soLuong = rs.getInt(3);
                Thuoc thuoc = getThuocById(rs.getString(2),soLuong);
                danhSachThuoc.add(thuoc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return danhSachThuoc;
    }

    public ArrayList<Thuoc> getToanBoThuoc(){
        ArrayList<Thuoc> danhSachThuoc = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "select * from thuoc";
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while(rs.next()){
                Thuoc thuoc = new Thuoc();
               thuoc.setMaThuoc(rs.getString(1));
               thuoc.setTenThuoc(rs.getString(2));
               thuoc.setDonVi(rs.getString(3));
               thuoc.setGiaBan(rs.getInt(4));
               thuoc.setCachDung(rs.getString(5));
               danhSachThuoc.add(thuoc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return  danhSachThuoc;
    }
    public Thuoc getThuocById(String idThuoc, int soLuong){
        Thuoc thuoc = new Thuoc();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "select * from thuoc where mathuoc like ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,idThuoc);
            rs = preparedStatement.executeQuery();
            rs.beforeFirst();
            while (rs.next()){
                thuoc.setMaThuoc(rs.getString(1));
                thuoc.setTenThuoc(rs.getString(2));
                thuoc.setDonVi(rs.getString(3));
                thuoc.setGiaBan(rs.getInt(4));
                thuoc.setCachDung(rs.getString(5));
                thuoc.setSoLuong(soLuong);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return thuoc;
    }
    public void updateThuoc(String maKhamBenh,Thuoc thuoc){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "INSERT INTO hoadonthuoc (makhambenh, mathuoc, soluong)\n" +
                    "VALUES (?, ?, ?);";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,maKhamBenh);
            preparedStatement.setString(2,thuoc.getMaThuoc());
            preparedStatement.setInt(3,thuoc.getSoLuong());

            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteThuoc(String maKhambenh, String maThuoc){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try{
            con = ConnectionDB.getConnection();
            String sql = "DELETE FROM hoadonthuoc WHERE mathuoc =? and makhambenh = ?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,maThuoc);
            preparedStatement.setString(2,maKhambenh);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThuocDAO thuocDAO = new ThuocDAO();
       thuocDAO.deleteThuoc("KB-1","T-1");

    }
}
