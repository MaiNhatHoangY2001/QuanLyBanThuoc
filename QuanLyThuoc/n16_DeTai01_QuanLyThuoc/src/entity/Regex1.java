package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdatepicker.impl.SqlDateModel;

public class Regex1 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean RegexDiaChi(JTextField txtDiaChi2) {
		String input = txtDiaChi2.getText();
		String regex = "^([A-Za-z0-9,a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập sai địa chỉ (Ví dụ nhập:56a Cầu Xéo, Tân quí, Tân Phú");
			txtDiaChi2.requestFocus();
			txtDiaChi2.selectAll();
			return true;
		} else
			return false;
	}

	public boolean kiemTraTuoi(SqlDateModel modelNgay) {
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Date ngaySinh = (Date) modelNgay.getValue();
		if (ngaySinh.compareTo(today) > 0) {
			JOptionPane.showMessageDialog(null, "Dữ liệu tuổi không hợp lệ phải trước ngày hiện tại");
			return true;
		} else
			return false;

	}

	public boolean kiemTraNgaySX(SqlDateModel modelNgay) {
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Date ngaySinh = (Date) modelNgay.getValue();
		if (ngaySinh.compareTo(today) > 0) {
			JOptionPane.showMessageDialog(null, "Dữ liệu Ngày sản xuất không hợp lệ phải trước ngày hiện tại");
			return true;
		} else
			return false;

	}

	public boolean kiemTraHanSD(SqlDateModel modelNgay) {
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		Date ngaySinh = (Date) modelNgay.getValue();
		if (ngaySinh.compareTo(today) < 0) {
			JOptionPane.showMessageDialog(null, "Dữ liệu Ngày hết hạn sữ dụng không hợp lệ phải sau ngày hiện tại");
			return true;
		} else
			return false;

	}

	public boolean RegexTen(JTextField txtTen2) {
		String input = txtTen2.getText();
		String regex = "^([A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập sai tên (Ví dụ nhập:Nguyễn Văn A)");
			txtTen2.requestFocus();
			txtTen2.selectAll();
			return true;
		} else
			return false;
	}

	public boolean RegexMaNV(JTextField txtMa2) {
		String input = txtMa2.getText();
		String regex = "^[N][V][0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập sai mã (Ví dụ nhập:NV1234 )");
			txtMa2.requestFocus();
			txtMa2.selectAll();
			return true;
		} else
			return false;
	}

	public boolean kiemTraRong(JTextField txt) {
		if (txt.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Dữ liệu không được để trống");
			txt.requestFocus();
			return true;
		}
		return false;
	}

	public boolean kiemTraSo(JTextField txtTuoi2) {
		try {
			int x = Integer.parseInt(txtTuoi2.getText());
			if (x < 0) {
				JOptionPane.showMessageDialog(null, "Nhập sai dữ liệu lương (Phải lớn hơn 0)");
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Nhập sai kiểu dữ liệu lương (phải nhập số)");
			return true;
		}
	}

	public boolean kiemTraSoDouble(JTextField txtTuoi2) {
		try {
			double x = Double.parseDouble(txtTuoi2.getText());
			if (x < 0) {
				JOptionPane.showMessageDialog(null, "Nhập sai dữ liệu lương (Phải lớn hơn 0)");
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Nhập sai kiểu dữ liệu lương (phải nhập số)");
			return true;
		}
	}

	public boolean RegexSDT(JTextField txtSDT) {
		String input = txtSDT.getText();
		String regex = "^[0][0-9]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập sai số điện thoại (Ví dụ nhập:0987654321");
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return true;
		} else
			return false;
	}

	public boolean RegexMaThuoc(JTextField txt) {
		String input = txt.getText();
		String regex = "^T[0-9]{4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập sai mã thuốc (Ví dụ nhập:T0000");
			txt.requestFocus();
			txt.selectAll();
			return true;
		} else
			return false;
	}

	public boolean RegexTenThuoc(JTextField txtDiaChi2) {
		String input = txtDiaChi2.getText();
		String regex = "^([A-Za-z0-9(),a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Nhập tên thuốc");
			txtDiaChi2.requestFocus();
			txtDiaChi2.selectAll();
			return true;
		} else
			return false;
	}

	public boolean RegexMaLoai(String maLoai) {
		String regex = "^LT[0-9]{3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(maLoai);
		return matcher.find();
	}

	public boolean RegexTenLoaiThuoc(String nameFull) {
		String regex = "^([A-Z][A-Za-za-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]*(\\s?))+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nameFull);
		return matcher.find();
	}

	public boolean RegexMaNuoc(String maLoai) {
		String regex = "^N[0-9]{3}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(maLoai);
		return matcher.find();
	}

	public boolean RegexTenNuoc(String tenNuoc) {
		String regex = "^[A-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐ][a-záàảãạâấầẩẫậăắằẳẵặéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđ]*(\\s[A-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ][a-záàảãạâấầẩẫậăắằẳẵặéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđ]*)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tenNuoc);
		return matcher.find();
	}
}
