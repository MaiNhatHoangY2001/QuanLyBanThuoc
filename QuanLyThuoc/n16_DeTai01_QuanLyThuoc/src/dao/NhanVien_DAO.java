package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVien_DAO {
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update NhanVien set luong=?, hoTen=?, ngaySinh=?, gioiTinh=?,diaChi=?,  SDT=? where maNV=? ");
			stmt.setDouble(1, nv.getLuong());
			stmt.setString(2, nv.getHoTen());
			stmt.setDate(3, nv.getNgaySinh());
			stmt.setBoolean(4, nv.isGioiTinh());
			stmt.setString(5, nv.getDiaChi());
			stmt.setString(6, nv.getSoDienThoai());
			stmt.setString(7, nv.getMaNV());
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

	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into NhanVien values (?, ?,?,?,?,?,?)");
			stmt.setDouble(2, nv.getLuong());
			stmt.setString(3, nv.getHoTen());
			stmt.setDate(4, nv.getNgaySinh());
			stmt.setBoolean(5, nv.isGioiTinh());
			stmt.setString(6, nv.getDiaChi());
			stmt.setString(7, nv.getSoDienThoai());
			stmt.setString(1, nv.getMaNV());

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

	public boolean xoa(String maNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete NhanVien where maNV = ?");
			stmt.setString(1, maNV);
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

	public ArrayList<NhanVien> getalltbNhanVien() {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT * FROM NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				Double luong = rs.getDouble(2);
				String hoTen = rs.getString(3);
				Date ngaySinh = rs.getDate(4);
				Boolean gioiTinh = rs.getBoolean(5);
				String diaChi = rs.getString(6);
				String soDienThoai = rs.getString(7);
				NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, (java.sql.Date) ngaySinh, gioiTinh, diaChi, luong);
				dsnv.add(nv);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return dsnv;
	}

	public ArrayList<NhanVien> getNhanVienTheoMaNV(String id) {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien where maNV = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				Double luong = rs.getDouble(2);
				String hoTen = rs.getString(3);
				Date ngaySinh = rs.getDate(4);
				Boolean gioiTinh = rs.getBoolean(5);
				String diaChi = rs.getString(6);
				String soDienThoai = rs.getString(7);
				NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, (java.sql.Date) ngaySinh, gioiTinh, diaChi, luong);
				dsnv.add(nv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
	public NhanVien get1NhanVienTheoMaNV(String id) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien where maNV = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				Double luong = rs.getDouble(2);
				String hoTen = rs.getString(3);
				Date ngaySinh = rs.getDate(4);
				Boolean gioiTinh = rs.getBoolean(5);
				String diaChi = rs.getString(6);
				String soDienThoai = rs.getString(7);
				nv = new NhanVien(maNV, hoTen, soDienThoai, (java.sql.Date) ngaySinh, gioiTinh, diaChi, luong);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nv;
	}

	public ArrayList<NhanVien> getNhanVienTheoTenNV(String id) {
		ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from NhanVien where hoTen = N'" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				Double luong = rs.getDouble(2);
				String hoTen = rs.getString(3);
				Date ngaySinh = rs.getDate(4);
				Boolean gioiTinh = rs.getBoolean(5);
				String diaChi = rs.getString(6);
				String soDienThoai = rs.getString(7);
				NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, (java.sql.Date) ngaySinh, gioiTinh, diaChi, luong);
				dsnv.add(nv);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsnv;
	}
}
