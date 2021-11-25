package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.KhachHangDao;
import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangDaoImpl extends UnicastRemoteObject implements KhachHangDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994501455254420035L;
	private SessionFactory sessionFactory;

	public KhachHangDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public boolean themKhachHang(KhachHang kh) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(kh);
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

	@Override
	public boolean capNhatKhachHang(KhachHang kh) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(kh);
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

	@Override
	public KhachHang getKhachHangTheoMa(String ma) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			KhachHang kh = session.get(KhachHang.class, ma);
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public KhachHang getKhachHangTheoSDT(String SDT) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "Select * from KhachHang\r\n" + "where SDT like " + SDT;
			KhachHang kh = session.createNativeQuery(query, KhachHang.class).getSingleResult();
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public KhachHang getKhachHangTheoMaVaSDT(String data) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from KhachHang\r\n" + "where maKH like '" + data + "'\r\n" + "or SDT like '"+data+"'";
			KhachHang kh = session.createNativeQuery(query, KhachHang.class).getSingleResult();
			tr.commit();
			return kh;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
