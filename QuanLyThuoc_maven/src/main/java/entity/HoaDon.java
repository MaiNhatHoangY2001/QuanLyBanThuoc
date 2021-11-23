package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maHoaDon;
	private Date ngayLap;
	private double thanhtien;
	@ManyToOne
	@JoinColumn(name="maNV")
	private NhanVien nv;
	@ManyToOne
	@JoinColumn(name="maKH")
	private KhachHang kh;
	
	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> ctHD;

	public HoaDon() {
		
	}

	public HoaDon(String maHoaDon, Date ngayLap, NhanVien nv, KhachHang kh) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.nv = nv;
		this.kh = kh;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
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

	public List<ChiTietHoaDon> getCtHD() {
		return ctHD;
	}

	public void setCtHD(List<ChiTietHoaDon> ctHD) {
		this.ctHD = ctHD;
	}
	
	public double getThanhtien() {
		return thanhtien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
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
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", nv=" + nv + ", kh=" + kh + "]";
	}
	
}
