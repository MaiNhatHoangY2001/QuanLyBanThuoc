package app_QLT;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

//import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.DateLabelFormatter;
import entity.NhanVien;
import entity.Regex;
import java.awt.Font;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV, txtTenNV, txtLuong, txtDiaChi, txtSDT, txtTim;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cboLoaiTK;
	private JButton btnTim, btnThem, btnXoa, btnXoaRong, btnSua;
	private String column[] = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Ngày sinh", "Giới tính", "Địa chỉ",
	"Lương" };
	private JTable tableNV;
	private DefaultTableModel modelNV;
	private SqlDateModel modelNgay;
	private NhanVien_DAO nv_dao;
	private Regex regex;
	private DecimalFormat df = new DecimalFormat("#");
	private Properties p;
	private JDatePickerImpl datePicker;
//	private JDateChooser ngaySinh;

	public FrmNhanVien() {
		setLayout(null);
		setBounds(0, 0, 1600, 1002);
		
		JPanel pThongTinNV = new JPanel();
		pThongTinNV.setBorder(BorderFactory.createTitledBorder("Nhập Thông tin nhân viên"));
		pThongTinNV.setBounds(10, 10, 1006, 275);
		pThongTinNV.setBackground(new Color(248,248,248));
		add(pThongTinNV);
		pThongTinNV.setLayout(null);
		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTenNV.setBounds(728, 30, 227, 40);
		pThongTinNV.add(txtTenNV);

		JLabel lblTen = new JLabel("Tên nhân viên:");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(514, 30, 158, 40);
		pThongTinNV.add(lblTen);
		txtMaNV = new JTextField();
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMaNV.setBounds(173, 28, 227, 40);
		pThongTinNV.add(txtMaNV);
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setBounds(29, 27, 150, 40);
		pThongTinNV.add(lblMa);
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ButtonGroup bg = new ButtonGroup();
		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNu.setBounds(839, 89, 60, 40);
		pThongTinNV.add(radNu);
		radNu.setBackground(new Color(248,248,248));
		bg.add(radNu);
		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNam.setBounds(728, 89, 81, 40);
		pThongTinNV.add(radNam);
		radNam.setBackground(new Color(248,248,248));
		bg.add(radNam);
		radNam.setSelected(true);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGioiTinh.setBounds(514, 89, 100, 40);
		pThongTinNV.add(lblGioiTinh);
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setBounds(173, 210, 466, 40);
		pThongTinNV.add(txtDiaChi);

		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChi.setBounds(29, 209, 100, 40);
		pThongTinNV.add(lblDiaChi);
		txtLuong = new JTextField();
		txtLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtLuong.setBounds(173, 150, 227, 40);
		pThongTinNV.add(txtLuong);

		JLabel lblLuong = new JLabel("Lương:");
		lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLuong.setBounds(29, 149, 111, 40);
		pThongTinNV.add(lblLuong);
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setBounds(728, 150, 227, 40);
		pThongTinNV.add(txtSDT);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSDT.setBounds(514, 149, 158, 40);
		pThongTinNV.add(lblSDT);

//		ngaySinh = new JDateChooser();
//		ngaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 20));
//		ngaySinh.setBounds(173, 89, 227, 40);
//		pThongTinNV.add(ngaySinh);
//		ngaySinh.setDateFormatString("dd/MM/yyyy");

		JLabel lblNgay = new JLabel("Ngày sinh:");
		lblNgay.setBounds(29, 89, 100, 40);
		pThongTinNV.add(lblNgay);
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		setBackground(new Color(248,248,248));
		
		JPanel pTacVu = new JPanel();
		pTacVu.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		pTacVu.setBounds(1026, 10, 557, 275);
		pTacVu.setBackground(new Color(248,248,248));
		add(pTacVu);
		pTacVu.setLayout(null);
		btnThem = new JButton("Thêm nhân viên");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThem.setBounds(77, 62, 170, 40);
		pTacVu.add(btnThem);
		btnThem.setToolTipText("Thêm thông tin nhân viên");
		btnThem.setBackground(new Color(20, 140, 255));
		btnThem.setForeground(Color.DARK_GRAY);
		btnThem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnXoa = new JButton("Xóa nhân viên");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoa.setBounds(347, 62, 170, 40);
		pTacVu.add(btnXoa);
		btnXoa.setToolTipText("Xóa thông tin nhân viên");
		btnXoa.setBackground(new Color(20, 140, 255));
		btnXoa.setForeground(Color.DARK_GRAY);
		btnXoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoaRong.setBounds(347, 179, 170, 40);
		pTacVu.add(btnXoaRong);
		btnXoaRong.setToolTipText("Xóa chữ trong các textbox");
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setForeground(Color.DARK_GRAY);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		btnSua = new JButton("Sửa nhân viên");
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSua.setBounds(77, 179, 170, 40);
		pTacVu.add(btnSua);
		btnSua.setToolTipText("Sửa thông tin nhân viên khi click vào bảng thông tin nhân viên");
		btnSua.setBackground(new Color(20, 140, 255));
		btnSua.setForeground(Color.DARK_GRAY);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo tên/mã nhân viên"));
		pTimKiem.setBounds(10, 295, 1573, 83);
		pTimKiem.setBackground(new Color(248,248,248));
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
		btnTim = new JButton("Tìm kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.setBounds(1262, 20, 131, 40);
		pTimKiem.add(btnTim);
		btnTim.setToolTipText("Tìm nhân viên theo mã hoặc tên (Thông tin xuất hiện trên bảng)");
		btnTim.setBackground(new Color(20, 140, 255));
		btnTim.setForeground(Color.DARK_GRAY);
		btnTim.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		txtTim = new JTextField();
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTim.setBounds(902, 21, 230, 40);
		pTimKiem.add(txtTim);

		JLabel lblThongTinTK = new JLabel("Nhập thông tin tìm kiếm");
		lblThongTinTK.setBounds(626, 20, 244, 40);
		pTimKiem.add(lblThongTinTK);
		lblThongTinTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTim.addActionListener(this);
		cboLoaiTK.addItem("Tìm kiếm theo tên");
		cboLoaiTK.addItem("Tìm kiếm theo mã");
		
		/**
		 * Phần bảng
		 */
		JPanel pTable = new JPanel();
		pTable.setBounds(10, 381, 1573, 708);
		add(pTable);
		
		modelNV=new DefaultTableModel(column,0);
		tableNV=new JTable(modelNV);
		tableNV.setRowHeight(20);
		tableNV.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 20));
		tableNV.setRowHeight(40);
		tableNV.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JScrollPane scrollNhanVien;
		pTable.setLayout(null);
		scrollNhanVien=new JScrollPane(tableNV,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollNhanVien.setBounds(10, 10, 1563, 571);
		scrollNhanVien.setBackground(new Color(248,248,248));
		scrollNhanVien.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		pTable.add(scrollNhanVien);
		
		/**
		 * thêm sự kiện
		 */
		btnSua.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnThem.addActionListener(this);
		tableNV.addMouseListener(this);
		
		
		/**
		 * DataBase
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		nv_dao = new NhanVien_DAO();
		// Kết nối Database
		DocDuLieuDatabase();

		/**
		 * Regex
		 */
		regex = new Regex();
	}

	private void DocDuLieuDatabase() {
		ArrayList<NhanVien> list = nv_dao.getalltbNhanVien();
		for (NhanVien nv : list) {
			modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDienThoai(), nv.getNgaySinh(),
					strGioiTinh(nv.isGioiTinh()), nv.getDiaChi(), df.format(nv.getLuong()) });
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object key = e.getSource();
		if (key == btnThem)
			themNV();
		else if (key == btnXoaRong)
			xoaRong();
		else if (key == btnXoa)
			xoaNV();
		else if (key == btnTim)
			timMaNV();
		else if (key == btnSua)
			suaNV();

	}

	private void suaNV() {
		if (kiemTra()) {

//			String maNV = txtMaNV.getText();
//			Double luong = Double.parseDouble(txtLuong.getText());
//			String hoTen = txtTenNV.getText();
//			String soDienThoai = txtSDT.getText();
//			Date ngaySinh = (Date) datePicker.getModel().getValue();
//			boolean gioiTinh = radNam.isSelected();
//			String diaChi = txtDiaChi.getText();
//			NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, ngaySinh, gioiTinh, diaChi, luong);
//			if (nv_dao.update(nv)) {
//				clearTable();
//				DocDuLieuDatabase();
//			} else
//				JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
		}

	}

	private void timMaNV() {
		ArrayList<NhanVien> dsnv;
		if (btnTim.getText().equals("Tìm")) {
			btnTim.setText("Hủy tìm");

			if (cboLoaiTK.getSelectedIndex() == 1)
				dsnv = nv_dao.getNhanVienTheoMaNV(txtTim.getText());
			else
				dsnv = nv_dao.getNhanVienTheoTenNV((txtTim.getText()));

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

	private void clearTable() {
		while (tableNV.getRowCount() > 0) {
			modelNV.removeRow(0);
		}
	}

	private void xoaNV() {
		if (tableNV.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn nhân viên cần xóa");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = tableNV.getSelectedRow();
				nv_dao.xoa(modelNV.getValueAt(tableNV.getSelectedRow(), 0).toString());
				modelNV.removeRow(index);
			}
		}

	}

	private void xoaRong() {
		txtMaNV.setText("");
		txtDiaChi.setText("");
		txtLuong.setText("");
		txtSDT.setText("");
		txtTenNV.setText("");
		modelNgay.setDate(1990, 8, 24);
		tableNV.clearSelection();
		txtMaNV.requestFocus();
	}

	private void themNV() {
		if (kiemTra()) {
//			String maNV = txtMaNV.getText();
//			Double luong = Double.parseDouble(txtLuong.getText());
//			String hoTen = txtTenNV.getText();
//			String soDienThoai = txtSDT.getText();
//			Date ngaySinh = (Date) datePicker.getModel().getValue();
//			boolean gioiTinh = radNam.isSelected();
//			String diaChi = txtDiaChi.getText();
//			NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, ngaySinh, gioiTinh, diaChi, luong);
//
//			if (nv_dao.create(nv)) {
//				modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDienThoai(), nv.getNgaySinh(),
//						strGioiTinh(nv.isGioiTinh()), nv.getDiaChi(), df.format(nv.getLuong()) });
//			} else
//				JOptionPane.showMessageDialog(this, "Trùng mã nhân viên");
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNV.getSelectedRow();
		txtMaNV.setText(modelNV.getValueAt(row, 0).toString());
		txtTenNV.setText(modelNV.getValueAt(row, 1).toString());
		txtSDT.setText(modelNV.getValueAt(row, 2).toString());
		modelNgay.setValue(Date.valueOf(modelNV.getValueAt(row, 3).toString()));
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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}

	private boolean kiemTra() {
		if (regex.kiemTraRong(txtMaNV))
			return false;
		if (regex.RegexMaNV(txtMaNV))
			return false;
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
		if (regex.kiemTraTuoi(modelNgay))
			return false;
		return true;
	}
}
