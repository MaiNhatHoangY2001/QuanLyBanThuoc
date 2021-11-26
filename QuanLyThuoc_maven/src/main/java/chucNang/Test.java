package chucNang;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.ThuocDao;
import dao.impl.ThuocDaoImpl;
import entity.NhanVien;
import util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
//		try {
//			ThuocDao dao = new ThuocDaoImpl();
//			List<?> list =  dao.getdsChiTietThuoc();
//			for (Object object : list) {
//				Object[] o = (Object[]) object;
//				System.out.println(o[0] + " " + o[1]+ " " +o[2]+ " " +o[3]+ " " +o[4]+ " " +o[5]+ " " +o[6]+ " " +o[7]+ " " +o[8]);
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		double tongthanhtien = 1000000;
//		String thanhtien = (tongthanhtien % 1) == 0 ? ((int)tongthanhtien) + "" : tongthanhtien + "";
//		System.out.println(thanhtien);
		
		NhanVien nv = new NhanVien("Nguyễn Văn A", "0334172541", LocalDate.of(2000, 12, 12), true, "TP.HCM", 10000000);
		SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tr = session.getTransaction();
		try {
			tr.begin();
			session.save(nv);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		} finally {
			session.close();
		}
				
	}

}
