package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NuocDao;
import entity.LoaiThuoc;
import entity.NuocSX;
import entity.Thuoc;
import util.HibernateUtil;

public class NuocDaoImpl extends UnicastRemoteObject implements NuocDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -487432704758489264L;
	private SessionFactory sessionFactory;

	public NuocDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}


	@Override
	public List<NuocSX> getdsNuocSX() throws RemoteException {
		List<NuocSX> list = new ArrayList<NuocSX>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from NuocSX";
			list = session.createNativeQuery(query, NuocSX.class).getResultList();
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public boolean themNuocSX(NuocSX nuocSX) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.save(nuocSX); //JPA - persist
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}


	@Override
	public boolean xoaNuocSX(String maNuoc) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.delete(session.find(NuocSX.class, maNuoc));
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}


	@Override
	public boolean updateNuocSX(NuocSX nuocSX) throws RemoteException {
		Session session = sessionFactory.getCurrentSession();

		Transaction tr = session.getTransaction();

		try {
			tr.begin();
			session.update(nuocSX);
			tr.commit();

			return true;
		} catch (Exception e) {
			tr.rollback();
		}

		return false;
	}

}
