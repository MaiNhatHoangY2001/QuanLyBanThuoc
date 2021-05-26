package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Thuoc;

public class TThuocT_Dao {
	public boolean update(Thuoc t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update Thuoc set tenThuoc=?, donGia=?, ngaySX=?, hanSuDung=?,  SLTon=?, maNCC=?, maLoai=?, idNuoc=? where maThuoc=? ");
			stmt.setString(1, t.getTenThuoc());
			stmt.setDouble(2, t.getDonGia());
			stmt.setDate(3, t.getNgaySX());
			stmt.setDate(4, t.getHanSuDung());
			stmt.setInt(5, t.getSLTon());
			stmt.setString(6, t.getNcc().getMaNCC());
			stmt.setString(7, t.getLoaiThuoc().getMaLoai());
			stmt.setString(8, t.getNuocSX().getIdNuoc());
			stmt.setString(9, t.getMaThuoc());
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
	
	public boolean create(Thuoc t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into Thuoc values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, t.getMaThuoc());
			stmt.setString(2, t.getTenThuoc());
			stmt.setDouble(3, t.getDonGia());
			stmt.setDate(4, t.getNgaySX());
			stmt.setDate(5, t.getHanSuDung());
			stmt.setInt(6, t.getSLTon());
			stmt.setString(7, t.getNcc().getMaNCC());
			stmt.setString(8, t.getLoaiThuoc().getMaLoai());
			stmt.setString(9, t.getNuocSX().getIdNuoc());
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
	
	public boolean xoa(String maThuoc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete Thuoc where maThuoc = ?");
			stmt.setString(1, maThuoc);
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
	
	public ArrayList<Thuoc> getalltbThuoc() {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			Statement statement = con.createStatement();
			String sql = "SELECT * FROM Thuoc";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoMaThuoc(String id) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Thuoc where maThuoc = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoTenThuoc(String id) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Thuoc where tenThuoc = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoMaNCC(String id) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Thuoc where maNCC = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoMaLoai(String id) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Thuoc where maLoai = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuoc;
	}
	
	public ArrayList<Thuoc> getThuocTheoIdNuoc(String id) {
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from Thuoc where idNuoc = '" + id + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc = rs.getString(2);
				double donGia = rs.getDouble(3);
				Date ngaySX = rs.getDate(4);
				Date hangSuDung = rs.getDate(5);
				int SLTon = rs.getInt(6);
				String maNCC = rs.getString(7);
				NhaCungCap ncc = new NhaCungCap(maNCC);
				String maLoai = rs.getString(8);
				LoaiThuoc loai = new LoaiThuoc(maLoai);
				String idNuoc = rs.getString(9);
				NuocSX nuoc  = new NuocSX(idNuoc);
				Thuoc t = new Thuoc(maThuoc, tenThuoc, donGia, ngaySX, hangSuDung, SLTon, ncc, loai, nuoc);
				dsThuoc.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsThuoc;
	}
}
