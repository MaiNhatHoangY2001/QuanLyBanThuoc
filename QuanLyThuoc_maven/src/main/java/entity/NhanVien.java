package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class NhanVien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4505044303581756213L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "NV"), strategy = "generator.SinhMaTheoNgay")
	@Column(columnDefinition = "char(10)")
	private String maNV;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String hoTen;
	@Column(name = "SDT", columnDefinition = "varchar(10)", nullable = false)
	private String soDienThoai;
	@Column(nullable = false)
	private Date ngaySinh;
	@Column(nullable = false)
	private boolean gioiTinh;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String diaChi;
	@Column(columnDefinition = "money", nullable = false)
	private double luong;

	@OneToMany(mappedBy = "nv")
	private List<HoaDon> hoaDons;

	public String getMaNV() {
		return maNV;
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

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
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

	public NhanVien(String hoTen, String soDienThoai, Date ngaySinh, boolean gioiTinh, String diaChi, double luong) {
		super();
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.luong = luong;
	}

	public NhanVien(String maNV, String hoTen, String soDienThoai, Date ngaySinh, boolean gioiTinh, String diaChi,
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
