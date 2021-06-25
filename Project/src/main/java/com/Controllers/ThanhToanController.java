package com.Controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAOS.BenhNhanDAO;
import com.Models.BenhNhan;
import com.Models.DichVu;
import com.Models.Thuoc;

@WebServlet(name = "searchID",urlPatterns =  "/searchID" )

public class ThanhToanController extends HttpServlet {
    /**
     *
     */
    String idBenhnhan = "";
    double tongTienThuoc;
    double tongTienDichVu;
    double tongTienThanhToan;
    String tongTien;
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get
        String action = req.getParameter("action");

        BenhNhanDAO dao;
        switch (action) {
            case "list":
                dao = new BenhNhanDAO();
                ArrayList<BenhNhan> list = dao.getDanhSachBenhNhanTuHangDoi();
                // push
                resp.setContentType("text/html;charset=UTF-8");
                HttpSession session = req.getSession();
                session.setAttribute("listBN", list);
                //
                req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);

                break;
            case "searchID2":
                idBenhnhan = req.getParameter("uname");

                System.out.println("ID" + idBenhnhan);
                dao = new BenhNhanDAO();
                ArrayList<BenhNhan> list1 = dao.getDanhSachBenhNhanTuHangDoi();
                BenhNhan bn = dao.getBenhNhanById2(idBenhnhan);
                BenhNhan bn2 = dao.chuanDoanById(idBenhnhan);
//	      System.out.println(bn);
                String id = bn.getMaBenhNhan();
                String name = bn.getTenBenhNhan();
                String sdt = bn.getDienThoai();
                String diaChi = bn.getDiaChi();
                String trieuChung = bn2.getTrieuChung();
                String chuanDoan = bn2.getChuanDoan();
                String tienSu = bn2.getTienSu();
                //xu li 1 ti
                System.out.println("list 1 ne"+list1);
                System.out.println(idBenhnhan+"idne");
                for (int i = 0; i < list1.size(); i++) {
                    if (list1.get(i).getMaBenhNhan().equals(idBenhnhan)) {

                        req.setAttribute("idBenhnhan", idBenhnhan);
                    }


                }


                //
                req.setAttribute("id", id);
                req.setAttribute("name", name);
                req.setAttribute("sdt", sdt);
                req.setAttribute("diaChi", diaChi);
                req.setAttribute("trieuChung", trieuChung);
                req.setAttribute("chuanDoan", chuanDoan);
                req.setAttribute("tienSu", tienSu);
                req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);
                break;
            case "Inhoadon":
                BenhNhanDAO dao2 = new BenhNhanDAO();
                System.out.println("idne" + idBenhnhan);
                String maKhamBenh = dao2.maKhamBenh(idBenhnhan);
                System.out.println(maKhamBenh);
                // lấy ra list thuốc
                HttpSession session1 = req.getSession();

                ArrayList<Thuoc> listThuoc = dao2.getThuocByMaKhamBenh(maKhamBenh);
                System.out.println(listThuoc);
                for (int i = 0; i < listThuoc.size(); i++) {
                    tongTienThuoc+=listThuoc.get(i).getGiaBan()*listThuoc.get(i).getSoLuong();

                }


                session1.setAttribute("listThuoc", listThuoc);



                //lấy ra list dịch vụ
                HttpSession session2 = req.getSession();

                ArrayList<DichVu> listdichVu = dao2.getDichVuByMaKhamBenh(maKhamBenh);
                for (int i = 0; i < listdichVu.size(); i++) {
                    tongTienDichVu+=listdichVu.get(i).getGiaBan()*listdichVu.get(i).getSoLuong();
                }

                System.out.println("list dich vu ne...."+listdichVu);
                session2.setAttribute("listDichVu", listdichVu);
                tongTienThanhToan=tongTienThuoc+tongTienDichVu;
                tongTien=String.valueOf(tongTienThanhToan);

                req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);

                break;
            case "Capnhatthanhtoan":
                HttpSession session3 = req.getSession();
                session3.setAttribute("tongTien", tongTien);
                req.getRequestDispatcher("thanhtoan.jsp").forward(req, resp);
                break;


            default:
                break;
        }

    }

    public static void main(String[] args) {
        BenhNhanDAO dao = new BenhNhanDAO();
        ArrayList<BenhNhan> ds = dao.getDanhSachBenhNhan();
        System.out.println(ds);
    }

}
