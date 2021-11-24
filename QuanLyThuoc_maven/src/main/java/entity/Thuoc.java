package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Thuoc implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "TH"), strategy = "generator.SinhMaTheoNgay")
	private String maThuoc;
	private String tenThuoc;
	private double donGia;
	private Date ngaySX;
	private Date hanSuDung;
	private int SLTon;

	@ManyToOne
	@JoinColumn(name = "maNCC")
	private NhaCungCap ncc;
	@ManyToOne
	@JoinColumn(name = "maLoai")
	private LoaiThuoc loaiThuoc;
	@ManyToOne
	@JoinColumn(name = "idNuoc")
	private NuocSX nuocSX;
	@OneToMany(mappedBy = "thuoc")
	private List<ChiTietHoaDon> ctDH;

	public Thuoc() {
		// TODO Auto-generated constructor stub
	}

	public String getMaThuoc() {
		return maThuoc;
	}

	public String getTenThuoc() {
		return tenThuoc;
	}

	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public Date getNgaySX() {
		return ngaySX;
	}

	public void setNgaySX(Date ngaySX) {
		this.ngaySX = ngaySX;
	}

	public Date getHanSuDung() {
		return hanSuDung;
	}

	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}

	public int getSLTon() {
		return SLTon;
	}

	public void setSLTon(int sLTon) {
		SLTon = sLTon;
	}

	public NhaCungCap getNcc() {
		return ncc;
	}

	public void setNcc(NhaCungCap ncc) {
		this.ncc = ncc;
	}

	public LoaiThuoc getLoaiThuoc() {
		return loaiThuoc;
	}

	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}

	public NuocSX getNuocSX() {
		return nuocSX;
	}

	public void setNuocSX(NuocSX nuocSX) {
		this.nuocSX = nuocSX;
	}

	public Thuoc(String maThuoc) {
		super();
		this.maThuoc = maThuoc;
	}

	public Thuoc(String tenThuoc, double donGia, Date ngaySX2, Date hanSD, int sLTon, NhaCungCap ncc2,
			LoaiThuoc loaiThuoc, NuocSX nuocSX) {
		super();
		this.tenThuoc = tenThuoc;
		this.donGia = donGia;
		this.ngaySX = ngaySX2;
		this.ncc = ncc2;
		this.hanSuDung = hanSD;
		this.SLTon = sLTon;
		this.loaiThuoc = loaiThuoc;
		this.nuocSX = nuocSX;
	}

	public Thuoc(String tenThuoc, double donGia, int sLTon, NhaCungCap ncc, LoaiThuoc loaiThuoc, NuocSX nuocSX) {
		super();
		this.tenThuoc = tenThuoc;
		this.donGia = donGia;
		SLTon = sLTon;
		this.ncc = ncc;
		this.loaiThuoc = loaiThuoc;
		this.nuocSX = nuocSX;
	}

	/**
	 * @param sLTon
	 */
	public Thuoc(int sLTon) {
		super();
		SLTon = sLTon;
	}

	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donGia=" + donGia + ", ngaySX=" + ngaySX
				+ ", hanSuDung=" + hanSuDung + ", SLTon=" + SLTon + ", ncc=" + ncc + ", loaiThuoc=" + loaiThuoc
				+ ", nuocSX=" + nuocSX + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maThuoc == null) ? 0 : maThuoc.hashCode());
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
		Thuoc other = (Thuoc) obj;
		if (maThuoc == null) {
			if (other.maThuoc != null)
				return false;
		} else if (!maThuoc.equals(other.maThuoc))
			return false;
		return true;
	}

}
