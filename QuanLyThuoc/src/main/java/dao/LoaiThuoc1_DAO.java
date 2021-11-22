package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiThuoc;
import entity.NhaCungCap;

public class LoaiThuoc1_DAO {
	public boolean update(LoaiThuoc l) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update LoaiThuoc set tenLoai=? where maLoai=? ");
			stmt.setString(1, l.getTenLoai());
			stmt.setString(2, l.getMaLoai());
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
	
	public boolean create(LoaiThuoc l) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into LoaiThuoc values (?, ?)");
			stmt.setString(1, l.getMaLoai());
			stmt.setString(2, l.getTenLoai());
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

	public boolean xoa(String maLoai) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete LoaiThuoc where maLoai = ?");
			stmt.setString(1, maLoai);
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
	
	public ArrayList<LoaiThuoc> getalltbLoaiThuoc() {
		ArrayList<LoaiThuoc> dsLT = new ArrayList<LoaiThuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM LoaiThuoc";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maLoai = rs.getString(1);
				String tenLoai = rs.getString(2);
				LoaiThuoc l = new LoaiThuoc(maLoai, tenLoai);
				dsLT.add(l);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return dsLT;
	}
	
	public LoaiThuoc getLTTheoMaLT(String id) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoaiThuoc where maLoai = N'" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			String maLoai = rs.getString(1);
			String tenLoai = rs.getString(2);
			LoaiThuoc l = new LoaiThuoc(maLoai, tenLoai);
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
