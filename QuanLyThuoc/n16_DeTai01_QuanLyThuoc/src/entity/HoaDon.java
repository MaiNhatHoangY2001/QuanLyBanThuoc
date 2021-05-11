package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class HoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maHoaDon;
	private LocalDate ngayLap;
	private NhanVien nv;
	private KhachHang kh;
	private ChiTietHoaDon ctHD;

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public ChiTietHoaDon getCtHD() {
		return ctHD;
	}

	public void setCtHD(ChiTietHoaDon ctHD) {
		this.ctHD = ctHD;
	}

	public HoaDon(String maHoaDon, LocalDate ngayLap, NhanVien nv, KhachHang kh, ChiTietHoaDon ctHD) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.nv = nv;
		this.kh = kh;
		this.ctHD = ctHD;
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public HoaDon() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHoaDon == null) ? 0 : maHoaDon.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", nv=" + nv + ", kh=" + kh + ", ctHD=" + ctHD
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		if (maHoaDon == null) {
			if (other.maHoaDon != null)
				return false;
		} else if (!maHoaDon.equals(other.maHoaDon))
			return false;
		return true;
	}

}
