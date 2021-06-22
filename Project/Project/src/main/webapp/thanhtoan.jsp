<%@page import="javax.sound.midi.SysexMessage"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page import="com.Models.*"%>
<%@ page import="com.DAOS.*"%>
<%@ page import="com.example.Project.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>




<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/thanhtoan.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <title>Document</title>

</head>
<body>
<header class="header">Màn Hình Thanh Toán</header>
<div class="container">
    <div class="column__left">
        <div class="column__left-frames1">




            <div class="frames1-row1" >
                <label for="" style="margin-left: 14px;">Ngày Đến</label>
                <input type="text">
                <button class="frames1-row1-button1">Tìm <i class="fas fa-search"></i></button>
            </div>
            <div class="frames1-row2" >
                <label for="">Đến ngày</label>
                <input type="text">
                <button class="frames1-row1-button1">Tìm<i class="fas fa-search"></i></button>
            </div>
            <form class="formLogin"
                  action="searchID" method="post">
                <div class="frames1-row3" >
                    <label for="">Tìm bn</label>
                    <input name="uname"  type="text" style="margin-left: 18px;">
                    <button name="action" value="searchID2" class="frames1-row1-button1">Cập nhật</button>
                </div>
            </form>
            <div class="frames1-row2"></div>
            <div class="frames1-row3"></div>

        </div>
        <div class="column__left-frames2"  style="height: 700px !important;">
            <div class="frames2">
                <table>
                    <tr>
                        <th>Mã bệnh nhân</th>
                        <th>Họ và Tên</th>
                        <th>Địa chỉ</th>
                    </tr>

                    <%
                        BenhNhanDAO dao=new BenhNhanDAO();
                        String idBenhnhan=(String) request.getAttribute("idBenhnhan");
                        ArrayList<BenhNhan> list = new ArrayList<BenhNhan>();
                        boolean ok;

                        //BenhNhanDAO dao =new BenhNhanDAO();
                        list =(ArrayList) session.getAttribute("listBN");
                        if ( list == null){
                            System.out.println("sai");
                        }
                        for (int i = 0; i < list.size(); i++) {

                    %>

                    <tr>


                        <td><%=list.get(i).getMaBenhNhan()%></td>
                        <td><%=list.get(i).getTenBenhNhan()%></td>
                        <td><%=list.get(i).getDiaChi()%></td>
                    </tr>
                    <%
                        }
                        ;
                    %>
                    <%
                        if (idBenhnhan != null) {
                    %>
                    <span style="color: green">Đã tìm thấy bệnh nhân trong hàng đợi</span>
                    <%
                        }
                    %>

                </table>

            </div>
        </div>
    </div>
    <%
        String id=(String) request.getAttribute("id");
        String name=(String) request.getAttribute("name");
        String sdt=(String) request.getAttribute("sdt");
        String diaChi=(String) request.getAttribute("diaChi");
        String trieuChung=(String) request.getAttribute("trieuChung");
        String chuanDoan=(String) request.getAttribute("chuanDoan");
        String tienSu=(String) request.getAttribute("tienSu");
        if(id==null) id="";
        if(name==null) name="";
        if(sdt==null) sdt="";
        if(diaChi==null) diaChi="";
        if(trieuChung==null) trieuChung="";
        if(chuanDoan==null) chuanDoan="";
        if(tienSu==null) tienSu="";
    %>


    <div class="column__right">
        <div class="column__right-frames1">
            <div class="column__right-frames1-1">
                <div class="column__right-frames1-1-row1">
                    <label for="">Mã bệnh nhân</label>
                    <input type="text" style="width: 100px;" value="<%=id%>">
                    <label for="">Ngày</label>
                    <input type="text" style="width: 150px;">

                </div>
                <div class="column__right-frames1-1-row2">
                    <label for="">Họ và Tên</label>
                    <input type="text" style="width: 100px;" value="<%=name%>">
                    <label for="">Tuổi</label>
                    <input type="text" style="width: 150px;">
                    <label for="">Tháng</label>
                    <input type="text" style="width: 80px;">

                </div>
                <div class="column__right-frames1-1-row3">
                    <label for="">Địa chỉ</label>
                    <input type="text" style="width: 220px;"value="<%=diaChi%>" >
                    <label for="">Điện thoại</label>
                    <input type="text" value="<%=sdt%> ">
                </div>
                <div class="column__right-frames1-1-row4">
                    <label for="">Chẩn đoán 1</label>
                    <input type="text" style="width: 200px; margin-right: 53px;"  value="<%=chuanDoan%>">
                    <label for="">Triệu chứng</label>
                    <input type="text" style="width: 200px;" value="<%=trieuChung%>">
                </div>
                <div class="column__right-frames1-1-row5">
                    <label for="">Chẩn đoán 2 </label>
                    <input type="text" style="width: 200px;margin-right: 53px;" value="<%=chuanDoan%>">
                    <label for="">Triệu chứng</label>
                    <input type="text" style="width: 200px;"  value="<%=trieuChung%>">
                </div>
                <div class="column__right-frames1-1-row6">
                    <label for="">Chẩn đoán 3</label>
                    <input type="text" style="width: 200px;margin-right: 85px;">
                    <label for="">Tiền sử</label>
                    <input type="text" style="width: 200px;"  value="<%=tienSu%>">
                </div>


            </div>
            <form class="formLogin"
                  action="searchID" method="post">
                <div class="column__right-frames1-2" >
                    <button name="action" value="Inhoadon" class="inhoadon">In Hóa Đơn <i class="fas fa-download fa-3x"></i></button>
                </div>
            </form>

        </div>

        <div class="column__right-frames2">
            <table>
                <tr>
                    <th>Tên thuốc-Vật tư y tế</th>
                    <th>Đơn vị</th>
                    <th>Số lượng</th>
                    <th>Giá bán</th>
                    <th>Cách dùng</th>

                </tr>
                <%
                    BenhNhanDAO dao1 = new BenhNhanDAO();
                    ArrayList<Thuoc> listThuoc = new ArrayList<Thuoc>();

                    //BenhNhanDAO dao =new BenhNhanDAO();
                    listThuoc = (ArrayList) session.getAttribute("listThuoc");
                    if (listThuoc == null) {
                        System.out.println("sai");
                    } else {
                        for (int i = 0; i < listThuoc.size(); i++) {



                %>
                <tr>
                    <td><%=listThuoc.get(i).getTenThuoc()%></td>
                    <td><%=listThuoc.get(i).getDonVi()%></td>
                    <td><%=listThuoc.get(i).getSoLuong()%></td>
                    <td><%=listThuoc.get(i).getGiaBan()%></td>
                    <td><%=listThuoc.get(i).getCachDung()%></td>

                </tr>
                <%
                        }
                    };

                %>
            </table>

        </div>
        <div class="column__right-frames3">
            <table>
                <tr>
                    <th>Dịch vụ-Cận lâm sàng</th>
                    <th>Đơn vị</th>
                    <th>Số lượng </th>
                    <th>Giá bán</th>


                </tr>
                <%
                    BenhNhanDAO dao2 = new BenhNhanDAO();
                    ArrayList<DichVu> listDichVu = new ArrayList<DichVu>();

                    //BenhNhanDAO dao =new BenhNhanDAO();
                    listDichVu = (ArrayList) session.getAttribute("listDichVu");
                    if (listDichVu == null) {
                        System.out.println("sai");
                    } else {
                        for (int i = 0; i < listDichVu.size(); i++) {



                %>

                <tr>
                    <td><%=listDichVu.get(i).getTenDichVu() %></td>
                    <td><%=listDichVu.get(i).getDonVi() %></td>
                    <td><%=listDichVu.get(i).getSoLuong() %></td>
                    <td><%=listDichVu.get(i).getGiaBan() %></td>


                </tr>
                <%
                        }
                    };

                %>

            </table>

        </div>
        <div class="column__right-frames4">
            <form class="formLogin"
                  action="searchID" method="post">
                <div class="frames4-1">
                    <Button name="action" value="Capnhatthanhtoan">Cập nhật thanh toán</Button>
                </div>
            </form>
            <%String tongTien=(String ) session.getAttribute("tongTien");
                if(tongTien==null) tongTien="";
            %>
            <div class="frames4-2">
                <div class="frames4-2-row1" >
                    <label for="">Tổng tiền thu</label>
                    <input type="text" name=""  style="margin-left: 40px;" value="<%=tongTien%>" >
                </div>

                <div class="frames4-2-row3">
                    <label for="">Kết quả</label>
                    <input type="text" name="" id="" style="margin-left: 78px;" value="<%=tongTien%>" >
                </div>
            </div>
        </div>


    </div>
</div>

</body>
</html>