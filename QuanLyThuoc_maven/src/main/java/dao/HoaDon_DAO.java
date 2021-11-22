package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	public boolean update(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("update HoaDon set ngayLap=?,maNV=?,maChiTietHD = ? where maHoaDon =? ");
			
			stmt.setDate(1, hd.getNgayLap());
			stmt.setString(2,hd.getNv().getMaNV());
			stmt.setString(3, hd.getKh().getMaKH());
			stmt.setString(4, hd.getCtHD().getMaChiTietHD());
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
	
	public boolean createHD(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("insert into HoaDon values (?,?,?,?,?)  ");
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setDate(2, hd.getNgayLap());
			stmt.setString(3,hd.getNv().getMaNV());
			stmt.setString(4, hd.getKh().getMaKH());
			stmt.setString(5, hd.getCtHD().getMaChiTietHD());
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
	
	public boolean xoaHD(String maHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n=0;
		try {
			stmt = con.prepareStatement("delete HoaDon where maHoaDon =? ");
			stmt.setString(1, maHD);
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
	
	public ArrayList<HoaDon> getAllHoaDon(){
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from HoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				Date ngaylap =  rs.getDate(2);
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString(5));
				HoaDon hd = new HoaDon(maHD, ngaylap, nv, kh, ct);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHD;
	}
	
	public ArrayList<HoaDon> getHDTheoMaKH(String maKH){
		ArrayList<HoaDon> dsHD = new ArrayList<HoaDon>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon where  maKh = '"+maKH+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maHD = rs.getString(1);
				Date ngaylap =  rs.getDate(2);
				NhanVien nv = new NhanVien(rs.getString(3));
				KhachHang kh = new KhachHang(rs.getString(4));
				ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString(5));
				HoaDon hd = new HoaDon(maHD, ngaylap, nv, kh, ct);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsHD;
	}
	

	
	
	
	
	
}
