package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.KhachHang;

public interface KhachHangDao extends Remote {
	public boolean themKhachHang(KhachHang kh) throws RemoteException;

	public boolean capNhatKhachHang(KhachHang kh) throws RemoteException;

	public KhachHang getKhachHangTheoMa(String ma) throws RemoteException;

	public KhachHang getKhachHangTheoSDT(String SDT) throws RemoteException;

	public KhachHang getKhachHangTheoMaVaSDT(String data) throws RemoteException;

	public List<KhachHang> getKhachHangDaMuaThuocTheoNgay(int ngay, int thang, int nam) throws RemoteException;
	
	public List<KhachHang> getKhachHangDaMuaThuocTheoThang(int thang, int nam) throws RemoteException;
	
	public List<KhachHang> getKhachHangDaMuaThuocTheoNam(int nam) throws RemoteException;

	public List<KhachHang> getAllKhachHang() throws RemoteException;

}
