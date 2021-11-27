package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NhaCungCap;
import entity.NuocSX;

public interface NhaCungCapDao extends Remote {
	public List<NhaCungCap> getdsNhaCungCap() throws RemoteException;
	public boolean themNCC(NhaCungCap ncc) throws RemoteException;
	public boolean xoaNCC(String ma) throws RemoteException;
	public boolean updateNCC(NhaCungCap ncc) throws RemoteException;
}
