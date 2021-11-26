package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.ChiTietHoaDon;

public interface ChiTietHoaDonDao extends Remote {
	public boolean themChiTietHoaDon(ChiTietHoaDon cthd) throws RemoteException;
}
