<?php
if (isset($_POST['email'])) {

    require_once "connect.php";
    $email = $_POST['email'];
    $fullName = $_POST['fullName'];
    $address = $_POST['address'];
    $phone = $_POST['phone'];

    $sql = "UPDATE `khachhang` SET `fullName` = ' $fullName',
                    `address` = '$address', `phone` = '$phone' WHERE `khachhang`.`email` = '$email';";
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>