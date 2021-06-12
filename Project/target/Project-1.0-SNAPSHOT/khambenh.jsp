<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" />
    <title>Document</title>
    
</head>
<body>
<header class="header">Màn Hình Thanh Toán</header>
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
                        <tr>
                          <th>Ngày khám</th>
                          <th>Họ và Tên</th>
                          <th>Tuổi</th>
                        </tr>
                        <tr>
                          <td>15/5/2021</td>
                          <td>Bình Minh</td>
                          <td>20</td>
                        </tr>
                      </table>
                </div>
            </div>
        </div>
        <div class="column__right">
            <div class="column__right-frames1">
                <div class="column__right-frames1-1">
                    <div class="column__right-frames1-1-row1">
                        <label for="">Mã bệnh nhân</label>
                        <input type="text" style="width: 100px;">
                        <label for="">Ngày</label>
                        <input type="text" style="width: 150px;">
                        
                    </div>
                    <div class="column__right-frames1-1-row2">
                        <label for="">Họ và Tên</label>
                        <input type="text" style="width: 100px;">
                        <label for="">Tuổi</label>
                        <input type="text" style="width: 150px;">
                        <label for="">Tháng</label>
                        <input type="text" style="width: 80px;">
                        <label for="">Cân nặng</label>
                        <input type="text" style="width: 80px;">
                    </div>
                    <div class="column__right-frames1-1-row3">
                        <label for="">Địa chỉ</label>
                        <input type="text" style="width: 220px;">
                        <label for="">Điện thoại</label>
                        <input type="text">
                    </div>
                    <div class="column__right-frames1-1-row4">
                        <label for="">Chẩn đoán 1</label>
                        <input type="text" style="width: 200px; margin-right: 53px;">
                        <label for="">Triệu chứng</label>
                        <input type="text" style="width: 200px;">
                    </div>
                    <div class="column__right-frames1-1-row5">
                        <label for="">Chẩn đoán 2 </label>
                        <input type="text" style="width: 200px;margin-right: 53px;">
                        <label for="">Triệu chứng</label>
                        <input type="text" style="width: 200px;">
                    </div>
                    <div class="column__right-frames1-1-row6">
                        <label for="">Chẩn đoán 3</label>
                        <input type="text" style="width: 200px;margin-right: 85px;">
                        <label for="">Tiền sử</label>
                        <input type="text" style="width: 200px;">
                    </div>
                    
        
                </div>
                <div class="column__right-frames1-2">
                  <button class="btnHistorypatient" >
                      Lịch sử Bệnh nhân
                  </button>
                
                    

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
                    </tr>
                    <tr>
                      <td>Paracetamol 200mg</td>
                      <td>Viên</td>
                      <td>4</td>
                      <td>5000</td>
                      <td>Điều trị đau đầu</td>
                      <td>20000</td>

                    </tr>
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
                      <td>1 the clinic</td>
                      <td>Ala</td>
                      <td>1</td>
                      <td>200000</td>
                      <td>200000</td>

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
                        <button>Lưu <i class="far fa-save fa-2x"></i></button>
                        <button>Xac nhận <i class="far fa-check-circle fa-2x"></i></button>
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