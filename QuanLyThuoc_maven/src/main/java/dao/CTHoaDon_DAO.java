package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.Thuoc;

public class CTHoaDon_DAO {
	public boolean update(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update ChiTietHoaDon set soLuong=?,maThuoc=?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setString(2, cthd.getThuoc().getMaThuoc());
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
	
	public boolean createCTHD(ChiTietHoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?)");
			stmt.setString(1, cthd.getMaChiTietHD());
			stmt.setInt(2, cthd.getSoLuong());
			stmt.setString(3, cthd.getThuoc().getMaThuoc());
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
	
	public boolean XoaCTHD(String maCTHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete ChiTietHoaDon where maChiTietHD = ?");
			stmt.setString(1, maCTHD);
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

	
	public ArrayList<ChiTietHoaDon> getAllCTHD(){
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maCTHD = rs.getString(1);
				int soluong = rs.getInt(2);
				Thuoc t = new Thuoc(rs.getString(3));
				ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD, soluong, t);
				dsCTHD.add(cthd);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsCTHD;
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
	
	public ChiTietHoaDon getCTHDTheoMa(String macthd){
		ChiTietHoaDon cthd = null;
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHoaDon where  maChiTietHD = '"+macthd+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				
				String maCTHD = rs.getString(1);
				int soluong = rs.getInt(2);
				Thuoc t = new Thuoc(rs.getString(3));
				cthd = new ChiTietHoaDon(maCTHD, soluong, t);	
			
			}
		
			}catch (SQLException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
				return cthd;
					
	}
	public double getTongDoanhTheoSDT(String sdt){
		double doanhthu=0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

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
