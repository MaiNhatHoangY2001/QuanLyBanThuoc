package frm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.SqlDateModel;

import com.toedter.calendar.JDateChooser;

import app.App;
import chucNang.ChucNang;
import chucNang.Regex;
import dao.KhachHangDao;
import dao.NhanVienDao;
//import dao.impl.NhanVienImlp;
import entity.NhanVien;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1480922184973547119L;
	private JTextField txtTenNV, txtLuong, txtDiaChi, txtSDT, txtTim;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cboLoaiTK;
	private JButton btnTim, btnThem, btnXoa, btnXoaRong, btnSua;
	private String column[] = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Ngày sinh", "Giới tính", "Địa chỉ",
			"Lương" };
	private JTable tblNhanVien;
	private DefaultTableModel modelNV;
	private JTable tableNV;
	private SqlDateModel modelNgay;
	private DecimalFormat df = new DecimalFormat("#");
	private JDateChooser ngaySinh;
	private Regex regex;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private DateTimeFormatter localDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private String ma;

	public FrmNhanVien() {

//		SecurityManager securityManager = System.getSecurityManager();
//		if (securityManager == null) {
//			System.setProperty("java.security.policy", "policy/policy.policy");
//			System.setSecurityManager(new SecurityManager());
//		}
//
//		try {
//			nv_dao = (NhanVienDao) Naming.lookup("rmi://192.168.1.7:9999/nhanVienDao");
//			// nv_dao = new NhanVienImlp();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		setLayout(null);
		setBounds(0, 0, 1600, 1002);

		JPanel pThongTinNV = new JPanel();
		pThongTinNV.setBorder(BorderFactory.createTitledBorder("Nhập Thông tin nhân viên"));
		pThongTinNV.setBounds(10, 10, 1006, 275);
		pThongTinNV.setBackground(Color.WHITE);
		add(pThongTinNV);
		pThongTinNV.setLayout(null);
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenNV.setBounds(184, 30, 227, 40);
		pThongTinNV.add(txtTenNV);

		JLabel lblTen = new JLabel("Tên nhân viên:");
		lblTen.setHorizontalAlignment(SwingConstants.LEFT);
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(29, 29, 158, 40);
		pThongTinNV.add(lblTen);
		ButtonGroup bg = new ButtonGroup();
		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNu.setBounds(839, 89, 60, 40);
		pThongTinNV.add(radNu);
		radNu.setBackground(Color.WHITE);
		bg.add(radNu);
		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNam.setBounds(728, 89, 81, 40);
		pThongTinNV.add(radNam);
		radNam.setBackground(Color.WHITE);
		bg.add(radNam);
		radNam.setSelected(true);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGioiTinh.setBounds(514, 89, 100, 40);
		pThongTinNV.add(lblGioiTinh);
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setBounds(183, 210, 466, 40);
		pThongTinNV.add(txtDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setHorizontalAlignment(SwingConstants.LEFT);
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChi.setBounds(29, 209, 100, 40);
		pThongTinNV.add(lblDiaChi);
		txtLuong = new JTextField();
		txtLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLuong.setBounds(184, 150, 466, 40);
		pThongTinNV.add(txtLuong);

		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuong.setBounds(29, 149, 111, 40);
		pThongTinNV.add(lblLuong);
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setBounds(728, 30, 227, 40);
		pThongTinNV.add(txtSDT);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDT.setBounds(514, 29, 158, 40);
		pThongTinNV.add(lblSDT);

		ngaySinh = new JDateChooser();
		ngaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
		ngaySinh.setBounds(184, 89, 227, 40);
		pThongTinNV.add(ngaySinh);
		ngaySinh.setDateFormatString("dd/MM/yyyy");

		JLabel lblNgay = new JLabel("Ngày sinh:");
		lblNgay.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgay.setBounds(29, 89, 100, 40);
		pThongTinNV.add(lblNgay);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBackground(Color.WHITE);

		JPanel pTacVu = new JPanel();
		pTacVu.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		pTacVu.setBounds(1026, 10, 557, 275);
		pTacVu.setBackground(Color.WHITE);
		add(pTacVu);
		pTacVu.setLayout(null);
		btnThem = new JButton("Thêm nhân viên");
		btnThem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem.setBounds(76, 51, 170, 50);
		pTacVu.add(btnThem);
		btnThem.setToolTipText("Thêm thông tin nhân viên");
		btnThem.setBackground(new Color(20, 140, 255));
		btnThem.setForeground(Color.WHITE);
		btnThem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnXoa = new JButton("Xóa nhân viên");
		btnXoa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoa.setBounds(346, 51, 170, 50);
		pTacVu.add(btnXoa);
		btnXoa.setToolTipText("Xóa thông tin nhân viên");
		btnXoa.setBackground(new Color(20, 140, 255));
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoaRong.setBounds(346, 168, 170, 50);
		pTacVu.add(btnXoaRong);
		btnXoaRong.setToolTipText("Xóa chữ trong các textbox");
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnSua = new JButton("Sửa nhân viên");
		btnSua.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSua.setBounds(76, 168, 170, 50);
		pTacVu.add(btnSua);
		btnSua.setToolTipText("Sửa thông tin nhân viên khi click vào bảng thông tin nhân viên");
		btnSua.setBackground(new Color(20, 140, 255));
		btnSua.setForeground(Color.WHITE);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo tên/mã nhân viên"));
		pTimKiem.setBounds(10, 295, 1573, 83);
		pTimKiem.setBackground(Color.WHITE);
		add(pTimKiem);
		pTimKiem.setLayout(null);
		cboLoaiTK = new JComboBox<String>();
		cboLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboLoaiTK.setBounds(268, 20, 260, 40);
		pTimKiem.add(cboLoaiTK);
		JLabel lblLoaiTK = new JLabel("Chọn loại tìm kiếm:");
		lblLoaiTK.setBounds(34, 20, 200, 40);
		pTimKiem.add(lblLoaiTK);
		lblLoaiTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim = new JButton("Tìm");
		btnTim.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.setBounds(1262, 20, 131, 50);
		pTimKiem.add(btnTim);
		btnTim.setToolTipText("Tìm nhân viên theo mã hoặc tên (Thông tin xuất hiện trên bảng)");
		btnTim.setBackground(new Color(20, 140, 255));
		btnTim.setForeground(Color.WHITE);
		btnTim.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTim.setBounds(902, 21, 230, 40);
		pTimKiem.add(txtTim);

		JLabel lblThongTinTK = new JLabel("Nhập thông tin tìm kiếm");
		lblThongTinTK.setBounds(626, 20, 244, 40);
		pTimKiem.add(lblThongTinTK);
		lblThongTinTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboLoaiTK.addItem("Tìm kiếm theo tên");
		cboLoaiTK.addItem("Tìm kiếm theo mã");

		/**
		 * Phần bảng
		 */
		new DefaultTableModel(column, 0);
		tblNhanVien = new JTable(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } },
				column)) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNhanVien.setRowMargin(5);
		tblNhanVien.setRowHeight(30);
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tblNhanVien.setToolTipText("Bảng thông tin của nhân viên");
		JTableHeader headerTableNV = tblNhanVien.getTableHeader();
		headerTableNV.setFont(new Font("Tahoma", Font.PLAIN, 24));
		headerTableNV.setBackground(new Color(248, 198, 153));
		JScrollPane thanhCuonNV = new JScrollPane(tblNhanVien);
		thanhCuonNV.setBackground(Color.WHITE);
		thanhCuonNV.setBorder(
				new TitledBorder(null, "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		thanhCuonNV.setBounds(10, 381, 1573, 604);
		thanhCuonNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		thanhCuonNV.setToolTipText("Bảng thông tin của nhân viên");

		add(thanhCuonNV);
		modelNV = (DefaultTableModel) tblNhanVien.getModel();

		/**
		 * thêm sự kiện
		 */
		btnSua.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		tblNhanVien.addMouseListener(this);
		btnTim.addActionListener(this);

		try {
			DocDuLieuDatabase();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private void DocDuLieuDatabase() throws RemoteException {
		List<NhanVien> list = App.nv_dao.getAllNhanVien();
		ChucNang.clearDataTable(modelNV);
		for (NhanVien nv : list) {
			modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDienThoai(),
					nv.getNgaySinh().format(localDateFormat), strGioiTinh(nv.isGioiTinh()), nv.getDiaChi(),
					df.format(nv.getLuong()) });
		}
		ChucNang.addNullDataTable(modelNV);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object key = e.getSource();
		try {
			if (key == btnThem) {
				themNV();
			} else if (key == btnXoaRong)
				xoaRong();
			else if (key == btnXoa) {
				xoaNV();
			} else if (key == btnSua) {
				suaNV();
			} else if (key.equals(btnTim))
				timMaNV();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void timMaNV() throws RemoteException {
		List<NhanVien> dsnv = new ArrayList<NhanVien>();
		if (btnTim.getText().equals("Tìm")) {
			btnTim.setText("Hủy tìm");

			if (cboLoaiTK.getSelectedIndex() == 1)
				dsnv = App.nv_dao.getNhanVienTheoMa(txtTim.getText());
			else
				dsnv = App.nv_dao.getNhanVienTheoTen((txtTim.getText()));

			clearTable();// xóa bảng

			if (!dsnv.isEmpty()) {
				for (NhanVien nv : dsnv) {
					modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDienThoai(), nv.getNgaySinh(),
							strGioiTinh(nv.isGioiTinh()), nv.getDiaChi(), df.format(nv.getLuong()) });
				}

			} else {
				JOptionPane.showMessageDialog(this, "Không có nhân viên nào theo thông tin tìm");
			}
		} else {
			btnTim.setText("Tìm");
			clearTable();
			DocDuLieuDatabase();
		}
	}

	private void suaNV() throws HeadlessException, RemoteException {
		if (kiemTra()) {
			Double luong = Double.parseDouble(txtLuong.getText());
			String hoTen = txtTenNV.getText();
			String soDienThoai = txtSDT.getText();
			Date date = new Date(ngaySinh.getDate().getTime());
			LocalDate ns = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			boolean gioiTinh = radNam.isSelected();
			String diaChi = txtDiaChi.getText();
			NhanVien nv = new NhanVien(ma, hoTen, soDienThoai, ns, gioiTinh, diaChi, luong);
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa nhân viên này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				App.nv_dao.suaNhanVien(nv);
				clearTable();
				DocDuLieuDatabase();
				JOptionPane.showMessageDialog(this, "Thông tin nhân viên đã được cập nhật");
			} else
				JOptionPane.showMessageDialog(this, "Đã hủy");
		}

	}

	private void clearTable() {
		while (tblNhanVien.getRowCount() > 0) {
			modelNV.removeRow(0);
		}
	}

	private void xoaNV() throws RemoteException {
		if (tblNhanVien.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên cần xóa");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = tblNhanVien.getSelectedRow();
				App.nv_dao.xoaNhanVien(tblNhanVien.getValueAt(index, 0).toString());
				clearTable();
				DocDuLieuDatabase();
			}
		}
	}

	private void xoaRong() throws RemoteException, ParseException {
		txtDiaChi.setText("");
		txtLuong.setText("");
		txtSDT.setText("");
		txtTenNV.setText("");
		ngaySinh.setDate(dateFormat.parse("28-4-1990"));
		tblNhanVien.clearSelection();
		txtTenNV.requestFocus();
		// modelNgay.setDate(1990, 8, 24);
		tableNV.clearSelection();
		clearTable();
		DocDuLieuDatabase();

	}

	private void themNV() throws RemoteException {
		if (kiemTra()) {
			Double luong = Double.parseDouble(txtLuong.getText());
			String hoTen = txtTenNV.getText();
			String soDienThoai = txtSDT.getText();
			Date date = new Date(ngaySinh.getDate().getTime());
			LocalDate ns = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			boolean gioiTinh = radNam.isSelected();
			String diaChi = txtDiaChi.getText();
			NhanVien nv = new NhanVien(hoTen, soDienThoai, ns, gioiTinh, diaChi, luong);

			App.nv_dao.themNhanVien(nv);
			JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
			clearTable();
			DocDuLieuDatabase();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tblNhanVien.getSelectedRow();
		if (modelNV.getValueAt(row, 0) != null) {
			ma = modelNV.getValueAt(row, 0).toString();
			txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
			txtSDT.setText(modelNV.getValueAt(row, 2).toString());
			try {
				ngaySinh.setDate(dateFormat.parse((modelNV.getValueAt(row, 3).toString())));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String gioiTinh = modelNV.getValueAt(row, 4).toString();
			if (gioiTinh.equals("Nam")) {
				radNam.setSelected(true);
				radNu.setSelected(false);
			} else {
				radNam.setSelected(false);
				radNu.setSelected(true);
			}
			txtDiaChi.setText(modelNV.getValueAt(row, 5).toString());
			txtLuong.setText(modelNV.getValueAt(row, 6).toString());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}

	private boolean kiemTra() {
		regex = new Regex();
		if (regex.kiemTraRong(txtTenNV))
			return false;
		if (regex.RegexTen(txtTenNV))
			return false;
		if (regex.kiemTraRong(txtSDT))
			return false;
		if (regex.RegexSDT(txtSDT))
			return false;
		if (regex.kiemTraRong(txtDiaChi))
			return false;
		if (regex.RegexDiaChi(txtDiaChi))
			return false;
		if (regex.kiemTraRong(txtLuong))
			return false;
		if (regex.kiemTraSo(txtLuong))
			return false;
		if (regex.kiemTraTuoi(ngaySinh))
			return false;
		return true;
	}
}
