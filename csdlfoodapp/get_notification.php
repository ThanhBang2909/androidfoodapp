<?php
    require 'connect.php';

    class NOTIFICATION{
        function __construct($id, $tittle, $body, $image){
            $this->id = $id;
            $this->tittle = $tittle;
            $this->body = $body;
            $this->image = $image;
        }
    }

    $query = "select * from notification";
    $data = mysqli_query($conn, $query);
    $arraylist = array();

    while($row=mysqli_fetch_assoc($data))
    {
        array_push($arraylist, new NOTIFICATION($row["id"],
        $row["tittle"], $row["body"], $row["image"]));
    }

    header("Content-type:application/json");
    echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
