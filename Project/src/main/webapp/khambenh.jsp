<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/khambenh.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <title>Document</title>
    
</head>
<body>
<header class="header">Màn Hình Khám bệnh</header>
    <div class="container">
        <div class="column__left">
            <div class="column__left-frames1">
              
                <div class="frames1-row3" >
                    <label for="">Tìm bn</label>
                    <input type="text" style="margin-left: 18px;">
                    <button class="frames1-row1-button1">Cập nhật</button>
                </div>
                <div class="frames1-row2"></div>
                <div class="frames1-row3"></div>

            </div>
            <div class="column__left-frames2" >
                <div class="frames2">
                    <table>
                        <thead>
                        <tr>
                          <th>Mã bệnh nhân</th>
                          <th>Họ và Tên</th>
                          <th>Điện Thoại<th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${listBenhNhan}" var="benhNhan">
                        <tr>
                          <td><a href="KhamBenhController?phongkham=${phongKham}&maBenhNhan=${benhNhan.maBenhNhan}">  ${benhNhan.maBenhNhan}</a>
                                </td>
                          <td>${benhNhan.tenBenhNhan}</td>
                          <td>${benhNhan.dienThoai}</td>
                        </tr>
                        </c:forEach>
                        </tbody>

                      </table>
                </div>
            </div>
        </div>
        <div class="column__right">
            <div class="column__right-frames1">
    <form action="KhamBenhController" method="get">
                <div class="column__right-frames1-1">
                    <div class="column__right-frames1-1-row1">
                        <input type="hidden" value="${phongKham}" name="phongkham">
                        <label for="">Mã bệnh nhân</label>
                        <input type="text" value="${benhNhan.maBenhNhan}" name="maBenhNhan" style="width: 100px;">
                        <label for="">Ngày</label>
                        <input type="text" style="width: 150px;">
                        
                    </div>

                    <div class="column__right-frames1-1-row2">
                        <label for="">Họ và Tên</label>
                        <input type="text" value="${benhNhan.tenBenhNhan}" style="width: 500px;">

                    </div>
                    <div class="column__right-frames1-1-row3">
                        <label for="">Địa chỉ</label>
                        <input type="text" value="${benhNhan.diaChi}" style="width: 220px;">
                        <label for="">Điện thoại</label>
                        <input type="text" value="${benhNhan.dienThoai}">
                    </div>
                    <div class="column__right-frames1-1-row4">
                        <label for="">Chẩn đoán</label>
                        <input type="text" value="${benhNhan.chuanDoan}" style="width: 200px; margin-right: 53px;">
                        <label for="">Triệu chứng</label>
                        <input type="text"  value="${benhNhan.trieuChung}" name="trieuChung" style="width: 200px;">
                    </div>
                    <div class="column__right-frames1-1-row6">

                        <label for="">Tiền sử</label>
                        <input type="text" style="width: 500px;">
                    </div>
                    <div class="column__right-frames1-1-row7">
                       <a style="" href="ThemThuocController?phongkham=${phongKham}&maKhamBenh=${maKhamBenh}&maBenhNhan=${benhNhan.maBenhNhan}"> Thêm thuốc </a>
                        <button style="margin-left: 500px" type="submit"> Lưu <i class="far fa-save fa-2x"></i></button>
                    </div>

                    
        
                </div>
    </form>
                <div class="column__right-frames1-2">
                    <label for="">Dấu hiệu sinh tồn</label>
                    <div class="frames1-2-row1" >
                        <label for="">Huyết áp</label>
                        <input type="text" name="" id="" style="width: 70px;">
                        <label for="">mmHG</label>
                    </div>
                    <div class="frames1-2-row2">
                        <label for="">Nhiệt độ</label>
                        <input type="text" name="" id="" style="width: 70px;">
                        <label for="">C</label>
                    </div>
                    <div class="frames1-2-row3">
                        <label for="">Mạch</label>
                        <input type="text" name="" id="" style="width: 70px; margin-left: 20px;">
                        <label for="">Lần/phút</label>
                    </div>
                    <div class="frames1-2-row4">
                        <label for="">Nhịp thở</label>
                        <input type="text" name="" id="" style="width: 70px;">
                        <label for="">Lần phút</label>
                    </div>
                    <div class="frames1-2-row5">
                        <label for="">Sp02</label>
                        <input type="text" name="" id="" style="width: 70px; margin-left: 24px;">
                        <label for="">%</label>
                    </div>
                
                    

                </div>
            </div>
            <div class="column__right-frames2">
                <table>
                    <tr>
                      <th>Tên thuốc-Vật tư y tế</th>
                      <th>Đơn vị</th>
                      <th>Số lượng </th>
                      <th>Giá bán</th>
                      <th>Cách dùng</th>
                      <th>Tổng</th>
                        <th> Xóa </th>
                    </tr>
                    <c:forEach items="${benhNhan.getDanhSachThuoc()}" var="thuoc">
                    <form action="KhamBenhController" method="get">
                    <tr>
                        <input type="hidden" value="${phongKham}" name="phongkham">
                        <input type="hidden" value="${benhNhan.maBenhNhan}" name="maBenhNhan">
                        <input type="hidden" value="${thuoc.getMaThuoc()}" name="xoaThuoc">
                      <td>${thuoc.getTenThuoc()}</td>
                      <td>${thuoc.getDonVi()}</td>
                      <td>${thuoc.getSoLuong()}</td>
                      <td>${thuoc.getGiaBan()} VNĐ</td>
                      <td>${thuoc.getCachDung()}</td>
                      <td>${thuoc.getTongTienThuoc()} VNĐ</td>
                        <td><button type="submit">Xóa</button></td>
                        </c:forEach>
                    </tr
                    </form>
                  </table>

            </div>
            <div class="column__right-frames3">
                <table>
                    <tr>
                      <th>Dịch vụ-Cận lâm sàng</th>
                      <th>Đơn vị</th>
                      <th>Số lượng </th>
                      <th>Giá bán</th>
                      <th>Tổng</th>
                      
                    </tr>
                    <tr>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td></td>

                    </tr>
                  </table>

            </div>
            <div class="column__right-frames4">
                <div class="frames4-1">
                    <div class="frames4-1-row1" style="display: flex;">
                        <div>
                        <label for="">Lời dặn</label>
                        <input type="text">
                        </div>
                        <div>
                            <label for="">Y,Bác sĩ</label>
                            <select id="cars">
                                <option value="bacsi1"> BS.Minh Nghĩa</option>
                                <option value="bacsi2">Yta.Minh Thư</option>    
                              </select>
                        </div>    
                    </div>

                    <div class="frames4-1-row2" style="margin-top: 5px;">
                        <form action="KhamBenhController" method="get">
                            <input value="${phongKham}" name="phongkham" hidden>
                        <input value="${benhNhan.maBenhNhan}" name="delete" hidden>
                        <button type="submit">Xac nhận <i class="far fa-check-circle fa-2x"></i></button>
                        </form>
                        <button>In Đơn <i class="fas fa-print fa-2x"></i></button>
                      
                    </div>


                </div>
                <div class="frames4-2">
                    <div class="frames4-2-row1" >
                        <label for="">Tổng tiền thu</label>
                        <input type="text" name="" id="" style="margin-left: 40px;">
                    </div>
                    <div class="frames4-2-row2">
                        <label for="">Tiền trả bệnh nhân</label>
                        <input type="text" name="" id="">
                    </div>
                    <div class="frames4-2-row3">
                        <label for="">Kết quả</label>
                        <input type="text" name="" id="" style="margin-left: 78px;">
                    </div>
                </div>
            </div>
           
            
        </div>
    </div>
     
</body>
</html>