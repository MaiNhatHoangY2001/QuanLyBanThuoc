package chucNang;

import java.rmi.RemoteException;
import java.util.List;

import dao.ThuocDao;
import dao.impl.ThuocDaoImpl;

public class Test {

	public static void main(String[] args) {
		try {
			ThuocDao dao = new ThuocDaoImpl();
			List<?> list =  dao.getdsChiTietThuoc();
			for (Object object : list) {
				Object[] o = (Object[]) object;
				System.out.println(o[0] + " " + o[1]+ " " +o[2]+ " " +o[3]+ " " +o[4]+ " " +o[5]+ " " +o[6]+ " " +o[7]+ " " +o[8]);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

}
