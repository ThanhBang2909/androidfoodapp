<?php
    require 'connect.php';

    $email = $_GET['email'];

    class USERS{
        function __construct($email, $password, $fullName, $address,
         $phone, $gioiTinh, $avartar){
            $this->email = $email;
            $this->password = $password;
            $this->fullName = $fullName;
            $this->address = $address;
            $this->phone = $phone;
            $this->gioiTinh = $gioiTinh;
            $this->avartar = $avartar;
        }
    }

    if($email==null)
        $query = "select * from khachhang";
    else
        $query = "select * from khachhang where email=$email";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new USERS($row["email"], $row["password"], $row["fullName"],
         $row["address"],$row["phone"], $row["gioiTinh"], $row["avartar"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>