package com.Controllers;

import com.DAOS.BenhNhanDAO;
import com.DAOS.ThuocDAO;
import com.Models.BenhNhan;
import com.Models.Thuoc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@WebServlet(name = "KhamBenhController", urlPatterns = "/KhamBenhController")
public class KhamBenhController extends HttpServlet {
    ArrayList<BenhNhan> danhSachBenhNhan = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BenhNhan benhNhanChiDinh = new BenhNhan();
        String maPhongKham = request.getParameter("phongkham");
        String maKhamBenh = getMaKhamBenhByIdBenhNhan(request.getParameter("maBenhNhan"));
        benhNhanChiDinh = getBenhNhanByMaKhamBenh(maKhamBenh);
        log(benhNhanChiDinh.getMaBenhNhan());
        ArrayList<Thuoc> danhSachThuoc = getDanhSachThuocByMaKhamBenh(maKhamBenh);
        benhNhanChiDinh.setDanhSachThuoc(danhSachThuoc);
        danhSachBenhNhan = getDanhSachBenhNhanTheoPhongKham(maPhongKham);
        request.setAttribute("maKhamBenh", maKhamBenh);
        request.setAttribute("benhNhan",benhNhanChiDinh);
        request.setAttribute("phongKham",maPhongKham);
        request.setAttribute("listBenhNhan",danhSachBenhNhan);
        request.getRequestDispatcher("khambenh.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

// Main function and method

    public ArrayList<Thuoc> getDanhSachThuocByMaKhamBenh(String maKhamBenh){
        ThuocDAO thuocDAO = new ThuocDAO();
      return  thuocDAO.getThuocByMaKhamBenh(maKhamBenh);
    }

    public String getMaKhamBenhByIdBenhNhan(String id){
        BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
        return benhNhanDAO.getMaKhamBenh(id);
    }

    public BenhNhan getBenhNhanByMaKhamBenh(String id){
        BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
        return benhNhanDAO.getBenhNhanByMaKhamBenh(id);

    }

    public ArrayList<BenhNhan> getDanhSachBenhNhanTheoPhongKham(String maHangDoi){
        BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
        return benhNhanDAO.getDanhSachBenhNhanTheoPhongKham(maHangDoi);
    }
    public void themTrieuChung(String maKhamBenh, String trieuChung){
        BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
        benhNhanDAO.updateTrieuChung(maKhamBenh,trieuChung);
    }
    public void keThuoc(){
//        Code here
    }
}
