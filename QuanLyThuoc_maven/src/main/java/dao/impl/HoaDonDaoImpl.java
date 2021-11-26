package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.HoaDonDao;
import entity.HoaDon;
import util.HibernateUtil;

public class HoaDonDaoImpl extends UnicastRemoteObject implements HoaDonDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3452934984235677113L;
	private SessionFactory sessionFactory;

	public HoaDonDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public boolean themHoaDon(HoaDon hd) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(hd);
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
