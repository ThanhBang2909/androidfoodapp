<?php
if (isset($_POST['email'])) {

    require_once "connect.php";
    $email = $_POST['email'];
    $fullName = $_POST['fullName'];
    $address = $_POST['address'];
    $phone = $_POST['phone'];

    $sql = "INSERT INTO `add_address` (`email`, `fullName`, `address`, `phone`) 
                    VALUES ('$email', '$fullName', '$address', '$phone');";

    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>