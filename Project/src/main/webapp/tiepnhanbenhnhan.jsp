<%@ page import="com.Models.BenhNhan" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language ="java" contentType ="text/html; charset = UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- CDN bootstrap css -->
    <link rel="stylesheet" href="./css/tiepnhanbenhnhan.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Tiếp Nhận Bệnh Nhân</title>
</head>

<body>
<section class="form_tiep_nhan">
    <div class="overlay"></div>
    <img class="background" src="./images/doctor-2.jpg" alt="">

    <div class="container pt-5">
        <div class="row title text-center">
            <div class="col-md-12 mb-5">
                <h2>Tiếp Nhận Bệnh Nhân</h2>
            </div>
        </div>
        <div class="row form">
            <div class="col-md-12">

                <form action="TiepNhanBenhNhanController" method="get">
                    <div class="row">
                        <%BenhNhan bn = (BenhNhan)request.getAttribute("BN");%>
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            Mã bệnh nhân
                        </div>
                        <div class="col-md-4"><input id = "idBenhNhan" type="text" size="40px" name="id" placeholder="Nhập mã bệnh nhân" value="<%=bn.getMaBenhNhan()%>">
                        </div>
                        <div class="col-md-2">
                            <button name="action" value="kiemtra" > Kiểm tra</button>
                        </div>

                    </div>
                    <div class="row mt-2">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            Tên bệnh nhân
                        </div>
                        <div class="col-md-4"><input type="text" size="40px" name="name"
                                                     placeholder="Nhập tên bệnh nhân" value="<%=bn.getTenBenhNhan()%>"></div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            Địa chỉ
                        </div>
                        <div class="col-md-4"><input type="text" size="40px" name="diaChi"
                                                     placeholder="Địa chỉ của bệnh nhân" value="<%=bn.getDiaChi()%>"></div>
                        <div class="col-md-2"></div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            Số điện thoại
                        </div>
                        <div class="col-md-4"><input type="text" size="40px" name="sdt" placeholder="Nhập số điện thoại"
                                                     value="<%=bn.getDienThoai()%>"></div>
                        <div class="col-md-2"></div>
                    </div>
                        <div class="row mt-2">
                            <div class="col-md-2"></div>
                            <div class="col-md-4">
                                Phòng khám</div>
                            <div class="col-md-4"> <select Name="assign" Size="Number_of_options">  
                                <option value="pk1"> Ngoại khoa </option>
                                <option value="pk2"> Nội khoa </option>
                                <option value="pk3"> Khoa mắt </option>
                                <option value="pk4"> Khoa răng </option>
                              </select> </div>
                            <div class="col-md-2"></div>
                        </div>

                    <div class="row mt-2">
                        <div class="col-md-2"></div>
                        <div class="col-md-4">
                            Ngày khám
                        </div>
                        <div class="col-md-4"><input  type="date" size="40px" name="date" value=""></div>
                        <div class="col-md-2"></div>

                    </div>
                    <div class="row">
                        <div class="col-md-12 text-center mt-5">
                            <button name="action" value="ok">Tiếp nhận bệnh nhân</button>
                        </div>
                    </div>
                    <%String thongbao =(String) request.getAttribute("thongbao");
                    if(thongbao!=null){%>
                    <div class="row">
                        <div class="col-md-12 text-center mt-2">
                            <input type="text" id="thanhcong" name="lname" style="width: 220px;" value="<%=thongbao%>" disabled>
                        </div>
                    </div>
                    <%}%>

                </form>
            </div>
        </div>
    </div>
</section>



<!-- JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>

</html>