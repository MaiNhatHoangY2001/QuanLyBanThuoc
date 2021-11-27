package dao.impl;

import java.math.BigDecimal;
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
	public double getTongDoanhThuThuocTheoThang(int thang, int nam) throws RemoteException {
		BigDecimal doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT      sum(ct.donGia * ct.soLuong)\r\n"
					+ "FROM              ChiTietHoaDon AS ct INNER JOIN\r\n"
					+ "                               HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
					+ "where MONTH(ngayLap) = " + thang + " and YEAR(ngayLap) = " + nam;
			doanhthu = (BigDecimal) session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : doanhthu.doubleValue();
	}

	@Override
	public double getTongDoanhThuThuocTheoNam(int nam) throws RemoteException {
		BigDecimal doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT      sum(ct.donGia * ct.soLuong)\r\n"
					+ "FROM              ChiTietHoaDon AS ct INNER JOIN\r\n"
					+ "                               HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
					+ "where YEAR(ngayLap) = " + nam;
			doanhthu = (BigDecimal) session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : doanhthu.doubleValue();
	}

	@Override
	public double getTongDoanhThuThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException {
		BigDecimal doanhthu = null;
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT      sum(ct.donGia * ct.soLuong)\r\n"
					+ "FROM              ChiTietHoaDon AS ct INNER JOIN\r\n"
					+ "                               HoaDon AS hd ON ct.maHoaDon = hd.maHoaDon "
					+ "where DAY(ngayLap) = " + ngay + " and MONTH(ngayLap) = " + thang + " and YEAR(ngayLap) = " + nam;
			doanhthu = (BigDecimal) session.createNativeQuery(query).getSingleResult();
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}

		return doanhthu == null ? 0 : doanhthu.doubleValue();
	}

	@Override
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(cthd);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return false;
	}
}
