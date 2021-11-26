package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ThuocDao;
import entity.Thuoc;
import util.HibernateUtil;

public class ThuocDaoImpl extends UnicastRemoteObject implements ThuocDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1155018079157136560L;
	private SessionFactory sessionFactory;

	public ThuocDaoImpl() throws RemoteException {
		sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	}

	@Override
	public List<Thuoc> getdsThuoc() throws RemoteException {
		List<Thuoc> list = new ArrayList<Thuoc>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from Thuoc";
			list = session.createNativeQuery(query, Thuoc.class).getResultList();
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
	public List<?> getdsChiTietThuoc() throws RemoteException {
		List<Thuoc> list = new ArrayList<Thuoc>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "SELECT        Thuoc.maThuoc, Thuoc.SLTon, Thuoc.donGia, Thuoc.hanSuDung, Thuoc.ngaySX, Thuoc.tenThuoc, LoaiThuoc.tenLoai, NhaCungCap.tenNCC, NuocSX.tenNuoc\r\n"
					+ "FROM            Thuoc INNER JOIN\r\n"
					+ "                         NuocSX ON Thuoc.idNuoc = NuocSX.idNuoc INNER JOIN\r\n"
					+ "                         NhaCungCap ON Thuoc.maNCC = NhaCungCap.maNCC INNER JOIN\r\n"
					+ "                         LoaiThuoc ON Thuoc.maLoai = LoaiThuoc.maLoai";
			list = session.createNativeQuery(query).getResultList();
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
	public Thuoc getThuocTheoMa(String ma) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			Thuoc thuoc = session.find(Thuoc.class, ma);
			tr.commit();
			return thuoc;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Thuoc> getdsThuocTheoTenNccNuocLoai(String tenThuoc, String maNcc, String maNuoc, String maLoai)
			throws RemoteException {
		List<Thuoc> list = new ArrayList<Thuoc>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from Thuoc\r\n"
					+ "where tenThuoc like '%"+tenThuoc+"%' \r\n"
					+ "and maLoai like '%"+maLoai+"%'\r\n"
					+ "and maNCC like '%"+maNcc+"%'\r\n"
					+ "and idNuoc like '%"+maNuoc+"%'";
			list = session.createNativeQuery(query, Thuoc.class).getResultList();
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
	public boolean capNhatThuoc(Thuoc t) throws RemoteException {
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.update(t);
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
	public List<Thuoc> getdsThuocTheoMaNcc(String maNCC) throws RemoteException {
		List<Thuoc> list = new ArrayList<Thuoc>();
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			String query = "select * from Thuoc\r\n"
					+ "where maNCC like '"+maNCC+"'";
			list = session.createNativeQuery(query, Thuoc.class).getResultList();
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
