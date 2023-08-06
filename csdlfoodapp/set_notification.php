<?php
if (isset($_POST['tittle']) && isset($_POST['body'])) {
    require "connect.php";

    $id = $_POST['id'];
    $tittle = $_POST['tittle'];
    $body = $_POST['body'];
    $image = $_POST['image'];
  
    $sql = "INSERT INTO `notification` (`id`, `tittle`, `body`, `image`) 
            VALUES (NULL, '$tittle', '$body', '');";
            
    if (!$conn->query($sql)) {
        echo "Failure";
    }else{
        echo "Success";
    }
}
?>