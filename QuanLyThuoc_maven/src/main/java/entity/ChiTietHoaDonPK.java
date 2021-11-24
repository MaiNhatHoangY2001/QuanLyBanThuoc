package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ChiTietHoaDonPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7133030204579944457L;
	private String hoaDon;
	private String thuoc;
	 public ChiTietHoaDonPK() {
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(hoaDon, thuoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDonPK other = (ChiTietHoaDonPK) obj;
		return Objects.equals(hoaDon, other.hoaDon) && Objects.equals(thuoc, other.thuoc);
	}
	
	 
}
