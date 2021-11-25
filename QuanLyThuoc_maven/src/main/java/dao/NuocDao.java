package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.NuocSX;

public interface NuocDao extends Remote {
	public List<NuocSX> getdsNuocSX() throws RemoteException;
}
