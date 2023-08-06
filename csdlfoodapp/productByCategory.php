<?php
    require 'connect.php';


    $machude = $_GET['macd'];

    class PRODUCT_BY_CATEGORY{
        function __construct($masp, $macd, $tensp, $giasp, $hinhsp, $mota){
            $this->masp = $masp;
            $this->macd = $macd;
            $this->tensp = $tensp;
            $this->giasp = $giasp;
            $this->hinhsp = $hinhsp;
            $this->mota = $mota;
        }
    }

    if($machude==null)
        $query = "select * from sanpham";
    else
        $query = "select * from sanpham where MaChuDe=$machude";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new PRODUCT_BY_CATEGORY($row["maSanPham"], $row["maChuDe"], $row["tenSanPham"],
         $row["giaSanPham"],$row["hinhSanPham"], $row["mota"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>