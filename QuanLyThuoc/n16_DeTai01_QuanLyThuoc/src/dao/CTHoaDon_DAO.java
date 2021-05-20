package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;

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
	

}
