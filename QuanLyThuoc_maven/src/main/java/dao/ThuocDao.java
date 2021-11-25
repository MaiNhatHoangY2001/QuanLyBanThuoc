package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Thuoc;

public interface ThuocDao extends Remote {
	 
	public List<Thuoc> getdsThuoc() throws RemoteException;
	public List<?> getdsChiTietThuoc() throws RemoteException;

}
