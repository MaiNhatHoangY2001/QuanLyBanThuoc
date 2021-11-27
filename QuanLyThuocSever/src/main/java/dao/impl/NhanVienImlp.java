package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhanVienDao;
import entity.NhanVien;
import util.HibernateUtil;

public class NhanVienImlp extends UnicastRemoteObject implements NhanVienDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3671510881434717081L;
	private SessionFactory sessionFactory;

	public NhanVienImlp() throws RemoteException {
		this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public boolean themNhanVien(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.save(nv); // JPA - persist
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}

	@Override
	public boolean suaNhanVien(NhanVien nv) {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.update(nv);
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}

	@Override
	public boolean xoaNhanVien(String maNV) {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.delete(session.find(NhanVien.class, maNV));
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}

	@Override
	public List<NhanVien> getAllNhanVien() throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String sql = "select * from NhanVien";

			List<NhanVien> dsNV = session.createNativeQuery(sql, NhanVien.class).getResultList();

			tr.commit();

			return dsNV;
		} catch (Exception e) {
			tr.rollback();
		}

		return null;
	}

	@Override
	public List<NhanVien> getNhanVienTheoMa(String maNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String sql = "select * from NhanVien where maNV = '" + maNV + "'";

			List<NhanVien> nv = session.createNativeQuery(sql, NhanVien.class).getResultList();

			tr.commit();

			return nv;
		} catch (Exception e) {
			tr.rollback();
		}

		return null;
	}

	@Override
	public List<NhanVien> getNhanVienTheoTen(String tenNV) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();

			String sql = "select * from NhanVien where hoTen = N'" + tenNV + "'";

			List<NhanVien> dsNV = session.createNativeQuery(sql, NhanVien.class).getResultList();

			tr.commit();

			return dsNV;
		} catch (Exception e) {
			tr.rollback();
		}

		return null;
	}
}
