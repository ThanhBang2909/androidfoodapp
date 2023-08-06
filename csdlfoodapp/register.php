<?php
if (isset($_POST['email']) && isset($_POST['password']) && isset($_POST['fullName']) && isset($_POST['address']) && isset($_POST['phone'])) {
    require "connect.php";
    $email = $_POST['email'];
    $password = $_POST['password'];
    $fullName = $_POST['fullName'];
    $address = $_POST['address'];
    $phone = $_POST['phone'];
    $sql = "INSERT INTO `khachhang` (`email`, `password`, `fullName`, `address`, `phone`, `gioiTinh`, `avartar`) 
                    VALUES ('$email', '$password', '$fullName', '$address', '$phone', '1', 'men.png');";
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>