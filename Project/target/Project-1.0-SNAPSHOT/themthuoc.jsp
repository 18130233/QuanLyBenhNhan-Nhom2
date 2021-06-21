<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Thêm thuốc</title>
    </head>

    <body>
        <div class="container">
            <table class="table   text-center table-striped">
                <thead>
                    <tr class="item header">
                        <th class="align-middle">Mã thuốc</th>
                        <th class="align-middle">Tên thuốc</th>
                        <th class="align-middle">Đơn vị</th>
                        <th class="align-middle">Giá bán</th>
                        <th class="align-middle">Số lượng</th>
                        <th class="align-middle">Cách dùng</th>
                        <th class="align-middle">Thêm thuốc</th>
                    </tr>
                </thead>
                <tbody>


                <c:forEach items="${danhSachThuoc}" var="thuoc">
<form action="ThemThuocController">

                    <tr class="item">
                        <!-- Template Reference variable-->
                        <td class="align-middle" scope="row">
                            <p>${thuoc.getMaThuoc()}</p>
                            <input name="maThuoc" value="${thuoc.getMaThuoc()}" hidden/>
                        </td>
                        <td class="align-middle">
                            <p>${thuoc.getTenThuoc()}</p>
                        </td>
                        <td class="align-middle">
                            <p>${thuoc.getDonVi()}</p>
                        </td>
                        <td class="align-middle">
                            <p>${thuoc.getGiaBan()} vnd</p>
                        </td>
                        <td class="align-middle">
                            <input class="quantity" type="number" value="" name="quantity" />
                        </td>
                        <td class="align-middle">
                            <p>${thuoc.getCachDung()}</p>
                        </td>
                    <input value="${maKhamBenh}" name="maKhamBenh" hidden />
                        <input value="${maBenhNhan}" name="maBenhNhan" hidden />
                        <input value="${phongKham}" name="phongkham" hidden />
                        <td class="align-middle">
                            <button type="submit"  class="remove_button">
                                Thêm thuốc
                            </button>
                        </td>
                    </tr>
</form>

</c:forEach>




                </tbody>
            </table>
            <a href="KhamBenhController?phongkham=${phongKham}&maBenhNhan=${maBenhNhan}"  class="remove_button">
                Quay về
            </a>

        </div>


    </body>

    </html>