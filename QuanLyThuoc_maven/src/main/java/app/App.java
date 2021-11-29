package app;

import java.rmi.Naming;

import dao.CTHoaDon_DAO;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiThuocDao;
import dao.NhaCungCapDao;
import dao.NhanVienDao;
import dao.NuocDao;
import dao.ThuocDao;
import frm.FrmDangNhap;

public class App {
	public static KhachHangDao khDao;
	public static NhaCungCapDao nccDao;
	public static LoaiThuocDao loaiDao;
	public static NuocDao nuocDao;
	public static HoaDonDao hdDao;
	public static CTHoaDon_DAO cthdDao;
	public static ThuocDao thuocDao;
	public static NhanVienDao nv_dao;

	public static void main(String[] args) {
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try {
//			String ip = "192.168.1.5:9999";
			String ip = "192.168.1.8:9999";

			khDao = (KhachHangDao) Naming.lookup("rmi://" + ip + "/khachHangDao");
			nccDao = (NhaCungCapDao) Naming.lookup("rmi://" + ip + "/nhaCungCapDao");
			loaiDao = (LoaiThuocDao) Naming.lookup("rmi://" + ip + "/loaiThuocDao");
			nuocDao = (NuocDao) Naming.lookup("rmi://" + ip + "/nuocDao");
			hdDao = (HoaDonDao) Naming.lookup("rmi://" + ip + "/hoaDonDao");
			cthdDao = (CTHoaDon_DAO) Naming.lookup("rmi://" + ip + "/ctHoaDon_DAO");
			thuocDao = (ThuocDao) Naming.lookup("rmi://" + ip + "/thuocDao");
			nv_dao = (NhanVienDao) Naming.lookup("rmi://" + ip + "/nhanVienDao");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		new FrmDangNhap().setVisible(true);

	}
}
