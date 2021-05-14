package app_QLT;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmThuoc extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Các thuộc tính của Phần Nhập thông tin Thuốc
	 */
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JTextField txtNgaySX;
	private JTextField txtHangSD;
	private JTextField txtDonGia;
	private JTextField txtSLTon;
	private JComboBox<String> cboLoai;

	/**
	 * Các thuộc tính của phần Nhập thông tin nhà cung cấp
	 */
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtDiaChi;
	private JTextField txtIDNuocSX;
	private JTextField txtTenNuocSX;

	/**
	 * Các thuộc tính của các nút
	 */
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnXoaRong;

	public FrmThuoc() {

		setLayout(null);

		/**
		 * phần nhập thông tin thuốc
		 */
		// Phần JLable
		JLabel lblMaThuoc = new JLabel("Mã thuốc:");
		lblMaThuoc.setBounds(30, 40, 100, 30);
		add(lblMaThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setBounds(30, 80, 100, 30);
		add(lblTenThuoc);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setBounds(30, 120, 100, 30);
		add(lblNgaySX);

		JLabel lblHangSD = new JLabel("Hạng sữ dụng:");
		lblHangSD.setBounds(30, 160, 100, 30);
		add(lblHangSD);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(30, 200, 100, 30);
		add(lblDonGia);

		JLabel lblSLTon = new JLabel("Số lượng tồn:");
		lblSLTon.setBounds(30, 240, 100, 30);
		add(lblSLTon);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(30, 280, 100, 30);
		add(lblLoai);

		// Phần các JTextField + JComboBox
		txtMaThuoc = new JTextField();
		txtMaThuoc.setBounds(140, 40, 230, 30);
		add(txtMaThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setBounds(140, 80, 230, 30);
		add(txtTenThuoc);

		txtNgaySX = new JTextField();
		txtNgaySX.setBounds(140, 120, 230, 30);
		add(txtNgaySX);

		txtHangSD = new JTextField();
		txtHangSD.setBounds(140, 160, 230, 30);
		add(txtHangSD);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(140, 200, 230, 30);
		add(txtDonGia);

		txtSLTon = new JTextField();
		txtSLTon.setBounds(140, 240, 230, 30);
		add(txtSLTon);

		cboLoai = new JComboBox<String>();
		cboLoai.setBounds(140, 280, 230, 30);
		add(cboLoai);

		/**
		 * Phần nhập thông tin nhà cung cấp
		 */
		// Phần JLable
		JLabel lblMaNCC = new JLabel("Mã NCC:");
		lblMaNCC.setBounds(410, 40, 100, 30);
		add(lblMaNCC);

		JLabel lblTenNCC = new JLabel("Tên NCC:");
		lblTenNCC.setBounds(410, 80, 100, 30);
		add(lblTenNCC);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(410, 120, 100, 30);
		add(lblDiaChi);

		JLabel lblIDNuocSX = new JLabel("ID nước SX:");
		lblIDNuocSX.setBounds(410, 160, 100, 30);
		add(lblIDNuocSX);

		JLabel lblTenNuocSX = new JLabel("Tên nước SX:");
		lblTenNuocSX.setBounds(410, 200, 100, 30);
		add(lblTenNuocSX);

		// phần JTextField
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(520, 40, 230, 30);
		add(txtMaNCC);

		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(520, 80, 230, 30);
		add(txtTenNCC);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(520, 120, 230, 30);
		add(txtDiaChi);

		txtIDNuocSX = new JTextField();
		txtIDNuocSX.setBounds(520, 160, 230, 30);
		add(txtIDNuocSX);

		txtTenNuocSX = new JTextField();
		txtTenNuocSX.setBounds(520, 200, 230, 30);
		add(txtTenNuocSX);

		/**
		 * Phần các Button
		 */
		btnThem = new JButton("Thêm thuốc");
		btnThem.setBounds(150, 350, 110, 40);
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(275, 350, 110, 40);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(400, 350, 110, 40);
		add(btnXoaRong);

		btnSua = new JButton("Sữa");
		btnSua.setBounds(525, 350, 110, 40);
		add(btnSua);

		/**
		 * Phần listView (Bảng thông tin)
		 */

		/**
		 * Phần chức năng hiển thị tổng số thuốc + các chức năng tìm kiếm
		 */

		/**
		 * thêm khung border title cho các phần
		 */
		// phần Nhập thông tin Thuốc
		JPanel pThuoc = new JPanel();
		pThuoc.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc"));
		pThuoc.setBounds(15, 10, 370, 320);
		add(pThuoc);

		// Phần nhập thông tin nhà cung cấp
		JPanel pNhaCC = new JPanel();
		pNhaCC.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhà cung cấp:"));
		pNhaCC.setBounds(395, 10, 370, 320);
		add(pNhaCC);

		// Phần ListView
		JPanel pThongTin = new JPanel();
		pThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin:"));
		pThongTin.setBounds(10, 400, 760, 320);
		add(pThongTin);
	}
}
