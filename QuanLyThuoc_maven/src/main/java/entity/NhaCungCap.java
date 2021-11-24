package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class NhaCungCap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3703171346646276753L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "NC"), strategy = "generator.SinhMaTheoNgay")
	private String maNCC;
	private String tenNCC;
	private String diaChi;

	@OneToMany(mappedBy = "ncc")
	private List<Thuoc> thuocs;

	public String getMaNCC() {
		return maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public NhaCungCap(String maNCC, String tenNCC, String diaChi) {
		super();
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
	}

	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}

	public NhaCungCap() {
		super();
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNCC == null) ? 0 : maNCC.hashCode());
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
		NhaCungCap other = (NhaCungCap) obj;
		if (maNCC == null) {
			if (other.maNCC != null)
				return false;
		} else if (!maNCC.equals(other.maNCC))
			return false;
		return true;
	}

}
