package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.KhachHang;

public interface KhachHangDao extends Remote {
	public boolean themKhachHang(KhachHang kh) throws RemoteException;
	public boolean capNhatKhachHang(KhachHang kh) throws RemoteException;
	public KhachHang getKhachHangTheoMa(String ma) throws RemoteException;
	public KhachHang getKhachHangTheoSDT(String SDT) throws RemoteException;
	public KhachHang getKhachHangTheoMaVaSDT(String data) throws RemoteException;
}
