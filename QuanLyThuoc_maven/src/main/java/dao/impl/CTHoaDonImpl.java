package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import connectDB.ConnectDB;
import dao.CTHoaDon_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.Thuoc;
import util.HibernateUtil;

public class CTHoaDonImpl extends UnicastRemoteObject implements CTHoaDon_DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -38764425465890166L;

	private SessionFactory sessionFactory;

	public CTHoaDonImpl() throws RemoteException {
		super();
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public boolean update(ChiTietHoaDon cthd) throws RemoteException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update ChiTietHoaDon set soLuong=?,maThuoc=?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setString(2, cthd.getThuoc().getMaThuoc());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}
		return n > 0;
	}

	@Override
	public boolean createCTHD(ChiTietHoaDon cthd) throws RemoteException {
//		ConnectDB.getInstance();
//		Connection con = ConnectDB.getConnection();
//		PreparedStatement stmt = null;
//		int n = 0;
//		try {
//			stmt = con.prepareStatement("insert into ChiTietHoaDon values(?,?,?)");
//			stmt.setString(1, cthd.getMaChiTietHD());
//			stmt.setInt(2, cthd.getSoLuong());
//			stmt.setString(3, cthd.getThuoc().getMaThuoc());
//			n = stmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally {
//			try {
//				stmt.close();
//			} catch (SQLException e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//
//		}
//		return n > 0;
		return true;
	}

	@Override
	public boolean XoaCTHD(String maCTHD) throws RemoteException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
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
		return n > 0;
	}

	@Override
	public ArrayList<ChiTietHoaDon> getAllCTHD() throws RemoteException {
		ArrayList<ChiTietHoaDon> dsCTHD = new ArrayList<ChiTietHoaDon>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int soluong = rs.getInt(4);
				Thuoc t = new Thuoc(rs.getString(2));
				HoaDon hd = new HoaDon(rs.getString(1));
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, t, soluong);
				dsCTHD.add(cthd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsCTHD;
	}

	@Override
	public double getTongDoanhThuThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException {
		Object doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT        sum(thanhtien)\r\n" + "FROM              HoaDon\r\n" + "where DAY(ngayLap) = "
					+ ngay + " and MONTH(ngayLap) = " + thang + " and YEAR(ngayLap) = " + nam;
			doanhthu = session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : (double) doanhthu;
	}

	@Override
	public double getTongDoanhTheoTen(String ten) throws RemoteException {
		double doanhthu = 0;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + "FROM HoaDon INNER JOIN\r\n"
					+ "ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n"
					+ "Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n"
					+ "KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + "where hoTen=N'" + ten + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return doanhthu;
	}

	@Override
	public double getTongDoanhTheoMa(String ma) throws RemoteException {
		double doanhthu = 0;

		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + "FROM HoaDon INNER JOIN\r\n"
					+ "ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n"
					+ "Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n"
					+ "KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + "where KhachHang.maKh=N'" + ma + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return doanhthu;
	}

	@Override
	public ChiTietHoaDon getCTHDTheoMa(String macthd) throws RemoteException {
		ChiTietHoaDon cthd = null;

		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietHoaDon where  maChiTietHD = '" + macthd + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int soluong = rs.getInt(4);
				Thuoc t = new Thuoc(rs.getString(2));
				HoaDon hd = new HoaDon(rs.getString(1));
				cthd = new ChiTietHoaDon(hd, t, soluong);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return cthd;
	}

	@Override
	public double getTongDoanhTheoSDT(String sdt) throws RemoteException {
		double doanhthu = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "SELECT doanhthu=sum(ChiTietHoaDon.soLuong*Thuoc.donGia)\r\n" + "FROM HoaDon INNER JOIN\r\n"
					+ "ChiTietHoaDon ON HoaDon.maChiTietHD = ChiTietHoaDon.maChiTietHD INNER JOIN\r\n"
					+ "Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n"
					+ "KhachHang ON HoaDon.maKH = KhachHang.maKh\r\n" + "where KhachHang.SDT=N'" + sdt + "'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				doanhthu = Double.parseDouble(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return doanhthu;

	}

	@Override
	public double getTongDoanhThuThuocTheoThang(int thang, int nam) throws RemoteException {
		Object doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT        sum(thanhtien)\r\n" + "FROM              HoaDon\r\n"
					+ "where MONTH(ngayLap) = " + thang + " and YEAR(ngayLap) = " + nam;
			doanhthu = session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : (double) doanhthu;
	}

	@Override
	public double getTongDoanhThuThuocTheoNam(int nam) throws RemoteException {
		Object doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT        sum(thanhtien)\r\n" + "FROM              HoaDon\r\n"
					+ "where YEAR(ngayLap) = " + nam;
			doanhthu = session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : (double) doanhthu;
	}

}
