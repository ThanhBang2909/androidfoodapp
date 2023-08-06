<?php
	require "connect.php";

	$customer_name = $_POST['customername'];
	$phonenumber = $_POST['phonenumber'];
	$customer_address = $_POST['customeraddress'];
	$customer_totalprice = $_POST['customertotalprice'];
	$customer_status = $_POST['customertolstatus'];
	$email = $_POST['email'];
	if(strlen($customer_name) > 0 && strlen($email) > 0 && strlen($phonenumber) && strlen($customer_address) > 0)
	{
		$query = "INSERT INTO hoadon (maHD, email, name, phone, address, thanhTien, status) 
				  VALUES (NULL, '$email', '$customer_name', '$phonenumber', '$customer_address', 
                   '$customer_totalprice', '$customer_status')";
		
		$stmt = mysqli_prepare($conn, $query);
        
        if(mysqli_stmt_execute($stmt)){
			$billid = mysqli_insert_id($conn);
			echo $billid;
		}else {
			echo 'Failed to insert data into the database.';
		}
		mysqli_stmt_close($stmt);
	}
	else {
		echo "Bạn hãy kiểm tra lại các dữ liệu.";
	}
?>
