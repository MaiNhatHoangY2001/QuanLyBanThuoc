package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ChiTietHoaDonPK.class)
public class ChiTietHoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3956251130076722107L;
	@Id
	@ManyToOne
	@JoinColumn(name = "maHoaDon", columnDefinition = "char(10)")
	private HoaDon hoaDon;
	@Id
	@ManyToOne
	@JoinColumn(name = "maThuoc", columnDefinition = "char(10)")
	private Thuoc thuoc;
	@Column(columnDefinition = "money", nullable = false)
	private double donGia;
	@Column(nullable = false)
	private int soLuong;

	public ChiTietHoaDon() {

	}

	public ChiTietHoaDon(HoaDon hoaDon, Thuoc thuoc, int soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.thuoc = thuoc;
		this.soLuong = soLuong;
		this.donGia = thuoc.getDonGia() * soLuong + 20000;
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

	public double getDonGia() {
		return donGia;
	}
	
	public void setDonGia() {
		this.donGia = getSoLuong() * getThuoc().getDonGia();
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", thuoc=" + thuoc + ", donGia=" + donGia + ", soLuong=" + soLuong
				+ "]";
	}

}
