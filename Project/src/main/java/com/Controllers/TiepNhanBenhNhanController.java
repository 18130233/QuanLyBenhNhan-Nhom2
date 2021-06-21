package com.Controllers;

import com.DAOS.BenhNhanDAO;
import com.Models.BenhNhan;
import com.Models.Date;
import com.Models.PhongKham;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "TiepNhanBenhNhanController", value = "/TiepNhanBenhNhanController")
public class TiepNhanBenhNhanController extends HttpServlet {
    private int succeedChecked;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BenhNhan bn;
        ///checkId
        //biến succeedChecked cho ta biết được tình trạng của bệnh nhân đó trong csdl
        //0:Bệnh nhân chưa có trong csdl và id  là mã bhyt-> mã có và tên k có
        //-1:Bệnh nhân chưa có trong csdl và id null -> mã k có , tên k có
        //1:Bệnh nhân đã có trong csdl -> tên có, mã có
        String action = request.getParameter("action");
        System.out.println("---" + action);
        if (action != null && action.equals("kiemtra")) {
            String idBenhNhan = request.getParameter("id");
            System.out.println("---" + idBenhNhan);
            if (idBenhNhan == null || idBenhNhan == "") {
                bn = new BenhNhan();
                succeedChecked = -1;
                request.setAttribute("BN", bn);
                request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request, response);
                return;

            } else {
                bn = checkId(idBenhNhan);
                System.out.println("ten bn" + bn.getTenBenhNhan());
                if (bn.getTenBenhNhan() == "" || bn.getTenBenhNhan() == null) {
                    succeedChecked = 0;
                } else {
                    succeedChecked = 1;
                }

                System.out.println("checkedaaaaa: " + succeedChecked);
                request.setAttribute("BN", bn);
                request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request, response);
                return;
            }
        }
        String actionTiepnhan = request.getParameter("Tiepnhanbenhnhan");
        if (actionTiepnhan != null && actionTiepnhan.equals("tiepnhan")) {
            bn = new BenhNhan();
            request.setAttribute("BN", bn);
            request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request, response);
            return;
        }
        System.out.println("benhnhan: " + request.getAttribute("BN"));
        if (action != null && action.equals("ok")) {
            bn = new BenhNhan();
            String maBenhNhan = request.getParameter("id");
            if (maBenhNhan == null || maBenhNhan == "") succeedChecked = -1;
            bn.setMaBenhNhan(maBenhNhan);
            bn.setTenBenhNhan(request.getParameter("name"));
            bn.setDiaChi(request.getParameter("diaChi"));
            bn.setDienThoai(request.getParameter("sdt"));
            //ngay kham
            Date date = new Date();
            date.setStringDate(request.getParameter("date"));
            //phong kham
            PhongKham pk = new PhongKham();
            pk.setTenPhongKham(request.getParameter("assign"));
            //set pk vs date cho bn
            bn.setNgayKham(date);
            bn.setPhongKham(pk);

            System.out.println("checked: " + succeedChecked);
            if (tiepNhanBenhNhan(bn, succeedChecked)) {
                request.setAttribute("BN", bn);
                request.setAttribute("thongbao", "Tiếp nhận thành công!");
                request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("BN", bn);
                request.setAttribute("thongbao", "Tiếp nhận không thành công!");
                request.getRequestDispatcher("tiepnhanbenhnhan.jsp").forward(request, response);
                return;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //    Main function and method
    public BenhNhan checkId(String id) {
        BenhNhanDAO bnDAO = new BenhNhanDAO();
        return bnDAO.checkId(id);
    }

    public boolean tiepNhanBenhNhan(BenhNhan benhNhan, int succeedChecked) {
        BenhNhanDAO dao = new BenhNhanDAO();
        return dao.addBenhNhan(benhNhan, succeedChecked);
    }
}
