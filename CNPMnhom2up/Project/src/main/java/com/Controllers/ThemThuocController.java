package com.Controllers;

import com.Models.Thuoc;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ThemThuocController", value = "/ThemThuocController")
public class ThemThuocController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    main function and method
    public ArrayList<Thuoc> getToanBoThuoc(){
//        Code here
        return  null;
    }

    public Thuoc getThuocById(String id){
//        Code here
        return null;
    }
    public void themThuoc(String idThuoc, int soLuong){
//        Code here
    }


}
