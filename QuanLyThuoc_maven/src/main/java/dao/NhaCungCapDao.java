package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhaCungCap;

public interface NhaCungCapDao extends Remote {
	public List<NhaCungCap> getdsNhaCungCap() throws RemoteException;
}
