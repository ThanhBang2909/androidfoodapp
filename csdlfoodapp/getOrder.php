<?php
    require 'connect.php';

    $email = $_GET['email'];

    class Order{
        function __construct($maHD, $name, $phone, $address, $thanhTien, $status){
            $this->maHD = $maHD;
            $this->name = $name;
            $this->phone = $phone;
            $this->address = $address;
            $this->thanhTien = $thanhTien;
            $this->status = $status;
        }
    }

    if($email==null)
        $query = "select * from hoadon";
    else
        $query = "select * from hoadon where email=$email";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new Order($row["maHD"],
        $row["name"], $row["phone"], $row["address"],$row["thanhTien"], $row["status"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
