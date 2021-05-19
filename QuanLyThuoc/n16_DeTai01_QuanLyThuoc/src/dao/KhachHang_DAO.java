package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class KhachHang_DAO {
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update KhachHang set hoTen=?,ngaySinh=?,gioiTinh = ?, diaChi = ?, SDT = ? where maKh =? ");
			stmt.setString(1, kh.getHoTen());
			stmt.setDate(2, kh.getNgaySinh());
			stmt.setBoolean(3, kh.isGioiTinh());
			stmt.setString(4, kh.getDiaChi());
			stmt.setString(5, kh.getSDT());
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
	
	public boolean createKH(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into KhachHang values (?,?,?,?,?,?) ");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTen());
			stmt.setDate(3, kh.getNgaySinh());
			stmt.setBoolean(4, kh.isGioiTinh());
			stmt.setString(5, kh.getDiaChi());
			stmt.setString(6, kh.getSDT());
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
	
	public boolean xoaKH(String maKH) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete KhachHang where maKh =? ");
			stmt.setString(1, maKH);
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

	public ArrayList<KhachHang> getAllKhachHang(){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KhachHang";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maKh = rs.getString(1);
				String hoTen =  rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				boolean gioiTinh = rs.getBoolean(4);
				String diaCHi = rs.getString(5);
				String sdt = rs.getString(6);
				KhachHang kh = new KhachHang(maKh, hoTen, ngaySinh, gioiTinh, diaCHi, sdt);
				dsKH.add(kh);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsKH;
	}
	
	public ArrayList<KhachHang> getKhachHangTheoSDT(String SDT){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang where SDT = '"+SDT+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maKh = rs.getString(1);
				String hoTen =  rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				boolean gioiTinh = rs.getBoolean(4);
				String diaCHi = rs.getString(5);
				String sdt = rs.getString(6);
				KhachHang kh = new KhachHang(maKh, hoTen, ngaySinh, gioiTinh, diaCHi, sdt);
				dsKH.add(kh);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsKH;
	}
	
	public ArrayList<KhachHang> getKhachHangTheoTen(String tenKH){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang where  hoTen = '"+tenKH+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maKh = rs.getString(1);
				String hoTen =  rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				boolean gioiTinh = rs.getBoolean(4);
				String diaCHi = rs.getString(5);
				String sdt = rs.getString(6);
				KhachHang kh = new KhachHang(maKh, hoTen, ngaySinh, gioiTinh, diaCHi, sdt);
				dsKH.add(kh);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsKH;
	}
	public ArrayList<KhachHang> getKhachHangTheoMa(String maKH){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from KhachHang where  maKh = '"+maKH+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maKh = rs.getString(1);
				String hoTen =  rs.getString(2);
				Date ngaySinh = rs.getDate(3);
				boolean gioiTinh = rs.getBoolean(4);
				String diaCHi = rs.getString(5);
				String sdt = rs.getString(6);
				KhachHang kh = new KhachHang(maKh, hoTen, ngaySinh, gioiTinh, diaCHi, sdt);
				dsKH.add(kh);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsKH;
	}
	
	
	
	
	
	
	
}
