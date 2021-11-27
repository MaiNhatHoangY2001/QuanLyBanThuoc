package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LoaiThuoc;
import entity.Thuoc;

public interface LoaiThuocDao extends Remote {
	public List<LoaiThuoc> getdsLoaiThuoc() throws RemoteException;
	public boolean themLoaiThuoc(LoaiThuoc loaiThuoc) throws RemoteException;
	public boolean xoaLoaiThuoc(String maLoai) throws RemoteException;
	public boolean updateloaiThuoc(LoaiThuoc loaiThuoc) throws RemoteException;
}
