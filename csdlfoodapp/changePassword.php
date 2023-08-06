<?php
if (isset($_POST['email'])) {

    require_once "connect.php";
    $email = $_POST['email'];
    $newPassword = $_POST['newPassword'];

    $sql = "UPDATE `khachhang` SET `password` = '$newPassword' WHERE `khachhang`.`email` = 'abc';";
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>