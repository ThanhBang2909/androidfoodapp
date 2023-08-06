<?php
if (isset($_POST['email']) && isset($_POST['password'])) {
    require_once "connect.php";
    $email = $_POST['email'];
    $password = $_POST['password'];
    $sql = "select * from khachhang where email='$email' and password='$password' ";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        echo "Success";
    }
    else{
        echo "Failure";
    }
}
?>