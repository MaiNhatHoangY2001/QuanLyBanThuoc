package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NuocSX;
import entity.Thuoc;

public class Thuoc_DAO {
	public ArrayList<Thuoc> getThuocKhachHangDaMua(String maKH){
		ArrayList<Thuoc> dsThuoc = new ArrayList<Thuoc>();
		
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT Thuoc.maThuoc, Thuoc.tenThuoc, Thuoc.donGia,NhaCungCap.tenNCC, LoaiThuoc.tenLoai, NuocSX.tenNuoc, ChiTietHoaDon.soLuong\r\n" + 
					"FROM     ChiTietHoaDon INNER JOIN HoaDon ON ChiTietHoaDon.maChiTietHD = HoaDon.maChiTietHD INNER JOIN\r\n" + 
					"Thuoc ON ChiTietHoaDon.maThuoc = Thuoc.maThuoc INNER JOIN\r\n" + 
					"LoaiThuoc ON Thuoc.maLoai = LoaiThuoc.maLoai INNER JOIN\r\n" + 
					"NhaCungCap ON Thuoc.maNCC = NhaCungCap.maNCC INNER JOIN\r\n" + 
					"NuocSX ON Thuoc.idNuoc = NuocSX.idNuoc\r\n" + 
					"where HoaDon.maKH ='"+maKH+"'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				String maThuoc = rs.getString(1);
				String tenThuoc =  rs.getString(2);
				double donGia=Double.parseDouble(rs.getString(3));
				String ncc=rs.getString(4);
				String tenLoai = rs.getString(5);
				String tenNuoc = rs.getString(6);
				int soluong=Integer.parseInt(rs.getString(7));
				NhaCungCap nhacungcap=new NhaCungCap(ncc);
				LoaiThuoc loaithuoc =new LoaiThuoc(tenLoai);
				NuocSX nuocsx=new NuocSX(tenNuoc);
				Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, donGia, soluong,nhacungcap,loaithuoc, nuocsx);
				dsThuoc.add(thuoc);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dsThuoc;
	}
}
