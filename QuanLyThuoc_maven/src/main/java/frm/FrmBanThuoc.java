package frm;

import java.awt.Color;

import javax.swing.JPanel;

import chucNang.ChucNang;
import chucNang.RoundedPanel;
import dao.CTHoaDon_DAO;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiThuocDao;
import dao.NhaCungCapDao;
import dao.NuocDao;
import dao.ThuocDao;
//import dao.impl.CTHoaDonImpl;
//import dao.impl.HoaDonDaoImpl;
//import dao.impl.KhachHangDaoImpl;
//import dao.impl.LoaiThuocDaoImpl;
//import dao.impl.NhaCungCapDaoImpl;
//import dao.impl.NuocDaoImpl;
//import dao.impl.ThuocDaoImpl;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.NuocSX;
import entity.Thuoc;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import app.App;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

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
	private KhachHang kh;
	private JLabel lblTenKH;
	private JLabel lblSDTKH;
	private JLabel lblDiaChiKH;
	private List<Thuoc> listThuocChon;
	private List<String> listMaThuocChon;
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
	private List<LoaiThuoc> listLoai;
	private List<NuocSX> listNuoc;
	private List<NhaCungCap> listNcc;
	private List<String> listMaLoaiChon;
	private List<String> listMaNccChon;
	private List<String> listMaNuocChon;
	private JComboBox cmbLoaiThuoc;
	private JComboBox cmbNuoc;
	private JComboBox cmbNCC;
	private ArrayList<ChiTietHoaDon> listChiTietHoaDon;
	private ArrayList<Thuoc> listThuocMua;
	private HoaDon hoadon;
	private JComboBox cmbTimKiem;

	/**
	 * Create the panel.
	 */
	public FrmBanThuoc() {
		kh = null;
		hoadon = new HoaDon(LocalDate.now(), new NhanVien("NV21110001"), kh);

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
		pnlNgang.setBounds(0, 0, 1600, 933);
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
				suKienTimKiemSanPham();
			}
		});
		txtTimKiemSP.setMargin(new Insets(2, 14, 2, 2));
		txtTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTimKiemSP.setColumns(10);
		txtTimKiemSP.setBounds(237, 11, 524, 40);
		pnlChonSP.add(txtTimKiemSP);

		cmbTimKiem = new JComboBox();
		cmbTimKiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] { "Tìm theo tên", "Tìm theo mã" }));
		cmbTimKiem.setBounds(771, 11, 273, 40);
		pnlChonSP.add(cmbTimKiem);

		cmbLoaiThuoc = new JComboBox();
		cmbLoaiThuoc.setModel(new DefaultComboBoxModel(new String[] { "Loại Thuốc" }));
		cmbLoaiThuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbLoaiThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbLoaiThuoc.setBounds(10, 62, 290, 40);
		pnlChonSP.add(cmbLoaiThuoc);

		cmbNuoc = new JComboBox();
		cmbNuoc.setModel(new DefaultComboBoxModel(new String[] { "Nước Sản Xuất" }));
		cmbNuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNuoc.setBounds(310, 62, 290, 40);
		pnlChonSP.add(cmbNuoc);

		cmbNCC = new JComboBox();
		cmbNCC.setModel(new DefaultComboBoxModel(new String[] { "Nhà Cung Cấp" }));
		cmbNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNCC.setBounds(610, 62, 290, 40);
		pnlChonSP.add(cmbNCC);

		JButton btnLoc = new JButton("Lọc");
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suKienTimKiemSanPham();
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
						|| (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_MINUS)))
					e.consume();
			}
		});
		txtNhapSoLuong.setHorizontalAlignment(SwingConstants.TRAILING);
		txtNhapSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suKienThemVaoGioHang();
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
				suKienThemVaoGioHang();
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
		String headerTitle[] = { "Tên thuốc", "Ngày sản xuất", "Hạn sử dụng", "Số lượng", "Đơn giá" };
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
		JLabel lblNhapKH = new JLabel("Nhập số điện thoại khách hàng:");
		lblNhapKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhapKH.setBounds(1074, 11, 510, 40);
		pnlNgang.add(lblNhapKH);

		JButton btnTimKiemKH = new JButton("Tìm kiếm");
		btnTimKiemKH.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTimKiemKH.setForeground(Color.WHITE);
		btnTimKiemKH.setBackground(new Color(20, 140, 255));
		btnTimKiemKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTimKiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suKienTimKiemKhachHang();
			}
		});
		btnTimKiemKH.setBounds(1434, 49, 140, 40);
		pnlNgang.add(btnTimKiemKH);

		txtTimKiemKH = new JTextField();
		txtTimKiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suKienTimKiemKhachHang();
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

		lblTenKH = new JLabel("");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenKH.setBounds(174, 17, 316, 40);
		pnlThongTinKH.add(lblTenKH);

		lblSDTKH = new JLabel("");
		lblSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDTKH.setBounds(149, 74, 341, 40);
		pnlThongTinKH.add(lblSDTKH);

		lblDiaChiKH = new JLabel("");
		lblDiaChiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChiKH.setBounds(95, 131, 395, 40);
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
				if (kh == null)
					JOptionPane.showMessageDialog(null, "Hãy tìm kiếm khách hàng trước");
				else {
					KhachHang khachhang;
					try {
						khachhang = App.khDao.getKhachHangTheoMa(kh.getMaKH());
						new FrmCapNhatThongTinKH(khachhang).setVisible(true);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
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
		String headerTitle1[] = { "STT", "Tên thuốc", "Số lượng", "Đơn giá", "Thành tiền" };
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
				else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE)
						|| (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_PERIOD))) {
					e.consume();
				}
			}
		});
		txtKHTra.setHorizontalAlignment(SwingConstants.TRAILING);
		txtKHTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suKienThanhToan();
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
		JButton btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suKienThanhToan();
			}
		});
		btnThanhToan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnThanhToan.setBackground(new Color(20, 140, 255));
		btnThanhToan.setBounds(1074, 735, 500, 75);
		pnlNgang.add(btnThanhToan);

		JButton btnHuyBo = new JButton("Làm Mới");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtKHTra.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa thanh toán hóa đơn");
				} else {
					kh = null;
					hoadon = new HoaDon(LocalDate.now(), new NhanVien("NV21110001"), kh);
					ChucNang.clearDataTable(modelGioHang);
					try {
						listThuocChon = App.thuocDao.getdsThuoc();
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					loadThongTinVaoTableSanPham(listThuocChon);
					cmbLoaiThuoc.setSelectedIndex(0);
					cmbNCC.setSelectedIndex(0);
					cmbNuoc.setSelectedIndex(0);
					txtTongThanhTien.setText("");
					txtNhapSoLuong.setText("");
					txtKHTra.setText("");
					txtTienTraLai.setText("");
					txtTimKiemSP.setText("");
					txtTimKiemKH.setText("");
					lblTenKH.setText("");
					lblDiaChiKH.setText("");
					lblSDTKH.setText("");
					listChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
					listThuocMua = new ArrayList<Thuoc>();
				}
			}
		});
		btnHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuyBo.setForeground(Color.WHITE);
		btnHuyBo.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnHuyBo.setBackground(new Color(248, 96, 96));
		btnHuyBo.setBounds(1074, 821, 500, 75);
		pnlNgang.add(btnHuyBo);

		try {
			listThuocChon = App.thuocDao.getdsThuoc();
			listLoai = App.loaiDao.getdsLoaiThuoc();
			listNcc = App.nccDao.getdsNhaCungCap();
			listNuoc = App.nuocDao.getdsNuocSX();
			listMaThuocChon = new ArrayList<String>();
			listMaLoaiChon = new ArrayList<String>();
			listMaNccChon = new ArrayList<String>();
			listMaNuocChon = new ArrayList<String>();
			listChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
			listThuocMua = new ArrayList<Thuoc>();
			loadThongTinVaoTableSanPham(listThuocChon);
			loadThongTinVaoCmbLoai(listLoai);
			loadThongTinVaoCmbNCC(listNcc);
			loadThongTinVaoCmbNuoc(listNuoc);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Sự kiện tìm kiếm sản phẩm
	 */
	private void suKienTimKiemSanPham() {
		String data = txtTimKiemSP.getText();
		// Tim kiem theo ma san pha,
		if (cmbTimKiem.getSelectedItem().equals("Tìm theo mã")) {
			try {
				Thuoc thuoc = App.thuocDao.getThuocTheoMa(data);
				if (thuoc == null) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
					loadThongTinVaoTableSanPham(listThuocChon);
				} else {
					ChucNang.clearDataTable(modelChonSP);
					load1ThongTinThuoc(thuoc);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			// Tim kiem theo ten san pham
		} else if (cmbTimKiem.getSelectedItem().equals("Tìm theo tên")) {
			try {
				int indexLoai = cmbLoaiThuoc.getSelectedIndex();
				String maloai = indexLoai == 0 ? "" : listMaLoaiChon.get(indexLoai - 1);
				int indexNCC = cmbNCC.getSelectedIndex();
				String maNCC = indexNCC == 0 ? "" : listMaNccChon.get(indexNCC - 1);
				int indexNuoc = cmbNuoc.getSelectedIndex();
				String maNuoc = indexNuoc == 0 ? "" : listMaNuocChon.get(indexNuoc - 1);
				List<Thuoc> list = App.thuocDao.getdsThuocTheoTenNccNuocLoai(data, maNCC, maNuoc, maloai);
				System.out.println(maloai + maNCC + maNuoc + list);
				if (list == null) {
					JOptionPane.showMessageDialog(null, "Không tìm thấy");
					loadThongTinVaoTableSanPham(listThuocChon);
				} else {
					loadThongTinVaoTableSanPham(list);
					if (listMaThuocChon.size() == 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy");
						loadThongTinVaoTableSanPham(listThuocChon);
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Sự kiện tìm kiếm khách hàng
	 */
	private void suKienTimKiemKhachHang() {
		String data = txtTimKiemKH.getText();
		try {
			kh = App.khDao.getKhachHangTheoMaVaSDT(data);
			if (kh == null) {
				JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
				txtTimKiemKH.requestFocus();
			} else {
				lblTenKH.setText(kh.getHoTen());
				lblSDTKH.setText(kh.getSDT());
				lblDiaChiKH.setText(kh.getDiaChi());
				hoadon.setKh(kh);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * sự kiện thanh toán tiền
	 */
	private void suKienThanhToan() {

		try {
			if (txtKHTra.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn chưa nhập tiền khách hàng trả");
			} else {
				double tongthangtien = Double.parseDouble(txtTongThanhTien.getText());
				double khachhangtra = Double.parseDouble(txtKHTra.getText());
				if (khachhangtra < tongthangtien) { // Nếu tiền của khách hàng trả không đủ
					JOptionPane.showMessageDialog(null, "Số tiền khách hàng trả chưa đủ");
					txtKHTra.requestFocus();
				} else { // Nếu tiền khách hàng trả đủ
					if (txtTienTraLai.getText().equals("")) {
						if (kh == null) {
							JOptionPane.showMessageDialog(null, "Bạn hãy cho biết khách hàng nào đang mua");
							txtTimKiemKH.requestFocus();
						} else {
							double tientra = khachhangtra - tongthangtien;
							String thanhtien = (tientra % 1) == 0 ? ((int) tientra) + "" : tientra + "";
							txtTienTraLai.setText(thanhtien);
							hoadon.setCtHD(listChiTietHoaDon);
							hoadon.setKh(kh);
							// Cập nhật dữ liêu vào sql
							try {
								App.hdDao.themHoaDon(hoadon);
								for (Thuoc thuoc : listThuocMua) {
									App.thuocDao.capNhatThuoc(thuoc);
								}
								for (ChiTietHoaDon ct : listChiTietHoaDon) {
									App.cthdDao.themChiTietHoaDon(ct);
								}
								JOptionPane.showMessageDialog(null, "Thanh toán thành công");
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán");
					}
				}
			}
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập số tiền khách hàng trả");
			txtKHTra.requestFocus();
		}
	}

	/**
	 * Sự kiện thêm vào giỏ hàng
	 */
	private void suKienThemVaoGioHang() {
		int count = tableChonSP.getSelectedRowCount();
		if (count == 0) { // Chưa chọn sản phẩm cần thêm vào giỏ hàng
			JOptionPane.showMessageDialog(null, "Hãy chọn sản phẩm để thêm vào giỏ hàng");
		} else if (count > 1) { // Chọn từ 2 sản phẩm trở lên
			JOptionPane.showMessageDialog(null, "Chỉ cho phép chọn 1 sản phẩm");
		} else { // chọn 1 sản phẩm
			try {
				int soluong = Integer.parseInt(txtNhapSoLuong.getText());
				String ma = listMaThuocChon.get(tableChonSP.getSelectedRow());
				Thuoc thuoc = App.thuocDao.getThuocTheoMa(ma);
				int viTriGioHang = KiemTraGioHang(listChiTietHoaDon, thuoc);
				if (viTriGioHang == -1) { // Nếu trong giỏ hàng chưa có sản phẩm này
					if (soluong > 0) { // số lượng thuốc cần mua lớn hơn 0
						if (thuoc.getSLTon() >= soluong) { // kiểm tra tồn kho có đủ số lượng hay không
							thuoc.setSLTon(thuoc.getSLTon() - soluong);
							ChiTietHoaDon chitiet = new ChiTietHoaDon(hoadon, thuoc, soluong);
							listChiTietHoaDon.add(chitiet);
							listThuocMua.add(thuoc);
							loadThongTinVaoTableGioHang(listChiTietHoaDon);
							modelChonSP.setValueAt(thuoc.getSLTon(), tableChonSP.getSelectedRow(), 3);
						} else { // kho không đủ số lượng
							JOptionPane.showMessageDialog(null, "Không đủ số lượng");
							txtNhapSoLuong.requestFocus();
						}
					} else if (thuoc.getSLTon() == 0) { // Nếu tồn kho = 0
						JOptionPane.showMessageDialog(null, "Thuốc đã hết");
						txtNhapSoLuong.requestFocus();
					} else { // Nếu nhập số lượng thuốc là số âm
						JOptionPane.showMessageDialog(null, "Sản phẩm chưa có trong giỏ hàng để giảm");
						txtNhapSoLuong.requestFocus();
					}
				} else { // Nếu trong giỏ hàng đã có sản phẩm đã chọn
					Thuoc t = listThuocMua.get(viTriGioHang);
					ChiTietHoaDon chitiet = listChiTietHoaDon.get(viTriGioHang);
					if (soluong >= 0) {
						if (t.getSLTon() >= soluong) {
							t.setSLTon(t.getSLTon() - soluong);
							chitiet.setSoLuong(chitiet.getSoLuong() + soluong);
							chitiet.setDonGia();
							listChiTietHoaDon.add(viTriGioHang, chitiet);
							listChiTietHoaDon.remove(viTriGioHang + 1);
							listThuocMua.add(viTriGioHang, t);
							listThuocMua.remove(viTriGioHang + 1);
							loadThongTinVaoTableGioHang(listChiTietHoaDon);
							modelChonSP.setValueAt(t.getSLTon(), tableChonSP.getSelectedRow(), 3);
						} else if (t.getSLTon() == 0) {
							JOptionPane.showMessageDialog(null, "Thuốc đã hết");
							txtNhapSoLuong.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "Không đủ số lượng");
							txtNhapSoLuong.requestFocus();
						}
					} else {
						if ((soluong * -1) <= chitiet.getSoLuong()) {
							t.setSLTon(t.getSLTon() - soluong);
							chitiet.setSoLuong(chitiet.getSoLuong() + soluong);
							chitiet.setDonGia();
							if (chitiet.getSoLuong() == 0) {
								listChiTietHoaDon.remove(viTriGioHang);
								loadThongTinVaoTableGioHang(listChiTietHoaDon);
								listThuocMua.remove(viTriGioHang);
								modelChonSP.setValueAt(t.getSLTon(), tableChonSP.getSelectedRow(), 3);
							} else {
								listChiTietHoaDon.add(viTriGioHang, chitiet);
								listChiTietHoaDon.remove(viTriGioHang + 1);
								listThuocMua.add(viTriGioHang, t);
								listThuocMua.remove(viTriGioHang + 1);
								loadThongTinVaoTableGioHang(listChiTietHoaDon);
								modelChonSP.setValueAt(t.getSLTon(), tableChonSP.getSelectedRow(), 3);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Lỗi! không được trả thuốc nhiều hơn thuốc hiện có trong giỏ hàng");
							txtNhapSoLuong.requestFocus();
						}
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Không được để rỗng số lượng");
				txtNhapSoLuong.requestFocus();
			}

		}
		double tongthanhtien = 0;
		for (ChiTietHoaDon chiTietHoaDon : listChiTietHoaDon) {
			tongthanhtien += chiTietHoaDon.getDonGia();
		}
		String thanhtien = (tongthanhtien % 1) == 0 ? ((int) tongthanhtien) + "" : tongthanhtien + "";
		txtTongThanhTien.setText(thanhtien);
	}

	/**
	 * kiểm tra thuốc có nằm trong giỏ hàng hay không
	 * 
	 * @param list
	 * @param t
	 * @return int: vị trí thuốc nằm trong giỏ hàng
	 */
	private int KiemTraGioHang(List<ChiTietHoaDon> list, Thuoc t) {
		int index = 0;
		if (list.size() == 0)
			return -1;
		for (ChiTietHoaDon ct : list) {
			if (ct.getThuoc().getMaThuoc().equals(t.getMaThuoc()))
				return index;
			index++;
		}
		return -1;
	}

	/**
	 * Load thông tin vào JComboBox Nước sản xuất
	 * 
	 * @param list
	 */
	private void loadThongTinVaoCmbNuoc(List<NuocSX> list) {
		for (NuocSX nuocSX : list) {
			cmbNuoc.addItem(nuocSX.getTenNuoc());
			listMaNuocChon.add(nuocSX.getIdNuoc());
		}
	}

	/**
	 * Load thông tin vào JComboBox nhà cung cấp
	 * 
	 * @param list
	 */
	private void loadThongTinVaoCmbNCC(List<NhaCungCap> list) {
		for (NhaCungCap nhaCungCap : list) {
			cmbNCC.addItem(nhaCungCap.getTenNCC());
			listMaNccChon.add(nhaCungCap.getMaNCC());
		}
	}

	/**
	 * Load thông tin vào JComboBox Loại thuốc
	 * 
	 * @param list
	 */
	private void loadThongTinVaoCmbLoai(List<LoaiThuoc> list) {
		for (LoaiThuoc loaiThuoc : list) {
			cmbLoaiThuoc.addItem(loaiThuoc.getTenLoai());
			listMaLoaiChon.add(loaiThuoc.getMaLoai());
		}
	}

	/**
	 * Load thông tin từ bảng sản phẩm vào bảng giỏ hàng(load chi tiết hóa đơn)
	 * 
	 * @param list
	 */
	private void loadThongTinVaoTableGioHang(List<ChiTietHoaDon> list) {
		ChucNang.clearDataTable(modelGioHang);
		int stt = 1;
		for (ChiTietHoaDon ct : list) {
			load1ThongTinChiTietHoaDon(ct, stt);
			stt++;
		}
	}

	/**
	 * load 1 thông tin chi tiết hóa đơn
	 * 
	 * @param ct
	 * @param stt
	 */
	private void load1ThongTinChiTietHoaDon(ChiTietHoaDon ct, int stt) {
		String[] n = { stt + "", ct.getThuoc().getTenThuoc(), ct.getSoLuong() + "",
				vnFormat.format(ct.getThuoc().getDonGia()), vnFormat.format(ct.getDonGia()) };
		modelGioHang.addRow(n);
	}

	/**
	 * load thông tin vào bảng sản phẩm thuốc
	 * 
	 * @param list
	 */
	private void loadThongTinVaoTableSanPham(List<Thuoc> list) {
		ChucNang.clearDataTable(modelChonSP);
		List<String> l = new ArrayList<String>();
		for (Thuoc thuoc : list) {
			load1ThongTinThuoc(thuoc);
			l.add(thuoc.getMaThuoc());
		}
		listMaThuocChon = l;
	}

	/**
	 * load 1 thông tin sản phẩm vào bảng
	 * 
	 * @param thuoc
	 */
	private void load1ThongTinThuoc(Thuoc thuoc) {
		String n[] = { thuoc.getTenThuoc(), dtf.format(thuoc.getNgaySX()), dtf.format(thuoc.getHanSuDung()),
				thuoc.getSLTon() + "", vnFormat.format(thuoc.getDonGia()) };
		modelChonSP.addRow(n);
	}

}
