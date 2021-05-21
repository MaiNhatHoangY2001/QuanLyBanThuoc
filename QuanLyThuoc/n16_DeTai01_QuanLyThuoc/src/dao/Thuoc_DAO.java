package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Thuoc;

public class Thuoc_DAO {
	public ArrayList<Thuoc> getThuocKhachHangDaMua(String maKH){
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Thuoc.maThuoc, Thuoc.tenThuoc, Thuoc.donGia,NhaCungCap.tenNCC, LoaiThuoc.tenLoai, NuocSX.tenNuoc, ChiTietHoaDon.soLuong\r\n" + 
					"FROM     ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n" + 
					"LoaiThuoc ON Thuoc.maLoai = LoaiThuoc.maLoai INNER JOIN\r\n" + 
					"NhaCungCap ON Thuoc.maNCC = NhaCungCap.maNCC INNER JOIN\r\n" + 
					"NuocSX ON Thuoc.idNuoc = NuocSX.idNuoc\r\n" + 
					"where HoaDon.maKH ='"+maKH+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc =  rs.getString(2);
				double donGia=Double.parseDouble(rs.getString(3));
				String ncc=rs.getString(4);
				String tenLoai = rs.getString(5);
				String tenNuoc = rs.getString(6);
				int soluong=Integer.parseInt(rs.getString(7));
				NhaCungCap nhacungcap=new NhaCungCap(ncc);
				LoaiThuoc loaithuoc =new LoaiThuoc(tenLoai);
				NuocSX nuocsx=new NuocSX(tenNuoc);
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donGia, soluong,nhacungcap,loaithuoc, nuocsx);
				dsThuoc.add(thuoc);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsThuoc;
	}
	public int getTongSoLuongThuoc(){
		int soluong=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Tong=sum(ChiTietHoaDon.soLuong)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD ";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				soluong = Integer.parseInt(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return soluong;
	}
	public int getTongSoLuongThuocTheoNgay(int ngay,int thang,int nam){
		int soluong=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Tong=sum(ChiTietHoaDon.soLuong)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD \r\n" + 
					"where day([ngayLap])='" + ngay + "' and MONTH([ngayLap])='" + thang + "' and YEAR([ngayLap])='" + nam + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				soluong = Integer.parseInt(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return soluong;
	}
	
	public int getTongSoLuongThuocTheoTen(String ten){
		int soluong=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Tong=sum(ChiTietHoaDon.soLuong)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where hoTen=N'"+ten+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				soluong = Integer.parseInt(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return soluong;
	}
	public int getTongSoLuongThuocTheoMa(String ma){
		int soluong=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Tong=sum(ChiTietHoaDon.soLuong)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where KhachHang.maKh='"+ma+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				soluong = Integer.parseInt(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return soluong;
	}
	
	public int getTongSoLuongThuocTheoSDT(String sdt){
		int soluong=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Tong=sum(ChiTietHoaDon.soLuong)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where KhachHang.SDT='"+sdt+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				soluong = Integer.parseInt(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return soluong;
	}
	public double getTongDoanhThuThuoc(){
		double doanhthu=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + 
					"FROM Thuoc INNER JOIN\r\n" + 
					"ChiTietHoaDon ON Thuoc.maThuoc = ChiTietHoaDon.maThuoc";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}
	public double getTongDoanhThuThuocTheoNgay(int ngay,int thang,int nam){
		double doanhthu=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + 
					"FROM ChiTietHoaDon INNER JOIN\r\n" + 
					"HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc\r\n" + 
					"where day([ngayLap])='" + ngay + "' and MONTH([ngayLap])='" + thang + "' and YEAR([ngayLap])='" + nam + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}
	public double getTongDoanhTheoTen(String ten){
		double doanhthu=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + 
					"FROM HoaDon INNER JOIN\r\n" + 
					"ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where hoTen=N'"+ten+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}
	public double getTongDoanhTheoMa(String ma){
		double doanhthu=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + 
					"FROM HoaDon INNER JOIN\r\n" + 
					"ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where KhachHang.maKh=N'"+ma+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}
	public double getTongDoanhTheoSDT(String sdt){
		double doanhthu=0;
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + 
					"FROM HoaDon INNER JOIN\r\n" + 
					"ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n" + 
					"KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + 
					"where KhachHang.SDT=N'"+sdt+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}
				 
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}
}
