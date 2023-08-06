-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 06, 2023 lúc 03:43 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE csdlfoodapp;
USE csdlfoodapp;
--
-- Cơ sở dữ liệu: `csdlfoodapp`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `add_address`
--

CREATE TABLE `add_address` (
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fullName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf32 COLLATE utf32_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `add_address`
--

INSERT INTO `add_address` (`email`, `fullName`, `address`, `phone`) VALUES
('abc', 'Thanh Bằng', 'Tân Phú', '0567657'),
('abc', 'Thanh Bằng', 'Thủ Đức', '0696986'),
('abc', 'Thanh Bằng', 'Tân phú', '0123456789'),
('abc', 'Thanh Bằng', 'Binh Tan', '012345'),
('abc', 'Bang', 'Ly Ban Bich', '3434545'),
('abc', '', '', ''),
('abc', 'AA', 'AAA', '076856'),
('abc', 'Bang', 'Tan Binh', '9657576'),
('abc', 'ThankAA', 'AAA', '42425'),
('abc', 'ssafsa', 'sadsa', '65476546'),
('thanhbang2909@gmail.com', 'Thanh Bang', 'Ben Tre', '068568747'),
('thanhbang2909@gmail.com', 'sdsadsa', 'sdadfs', '7546546'),
('thanhbang2909@gmail.com', 'Thanh', '123', 'ewewf'),
('bbb', 'Thanh Bang', 'Tan Phu', '3242444');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `id` int(11) NOT NULL,
  `maHD` int(255) NOT NULL,
  `maSanPham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tenSanPham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `giaSanPham` int(255) NOT NULL,
  `hinhSanPham` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `soLuong` int(255) NOT NULL,
  `thanhTien` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`id`, `maHD`, `maSanPham`, `tenSanPham`, `giaSanPham`, `hinhSanPham`, `soLuong`, `thanhTien`) VALUES
(1, 1, 'CF02', 'Café sữa đá', 20000, '', 2, 20000),
(2, 2, 'CF02', 'Café sữa đá', 20000, '', 2, 20000),
(3, 2, 'CF01', 'Café đen đá', 20000, '', 2, 20000),
(4, 2, 'DAN01', 'Hamburger', 30000, '', 3, 30000),
(5, 3, 'CF02', 'Café sữa đá', 20000, '', 3, 60000),
(6, 4, 'CF02', 'Café sữa đá', 20000, 'cfsuada.png', 3, 60000),
(7, 4, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 3, 60000),
(8, 4, 'DAN01', 'Hamburger', 30000, 'hamburger.jpeg', 1, 30000),
(9, 5, 'DU03', 'Nước có ga', 15000, 'nuoccoga.png', 3, 45000),
(10, 6, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 2, 40000),
(11, 7, 'DAN02', 'Gà rán', 20000, 'garan.jpeg', 2, 40000),
(12, 8, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(13, 8, 'DAN02', 'Gà rán', 20000, 'garan.jpeg', 1, 20000),
(14, 8, 'DAN04', 'Pizza', 40000, 'pizza.png', 1, 40000),
(15, 9, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 4, 80000),
(16, 9, 'CF02', 'Café sữa đá', 20000, 'cfsuada.png', 1, 20000),
(17, 10, 'CF03', 'Café nóng', 20000, 'cfnong.jpeg', 2, 40000),
(18, 11, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(19, 12, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(20, 13, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(21, 14, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(22, 15, 'CF02', 'Café sữa đá', 20000, 'cfsuada.png', 1, 20000),
(23, 16, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(24, 17, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(25, 17, 'DAN01', 'Hamburger', 30000, 'hamburger.jpeg', 1, 30000),
(26, 17, 'CF03', 'Café nóng', 20000, 'cfnong.jpeg', 1, 20000),
(27, 18, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000),
(28, 19, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 2, 40000),
(29, 20, 'CF01', 'Café đen đá', 20000, 'cfdenda.jpeg', 1, 20000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chudesanpham`
--

CREATE TABLE `chudesanpham` (
  `maChuDe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tenChuDe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hinhChuDe` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chudesanpham`
--

INSERT INTO `chudesanpham` (`maChuDe`, `tenChuDe`, `hinhChuDe`) VALUES
('CF', 'Café', 'cafe.jpeg'),
('DAN', 'Đồ ăn nhanh', 'pizza.png'),
('DU', 'Đồ uống', 'tra.jpeg'),
('TS', 'Trà sữa', 'ts.jpeg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `maHD` int(255) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `thanhTien` int(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`maHD`, `email`, `name`, `phone`, `address`, `thanhTien`, `status`) VALUES
(1, 'abc', 'Thanh Bằng', 'Thanh Bằng', 'Thanh Bằng', 20000, 0),
(2, 'abc', 'Thanh Bằng', '0696986', 'Thủ Đức', 110000, 0),
(3, 'abc', 'Thanh Bằng', '0123456789', 'Tân phú', 60000, 0),
(4, 'abc', 'Thanh Bằng', '0123456789', 'Tân phú', 150000, 0),
(5, 'abc', 'Thanh Bằng', '0696986', 'Thủ Đức', 45000, 0),
(6, 'abc', 'Thanh Bằng', '0123456789', 'Tân phú', 40000, 0),
(7, 'abc', 'Thanh Bằng', '0696986', 'Thủ Đức', 40000, 0),
(8, 'abc', 'ThankAA', '42425', 'AAA', 80000, 0),
(9, 'abc', 'ssafsa', '65476546', 'sadsa', 100000, 0),
(10, 'abc', 'Thanh Bằng', '0567657', 'Tân Phú', 40000, 0),
(11, 'abc', 'Thanh Bằng', '0123456789', 'Tân phú', 20000, 0),
(12, 'thanhbang2909@gmail.com', 'Thanh Bang', '068568747', 'Ben Tre', 20000, 0),
(13, 'thanhbang2909@gmail.com', 'Thanh Bang', '068568747', 'Ben Tre', 20000, 0),
(14, 'thanhbang2909@gmail.com', 'Thanh Bang', '068568747', 'Ben Tre', 20000, 0),
(15, 'thanhbang2909@gmail.com', 'Thanh Bang', '068568747', 'Ben Tre', 20000, 0),
(16, 'thanhbang2909@gmail.com', 'Thanh Bang', '068568747', 'Ben Tre', 20000, 0),
(17, 'abc', 'Thanh Bằng', '0696986', 'Thủ Đức', 70000, 0),
(18, 'thanhbang2909@gmail.com', 'Thanh', 'ewewf', '123', 20000, 0),
(19, 'abc', 'Thanh Bằng', '0696986', 'Thủ Đức', 40000, 0),
(20, 'bbb', 'Thanh Bang', '3242444', 'Tan Phu', 20000, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `fullName` text COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gioiTinh` tinyint(1) NOT NULL,
  `avartar` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`email`, `password`, `fullName`, `address`, `phone`, `gioiTinh`, `avartar`) VALUES
('abc', '123', ' Thanh', 'Tân Phu', '01234567', 1, 'men.png'),
('abcd', '1234', 'Bang', 'Tan Phu', '35465768', 0, ''),
('bbb', '1234', ' Thanh Bang', 'Luy Ban Bich', '38249824', 1, 'men.png'),
('daudau309@gmail.com', '123456', 'Thank Bang', 'Tan Phu', '04576574', 1, 'men.png'),
('daudau390@gmail.com', '123', 'Bang', 'TanPhu', '12465476', 1, 'men.png'),
('ltbang123@gmail.com', '123', 'Thank Bang', 'San Pus', '5236547', 1, 'men.png'),
('thanhbang2909@gmail.com', '123', 'Thanh Bang', 'Ben Tre', '07846453', 1, 'men.png'),
('xeticix549@rockdian.com', '123', 'bang', 'tan phu', '034945435', 1, 'men.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhangdanhgia`
--

CREATE TABLE `khachhangdanhgia` (
  `Email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MaSanPham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `NgayDanhGia` date NOT NULL,
  `SoSao` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `tittle` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `body` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `notification`
--

INSERT INTO `notification` (`id`, `tittle`, `body`, `image`) VALUES
(1, 'Thông báo khuyến mãi', 'Đây là thông báo khuyến mãi', ''),
(2, 'aaaaaaaaaaaa', 'aaaaaaaaaaaa', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `maSanPham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `maChuDe` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tenSanPham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `giaSanPham` int(11) NOT NULL,
  `hinhSanPham` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mota` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`maSanPham`, `maChuDe`, `tenSanPham`, `giaSanPham`, `hinhSanPham`, `mota`) VALUES
('CF01', 'CF', 'Café đen đá', 20000, 'cfdenda.jpeg', 'Cà phê là một loại thức uống được ủ từ hạt cà phê rang, lấy từ quả của cây cà phê. Các giống cây cà phê được bắt nguồn từ vùng nhiệt đới châu Phi và các vùng Madagascar, Comoros, Mauritius và Réunion trên các khu vực thuộc đường xích đạo.'),
('CF02', 'CF', 'Café sữa đá', 20000, 'cfsuada.png', 'Cà phê là một loại thức uống được ủ từ hạt cà phê rang, lấy từ quả của cây cà phê. Các giống cây cà phê được bắt nguồn từ vùng nhiệt đới châu Phi và các vùng Madagascar, Comoros, Mauritius và Réunion trên các khu vực thuộc đường xích đạo.'),
('CF03', 'CF', 'Café nóng', 20000, 'cfnong.jpeg', 'Cà phê là một loại thức uống được ủ từ hạt cà phê rang, lấy từ quả của cây cà phê. Các giống cây cà phê được bắt nguồn từ vùng nhiệt đới châu Phi và các vùng Madagascar, Comoros, Mauritius và Réunion trên các khu vực thuộc đường xích đạo.'),
('DAN01', 'DAN', 'Hamburger', 30000, 'hamburger.jpeg', 'Hamburger là một loại thức ăn bao gồm bánh mì kẹp thịt xay ở giữa. Miếng thịt có thể được nướng, chiên, hun khói hay nướng trên lửa.'),
('DAN02', 'DAN', 'Gà rán', 20000, 'garan.jpeg', 'Gà rán miền Nam Hoa Kỳ, hay còn gọi là gà rán, là một món ăn xuất xứ từ miền Nam Hoa Kỳ; nguyên liệu chính là những miếng thịt gà đã được lăn bột rồi chiên trên chảo, chiên ngập dầu, chiên áp suất hoặc chiên không dầu.'),
('DAN03', 'DAN', 'Khoai tây chiên', 20000, 'khoaitay.jpeg', 'Khoai tây chiên kiểu Pháp, hay French-fried potatoes là khoai tây chiên giòn cắt thành sợi hoặc hình que.'),
('DAN04', 'DAN', 'Pizza', 40000, 'pizza.png', 'Pizza, Tiếng La tinh thường đọc là Pi-da, là loại bánh dẹt, tròn được chế biến từ bột mì, nấm men... sau khi đã được ủ bột để nghỉ ít nhất 24 tiếng đồng hồ và nhào nặn thành loại bánh có hình dạng tròn và dẹt, và được cho vào lò nướng chín trước khi ăn.'),
('DAN05', 'DAN', 'Hotdog', 25000, 'hotdog.jpeg', 'Hot dog là một loại đồ ăn nhanh của Mỹ. Là bánh mì kẹp xúc xích, thường có thêm mù tạt, nước sốt cà chua, hành, mayonnaise, gia vị có thể có hoặc không có dưa cải Đức.'),
('DU01', 'DU', 'Trà trái cây', 25000, 'tratraicay.jpeg', 'Trà trái cây nhiệt đới được kết hợp giữa vị trà thơm nhẹ cùng nhiều loại trái cây giòn mát, ngọt thanh đem đến một món thức uống giải khát cực kỳ hiệu quả trong mùa hè oi bức.'),
('DU02', 'DU', 'Trà tắc', 20000, 'tratac.jpeg', 'Trà tắc thơm ngon, mát lạnh chua chua ngọt ngọt sẽ là một thức uống giúp gia đình bạn xua tan đi cái nắng nóng oi ả của ngày hè.'),
('DU03', 'DU', 'Nước có ga', 15000, 'nuoccoga.png', 'Nước có ga là nước có chứa khí carbon dioxide hòa tan, được bơm nhân tạo bằng áp lực hoặc xảy ra do quá trình địa chất tự nhiên. Cacbonic bão hòa làm cho các bong bóng nhỏ hình thành, tạo cho nước có các sủi bọt.'),
('DU04', 'DU', 'Trà ổi', 25000, 'traoi.jpeg', 'Trà ổi hỗ trợ giảm cân Orihiro được chiết xuất từ 100% lá ổi tự nhiên chứa polyphenol, carotenoid, flavonoid, tannin, acid psiditanic có khả năng ngăn chặn sự chuyển hóa tinh bột thành đường. Nhờ thế, khi uống trà ổi thường xuyên sẽ hỗ trợ rất tốt cho việ'),
('DU05', 'DU', 'Hồng trà', 20000, 'hongtra.jpeg', 'Hồng trà hay Trà Đen là một loại trà. Thông thường, nó có vị mạnh mẽ hơn so với các loại khác của trà như trà xanh. Nó cũng có nhiều caffeine hơn. Tên gọi hồng trà bắt nguồn từ màu sắc của nước trà, được dùng phổ biến tại các nước Đông Á và một số nơi khá'),
('TS01', 'TS', 'Trà sữa TS', 25000, 'truyenthong.jpeg', 'Trà sữa Đài Loan được nổi tiếng trên toàn thế giới với hương vị đậm đà mà không thể lẫn vào đâu được. Chắc chắn khi đặt chân tới Đài Loan, bạn không thể quên thưởng thức một ly trà sữa đâu nhé!'),
('TS02', 'TS', 'Trà sữa thái xanh', 25000, 'thaixanh.jpeg', 'Trà sữa là một trong những thức uống rất được mọi người ưa chuộng bởi sự hòa quyện giữa vị đắng của trà và vị béo ngọt của sữa. Chính bởi sự yêu thích ấy mà hãy theo chân Điện máy XANH vào bếp để thực hiện ngay 2 cách làm trà thái xanh và trà thái đỏ thơm'),
('TS03', 'TS', 'Trà sữa trân châu', 35000, 'duongden.jpeg', 'Trà sữa trân châu đường đen được yêu thích nhờ sự kết hợp hết sức hoàn hảo giữa vị trà sữa thơm béo và trân châu đường đen mềm, ngọt. Vậy để làm một ly trà sữa trân châu đường đen tại nhà liệu có dễ dàng? Hôm nay, cùng vào bếp học ngay cách làm món trà sữ'),
('TS04', 'TS', 'Trà sữa socolate', 20000, 'socolate.jpeg', 'Trà bá tước hay còn có tên gọi là Earl Grey, đây là một loại trà được kết hợp giữa hương vị trà đen và tinh dầu của vỏ cam Bergamot. Tuy nhiên, có người lại cho rằng trà bá tước chính là hỗn hợp của vị cam đắng từ vùng Địa Trung Hải với vị chanh từ vùng Đ'),
('TS05', 'TS', 'Trà sữa Machiato', 30000, 'machiato.png', 'Trà sữa Machiato Không độ có đường chai 268ml với nguồn nguyên liệu tự nhiên, có vị ngon được kết hợp bởi tinh chất của lá trà xanh Thái Nguyên hòa quyện với vị mát lành của sữa tự nhiên và hương thơm ngậy của kem sữa Macchiato.');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `add_address`
--
ALTER TABLE `add_address`
  ADD KEY `email` (`email`);

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`id`,`maHD`),
  ADD KEY `maHD` (`maHD`),
  ADD KEY `maSanPham` (`maSanPham`);

--
-- Chỉ mục cho bảng `chudesanpham`
--
ALTER TABLE `chudesanpham`
  ADD PRIMARY KEY (`maChuDe`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`maHD`),
  ADD KEY `email` (`email`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`email`) USING BTREE;

--
-- Chỉ mục cho bảng `khachhangdanhgia`
--
ALTER TABLE `khachhangdanhgia`
  ADD PRIMARY KEY (`Email`,`MaSanPham`),
  ADD KEY `pk_masanpham` (`MaSanPham`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`maSanPham`,`maChuDe`),
  ADD KEY `pk_macd` (`maChuDe`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `maHD` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `add_address`
--
ALTER TABLE `add_address`
  ADD CONSTRAINT `add_address_ibfk_1` FOREIGN KEY (`email`) REFERENCES `khachhang` (`email`);

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`maHD`) REFERENCES `hoadon` (`maHD`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`email`) REFERENCES `khachhang` (`email`);

--
-- Các ràng buộc cho bảng `khachhangdanhgia`
--
ALTER TABLE `khachhangdanhgia`
  ADD CONSTRAINT `pk_eamil` FOREIGN KEY (`Email`) REFERENCES `khachhang` (`email`),
  ADD CONSTRAINT `pk_masanpham` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `pk_macd` FOREIGN KEY (`maChuDe`) REFERENCES `chudesanpham` (`MaChuDe`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
