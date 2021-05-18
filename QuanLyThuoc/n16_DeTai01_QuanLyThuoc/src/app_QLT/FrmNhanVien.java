package app_QLT;

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

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import entity.DateLabelFormatter;
import entity.NhanVien;
import entity.Regex;

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
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	public FrmNhanVien() {
		setLayout(null);

		/**
		 * Phần nhập thông tin nhân viên
		 */
		JLabel lblMa = new JLabel("Mã nhân viên");
		lblMa.setBounds(50, 40, 100, 20);
		add(lblMa);
		txtMaNV = new JTextField();
		txtMaNV.setBounds(180, 40, 200, 30);
		add(txtMaNV);

		JLabel lblTen = new JLabel("Tên nhân viên");
		lblTen.setBounds(400, 40, 100, 20);
		add(lblTen);
		txtTenNV = new JTextField();
		txtTenNV.setBounds(510, 40, 220, 30);
		add(txtTenNV);

		JLabel lblNgay = new JLabel("Ngày sinh");
		lblNgay.setBounds(50, 80, 100, 20);
		add(lblNgay);
		modelNgay = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelNgay, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(180, 80, 200, 30);
		modelNgay.setDate(1990, 8, 24);
		modelNgay.setSelected(true);
		add(datePicker);

		JLabel lblLuong = new JLabel("Lương nhân viên");
		lblLuong.setBounds(50, 120, 100, 20);
		add(lblLuong);
		txtLuong = new JTextField();
		txtLuong.setBounds(180, 120, 200, 30);
		add(txtLuong);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(50, 160, 100, 20);
		add(lblDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(180, 160, 550, 30);
		add(txtDiaChi);

		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(400, 120, 100, 20);
		add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setBounds(510, 120, 220, 30);
		add(txtSDT);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setBounds(400, 80, 100, 20);
		add(lblGioiTinh);
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		radNam.setBounds(510, 80, 50, 20);
		radNu.setBounds(600, 80, 50, 20);
		ButtonGroup bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		radNam.setSelected(true);
		add(radNam);
		add(radNu);

		/**
		 * Phần tìm kiếm
		 */
		JLabel lblLoaiTK = new JLabel("Chọn loại tìm kiếm");
		lblLoaiTK.setBounds(40, 270, 150, 20);
		add(lblLoaiTK);
		cboLoaiTK = new JComboBox<String>();
		cboLoaiTK.addItem("Tìm kiếm theo tên");
		cboLoaiTK.addItem("Tìm kiếm theo mã");
		cboLoaiTK.setBounds(200, 270, 210, 25);
		add(cboLoaiTK);

		JLabel lblThongTinTK = new JLabel("Nhập thông tin tìm kiếm");
		lblThongTinTK.setBounds(40, 310, 150, 20);
		add(lblThongTinTK);
		txtTim = new JTextField();
		txtTim.setBounds(200, 310, 120, 30);
		add(txtTim);
		btnTim = new JButton("Tìm");
		btnTim.setBounds(330, 310, 80, 30);
		add(btnTim);

		/**
		 * Phần tác vụ
		 */
		btnThem = new JButton("Thêm");
		btnThem.setBounds(480, 270, 120, 30);
		add(btnThem);

		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(635, 270, 120, 30);
		add(btnXoa);

		btnXoaRong = new JButton("Xóa rỗng");
		btnXoaRong.setBounds(480, 315, 120, 30);
		add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(635, 315, 120, 30);
		add(btnSua);

		/**
		 * Phần bảng
		 */
		modelNV = new DefaultTableModel(column, 0);
		tableNV = new JTable(modelNV);
		JScrollPane sp = new JScrollPane(tableNV);
		sp.setBounds(20, 400, 740, 240);
		add(sp);

		/**
		 * thêm border title
		 */
		JPanel border4 = new JPanel();
		border4.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		border4.setBounds(10, 380, 760, 270);
		add(border4);

		JPanel border3 = new JPanel();
		border3.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		border3.setBounds(450, 240, 320, 120);
		add(border3);

		JPanel border2 = new JPanel();
		border2.setBorder(BorderFactory.createTitledBorder("Tìm kiếm theo tên/mã nhân viên"));
		border2.setBounds(10, 240, 420, 120);
		add(border2);

		JPanel border1 = new JPanel();
		border1.setBorder(BorderFactory.createTitledBorder("Nhập Thông tin nhân viên"));
		border1.setBounds(10, 10, 760, 220);
		add(border1);

		/**
		 * thêm sự kiện
		 */
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaRong.addActionListener(this);
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

			String maNV = txtMaNV.getText();
			Double luong = Double.parseDouble(txtLuong.getText());
			String hoTen = txtTenNV.getText();
			String soDienThoai = txtSDT.getText();
			Date ngaySinh = (Date) datePicker.getModel().getValue();
			boolean gioiTinh = radNam.isSelected();
			String diaChi = txtDiaChi.getText();
			NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, ngaySinh, gioiTinh, diaChi, luong);
			if (nv_dao.update(nv)) {
				clearTable();
				DocDuLieuDatabase();
			} else
				JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
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
			String maNV = txtMaNV.getText();
			Double luong = Double.parseDouble(txtLuong.getText());
			String hoTen = txtTenNV.getText();
			String soDienThoai = txtSDT.getText();
			Date ngaySinh = (Date) datePicker.getModel().getValue();
			boolean gioiTinh = radNam.isSelected();
			String diaChi = txtDiaChi.getText();
			NhanVien nv = new NhanVien(maNV, hoTen, soDienThoai, ngaySinh, gioiTinh, diaChi, luong);

			if (nv_dao.create(nv)) {
				modelNV.addRow(new Object[] { nv.getMaNV(), nv.getHoTen(), nv.getSoDienThoai(), nv.getNgaySinh(),
						strGioiTinh(nv.isGioiTinh()), nv.getDiaChi(), df.format(nv.getLuong()) });
			} else
				JOptionPane.showMessageDialog(this, "Trùng mã nhân viên");
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
