package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDB.ConnectDB;

public class PhatSinhMa {
	


	public String maKH() {
		String makh="";
		try {
			ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('KH', RIGHT(CONCAT('0000',ISNULL(right(max(maKh),4),0) + 1),4)) from dbo.KhachHang where maKh like 'KH%' ";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next())
		{
			makh = rs.getString(1);
		}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return makh;
	}
	
	public String maCTHD() {
		String macthd="";
		try {
			ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('CTHD', RIGHT(CONCAT('0000',ISNULL(right(max(maChiTietHD),4),0) + 1),4)) from dbo.ChiTietHoaDon where maChiTietHD like 'CTHD%' ";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next())
		{
			macthd = rs.getString(1);
		}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return macthd;
	}
	
	public String maHD() {
		String mahd="";
		try {
			ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "select CONCAT('HD', RIGHT(CONCAT('0000',ISNULL(right(max(maHoaDon),4),0) + 1),4)) from dbo.HoaDon where maKh like 'HD%' ";
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next())
		{
			mahd = rs.getString(1);
		}
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return mahd;
	}
	

}
