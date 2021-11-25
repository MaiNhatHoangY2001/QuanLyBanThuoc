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
public class NuocSX implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1938720151580355548L;
	@Id
	@GeneratedValue(generator = "sinhMaTheoNgay")
	@GenericGenerator(name = "sinhMaTheoNgay", parameters = @Parameter(name = "prefix", value = "SX"), strategy = "generator.SinhMaTheoNgay")
	private String idNuoc;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenNuoc;

	@OneToMany(mappedBy = "nuocSX")
	private List<Thuoc> thuocs;

	public String getIdNuoc() {
		return idNuoc;
	}

	public String getTenNuoc() {
		return tenNuoc;
	}

	public void setTenNuoc(String tenNuoc) {
		this.tenNuoc = tenNuoc;
	}

	public NuocSX(String tenNuoc) {
		super();
		this.tenNuoc = tenNuoc;
	}

	public NuocSX() {
		super();
	}

	@Override
	public String toString() {
		return "NuocSX [idNuoc=" + idNuoc + ", tenNuoc=" + tenNuoc + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNuoc == null) ? 0 : idNuoc.hashCode());
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
		NuocSX other = (NuocSX) obj;
		if (idNuoc == null) {
			if (other.idNuoc != null)
				return false;
		} else if (!idNuoc.equals(other.idNuoc))
			return false;
		return true;
	}

}
