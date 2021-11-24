package chucNang;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import entity.ChiTietHoaDon;
import entity.ChiTietHoaDonPK;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NuocSX;
import entity.Thuoc;

public class MySessionFactory {
	private SessionFactory sessionFactory;
	
	public MySessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.configure() //hibernate.cfg.xml
				.build();
		
		Metadata metadata = new MetadataSources(serviceRegistry)
				.addAnnotatedClass(NuocSX.class)
				.addAnnotatedClass(NhaCungCap.class)
				.addAnnotatedClass(LoaiThuoc.class)
				.addAnnotatedClass(Thuoc.class)
				.addAnnotatedClass(KhachHang.class)
				.addAnnotatedClass(HoaDon.class)
				.addAnnotatedClass(NhanVien.class)
				.addAnnotatedClass(ChiTietHoaDonPK.class)
				.addAnnotatedClass(ChiTietHoaDon.class)
				.getMetadataBuilder()
				.build();
		
		sessionFactory = metadata
				.getSessionFactoryBuilder()
				.build();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void close() {
		sessionFactory.close();
	}
}
