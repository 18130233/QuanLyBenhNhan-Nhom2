package com.Controllers;

import com.DAOS.BenhNhanDAO;
import com.DAOS.ThuocDAO;
import com.Models.BenhNhan;
import com.Models.Thuoc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ThemThuocController", urlPatterns = "/ThemThuocController")
public class ThemThuocController extends HttpServlet {
    ArrayList<Thuoc> danhSachThuoc = new ArrayList<Thuoc>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        danhSachThuoc = getToanBoThuoc();
        String phongKham = request.getParameter("phongkham");
        String maKhamBenh = request.getParameter("maKhamBenh");
        String maBenhNhan = request.getParameter("maBenhNhan");
        String maThuoc = request.getParameter("maThuoc");
        if(request.getParameter("quantity") != null){
            int soLuong = Integer.parseInt(request.getParameter("quantity"));
            Thuoc thuoc = getThuocById(maThuoc,soLuong);
            themThuoc(maKhamBenh,thuoc);
        }

        request.setAttribute("phongKham",phongKham);
        request.setAttribute("maBenhNhan",maBenhNhan);
        request.setAttribute("maKhamBenh",maKhamBenh);
        request.setAttribute("danhSachThuoc",danhSachThuoc);
        request.getRequestDispatcher("themthuoc.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

//    main function and method
    public ArrayList<Thuoc> getToanBoThuoc(){
        ThuocDAO thuocDAO = new ThuocDAO();
        return  thuocDAO.getToanBoThuoc();
    }

    public Thuoc getThuocById(String id, int soLuong){
        ThuocDAO thuocDAO = new ThuocDAO();
        return thuocDAO.getThuocById(id, soLuong);

    }
   public void themThuoc(String maKhamBenh, Thuoc thuoc){
        ThuocDAO thuocDAO = new ThuocDAO();
        thuocDAO.updateThuoc(maKhamBenh,thuoc);

    }


}
