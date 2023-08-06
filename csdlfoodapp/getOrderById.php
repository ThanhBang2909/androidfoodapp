<?php
    require 'connect.php';


    $maHD = $_GET['maHD'];

    class Order_by_id{
        function __construct($maHD, $maSanPham, $tenSanPham, $giaSanPham,$hinhSanPham ,$soLuong, $thanhTien){
            $this->maHD = $maHD;
            $this->maSanPham = $maSanPham;
            $this->tenSanPham = $tenSanPham;
            $this->giaSanPham = $giaSanPham;
            $this->hinhSanPham = $hinhSanPham;
            $this->soLuong = $soLuong;
            $this->thanhTien = $thanhTien;
        }
    }

    if($maHD==null)
        $query = "select * from chitiethoadon";
    else
        $query = "select * from chitiethoadon where maHD=$maHD";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new Order_by_id($row["maHD"], $row["maSanPham"], $row["tenSanPham"],
         $row["giaSanPham"],$row["hinhSanPham"] ,$row["soLuong"], $row["thanhTien"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>