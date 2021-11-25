package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LoaiThuoc;

public interface LoaiThuocDao extends Remote {
	public List<LoaiThuoc> getdsLoaiThuoc() throws RemoteException;
}
