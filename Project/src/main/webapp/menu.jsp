<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/menu.css">
    <title>Menu</title>
</head>

<body>
    <section class="banner">

        <img src="./images/doctor-2.jpg" id="myImage">
        <div class="overlay">
        </div>
        <div class="banner-content">
            <div class="container">
       
                <div class="row">
                    <div class="col-md-4">
                        <form method="get" action="TiepNhanBenhNhanController">
                        <button name = "Tiepnhanbenhnhan" value="tiepnhan" class="btn">Tiếp nhận bệnh nhân</button>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <form method="get" action="KhamBenhController">
                            <div class="row">
                            <div class="col-md-4"> <select Name="phongkham" Size="Number_of_options">
                                <option value="pk1" name="phongkham"> Ngoại khoa </option>
                                <option value="pk2"  name="phongkham"> Nội khoa </option>
                                <option value="pk3"  name="phongkham"> Khoa mắt </option>
                                <option value="pk4"  name="phongkham"> Khoa răng </option>
                            </select> </div>
                            <button type="submit" class="btn">Khám bệnh</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <form method="get"  action="searchID">
                            <button name="action" value="list" class="btn">Thanh Toán</button>
                        </form>
                    </div>

                </div>
            </div>
            <!-- /.container -->
            <!-- /.banner-content -->
        </div>
    </section>

</body>

</html>