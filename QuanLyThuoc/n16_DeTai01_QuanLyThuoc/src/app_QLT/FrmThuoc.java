package app_QLT;


import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
	
	/**
	 * các thuộc tính của bảng ListView
	 */
	private JTable tableThuoc;
	private DefaultTableModel modelThuoc;
	
	/**
	 * Các thuộc tính của phần tổng số thuốc
	 */
	private JTextField txtTongSoThuoc;
	
	/**
	 * Các thuộc tính trình trạng
	 */
	private JRadioButton radAll;
	private JRadioButton radConHang;
	private JRadioButton radHetHang;
	
	/**
	 * Các thuôc tính tìm kiếm
	 */
	private JComboBox<String> cboLoaiTimKiem;
	private JTextField txtThongTinTimKiem;
	private JButton btnTim;

	public FrmThuoc() {

		setLayout(null);

		/**
		 * phần nhập thông tin thuốc
		 */
			// Phần JLable
		JLabel lblMaThuoc = new JLabel("Mã thuốc:");
		lblMaThuoc.setBounds(30, 30, 100, 30);
		add(lblMaThuoc);

		JLabel lblTenThuoc = new JLabel("Tên thuốc:");
		lblTenThuoc.setBounds(30, 65, 100, 30);
		add(lblTenThuoc);

		JLabel lblNgaySX = new JLabel("Ngày sản xuất:");
		lblNgaySX.setBounds(30, 100, 100, 30);
		add(lblNgaySX);

		JLabel lblHangSD = new JLabel("Hạng sữ dụng:");
		lblHangSD.setBounds(30, 135, 100, 30);
		add(lblHangSD);

		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setBounds(30, 170, 100, 30);
		add(lblDonGia);

		JLabel lblSLTon = new JLabel("Số lượng tồn:");
		lblSLTon.setBounds(30, 205, 100, 30);
		add(lblSLTon);

		JLabel lblLoai = new JLabel("Loại:");
		lblLoai.setBounds(30, 240, 100, 30);
		add(lblLoai);

			// Phần các JTextField + JComboBox
		txtMaThuoc = new JTextField();
		txtMaThuoc.setBounds(125, 30, 245, 30);
		add(txtMaThuoc);

		txtTenThuoc = new JTextField();
		txtTenThuoc.setBounds(125, 65, 245, 30);
		add(txtTenThuoc);

		txtNgaySX = new JTextField();
		txtNgaySX.setBounds(125, 100, 245, 30);
		add(txtNgaySX);

		txtHangSD = new JTextField();
		txtHangSD.setBounds(125, 135, 245, 30);
		add(txtHangSD);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(125, 170, 245, 30);
		add(txtDonGia);

		txtSLTon = new JTextField();
		txtSLTon.setBounds(125, 205, 245, 30);
		add(txtSLTon);

		cboLoai = new JComboBox<String>();
		cboLoai.setBounds(125, 240, 245, 30);
		add(cboLoai);
		
		

		/**
		 * Phần nhập thông tin nhà cung cấp
		 */
			// Phần JLable
		JLabel lblMaNCC = new JLabel("Mã NCC:");
		lblMaNCC.setBounds(410, 30, 100, 30);
		add(lblMaNCC);

		JLabel lblTenNCC = new JLabel("Tên NCC:");
		lblTenNCC.setBounds(410, 65, 100, 30);
		add(lblTenNCC);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(410, 100, 100, 30);
		add(lblDiaChi);

		JLabel lblIDNuocSX = new JLabel("ID nước SX:");
		lblIDNuocSX.setBounds(410, 135, 100, 30);
		add(lblIDNuocSX);

		JLabel lblTenNuocSX = new JLabel("Tên nước SX:");
		lblTenNuocSX.setBounds(410, 170, 100, 30);
		add(lblTenNuocSX);

			// phần JTextField
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(505, 30, 245, 30);
		add(txtMaNCC);

		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(505, 65, 245, 30);
		add(txtTenNCC);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(505, 100, 245, 30);
		add(txtDiaChi);

		txtIDNuocSX = new JTextField();
		txtIDNuocSX.setBounds(505, 135, 245, 30);
		add(txtIDNuocSX);

		txtTenNuocSX = new JTextField();
		txtTenNuocSX.setBounds(505, 170, 245, 30);
		add(txtTenNuocSX);
		
		

		/**
		 * Phần các Button
		 */
		btnThem = new JButton("Thêm thuốc");
		btnThem.setBounds(150, 295, 105, 35);
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(275, 295, 105, 35);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBounds(400, 295, 105, 35);
		add(btnXoaRong);

		btnSua = new JButton("Sữa");
		btnSua.setBounds(525, 295, 105, 35);
		add(btnSua);
		
		

		/**
		 * Phần listView (Bảng thông tin)
		 */
		String header[] = {"Mã Thuốc", "Tên thuôc", "NSX", "HSD", "Đơn giá", "SLT", "Loại", "Mã NCC", "Tên NCC", "Địa chỉ", "ID Nước", "Tên Nước"};
		modelThuoc = new DefaultTableModel(header, 0);
		tableThuoc = new JTable(modelThuoc);
		JScrollPane thanhCuon = new JScrollPane(tableThuoc);
		thanhCuon.setBounds(20, 355, 740, 110);
		add(thanhCuon);
		
		
		
		/**
		 * phần tổng số thuốc
		 */
		JLabel lblTongSoThuoc = new JLabel("Tổng số thuốc");
		lblTongSoThuoc.setBounds(460, 485, 100, 30);
		add(lblTongSoThuoc);
		txtTongSoThuoc = new JTextField();
		txtTongSoThuoc.setEditable(false);
		txtTongSoThuoc.setBounds(560, 485, 200, 30);
		add(txtTongSoThuoc);
		
		

		/**
		 * Phần tình trang thuốc
		 */
		radAll = new JRadioButton("Tất cả");
		radAll.setSelected(true);
		radAll.setBounds(60, 540, 130, 30);
		add(radAll);
		radConHang = new JRadioButton("Còn hạng sữ dụng");
		radConHang.setBounds(60, 570, 130, 30);
		add(radConHang);
		radHetHang = new JRadioButton("Hết hạng sữ dụng");
		radHetHang.setBounds(60, 600, 130, 30);
		add(radHetHang);
		ButtonGroup groupRad = new ButtonGroup();
		groupRad.add(radAll);
		groupRad.add(radConHang);
		groupRad.add(radHetHang);
		
		

		/**
		 * Phần tìm kiếm theo tên và mã thuốc
		 */
			// Phần title tìm kiếm
		JLabel lblLoaiTimKiem = new JLabel("Chọn loai tìm kiếm:");
		lblLoaiTimKiem.setBounds(285, 540, 140, 30);
		add(lblLoaiTimKiem);
		JLabel lblNhapTim = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhapTim.setBounds(285, 585, 140, 30);
		add(lblNhapTim);
			// Phần txt và combobox
		cboLoaiTimKiem = new JComboBox<String>();
		cboLoaiTimKiem.setBounds(435, 540, 320, 30);
		add(cboLoaiTimKiem);
		txtThongTinTimKiem = new JTextField();
		txtThongTinTimKiem.setBounds(435, 585, 210, 30);
		add(txtThongTinTimKiem);
		btnTim = new JButton("Tìm");
		btnTim.setBounds(655, 585, 100, 30);
		add(btnTim);
		
		
		
		/**
		 * thêm khung border title cho các phần
		 */
			// phần Nhập thông tin Thuốc
		JPanel pThuoc = new JPanel();
		pThuoc.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc"));
		pThuoc.setBounds(15, 10, 370, 275);
		add(pThuoc);

			// Phần nhập thông tin nhà cung cấp
		JPanel pNhaCC = new JPanel();
		pNhaCC.setBorder(BorderFactory.createTitledBorder("Nhập thông tin nhà cung cấp"));
		pNhaCC.setBounds(395, 10, 370, 275);
		add(pNhaCC);

			// Phần ListView
		JPanel pThongTin = new JPanel();
		pThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin"));
		pThongTin.setBounds(10, 335, 760, 145);
		add(pThongTin);
		
			// phần Trình trạng thuốc
		JPanel pTrinhTrang = new JPanel();
		pTrinhTrang.setBorder(BorderFactory.createTitledBorder("Trình trạng thuốc"));
		pTrinhTrang.setBounds(10, 515, 250, 135);
		add(pTrinhTrang);
			// Phần tìm kiếm thuốc theo mã và theo tên
		JPanel pTimKiem = new JPanel();
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo tên/mã thuốc"));
		pTimKiem.setBounds(270, 515, 500, 135);
		add(pTimKiem);
		
	}
}
