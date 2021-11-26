package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ChiTietHoaDonDao;
import entity.ChiTietHoaDon;
import util.HibernateUtil;

public class ChiTietHoaDonDaoImpl extends UnicastRemoteObject implements ChiTietHoaDonDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6807798313653826416L;
	private SessionFactory sessionFactory;

	public ChiTietHoaDonDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
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
