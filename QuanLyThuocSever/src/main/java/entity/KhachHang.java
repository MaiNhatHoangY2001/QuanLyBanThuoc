package entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class KhachHang implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6417631108679592102L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "KH"), strategy = "generator.SinhMaTheoNgay")
	@Column(columnDefinition = "char(10)")
	private String maKH;
	@Column(columnDefinition = "Nvarchar(255)", nullable = false)
	private String hoTen;
	@Column(nullable = false)
	private LocalDate ngaySinh;
	@Column(nullable = false)
	private boolean gioiTinh;
	@Column(columnDefinition = "Nvarchar(255)", nullable = false)
	private String diaChi;
	@Column(columnDefinition = "varchar(10)", nullable = false)
	private String SDT;

	@OneToMany(mappedBy = "kh")
	private List<HoaDon> hoaDons;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public KhachHang(String hoTen, LocalDate ngaySinh, boolean gioiTinh, String diaChi, String sDT) {
		super();
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.SDT = sDT;
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang() {
		super();
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", diaChi=" + diaChi + ", SDT=" + SDT + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		return true;
	}

}
