package app_QLT;

import java.awt.Color;

import javax.swing.JPanel;

import chucNang.RoundedPanel;
import entity.KhachHang;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmBanThuoc extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5384818405392700507L;
	private JTextField txtTimKiemKH;
	private JTextField txtTimKiemSP;
	private JTextField txtNhapSoLuong;
	private JTable tableChonSP;
	private JTable tableGioHang;
	private JTextField txtTongThanhTien;
	private JTextField txtKHTra;
	private JTextField txtTienTraLai;
	private DefaultTableModel modelChonSP;
	private DefaultTableModel modelGioHang;

	/**
	 * Create the panel.
	 */
	public FrmBanThuoc() {
		setSize(1600, 911);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		/*
		 * JPanel Main
		 */
		JPanel pnlNgang = new JPanel();
		pnlNgang.setBounds(new Rectangle(10, 10, 10, 10));
		pnlNgang.setLayout(null);
		pnlNgang.setBackground(Color.WHITE);
		pnlNgang.setBounds(0, 0, 1600, 910);
		add(pnlNgang);
		
		
		/*
		 * JPanel chọn sản phẩm
		 */
		RoundedPanel pnlChonSP = new RoundedPanel();
		pnlChonSP.setShady(false);
		pnlChonSP.setBackground(Color.WHITE);
		pnlChonSP.setBounds(10, 11, 1054, 400);
		pnlNgang.add(pnlChonSP);
		pnlChonSP.setLayout(null);
		
		txtTimKiemSP = new JTextField();
		txtTimKiemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		txtTimKiemSP.setMargin(new Insets(2, 14, 2, 2));
		txtTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemSP.setColumns(10);
		txtTimKiemSP.setBounds(237, 11, 524, 40);
		pnlChonSP.add(txtTimKiemSP);
		
		JComboBox cmbTimKiem = new JComboBox();
		cmbTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"Tìm theo tên", "Tìm theo mã"}));
		cmbTimKiem.setBounds(771, 11, 273, 40);
		pnlChonSP.add(cmbTimKiem);
		
		JComboBox cmbLoaiThuoc = new JComboBox();
		cmbLoaiThuoc.setModel(new DefaultComboBoxModel(new String[] {"Loại Thuốc"}));
		cmbLoaiThuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbLoaiThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbLoaiThuoc.setBounds(10, 62, 290, 40);
		pnlChonSP.add(cmbLoaiThuoc);
		
		JComboBox cmbNuoc = new JComboBox();
		cmbNuoc.setModel(new DefaultComboBoxModel(new String[] {"Nước Sản Xuất"}));
		cmbNuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNuoc.setBounds(310, 62, 290, 40);
		pnlChonSP.add(cmbNuoc);
		
		JComboBox cmbNCC = new JComboBox();
		cmbNCC.setModel(new DefaultComboBoxModel(new String[] {"Nhà Cung Cấp"}));
		cmbNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNCC.setBounds(610, 62, 290, 40);
		pnlChonSP.add(cmbNCC);
		
		JButton btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoc.setForeground(Color.WHITE);
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoc.setBackground(new Color(20, 140, 255));
		btnLoc.setBounds(910, 62, 134, 40);
		pnlChonSP.add(btnLoc);
		
		txtNhapSoLuong = new JTextField();
		txtNhapSoLuong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (txtNhapSoLuong.getText().length() >= 3)
					e.consume();
				else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE)
						|| (vChar == KeyEvent.VK_DELETE)))
					e.consume();
			}
		});
		txtNhapSoLuong.setHorizontalAlignment(SwingConstants.TRAILING);
		txtNhapSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtNhapSoLuong.setMargin(new Insets(2, 14, 2, 14));
		txtNhapSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNhapSoLuong.setColumns(10);
		txtNhapSoLuong.setBounds(584, 349, 200, 39);
		pnlChonSP.add(txtNhapSoLuong);
		
		JButton btnThemVaoGioHang = new JButton("Thêm vào giỏ hàng");
		btnThemVaoGioHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThemVaoGioHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThemVaoGioHang.setForeground(Color.WHITE);
		btnThemVaoGioHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThemVaoGioHang.setBackground(new Color(20, 140, 255));
		btnThemVaoGioHang.setBounds(794, 348, 250, 40);
		pnlChonSP.add(btnThemVaoGioHang);
		
		
		/*
		 * Table Giỏ Hàng
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 1034, 231);
		pnlChonSP.add(scrollPane);
		
		tableChonSP = new JTable();
		String headerTitle[] = { "Tên thuốc", "Loại thuốc", "Nước sản xuất", "Ngày sản xuất", "Hạn sử dụng", "Số lượng", "Đơn giá" };
		// Model Table
		modelChonSP = new DefaultTableModel(headerTitle, 50) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				default:
					return false;
				}
			}

		};
		// Table
		tableChonSP = new JTable(modelChonSP);
		tableChonSP.setRowHeight(35); // set height items
		tableChonSP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableChonSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Set Font title table
		JTableHeader headerTable = tableChonSP.getTableHeader();
		headerTable.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTable.setBackground(new Color(248, 198, 153));
		scrollPane.setViewportView(tableChonSP);
		scrollPane.setEnabled(false);
		scrollPane.setViewportView(tableChonSP);
		
		JLabel lblNhpThngTin = new JLabel("Nhập thông tin cần tìm:");
		lblNhpThngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpThngTin.setBounds(10, 10, 217, 40);
		pnlChonSP.add(lblNhpThngTin);
		
		JLabel lblNhpSLng = new JLabel("Nhập số lượng:");
		lblNhpSLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpSLng.setBounds(434, 348, 140, 40);
		pnlChonSP.add(lblNhpSLng);
		
		/*
		 * Phần khách hàng
		 */
		JLabel lblNhapKH = new JLabel("Nhập số điện thoại hoặc CMND của khách hàng:");
		lblNhapKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhapKH.setBounds(1074, 11, 510, 40);
		pnlNgang.add(lblNhapKH);
		
		JButton btnTimKiemKH = new JButton("Tìm kiếm");
		btnTimKiemKH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTimKiemKH.setForeground(Color.WHITE);
		btnTimKiemKH.setBackground(new Color(20,140,255));
		btnTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTimKiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTimKiemKH.setBounds(1434, 49, 140, 40);
		pnlNgang.add(btnTimKiemKH);
		
		txtTimKiemKH = new JTextField();
		txtTimKiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtTimKiemKH.setMargin(new Insets(2, 14, 2, 2));
		txtTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemKH.setBounds(1074, 50, 350, 40);
		pnlNgang.add(txtTimKiemKH);
		txtTimKiemKH.setColumns(10);
		
		RoundedPanel pnlThongTinKH = new RoundedPanel();
		pnlThongTinKH.setShady(false);
		pnlThongTinKH.setLayout(null);
		pnlThongTinKH.setBackground(Color.WHITE);
		pnlThongTinKH.setBounds(1074, 100, 500, 189);
		pnlNgang.add(pnlThongTinKH);
		
		JLabel lblTitleTenKH = new JLabel("Tên khách hàng:");
		lblTitleTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTenKH.setBounds(10, 17, 154, 40);
		pnlThongTinKH.add(lblTitleTenKH);
		
		JLabel lblTitleSDTKH = new JLabel("Số điện thoại:");
		lblTitleSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleSDTKH.setBounds(10, 74, 129, 40);
		pnlThongTinKH.add(lblTitleSDTKH);
		
		JLabel lblTitleDiaChiKH = new JLabel("Địa chỉ:");
		lblTitleDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleDiaChiKH.setBounds(10, 131, 75, 40);
		pnlThongTinKH.add(lblTitleDiaChiKH);
		
		JLabel lblTenKH = new JLabel("Nguyên Công Phượng");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenKH.setBounds(174, 17, 326, 40);
		pnlThongTinKH.add(lblTenKH);
		
		JLabel lblSDTKH = new JLabel("0978459140");
		lblSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDTKH.setBounds(149, 74, 351, 40);
		pnlThongTinKH.add(lblSDTKH);
		
		JLabel lblDiaChiKH = new JLabel("Tân Xuân, Ba Tri, Bên Tre");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChiKH.setBounds(95, 131, 405, 40);
		pnlThongTinKH.add(lblDiaChiKH);
		
		JButton btnThmThngTin = new JButton("Thêm thông tin khách hàng");
		btnThmThngTin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmThemThongTinKH().setVisible(true);
			}
		});
		btnThmThngTin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThmThngTin.setForeground(Color.WHITE);
		btnThmThngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThmThngTin.setBackground(new Color(20, 140, 255));
		btnThmThngTin.setBounds(1074, 300, 500, 50);
		pnlNgang.add(btnThmThngTin);
		
		JButton btnCpNhtThng = new JButton("Cập nhật thông tin khách hàng");
		btnCpNhtThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang("KH001", "Nguyễn Văn Toàn", new Date(2000,4,4), true, "Tân Xuân, Ba Tri, Bến Tre", "0334172541");
				new FrmCapNhatThongTinKH(kh).setVisible(true);
			}
		});
		btnCpNhtThng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCpNhtThng.setForeground(Color.WHITE);
		btnCpNhtThng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCpNhtThng.setBackground(new Color(20, 140, 255));
		btnCpNhtThng.setBounds(1074, 361, 500, 50);
		pnlNgang.add(btnCpNhtThng);
		
		/*
		 * Phần Nhân Viên
		 */
		RoundedPanel pnlThongTInNV = new RoundedPanel();
		pnlThongTInNV.setShady(false);
		pnlThongTInNV.setLayout(null);
		pnlThongTInNV.setBackground(Color.WHITE);
		pnlThongTInNV.setBounds(10, 735, 488, 164);
		pnlNgang.add(pnlThongTInNV);
		
		JLabel lblTitleMSNV = new JLabel("Mã nhân viên:");
		lblTitleMSNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleMSNV.setBounds(10, 11, 134, 40);
		pnlThongTInNV.add(lblTitleMSNV);
		
		JLabel lblTitleTenNV = new JLabel("Tên nhân viên:");
		lblTitleTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTenNV.setBounds(10, 62, 140, 40);
		pnlThongTInNV.add(lblTitleTenNV);
		
		JLabel lblTitleChucVu = new JLabel("Chức vụ:");
		lblTitleChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleChucVu.setBounds(10, 113, 83, 40);
		pnlThongTInNV.add(lblTitleChucVu);
		
		JLabel lblMSNV = new JLabel("NV0001");
		lblMSNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMSNV.setBounds(154, 11, 324, 40);
		pnlThongTInNV.add(lblMSNV);
		
		JLabel lblTenNV = new JLabel("Nguyễn Tiến Linh");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenNV.setBounds(154, 62, 324, 40);
		pnlThongTInNV.add(lblTenNV);
		
		JLabel lblChucVu = new JLabel("Nhân viên bán hàng");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChucVu.setBounds(103, 113, 375, 40);
		pnlThongTInNV.add(lblChucVu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 422, 1564, 302);
		pnlNgang.add(scrollPane_1);
		
		tableGioHang = new JTable();
		String headerTitle1[] = { "Tên thuốc", "Loại thuốc", "Nước sản xuất", "Ngày sản xuất", "Hạn sử dụng", "Số lượng", "Đơn giá", "Thành tiền" };
		// Model Table
		modelGioHang = new DefaultTableModel(headerTitle1, 50) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				switch (col) {
				default:
					return false;
				}
			}

		};
		// Table
		tableGioHang = new JTable(modelGioHang);
		tableGioHang.setRowHeight(35); // set height items
		tableGioHang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableGioHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Set Font title table
		JTableHeader headerTable1 = tableGioHang.getTableHeader();
		headerTable1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTable1.setBackground(new Color(248, 198, 153));
		scrollPane_1.setViewportView(tableGioHang);
		scrollPane_1.setEnabled(false);
		scrollPane_1.setViewportView(tableGioHang);
		
		/*
		 * Phần thanh toán
		 */
		RoundedPanel pnlThanhToan = new RoundedPanel();
		pnlThanhToan.setShady(false);
		pnlThanhToan.setLayout(null);
		pnlThanhToan.setBackground(Color.WHITE);
		pnlThanhToan.setBounds(508, 735, 556, 164);
		pnlNgang.add(pnlThanhToan);
		
		txtTongThanhTien = new JTextField();
		txtTongThanhTien.setHorizontalAlignment(SwingConstants.TRAILING);
		txtTongThanhTien.setEditable(false);
		txtTongThanhTien.setMargin(new Insets(2, 14, 2, 14));
		txtTongThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTongThanhTien.setColumns(10);
		txtTongThanhTien.setBounds(189, 12, 357, 40);
		pnlThanhToan.add(txtTongThanhTien);
		
		txtKHTra = new JTextField();
		txtKHTra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vChar = e.getKeyChar();
				if (txtKHTra.getText().length() >= 9)
					e.consume();
				else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_PERIOD))) {
					e.consume();
				}
			}
		});
		txtKHTra.setHorizontalAlignment(SwingConstants.TRAILING);
		txtKHTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtKHTra.setMargin(new Insets(2, 14, 2, 14));
		txtKHTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKHTra.setColumns(10);
		txtKHTra.setBounds(189, 63, 357, 40);
		pnlThanhToan.add(txtKHTra);
		
		txtTienTraLai = new JTextField();
		txtTienTraLai.setHorizontalAlignment(SwingConstants.TRAILING);
		txtTienTraLai.setEditable(false);
		txtTienTraLai.setMargin(new Insets(2, 14, 2, 14));
		txtTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTienTraLai.setColumns(10);
		txtTienTraLai.setBounds(189, 114, 357, 40);
		pnlThanhToan.add(txtTienTraLai);
		
		JLabel lblTitleTongThanhTien = new JLabel("Tổng thành tiền:");
		lblTitleTongThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTongThanhTien.setBounds(10, 11, 154, 40);
		pnlThanhToan.add(lblTitleTongThanhTien);
		
		JLabel lblKhachHangTra = new JLabel("Khách hàng trả:");
		lblKhachHangTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhachHangTra.setBounds(10, 62, 154, 40);
		pnlThanhToan.add(lblKhachHangTra);
		
		JLabel lblTienTraLai = new JLabel("Tiền trả lại:");
		lblTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTienTraLai.setBounds(10, 113, 154, 40);
		pnlThanhToan.add(lblTienTraLai);
		
		/*
		 * Các nút thanh toán
		 */
		JButton btnThanhToanVaIn = new JButton("Thanh toán và in hóa đơn");
		btnThanhToanVaIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThanhToanVaIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhToanVaIn.setForeground(Color.WHITE);
		btnThanhToanVaIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThanhToanVaIn.setBackground(new Color(20, 140, 255));
		btnThanhToanVaIn.setBounds(1074, 735, 500, 75);
		pnlNgang.add(btnThanhToanVaIn);
		
		JButton btnThanhTon = new JButton("Thanh toán");
		btnThanhTon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnThanhTon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhTon.setForeground(Color.WHITE);
		btnThanhTon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThanhTon.setBackground(new Color(20, 140, 255));
		btnThanhTon.setBounds(1074, 821, 245, 75);
		pnlNgang.add(btnThanhTon);
		
		JButton btnHuyBo = new JButton("Hủy bỏ");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuyBo.setForeground(Color.WHITE);
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHuyBo.setBackground(new Color(248,96,96));
		btnHuyBo.setBounds(1329, 821, 245, 75);
		pnlNgang.add(btnHuyBo);
		

	}
}
