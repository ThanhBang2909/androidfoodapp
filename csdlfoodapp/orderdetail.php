<?php
	require "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json,true);
	foreach ($data as $value) {
		$maHD = $value['maHD'];
		$maSanPham = $value['maSanPham'];
		$tenSanPham = $value['tenSanPham'];
		$giaSanPham = $value['giaSanPham'];
		$hinhSanPham = $value['hinhSanPham'];
		$soLuong = $value['soLuong']; 
        $thanhTien = $value['thanhTien'];

		$query = "INSERT INTO chitiethoadon (id, maHD, maSanPham, tenSanPham, giaSanPham, hinhSanPham ,soLuong, thanhTien)
					VALUES (null, '$maHD', '$maSanPham', '$tenSanPham', '$giaSanPham','$hinhSanPham', '$soLuong', '$thanhTien')";
		$Dta = mysqli_query($conn,$query);
	}
	if ($Dta) {
		echo "1";
	}else {
		echo "0";
	}
?>
