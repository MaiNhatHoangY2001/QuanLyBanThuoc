package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaCungCap;
import entity.NuocSX;

public class NuocSX_DAO {
	public boolean update(NuocSX nuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update NuocSX set tenNuoc=? where idNuoc=? ");
			stmt.setString(1, nuoc.getTenNuoc());
			stmt.setString(2, nuoc.getIdNuoc());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Lỗi sql");
			}
		}
		return n > 0;
	}
	
	public boolean create(NuocSX nuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NuocSX values (?, ?)");
			stmt.setString(1, nuoc.getIdNuoc());
			stmt.setString(2, nuoc.getTenNuoc());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean xoa(String idNuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete NuocSX where idNuoc = ?");
			stmt.setString(1, idNuoc);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public ArrayList<NuocSX> getalltbNuoc() {
		ArrayList<NuocSX> dsNuoc = new ArrayList<NuocSX>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NuocSX";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String idNuoc = rs.getString(1);
				String tenNuoc = rs.getString(2);
//				NuocSX nuoc = new NuocSX(idNuoc, tenNuoc);
//				dsNuoc.add(nuoc);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return dsNuoc;
	}
	
	public NuocSX getNuocTheoMaNuoc(String id) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NuocSX where idNuoc = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			String idNuoc = rs.getString(1);
			String tenNuoc = rs.getString(2);
//			NuocSX nuoc = new NuocSX(idNuoc, tenNuoc);
			return null; 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
