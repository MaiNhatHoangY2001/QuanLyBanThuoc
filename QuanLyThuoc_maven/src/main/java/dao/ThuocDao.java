package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.Thuoc;

public interface ThuocDao extends Remote {
	public boolean capNhatThuoc(Thuoc t) throws RemoteException;
	public List<Thuoc> getdsThuoc() throws RemoteException;
	public List<?> getdsChiTietThuoc() throws RemoteException;
	public Thuoc getThuocTheoMa(String ma) throws RemoteException;
	public List<Thuoc> getdsThuocTheoTenNccNuocLoai(String tenThuoc, String maNcc, String maNuoc, String maLoai) throws RemoteException;
	public List<Thuoc> getdsThuocTheoMaNcc(String maNCC) throws RemoteException;
	

	public int getTongSoLuongThuoc(int year) throws RemoteException;

	public int getTongLoaiThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException;

	public int getTongLoaiThuocTheoThang(int thang, int nam) throws RemoteException;

	public int getTongLoaiThuocTheoNam(int nam) throws RemoteException;

	public List<Thuoc> getThuocKhachHangDaMua(String maKH) throws RemoteException;

	public int getTongSoLuongThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException;

	public int getTongSoLuongThuocTheoThang(int thang, int nam) throws RemoteException;

	public int getTongSoLuongThuocTheoNam(int nam) throws RemoteException;
}
