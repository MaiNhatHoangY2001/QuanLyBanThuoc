package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import dao.CTHoaDon_DAO;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiThuocDao;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.NuocDao;
import dao.ThuocDao;
import dao.impl.CTHoaDonImpl;
import dao.impl.HoaDonDaoImpl;
import dao.impl.KhachHangDaoImpl;
import dao.impl.LoaiThuocDaoImpl;
import dao.impl.NhaCungCapDaoImpl;
import dao.impl.NhanVienImlp;
import dao.impl.NuocDaoImpl;
import dao.impl.ThuocDaoImpl;

public class AppServer {

	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			CTHoaDon_DAO ctHoaDon_DAO = new CTHoaDonImpl();
			HoaDonDao hoaDonDao = new HoaDonDaoImpl();
			KhachHangDao khachHangDao = new KhachHangDaoImpl();
			LoaiThuocDao loaiThuocDao = new LoaiThuocDaoImpl();
			NhaCungCapDao nhaCungCapDao = new NhaCungCapDaoImpl();
			NhanVienDao nhanVienDao = new NhanVienImlp();
			NuocDao nuocDao = new NuocDaoImpl();
			ThuocDao thuocDao = new ThuocDaoImpl();

			LocateRegistry.createRegistry(9999);
			 String ip = "192.168.1.6:9999";
			//String ip = "192.168.1.5:9999";
//			String ip = "192.168.1.5:9999";
			// String ip = "192.168.1.7:9999";
			//String ip = "192.168.1.8:9999";
			
			Naming.bind("rmi://" + ip + "/ctHoaDon_DAO", ctHoaDon_DAO);
			Naming.bind("rmi://" + ip + "/hoaDonDao", hoaDonDao);
			Naming.bind("rmi://" + ip + "/khachHangDao", khachHangDao);
			Naming.bind("rmi://" + ip + "/loaiThuocDao", loaiThuocDao);
			Naming.bind("rmi://" + ip + "/nhaCungCapDao", nhaCungCapDao);
			Naming.bind("rmi://" + ip + "/nhanVienDao", nhanVienDao);
			Naming.bind("rmi://" + ip + "/nuocDao", nuocDao);
			Naming.bind("rmi://" + ip + "/thuocDao", thuocDao);
			System.out.println("Server sẵn sàng");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
