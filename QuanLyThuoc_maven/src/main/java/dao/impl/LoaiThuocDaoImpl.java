package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.LoaiThuocDao;
import entity.LoaiThuoc;
import util.HibernateUtil;

public class LoaiThuocDaoImpl extends UnicastRemoteObject implements LoaiThuocDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5166322974724843292L;
	private SessionFactory sessionFactory;
	
	public LoaiThuocDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public List<LoaiThuoc> getdsLoaiThuoc() throws RemoteException {
		List<LoaiThuoc> list = new ArrayList<LoaiThuoc>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from LoaiThuoc";
			list = session.createNativeQuery(query, LoaiThuoc.class).getResultList();
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
