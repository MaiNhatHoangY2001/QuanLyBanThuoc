package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.LoaiThuoc;
import entity.NuocSX;

public interface NuocDao extends Remote {
	public List<NuocSX> getdsNuocSX() throws RemoteException;
	public boolean themNuocSX(NuocSX nuocSX) throws RemoteException;
	public boolean xoaNuocSX(String maNuoc) throws RemoteException;
	public boolean updateNuocSX(NuocSX nuocSX) throws RemoteException;
}
