package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.ChiTietHoaDon;

public interface CTHoaDon_DAO extends Remote {
	public boolean update(ChiTietHoaDon cthd) throws RemoteException;

	public boolean createCTHD(ChiTietHoaDon cthd) throws RemoteException;

	public boolean XoaCTHD(String maCTHD) throws RemoteException;

	public ArrayList<ChiTietHoaDon> getAllCTHD() throws RemoteException;

	public double getTongDoanhThuThuoc() throws RemoteException;

	public double getTongDoanhThuThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException;

	public double getTongDoanhTheoTen(String ten) throws RemoteException;
	
	public double getTongDoanhTheoMa(String ma) throws RemoteException;
	
	public ChiTietHoaDon getCTHDTheoMa(String macthd) throws RemoteException;
	
	public double getTongDoanhTheoSDT(String sdt) throws RemoteException;
}
