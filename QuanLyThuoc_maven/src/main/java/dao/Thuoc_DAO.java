package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Thuoc;

public class Thuoc_DAO {
	
	public boolean update(Thuoc thuoc)
	{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update Thuoc set tenThuoc=?, donGia=?, ngaySX = ?, hanSuDung = ?, SLTon= ?, maNCC =?, maLoai =?, idNuoc = ? where maThuoc =? ");
			stmt.setString(1, thuoc.getTenThuoc());
			stmt.setDouble(2, thuoc.getDonGia());
			stmt.setDate(3, thuoc.getNgaySX());
			stmt.setDate(4, thuoc.getHanSuDung());
			stmt.setInt(5, thuoc.getSLTon());
			stmt.setString(6, thuoc.getNcc().getMaNCC());
			stmt.setString(7, thuoc.getLoaiThuoc().getMaLoai());
			stmt.setString(8, thuoc.getNuocSX().getIdNuoc());
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}
	
	public boolean createThuoc(Thuoc thuoc)
	{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into Thuoc values(?,?,?,?,?,?,?,?,?)  ");
			stmt.setString(1, thuoc.getMaThuoc());
			stmt.setString(2, thuoc.getTenThuoc());
			stmt.setDouble(3, thuoc.getDonGia());
			stmt.setDate(4, thuoc.getNgaySX());
			stmt.setDate(5, thuoc.getHanSuDung());
			stmt.setInt(6, thuoc.getSLTon());
			stmt.setString(7, thuoc.getNcc().getMaNCC());
			stmt.setString(8, thuoc.getLoaiThuoc().getMaLoai());
			stmt.setString(9, thuoc.getNuocSX().getIdNuoc());
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}
	public boolean xoaThuoc(Thuoc thuoc)
	{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete Thuoc where maThuoc =? ");
			stmt.setString(1, thuoc.getMaThuoc());
			n = stmt.executeUpdate();
			} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
		return n>0;
	}
	public ArrayList<Thuoc> getAllThuoc(){
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Thuoc";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date HSD = rs.getDate(5);
				int SLTon = rs.getInt(6);
				NhaCungCap ncc  = new NhaCungCap(rs.getString(7));
				LoaiThuoc  Loai = new LoaiThuoc( rs.getString(8));
				NuocSX  nuoc = new NuocSX(rs.getString(9));
//				Thuoc thuoc  = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, HSD, SLTon, ncc, Loai, nuoc);
//				dsThuoc.add(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoLoai(String loai){
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Thuoc where maLoai = N'"+loai+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date HSD = rs.getDate(5);
				int SLTon = rs.getInt(6);
				NhaCungCap ncc  = new NhaCungCap(rs.getString(7));
				LoaiThuoc  Loai = new LoaiThuoc( rs.getString(8));
				NuocSX  nuoc = new NuocSX(rs.getString(9));
//				Thuoc thuoc  = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, HSD, SLTon, ncc, Loai, nuoc);
//				dsThuoc.add(thuoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsThuoc;
	}
	
	public Thuoc  getThuocTheoTen(String tenThuoc){
		Thuoc thuoc = null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql ="Select * from Thuoc where tenThuoc = N'"+tenThuoc+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				String maThuoc = rs.getString(1);
				String tenT= rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date HSD = rs.getDate(5);
				int SLTon = rs.getInt(6);
				NhaCungCap ncc  = new NhaCungCap(rs.getString(7));
				LoaiThuoc  Loai = new LoaiThuoc( rs.getString(8));
				NuocSX  nuoc = new NuocSX(rs.getString(9));
//				thuoc = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, HSD, SLTon, ncc, Loai, nuoc);
				
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return thuoc;
	}
	
	
	
	
	
	
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
//				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donGia, soluong,nhacungcap,loaithuoc, nuocsx);
//				dsThuoc.add(thuoc);
				
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
	
}
