package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class LoaiThuoc implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4529334656901915994L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "LT"), strategy = "generator.SinhMaTheoNgay")
	private String maLoai;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenLoai;
	
	@OneToMany(mappedBy = "loaiThuoc")
	private List<Thuoc> thuocs;

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public LoaiThuoc(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}

	public LoaiThuoc(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public LoaiThuoc() {
		super();
	}

	@Override
	public String toString() {
		return "LoaiThuoc [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLoai == null) ? 0 : maLoai.hashCode());
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
		LoaiThuoc other = (LoaiThuoc) obj;
		if (maLoai == null) {
			if (other.maLoai != null)
				return false;
		} else if (!maLoai.equals(other.maLoai))
			return false;
		return true;
	}

}
