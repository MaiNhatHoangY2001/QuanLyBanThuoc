USE [QuanLyThuoc2]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDon] [char](10) NOT NULL,
	[maThuoc] [char](10) NOT NULL,
	[donGia] [money] NOT NULL,
	[soLuong] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC,
	[maThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [char](10) NOT NULL,
	[ngayLap] [date] NOT NULL,
	[maKH] [char](10) NOT NULL,
	[maNV] [char](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [char](10) NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[hoTen] [nvarchar](255) NOT NULL,
	[ngaySinh] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiThuoc]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiThuoc](
	[maLoai] [char](10) NOT NULL,
	[tenLoai] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[maNCC] [char](10) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[tenNCC] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [char](10) NOT NULL,
	[diaChi] [nvarchar](255) NOT NULL,
	[gioiTinh] [bit] NOT NULL,
	[hoTen] [nvarchar](255) NOT NULL,
	[luong] [money] NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[SDT] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NuocSX]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NuocSX](
	[idNuoc] [char](10) NOT NULL,
	[tenNuoc] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[idNuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Thuoc]    Script Date: 29/11/2021 20:30:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Thuoc](
	[maThuoc] [char](10) NOT NULL,
	[SLTon] [int] NOT NULL,
	[donGia] [money] NOT NULL,
	[hanSuDung] [date] NOT NULL,
	[ngaySX] [date] NOT NULL,
	[tenThuoc] [nvarchar](255) NOT NULL,
	[maLoai] [char](10) NOT NULL,
	[maNCC] [char](10) NOT NULL,
	[idNuoc] [char](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[maThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110002', N'TH21110004', 700000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110003', N'TH21110003', 152000.0000, 4)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110005', N'TH21110005', 938000.0000, 2)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110006', N'TH21110003', 53000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110007', N'TH21110002', 105000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110009', N'TH21110015', 90000.0000, 2)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110010', N'TH21110014', 200000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110011', N'TH21110001', 95000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110013', N'TH21110003', 53000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110014', N'TH21110014', 200000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110016', N'TH21110012', 41000.0000, 1)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon], [maThuoc], [donGia], [soLuong]) VALUES (N'HD21110017', N'TH21110011', 42000.0000, 2)
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110001', CAST(N'2021-11-29' AS Date), N'KH21110001', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110002', CAST(N'2021-11-29' AS Date), N'KH21110001', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110003', CAST(N'2021-11-29' AS Date), N'KH21110001', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110004', CAST(N'2021-11-29' AS Date), N'KH0001    ', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110005', CAST(N'2021-11-29' AS Date), N'KH0001    ', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110006', CAST(N'2021-11-29' AS Date), N'KH0001    ', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110007', CAST(N'2021-11-29' AS Date), N'KH0001    ', N'NV21110001')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110008', CAST(N'2021-11-29' AS Date), N'KH0002    ', N'NV21110002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110009', CAST(N'2021-11-29' AS Date), N'KH0002    ', N'NV21110002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110010', CAST(N'2021-11-29' AS Date), N'KH0002    ', N'NV21110002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110011', CAST(N'2021-11-29' AS Date), N'KH0002    ', N'NV21110002')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110012', CAST(N'2021-11-29' AS Date), N'KH0004    ', N'NV21110004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110013', CAST(N'2021-11-29' AS Date), N'KH0004    ', N'NV21110004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110014', CAST(N'2021-11-29' AS Date), N'KH0004    ', N'NV21110004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110015', CAST(N'2021-11-29' AS Date), N'KH0005    ', N'NV21110004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110016', CAST(N'2021-11-29' AS Date), N'KH0005    ', N'NV21110004')
INSERT [dbo].[HoaDon] ([maHoaDon], [ngayLap], [maKH], [maNV]) VALUES (N'HD21110017', CAST(N'2021-11-29' AS Date), N'KH0005    ', N'NV21110004')
GO
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH0001    ', N'0374598439', N' Hoàng Văn Thụ, phường 8, Phú Nhuận', 1, N'Phan Hữu Trọng', CAST(N'1992-08-03' AS Date))
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH0002    ', N'0325484883', N'125/16/11 đường Bùi Đình Túy, P24, Quận Bình Thạnh', 1, N'Nguyễn Văn Đức', CAST(N'1996-04-28' AS Date))
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH0003    ', N'0383681481', N'167 đường số 15, Phường Tân Quy, Quận 7', 1, N'Hà Đức Chinh', CAST(N'1997-11-23' AS Date))
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH0004    ', N'0334975466', N'181 Nguyễn Gia Trí, Phường 25, Bình Thạnh', 0, N'Nguyễn Thị Bảo Vy', CAST(N'1989-09-23' AS Date))
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH0005    ', N'0335309994', N'ĐT7A, An Điền, Bến Cát, Bình Dương', 0, N'Trận Thị Cát Tường', CAST(N'1986-12-04' AS Date))
INSERT [dbo].[KhachHang] ([maKH], [SDT], [diaChi], [gioiTinh], [hoTen], [ngaySinh]) VALUES (N'KH21110001', N'0986439506', N'tp Hồ Chí Minh', 1, N'Hoang', CAST(N'2001-03-26' AS Date))
GO
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT001     ', N'Thuốc kháng di ứng')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT002     ', N'Thuốc kháng viêm')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT003     ', N'Thuốc ngừa thai')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT004     ', N'Thuốc cảm lạnh, ')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT005     ', N'Thuốc da liễu')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT006     ', N'Thuốc giảm cân')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT007     ', N'Thuốc Mắt/Tai/Mũi')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT008     ', N'Thuốc tiêu hóa')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT009     ', N'Thuốc thần kinh')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT010     ', N'Giảm đau, hạ sốt')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT011     ', N'Dầu gió, dầu cù là ...')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT012     ', N'Thuốc dành cho phụ nữ')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT013     ', N'Thuốc dành cho nam')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT014     ', N'Thuốc cơ xương khớp')
INSERT [dbo].[LoaiThuoc] ([maLoai], [tenLoai]) VALUES (N'LT015     ', N'Vitamin & Khoáng chất')
GO
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC001    ', N'Xa lộ Hà Nội, Long Bình, Quận 9, Thành phố Hồ Chí Minh, Vietnam', N'Sanofi Aventis')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC002    ', N'89 Trần Thiện Chánh, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Vietnam', N'Merap Group')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC003    ', N'Budapest, Gyömrői út 19-21, 1103 Hungary', N'Gedeon Richter')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC004    ', N'Industriestraße 8, 85072 Eichstätt, Germany', N'Engelhard')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC005    ', N'67 Đ. Số 12, Phường 10, Gò Vấp, Thành phố Hồ Chí Minh, Vietnam', N'Bidiphar')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC006    ', N'44 Đồng Nai, Phường 15, Quận 10, Thành phố Hồ Chí Minh, Vietnam', N'Pymepharco')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC007    ', N'Lầu 18 Phòng 1808, Trade Tower, 37 Đ. Tôn Đức Thắng, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh, Vietnam', N'Rohto-Mentholatum')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC008    ', N'Room 1203, 12th Floor, Harbour View Tower 35 Nguyen Hue St. Ben Nghe Ward, District 1, HCMC, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh, Vietnam', N'Janssen    ')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC009    ', N'23 Chemin de Penchenery, 81100 Castres, Pháp', N'Tanganil')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC010    ', N'New Frontiers Science Park, Third Ave, Harlow CM19 5AW, Vương quốc Anh', N'Panadol')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC011    ', N'43/2 Hoà Bình, P. Tân Thới Hoà, Q. Tân Phú, TP. HCM, Việt Nam', N'Truong Son')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC012    ', N'367 Nguyễn Trãi, P. Nguyễn Cư Trinh, Quận 1, TP. Hồ Chí Minh, Việt Nam', N'Gynofar')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC013    ', N'Lô 14 Đường số 8, Tân Tạo A, Bình Tân, TP. Hồ Chí Minh, Việt Nam', N'Danapha')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC014    ', N'Lô 51, Đường số 2, KCN Tân Tạo, Q.Bình Tân, TP.HCM, Việt Nam', N'SPM')
INSERT [dbo].[NhaCungCap] ([maNCC], [diaChi], [tenNCC]) VALUES (N'NCC015    ', N'3 Rue Joseph Monier, 92506 Rueil-Malmaison, Pháp', N'BristolMyersSquibb')
GO
INSERT [dbo].[NhanVien] ([maNV], [diaChi], [gioiTinh], [hoTen], [luong], [ngaySinh], [SDT]) VALUES (N'NV21110001', N'802B Âu Cơ, Phường 14, Quận Tân Bình', 1, N'Nguyễn Văn Toàn', 15000000.0000, CAST(N'1993-03-09' AS Date), N'0378154986')
INSERT [dbo].[NhanVien] ([maNV], [diaChi], [gioiTinh], [hoTen], [luong], [ngaySinh], [SDT]) VALUES (N'NV21110002', N'167 đường số 15, Phường Tân Quy, Quận 7', 1, N'Nguyễn Tiến Linh', 15000000.0000, CAST(N'1990-08-22' AS Date), N'0389422142')
INSERT [dbo].[NhanVien] ([maNV], [diaChi], [gioiTinh], [hoTen], [luong], [ngaySinh], [SDT]) VALUES (N'NV21110003', N'56a Cầu Xéo, Tân quí, Tân Phú', 1, N'Nguyễn Công Phượng', 17000000.0000, CAST(N'1996-08-09' AS Date), N'0342844199')
INSERT [dbo].[NhanVien] ([maNV], [diaChi], [gioiTinh], [hoTen], [luong], [ngaySinh], [SDT]) VALUES (N'NV21110004', N'đường số 59 Phan Văn Trị, Phường 16, Gò Vấp ', 1, N'Nguyễn Thành Lương', 20000000.0000, CAST(N'1991-04-18' AS Date), N'0334172541')
INSERT [dbo].[NhanVien] ([maNV], [diaChi], [gioiTinh], [hoTen], [luong], [ngaySinh], [SDT]) VALUES (N'NV21110005', N'12 Nguyễn Văn Bảo, phường 4, Gò Vấp', 1, N'Nguyễn Thị Ngọc Hoa', 30000000.0000, CAST(N'1990-07-19' AS Date), N'0862303930')
GO
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N001      ', N'Mỹ')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N002      ', N'Ấn Độ')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N003      ', N'Nga')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N004      ', N'Thái Lan')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N005      ', N'Pháp')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N006      ', N'Trung Quốc')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N007      ', N'Canada')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N008      ', N'Việt Nam')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N009      ', N'Indonesia')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N010      ', N'Nhật Bản')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N011      ', N'Đức')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N013      ', N'Hungary')
INSERT [dbo].[NuocSX] ([idNuoc], [tenNuoc]) VALUES (N'N014      ', N'Anh')
GO
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110001', 19, 75000.0000, CAST(N'2022-05-15' AS Date), CAST(N'2020-09-09' AS Date), N'Telfast HD 180mg ', N'LT001     ', N'NCC001    ', N'N005      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110002', 26, 85000.0000, CAST(N'2022-06-16' AS Date), CAST(N'2020-06-02' AS Date), N'Medoral (Chai 250ml)', N'LT002     ', N'NCC002    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110003', 68, 33000.0000, CAST(N'2022-02-12' AS Date), CAST(N'2021-01-09' AS Date), N'Postinor 1 1.5mg  ', N'LT003     ', N'NCC003    ', N'N013      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110004', 117, 680000.0000, CAST(N'2020-01-20' AS Date), CAST(N'2020-12-09' AS Date), N'Prospan (100ml)', N'LT004     ', N'NCC004    ', N'N011      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110005', 38, 459000.0000, CAST(N'2022-09-12' AS Date), CAST(N'2020-11-16' AS Date), N'Bidiphar (250ml) ', N'LT005     ', N'NCC005    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110006', 19, 420000.0000, CAST(N'2021-08-20' AS Date), CAST(N'2020-12-07' AS Date), N'Semiflit 120 ', N'LT006     ', N'NCC006    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110007', 200, 46900.0000, CAST(N'2022-09-21' AS Date), CAST(N'2020-07-23' AS Date), N'V.Rohto Vitamin (13ml) ', N'LT007     ', N'NCC007    ', N'N010      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110008', 100, 18500.0000, CAST(N'2012-12-12' AS Date), CAST(N'2020-05-06' AS Date), N'Fugacar Mebendazole (500mg)', N'LT008     ', N'NCC008    ', N'N010      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110009', 100, 150000.0000, CAST(N'2022-01-12' AS Date), CAST(N'2020-09-19' AS Date), N'Tanganil (500mg)', N'LT009     ', N'NCC009    ', N'N005      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110010', 150, 214000.0000, CAST(N'2022-06-12' AS Date), CAST(N'2020-04-30' AS Date), N'Panadol Extra (180 viên/hộp)', N'LT010     ', N'NCC010    ', N'N014      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110011', 48, 11000.0000, CAST(N'2023-12-22' AS Date), CAST(N'2020-05-01' AS Date), N'Dầu gió Trường Sơn (6ml)', N'LT011     ', N'NCC011    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110012', 49, 21000.0000, CAST(N'2022-01-12' AS Date), CAST(N'2020-12-29' AS Date), N'Gynofar (500ml)', N'LT012     ', N'NCC012    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110013', 30, 140000.0000, CAST(N'2022-05-03' AS Date), CAST(N'2020-05-26' AS Date), N'Tadimax (Hộp 1 lọ 42 viên)', N'LT013     ', N'NCC013    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110014', 118, 180000.0000, CAST(N'2024-01-04' AS Date), CAST(N'2020-04-04' AS Date), N'Mongor (Tuýp 20 viên)', N'LT014     ', N'NCC014    ', N'N008      ')
INSERT [dbo].[Thuoc] ([maThuoc], [SLTon], [donGia], [hanSuDung], [ngaySX], [tenThuoc], [maLoai], [maNCC], [idNuoc]) VALUES (N'TH21110015', 198, 35000.0000, CAST(N'2025-10-20' AS Date), CAST(N'2020-08-09' AS Date), N'Upsa-C (1g)', N'LT015     ', N'NCC015    ', N'N005      ')
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FK48lko1mwj5apm7o0w68jp3ugw] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK48lko1mwj5apm7o0w68jp3ugw]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [FKw591wkpw6hcptf6iquud4938] FOREIGN KEY([maThuoc])
REFERENCES [dbo].[Thuoc] ([maThuoc])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FKw591wkpw6hcptf6iquud4938]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKg1ebuq3tydt58wb4gutehmh9w] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKg1ebuq3tydt58wb4gutehmh9w]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [FKoyaa1162ps1nyifmfv2pv2qr] FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FKoyaa1162ps1nyifmfv2pv2qr]
GO
ALTER TABLE [dbo].[Thuoc]  WITH CHECK ADD  CONSTRAINT [FK46p9mi1xp0fkexhlotb8btgtj] FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiThuoc] ([maLoai])
GO
ALTER TABLE [dbo].[Thuoc] CHECK CONSTRAINT [FK46p9mi1xp0fkexhlotb8btgtj]
GO
ALTER TABLE [dbo].[Thuoc]  WITH CHECK ADD  CONSTRAINT [FK9bqaoeieaj0s8r4lkfknw5c17] FOREIGN KEY([maNCC])
REFERENCES [dbo].[NhaCungCap] ([maNCC])
GO
ALTER TABLE [dbo].[Thuoc] CHECK CONSTRAINT [FK9bqaoeieaj0s8r4lkfknw5c17]
GO
ALTER TABLE [dbo].[Thuoc]  WITH CHECK ADD  CONSTRAINT [FKq937xyt709rc9jyxsqobjv8xe] FOREIGN KEY([idNuoc])
REFERENCES [dbo].[NuocSX] ([idNuoc])
GO
ALTER TABLE [dbo].[Thuoc] CHECK CONSTRAINT [FKq937xyt709rc9jyxsqobjv8xe]
GO
