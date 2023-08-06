<?php
    require 'connect.php';

    $email = $_GET['email'];

    class address{
        function __construct($email, $fullName, $address, $phone){
            $this->email = $email;
            $this->fullName = $fullName;
            $this->address = $address;
            $this->phone = $phone;
        }
    }

    if($email==null)
        $query = "select * from add_address";
    else
        $query = "select * from add_address where email=$email";

    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new address($row["email"],$row["fullName"],
                                $row["address"],$row["phone"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>