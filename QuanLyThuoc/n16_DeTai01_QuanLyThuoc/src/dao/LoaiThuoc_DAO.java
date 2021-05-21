package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiThuoc;



public class LoaiThuoc_DAO {
	
	public ArrayList<LoaiThuoc> getallLoaiThuoc(){
		ArrayList<LoaiThuoc> dsLT = new ArrayList<LoaiThuoc>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql ="Select * from LoaiThuoc";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				String maLT = rs.getString(1);
				String tenLT = rs.getString(2);
				LoaiThuoc lt = new LoaiThuoc(maLT, tenLT);
				dsLT.add(lt);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsLT;
	}
	public String  getMaLoaiThuocTheoTen(String tenLoai){
		String maLT ="";
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql ="Select maLoai from LoaiThuoc where tenLoai = N'"+tenLoai+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next())
			{
				 maLT = rs.getString(1);
				
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return maLT;
	}
	
}
