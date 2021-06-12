package com.Controllers;

import com.Models.BenhNhan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "KhamBenhController", value = "/KhamBenhController")
public class KhamBenhController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

// Main function and method

    public BenhNhan getBenhNhanById(String id){
//code here
        return null;
    }

    public ArrayList<BenhNhan> getDanhSachBenhNhan(){
//        Code here
        return  null;
    }
    public void themTrieuChung(String idBenhNhan, String trieuChung){
//        Code here
    }
    public void keThuoc(){
//        Code here
    }
}
