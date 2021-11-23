package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="maHoaDon")
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name="maThuoc")
	private Thuoc thuoc;
	private double donGia;
	private int soLuong;
	
	public ChiTietHoaDon() {
		
	}

	public ChiTietHoaDon(HoaDon hoaDon, Thuoc thuoc, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(donGia, hoaDon, soLuong, thuoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		return Double.doubleToLongBits(donGia) == Double.doubleToLongBits(other.donGia)
				&& Objects.equals(hoaDon, other.hoaDon) && soLuong == other.soLuong
				&& Objects.equals(thuoc, other.thuoc);
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", donGia=" + donGia + ", soLuong=" + soLuong
				+ "]";
	}

	
}
