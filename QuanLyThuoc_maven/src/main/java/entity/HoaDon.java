package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class HoaDon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6695808343880951516L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "HD"), strategy = "generator.SinhMaTheoNgay")
	@Column(columnDefinition = "char(10)")
	private String maHoaDon;
	@Column(nullable = false)
	private LocalDate ngayLap;
	@Column(columnDefinition = "money", nullable = false)
	private double thanhtien;
	@ManyToOne
	@JoinColumn(name = "maNV", nullable = false)
	private NhanVien nv;
	@ManyToOne
	@JoinColumn(name = "maKH", nullable = false)
	private KhachHang kh;

	@OneToMany(mappedBy = "hoaDon")
	private List<ChiTietHoaDon> ctHD;

	public HoaDon() {

	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public HoaDon(LocalDate ngayLap, NhanVien nv, KhachHang kh) {
		super();
		this.ngayLap = ngayLap;
		this.nv = nv;
		this.kh = kh;
		this.thanhtien = thanhTien();
	}

	public double thanhTien() {
		double tong = 0;
		for (ChiTietHoaDon chiTietHoaDon : ctHD) {
			tong += chiTietHoaDon.getDonGia();
		}
		return tong;
	}

	public String getMaHoaDon() {
		return maHoaDon;
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

	public List<ChiTietHoaDon> getCtHD() {
		return ctHD;
	}

	public void setCtHD(List<ChiTietHoaDon> ctHD) {
		this.ctHD = ctHD;
	}

	public double getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien() {
		this.thanhtien = 0;
		for (ChiTietHoaDon chiTietHoaDon : ctHD) {
			this.thanhtien += chiTietHoaDon.getDonGia();
		}
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
