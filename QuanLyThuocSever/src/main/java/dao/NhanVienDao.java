package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote {
	public boolean themNhanVien(NhanVien nv) throws RemoteException;

	public boolean suaNhanVien(NhanVien nv) throws RemoteException;

	public boolean xoaNhanVien(String maNV) throws RemoteException;

	public List<NhanVien> getAllNhanVien() throws RemoteException;

	public List<NhanVien> getNhanVienTheoMa(String maNV) throws RemoteException;

	public List<NhanVien> getNhanVienTheoTen(String tenNV) throws RemoteException;
}
