package com.DAOS;

import com.DBUtils.ConnectionDB;
import com.Models.BenhNhan;
import com.Models.DichVu;
import com.Models.Thuoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BenhNhanDAO {
	static ConnectionDB currentcon;
	static ResultSet rs = null;
	private java.sql.PreparedStatement pstmt = null;
    public void updateTrieuChung(String idBenhNhan, String trieuChung){
//        Code here
    }
    public void updateThuoc(String idBenhNhan, Thuoc thuoc){
//        Code here
    }
    public BenhNhan getBenhNhanById(String id){
//        Code here
    	BenhNhan benhnhan = null;
    
    	java.sql.Connection connect=ConnectionDB.getConnection();
    	String sql="select * from benhnhan where maBenhNhan = ?";
    	if (connect!=null) {
    		try {
				pstmt=connect.prepareStatement(sql);
				//init parameterer
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					 benhnhan=new BenhNhan();
					benhnhan.setMaBenhNhan(rs.getString("maBenhNhan"));
					benhnhan.setTenBenhNhan(rs.getString("tenBenhNhan"));
					benhnhan.setDiaChi(rs.getString("diaChi"));
					benhnhan.setDienThoai(rs.getString("dienThoai"));
		
					
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
    	
  
    	
    	
    
    	
        return benhnhan;
    }
    public void checkId(String id){
//        Code here
    }
    public void addBenhNhan(BenhNhan benhNhan){
//        code here
    }
    public ArrayList<BenhNhan> getDanhSachBenhNhan(){
		//        Code here
    	java.sql.Connection connect=ConnectionDB.getConnection();
    	ArrayList<BenhNhan> result=new ArrayList<BenhNhan>();
    	String sql="select * from benhnhan";
    	if (connect!=null) {
    		try {
				pstmt=connect.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					BenhNhan benhnhan=new BenhNhan();
					benhnhan.setMaBenhNhan(rs.getString("maBenhNhan"));
					benhnhan.setTenBenhNhan(rs.getString("tenBenhNhan"));
					benhnhan.setDiaChi(rs.getString("diaChi"));
					benhnhan.setDienThoai(rs.getString("dienThoai"));
					result.add(benhnhan);
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;

    }
   
    //lay danh sach benh nahn tu hang doi
    public ArrayList<BenhNhan> getDanhSachBenhNhanTuHangDoi(){
		//        Code here
    	java.sql.Connection connect=ConnectionDB.getConnection();
    	ArrayList<BenhNhan> result=new ArrayList<BenhNhan>();
    	String sql="select benhnhan.* from cthangdoi join benhnhan where cthangdoi.maBenhNhan=benhnhan.maBenhNhan";
    	if (connect!=null) {
    		try {
				pstmt=connect.prepareStatement(sql);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					BenhNhan benhnhan=new BenhNhan();
					benhnhan.setMaBenhNhan(rs.getString("maBenhNhan"));
					benhnhan.setTenBenhNhan(rs.getString("tenBenhNhan"));
					benhnhan.setDiaChi(rs.getString("diaChi"));
					benhnhan.setDienThoai(rs.getString("dienThoai"));
					result.add(benhnhan);
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;

    }
    //chuan doan benh nhan by id
    public BenhNhan chuanDoanById(String id){
//      Code here
  	BenhNhan bn = null;
  
  	java.sql.Connection connect=ConnectionDB.getConnection();
  	String sql="select khambenh.maBenhNhan,benhnhan.tenBenhNhan,khambenh.trieuChung,khambenh.chuanDoan ,khambenh.tienSu from khambenh join benhnhan on khambenh.maBenhNhan=benhnhan.maBenhNhan where benhnhan.maBenhNhan=?";
  	if (connect!=null) {
  		try {
				pstmt=connect.prepareStatement(sql);
				//init parameterer
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					String maBN=rs.getString("maBenhNhan");
					String ten=rs.getString("tenBenhNhan");
					String trieuChung=rs.getString("trieuChung");
					String chuanDoan=rs.getString("chuanDoan");
					String tienSu=rs.getString("tienSu");
		 bn=new BenhNhan(maBN, ten, trieuChung, chuanDoan, tienSu);
		 System.out.println(bn);
					
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
  	

      return bn;
  }
    public String maKhamBenh(String id){
//      Code here
  	String maKB="";
  
  	java.sql.Connection connect=ConnectionDB.getConnection();
  	String sql="SELECT  maKhamBenh from khambenh where maBenhNhan = ?";
  	if (connect!=null) {
  		try {
				pstmt=connect.prepareStatement(sql);
				//init parameterer
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					maKB=rs.getString(1);
		
					
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}

      return maKB;
  }
    //lay thuoc theo ma kham benh
    public ArrayList<Thuoc> getThuocByMaKhamBenh(String maKB){
//      Code here
  	ArrayList<Thuoc> listThuoc = new ArrayList<Thuoc>();
  	
  
  	java.sql.Connection connect=ConnectionDB.getConnection();
  	String sql="select thuoc.tenThuoc,thuoc.donVi,hoadonthuoc.soLuong,thuoc.giaBan,thuoc.cachDung from hoadonthuoc join thuoc on thuoc.maThuoc=hoadonthuoc.maThuoc where maKhamBenh= ?";
  	if (connect!=null) {
  		try {
				pstmt=connect.prepareStatement(sql);
				//init parameterer
				pstmt.setString(1, maKB);
				rs=pstmt.executeQuery();
				while (rs.next()) {
		
					String tenThuoc=rs.getString("tenThuoc");
					String donVi=rs.getString("donVi");
					int soLuong=rs.getInt("soLuong");
					int giaBan=rs.getInt("giaBan");
					String cachDung=rs.getString("cachDung");
					Thuoc thuoc=new Thuoc(tenThuoc, donVi, soLuong, giaBan, cachDung);
					listThuoc.add(thuoc);
					
		
					
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
  	

  	
  	
  
  	
      return listThuoc;
  }
    //arraylist dich vu
    public ArrayList<DichVu> getDichVuByMaKhamBenh(String maKB){
//      Code here
  	ArrayList<DichVu> listDichVu = new ArrayList<DichVu>();
  	
  
  	java.sql.Connection connect=ConnectionDB.getConnection();
  	String sql="select  dichvu.tenDichVu,dichvu.donVi,hoadondichvu.soLuong,dichvu.giaBan from dichvu join hoadondichvu on dichvu.maDichVu=hoadondichvu.maDichVu where maKhamBenh=?";
  	if (connect!=null) {
  		try {
				pstmt=connect.prepareStatement(sql);
				//init parameterer
				pstmt.setString(1, maKB);
				rs=pstmt.executeQuery();
				while (rs.next()) {
		
					String tenDichVu=rs.getString("tenDichVu");
					String donVi=rs.getString("donVi");
					int soLuong=rs.getInt("soLuong");
					int giaBan=rs.getInt("giaBan");

					DichVu dichvu =new DichVu(tenDichVu, donVi, giaBan, soLuong);
					listDichVu.add(dichvu);
					
		
					
				}
				if (connect!=null) connect.close();
				if (pstmt!=null) pstmt.close();
				if (rs!=null) rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
  	

  	
  	
  
  	
      return listDichVu;
  }
    
    public static void main(String[] args) {
		BenhNhanDAO dao=new BenhNhanDAO();
//		ArrayList<BenhNhan> list=dao.getDanhSachBenhNhanTuHangDoi();
//		System.out.println(list);
//		BenhNhan bn=dao.getBenhNhanById("BN03");
//		BenhNhan bn=dao.chuanDoanById("BN01");
//		System.out.println(bn.getTienSu());
//		String a=dao.maKhamBenh("BN01");
//		System.out.println(a);
//		ArrayList<Thuoc> list=dao.getThuocByMaKhamBenh("KB01");
//		System.out.println(list);
	ArrayList<DichVu> list=dao.getDichVuByMaKhamBenh("KB01");
	System.out.println(list);

	}
}
