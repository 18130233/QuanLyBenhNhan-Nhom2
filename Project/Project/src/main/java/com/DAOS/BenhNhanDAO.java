package com.DAOS;

import com.DBUtils.ConnectionDB;
import com.Models.BenhNhan;
import com.Models.Date;
import com.Models.DichVu;
import com.Models.PhongKham;
import com.Models.Thuoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BenhNhanDAO {
	static ConnectionDB currentcon;
	static ResultSet rs = null;
	private java.sql.PreparedStatement pstmt = null;
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
            ps = cn.prepareStatement("select * from benhnhan where maBenhNhan like ?");
            ps.setString(1,id);
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
        //succeedChecked=1 -> khÃ´ng cáº§n thÃªm vÃ´
        //succeedChecked = 0:  thÃªm bá»‡nh bá»‡nh nhÃ¢n bÃ¬nh thÆ°á»�ng
        //succeedChecked=-1: cáº§n generate mÃ£
        //count 2 hoáº·c 3 thÃ¬ tiep nhan benh nhan thanh cong!
        int count = 0;
        if (succeedChecked == 0 || succeedChecked == -1) {
            insertDataInBenhNhan(benhNhan, succeedChecked);
            count = count + 1;
        }
        //tÃ¬m gia tri tham so phu hop cho CTHangDoi
        //lay ra ma hang doi dá»±a trÃªn tÃªn phÃ²ng khÃ¡m
        //lay ma chi tiet phong kham dá»±a trÃªn tÃªn phÃ²ng khÃ¡m
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
        //them báº£ng chi tiáº¿t hÃ ng Ä‘á»£i xong
        String maKhamBenh = gennerateID("select * from khambenh") + "";
        //ma benh nhan
        String mabenhnhan = benhNhan.getMaBenhNhan();
        System.out.println("-----"+mabenhnhan);
        //machiphongkham
        System.out.println("-----"+maCTPhongKham);
        //ngaykham
        String ngayKham = benhNhan.getNgayKham().getStringDate();
        System.out.println("-----"+ngayKham);
        //thÃªm báº£ng khÃ¡m bá»‡nh
        if (insertDataInKhamBenh(mabenhnhan, maKhamBenh, maCTPhongKham, "", "", "", maHangDoi, ngayKham))
            count = count + 1;
        //1 la them 2 bang hai la them 3 bang tuy vao benh nhan da co trong csdl chua?
        System.out.println("count" + count);
        return (count == 2 || count == 3);
    }

    private boolean insertDataInBenhNhan(BenhNhan benhNhan, int succeedChecked) {
        //dÃ¹ng khi benh nhan chua co trong csdl
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

    ///////////////////////////////////////////
    public BenhNhan getBenhNhanById2(String id){
//      Code here
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
    //
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
