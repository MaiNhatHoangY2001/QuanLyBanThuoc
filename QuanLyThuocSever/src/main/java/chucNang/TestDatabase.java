package chucNang;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import util.HibernateUtil;

public class TestDatabase {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new HibernateUtil().getSessionFactory();
	}
}
