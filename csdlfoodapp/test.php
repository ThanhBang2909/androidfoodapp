<?php
// Kết nối đến cơ sở dữ liệu
require 'connect.php';

// Xác định trang hiện tại và số lượng sản phẩm trên mỗi trang
$page = isset($_GET['page']) ? $_GET['page'] : 1;
$records_per_page = 6;
$offset = ($page - 1) * $records_per_page;

// Truy vấn để lấy sản phẩm với thông tin tên, giá và mô tả
$query = "SELECT maSanPham, maChuDe, tenSanPham,giaSanPham,hinhSanPham,mota 
        FROM sanpham LIMIT $offset, $records_per_page";
$result = mysqli_query($conn, $query);

// Tạo một mảng để lưu trữ danh sách sản phẩm
$products = array();

// Lặp qua các bản ghi và thêm vào mảng sản phẩm
while ($row = mysqli_fetch_assoc($result)) {
    $product = array(
        'maSanPham' => $row['maSanPham'],
        'maChuDe' => $row['maChuDe'],
        'tenSanPham' => $row['tenSanPham'],
        'giaSanPham' => $row['giaSanPham'],
        'hinhSanPham' => $row['hinhSanPham'],
        'mota' => $row['mota']
    );
    $products[] = $product;
}

// Tính toán số lượng sản phẩm trong cơ sở dữ liệu
$query = "SELECT COUNT(*) as total FROM sanpham";
$result = mysqli_query($conn, $query);
$row = mysqli_fetch_assoc($result);
$total_records = $row['total'];
$total_pages = ceil($total_records / $records_per_page);

// Tạo một mảng kết quả để chứa thông tin phân trang và danh sách sản phẩm
$response = array(
    'page' => $page,
    'total_pages' => $total_pages,
    'products' => $products
);

// Chuyển đổi mảng kết quả thành định dạng JSON và gửi về client
header("Content-type:application/json");
echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

// Đóng kết nối đến cơ sở dữ liệu
mysqli_close($conn);
?>
