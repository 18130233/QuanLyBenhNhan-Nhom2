package com.Controllers;

import com.Models.BenhNhan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ThanhToanController", value = "/ThanhToanController")
public class ThanhToanController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    Main function and method

    public BenhNhan getThongTinBenhNhan(String idBenhNhan){
//        Code here
        return null;
    }
    public int tinhTongTien(String idBenhNhan){
//        Code here
        return 0;
    }
}
