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
	private NhanVien nv;
	private JComboBox cmbTimKiem;
	private JTextField txtMaNV;
	private JLabel lblTenNV;
	private JButton btnTimNV;

	/**
	 * Create the panel.
	 */
	public FrmBanThuoc() {
		kh = null;
		nv = null;
		hoadon = new HoaDon(LocalDate.now(), nv, kh);

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
		 * JPanel ch???n s???n ph???m
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
		cmbTimKiem.setModel(new DefaultComboBoxModel(new String[] { "T??m theo t??n", "T??m theo m??" }));
		cmbTimKiem.setBounds(771, 11, 273, 40);
		pnlChonSP.add(cmbTimKiem);

		cmbLoaiThuoc = new JComboBox();
		cmbLoaiThuoc.setModel(new DefaultComboBoxModel(new String[] { "Lo???i Thu???c" }));
		cmbLoaiThuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbLoaiThuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbLoaiThuoc.setBounds(10, 62, 290, 40);
		pnlChonSP.add(cmbLoaiThuoc);

		cmbNuoc = new JComboBox();
		cmbNuoc.setModel(new DefaultComboBoxModel(new String[] { "N?????c S???n Xu???t" }));
		cmbNuoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNuoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNuoc.setBounds(310, 62, 290, 40);
		pnlChonSP.add(cmbNuoc);

		cmbNCC = new JComboBox();
		cmbNCC.setModel(new DefaultComboBoxModel(new String[] { "Nh?? Cung C???p" }));
		cmbNCC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNCC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNCC.setBounds(610, 62, 290, 40);
		pnlChonSP.add(cmbNCC);

		JButton btnLoc = new JButton("L???c");
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

		JButton btnThemVaoGioHang = new JButton("Th??m v??o gi??? h??ng");
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
		 * Table Gi??? H??ng
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 1034, 231);
		pnlChonSP.add(scrollPane);

		tableChonSP = new JTable();
		String headerTitle[] = { "T??n thu???c", "Ng??y s???n xu???t", "H???n s??? d???ng", "S??? l?????ng", "????n gi??" };
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

		JLabel lblNhpThngTin = new JLabel("Nh???p th??ng tin c???n t??m:");
		lblNhpThngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpThngTin.setBounds(10, 10, 217, 40);
		pnlChonSP.add(lblNhpThngTin);

		JLabel lblNhpSLng = new JLabel("Nh???p s??? l?????ng:");
		lblNhpSLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpSLng.setBounds(434, 348, 140, 40);
		pnlChonSP.add(lblNhpSLng);

		/*
		 * Ph???n kh??ch h??ng
		 */
		JLabel lblNhapKH = new JLabel("Nh???p s??? ??i???n tho???i kh??ch h??ng:");
		lblNhapKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhapKH.setBounds(1074, 11, 510, 40);
		pnlNgang.add(lblNhapKH);

		JButton btnTimKiemKH = new JButton("T??m ki???m");
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

		JLabel lblTitleTenKH = new JLabel("T??n kh??ch h??ng:");
		lblTitleTenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTenKH.setBounds(10, 17, 154, 40);
		pnlThongTinKH.add(lblTitleTenKH);

		JLabel lblTitleSDTKH = new JLabel("S??? ??i???n tho???i:");
		lblTitleSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleSDTKH.setBounds(10, 74, 129, 40);
		pnlThongTinKH.add(lblTitleSDTKH);

		JLabel lblTitleDiaChiKH = new JLabel("?????a ch???:");
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

		JButton btnThmThngTin = new JButton("Th??m th??ng tin kh??ch h??ng");
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

		JButton btnCpNhtThng = new JButton("C???p nh???t th??ng tin kh??ch h??ng");
		btnCpNhtThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (kh == null)
					JOptionPane.showMessageDialog(null, "H??y t??m ki???m kh??ch h??ng tr?????c");
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
		 * Ph???n Nh??n Vi??n
		 */
		RoundedPanel pnlThongTInNV = new RoundedPanel();
		pnlThongTInNV.setShady(false);
		pnlThongTInNV.setLayout(null);
		pnlThongTInNV.setBackground(Color.WHITE);
		pnlThongTInNV.setBounds(10, 735, 488, 164);
		pnlNgang.add(pnlThongTInNV);

		JLabel lblTitleMSNV = new JLabel("M?? nh??n vi??n:");
		lblTitleMSNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleMSNV.setBounds(10, 28, 134, 40);
		pnlThongTInNV.add(lblTitleMSNV);

		JLabel lblTitleTenNV = new JLabel("T??n nh??n vi??n:");
		lblTitleTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTenNV.setBounds(10, 96, 134, 40);
		pnlThongTInNV.add(lblTitleTenNV);

		lblTenNV = new JLabel("");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTenNV.setBounds(154, 96, 324, 40);
		pnlThongTInNV.add(lblTenNV);
		
		btnTimNV = new JButton("T??m");
		btnTimNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suKienTimKiemNV();
			}
		});
		btnTimNV.setForeground(Color.WHITE);
		btnTimNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTimNV.setBackground(new Color(20, 140, 255));
		btnTimNV.setBounds(378, 28, 100, 40);
		pnlThongTInNV.add(btnTimNV);
		
		txtMaNV = new JTextField();
		txtMaNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				suKienTimKiemNV();
			}
			
		});
		txtMaNV.setMargin(new Insets(2, 6, 2, 2));
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaNV.setBounds(154, 28, 214, 40);
		pnlThongTInNV.add(txtMaNV);
		txtMaNV.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 422, 1564, 302);
		pnlNgang.add(scrollPane_1);

		tableGioHang = new JTable();
		String headerTitle1[] = { "STT", "T??n thu???c", "S??? l?????ng", "????n gi??", "Th??nh ti???n" };
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
		 * Ph???n thanh to??n
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

		JLabel lblTitleTongThanhTien = new JLabel("T???ng th??nh ti???n:");
		lblTitleTongThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitleTongThanhTien.setBounds(10, 11, 154, 40);
		pnlThanhToan.add(lblTitleTongThanhTien);

		JLabel lblKhachHangTra = new JLabel("Kh??ch h??ng tr???:");
		lblKhachHangTra.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhachHangTra.setBounds(10, 62, 154, 40);
		pnlThanhToan.add(lblKhachHangTra);

		JLabel lblTienTraLai = new JLabel("Ti???n tr??? l???i:");
		lblTienTraLai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTienTraLai.setBounds(10, 113, 154, 40);
		pnlThanhToan.add(lblTienTraLai);

		/*
		 * C??c n??t thanh to??n
		 */
		JButton btnThanhToan = new JButton("Thanh To??n");
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

		JButton btnHuyBo = new JButton("L??m M???i");
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtKHTra.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "B???n ch??a thanh to??n h??a ????n");
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
	 * S??? ki???n t??m ki???m nh??n vi??n
	 */
	private void suKienTimKiemNV() {
		String data = txtMaNV.getText();
		if (data.equals("")) {
			JOptionPane.showMessageDialog(null, "B???n h??y nh???p th??ng tin c???n t??m");
			txtMaNV.requestFocus();
		} else {
			try {
				nv = App.nv_dao.getNVTheoMa(data);
				if (nv == null) {
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y nh??n vi??n");
					txtMaNV.requestFocus();
				}
				else {
					lblTenNV.setText(nv.getHoTen());
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * S??? ki???n t??m ki???m s???n ph???m
	 */
	private void suKienTimKiemSanPham() {
		String data = txtTimKiemSP.getText();
		// Tim kiem theo ma san pha,
		if (cmbTimKiem.getSelectedItem().equals("T??m theo m??")) {
			try {
				Thuoc thuoc = App.thuocDao.getThuocTheoMa(data);
				if (thuoc == null) {
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y");
					loadThongTinVaoTableSanPham(listThuocChon);
				} else {
					ChucNang.clearDataTable(modelChonSP);
					load1ThongTinThuoc(thuoc);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			// Tim kiem theo ten san pham
		} else if (cmbTimKiem.getSelectedItem().equals("T??m theo t??n")) {
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
					JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y");
					loadThongTinVaoTableSanPham(listThuocChon);
				} else {
					loadThongTinVaoTableSanPham(list);
					if (listMaThuocChon.size() == 0) {
						JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y");
						loadThongTinVaoTableSanPham(listThuocChon);
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * S??? ki???n t??m ki???m kh??ch h??ng
	 */
	private void suKienTimKiemKhachHang() {
		String data = txtTimKiemKH.getText();
		try {
			kh = App.khDao.getKhachHangTheoMaVaSDT(data);
			if (kh == null) {
				JOptionPane.showMessageDialog(null, "Kh??ng t??m th???y kh??ch h??ng");
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
	 * s??? ki???n thanh to??n ti???n
	 */
	private void suKienThanhToan() {

		try {
			if (txtKHTra.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "B???n ch??a nh???p ti???n kh??ch h??ng tr???");
			} else {
				double tongthangtien = Double.parseDouble(txtTongThanhTien.getText());
				double khachhangtra = Double.parseDouble(txtKHTra.getText());
				if (khachhangtra < tongthangtien) { // N???u ti???n c???a kh??ch h??ng tr??? kh??ng ?????
					JOptionPane.showMessageDialog(null, "S??? ti???n kh??ch h??ng tr??? ch??a ?????");
					txtKHTra.requestFocus();
				} else { // N???u ti???n kh??ch h??ng tr??? ?????
					if (txtTienTraLai.getText().equals("")) {
						if (kh == null) {
							JOptionPane.showMessageDialog(null, "B???n h??y cho bi???t kh??ch h??ng n??o ??ang mua");
							txtTimKiemKH.requestFocus();
						} else if (nv == null) {
							JOptionPane.showMessageDialog(null, "B???n h??y cho bi???t nh??n vi??n n??o ??ang l???p h??a ????n");
							txtMaNV.requestFocus();
						} else {
							double tientra = khachhangtra - tongthangtien;
							String thanhtien = (tientra % 1) == 0 ? ((int) tientra) + "" : tientra + "";
							txtTienTraLai.setText(thanhtien);
							hoadon.setCtHD(listChiTietHoaDon);
							hoadon.setKh(kh);
							hoadon.setNv(nv);
							// C???p nh???t d??? li??u v??o sql
							try {
								App.hdDao.themHoaDon(hoadon);
								for (Thuoc thuoc : listThuocMua) {
									App.thuocDao.capNhatThuoc(thuoc);
								}
								for (ChiTietHoaDon ct : listChiTietHoaDon) {
									App.cthdDao.themChiTietHoaDon(ct);
								}
								JOptionPane.showMessageDialog(null, "Thanh to??n th??nh c??ng");
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "H??a ????n ???? ???????c thanh to??n");
					}
				}
			}
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "B???n ch??a nh???p s??? ti???n kh??ch h??ng tr???");
			txtKHTra.requestFocus();
		}
	}

	/**
	 * S??? ki???n th??m v??o gi??? h??ng
	 */
	private void suKienThemVaoGioHang() {
		int count = tableChonSP.getSelectedRowCount();
		if (count == 0) { // Ch??a ch???n s???n ph???m c???n th??m v??o gi??? h??ng
			JOptionPane.showMessageDialog(null, "H??y ch???n s???n ph???m ????? th??m v??o gi??? h??ng");
		} else if (count > 1) { // Ch???n t??? 2 s???n ph???m tr??? l??n
			JOptionPane.showMessageDialog(null, "Ch??? cho ph??p ch???n 1 s???n ph???m");
		} else { // ch???n 1 s???n ph???m
			try {
				int soluong = Integer.parseInt(txtNhapSoLuong.getText());
				String ma = listMaThuocChon.get(tableChonSP.getSelectedRow());
				Thuoc thuoc = App.thuocDao.getThuocTheoMa(ma);
				int viTriGioHang = KiemTraGioHang(listChiTietHoaDon, thuoc);
				if (viTriGioHang == -1) { // N???u trong gi??? h??ng ch??a c?? s???n ph???m n??y
					if (soluong > 0) { // s??? l?????ng thu???c c???n mua l???n h??n 0
						if (thuoc.getSLTon() >= soluong) { // ki???m tra t???n kho c?? ????? s??? l?????ng hay kh??ng
							thuoc.setSLTon(thuoc.getSLTon() - soluong);
							ChiTietHoaDon chitiet = new ChiTietHoaDon(hoadon, thuoc, soluong);
							listChiTietHoaDon.add(chitiet);
							listThuocMua.add(thuoc);
							loadThongTinVaoTableGioHang(listChiTietHoaDon);
							modelChonSP.setValueAt(thuoc.getSLTon(), tableChonSP.getSelectedRow(), 3);
						} else { // kho kh??ng ????? s??? l?????ng
							JOptionPane.showMessageDialog(null, "Kh??ng ????? s??? l?????ng");
							txtNhapSoLuong.requestFocus();
						}
					} else if (thuoc.getSLTon() == 0) { // N???u t???n kho = 0
						JOptionPane.showMessageDialog(null, "Thu???c ???? h???t");
						txtNhapSoLuong.requestFocus();
					} else { // N???u nh???p s??? l?????ng thu???c l?? s??? ??m
						JOptionPane.showMessageDialog(null, "S???n ph???m ch??a c?? trong gi??? h??ng ????? gi???m");
						txtNhapSoLuong.requestFocus();
					}
				} else { // N???u trong gi??? h??ng ???? c?? s???n ph???m ???? ch???n
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
							JOptionPane.showMessageDialog(null, "Thu???c ???? h???t");
							txtNhapSoLuong.requestFocus();
						} else {
							JOptionPane.showMessageDialog(null, "Kh??ng ????? s??? l?????ng");
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
									"L???i! kh??ng ???????c tr??? thu???c nhi???u h??n thu???c hi???n c?? trong gi??? h??ng");
							txtNhapSoLuong.requestFocus();
						}
					}
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Kh??ng ???????c ????? r???ng s??? l?????ng");
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
	 * ki???m tra thu???c c?? n???m trong gi??? h??ng hay kh??ng
	 * 
	 * @param list
	 * @param t
	 * @return int: v??? tr?? thu???c n???m trong gi??? h??ng
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
	 * Load th??ng tin v??o JComboBox N?????c s???n xu???t
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
	 * Load th??ng tin v??o JComboBox nh?? cung c???p
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
	 * Load th??ng tin v??o JComboBox Lo???i thu???c
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
	 * Load th??ng tin t??? b???ng s???n ph???m v??o b???ng gi??? h??ng(load chi ti???t h??a ????n)
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
	 * load 1 th??ng tin chi ti???t h??a ????n
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
	 * load th??ng tin v??o b???ng s???n ph???m thu???c
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
	 * load 1 th??ng tin s???n ph???m v??o b???ng
	 * 
	 * @param thuoc
	 */
	private void load1ThongTinThuoc(Thuoc thuoc) {
		String n[] = { thuoc.getTenThuoc(), dtf.format(thuoc.getNgaySX()), dtf.format(thuoc.getHanSuDung()),
				thuoc.getSLTon() + "", vnFormat.format(thuoc.getDonGia()) };
		modelChonSP.addRow(n);
	}
}
