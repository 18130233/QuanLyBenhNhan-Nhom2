package com.Controllers;

import com.Models.BenhNhan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TiepNhanBenhNhanController", value = "/TiepNhanBenhNhanController")
public class TiepNhanBenhNhanController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

//    Main function and method
    public void checkId(String id){
//        Code here
    }

    public void tiepNhanBenhNhan(BenhNhan benhNhan){
//        Code here
    }
}
