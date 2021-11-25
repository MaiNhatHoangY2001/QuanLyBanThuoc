package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import entity.NuocSX;
import util.HibernateUtil;

public class NhaCungCapDaoImpl extends UnicastRemoteObject implements NhaCungCapDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5268391342302709764L;
	private SessionFactory sessionFactory;
	
	public NhaCungCapDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public List<NhaCungCap> getdsNhaCungCap() throws RemoteException {
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from NhaCungCap";
			list = session.createNativeQuery(query, NhaCungCap.class).getResultList();
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

}
