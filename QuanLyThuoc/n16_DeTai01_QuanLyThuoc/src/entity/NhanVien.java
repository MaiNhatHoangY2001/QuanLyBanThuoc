package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class NhanVien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maNV;
	private String hoTen;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private boolean gioiTinh;
	private String diaChi;
	private double luong;

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public NhanVien(String maNV, String hoTen, String soDienThoai, LocalDate ngaySinh, boolean gioiTinh, String diaChi,
			double luong) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.luong = luong;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", soDienThoai=" + soDienThoai + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", luong=" + luong + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}

}
