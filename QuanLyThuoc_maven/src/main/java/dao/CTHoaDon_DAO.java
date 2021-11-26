package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import entity.ChiTietHoaDon;

public interface CTHoaDon_DAO extends Remote {

	public double getTongDoanhThuThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException;

	public double getTongDoanhThuThuocTheoThang(int thang, int nam) throws RemoteException;

	public double getTongDoanhThuThuocTheoNam(int nam) throws RemoteException;
	
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;

}
