package entity;

import java.io.Serializable;

public class NuocSX implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idNuoc;
	private String tenNuoc;

	public String getIdNuoc() {
		return idNuoc;
	}

	public void setIdNuoc(String idNuoc) {
		this.idNuoc = idNuoc;
	}

	public String getTenNuoc() {
		return tenNuoc;
	}

	public void setTenNuoc(String tenNuoc) {
		this.tenNuoc = tenNuoc;
	}

	public NuocSX(String idNuoc, String tenNuoc) {
		super();
		this.idNuoc = idNuoc;
		this.tenNuoc = tenNuoc;
	}

	public NuocSX(String idNuoc) {
		super();
		this.idNuoc = idNuoc;
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