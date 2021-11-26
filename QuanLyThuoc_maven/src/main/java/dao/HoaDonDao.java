package dao;

import java.rmi.Remote;

import entity.HoaDon;
import java.rmi.RemoteException;

public interface HoaDonDao extends Remote {
	public boolean themHoaDon(HoaDon hd) throws RemoteException;
}
