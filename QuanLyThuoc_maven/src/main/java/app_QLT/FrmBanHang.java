package app_QLT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JCheckBox;
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

import chucNang.DateLabelFormatter;
import chucNang.Regex;
import connectDB.ConnectDB;
import dao.CTHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiThuoc_DAO;
import dao.NhanVien_DAO;
import dao.PhatSinhMa;
import dao.Thuoc_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.NhanVien;
import entity.Thuoc;

public class FrmBanHang extends JPanel implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimSDT;
	private JButton btnTimSDT;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JButton btnThemKH;
	private JComboBox<String> cboLoaiThuoc;
	private JComboBox<String> cboTenThuoc;
	private JTextField txtSoLuongThuoc;
	private JButton btnThemVaoHD;
	private JButton btnXoaRong;
	private JButton btnXoaHD;
	private JButton btnSua;
	private JLabel lbXuatTenKH;
	private String column[] = { "Tên thuốc", "Loại thuốc", "Số lượng", "Đơn giá", "Thành tiền" };
	private DefaultTableModel modelBanHang;
	private JTable tableBanHang;
	private JTextField txtTongTienBH;
	private JButton btnThanhToan;
	private SqlDateModel modelNgayKH;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private SqlDateModel modelNgayLapHD;
	private Properties p1;
	private JDatePanelImpl datePanel1;
	private JDatePickerImpl datePicker1;
	private JComboBox<String> cbmaNVNhap;
	private Regex regex;
	private KhachHang_DAO kh_dao;
	private Thuoc_DAO thuoc_dao;
	private HoaDon_DAO hoadon_dao;
	private CTHoaDon_DAO cthd_dao;
	private LoaiThuoc_DAO loaithuoc_dao;
	private DecimalFormat df = new DecimalFormat("#");
	private NhanVien_DAO nv_dao;
	private JCheckBox chkGiam;

	public FrmBanHang() {

		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		kh_dao = new KhachHang_DAO();
//		nv_dao = new NhanVien_DAO();
		thuoc_dao = new Thuoc_DAO();
		loaithuoc_dao = new LoaiThuoc_DAO();
		hoadon_dao = new HoaDon_DAO();
		// cthd_dao = new CTHoaDon_DAO();
		regex = new Regex();

		setBackground(new Color(248, 248, 248));
		setLayout(null);
//		tim sdt

		JLabel lbTimSDT = new JLabel("Tìm số điện thoại");
		lbTimSDT.setBounds(220, 5, 120, 30);
		add(lbTimSDT);
		txtTimSDT = new JTextField();
		txtTimSDT.setBounds(340, 5, 120, 30);
		add(txtTimSDT);
		btnTimSDT = new JButton("Tìm");
		btnTimSDT.setBounds(460, 5, 70, 30);
		btnTimSDT.setToolTipText("Tìm khách hàng");
		btnTimSDT.setBackground(new Color(191, 247, 249));
		btnTimSDT.setForeground(Color.DARK_GRAY);
		btnTimSDT.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(btnTimSDT);

//		nhap thong tin hoa don
		JPanel border1 = new JPanel();
		border1.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		border1.setBounds(10, 50, 375, 230);
		border1.setBackground(new Color(248, 248, 248));
		border1.setToolTipText("Khách hàng");

		JLabel lbTenKH = new JLabel("Tên khách hàng");
		lbTenKH.setBounds(30, 70, 100, 30);
		add(lbTenKH);
		txtTenKH = new JTextField();
		txtTenKH.setBounds(150, 70, 215, 30);
		add(txtTenKH);

		JLabel lbSDT = new JLabel("Số điện thoại");
		lbSDT.setBounds(30, 100, 100, 30);
		add(lbSDT);
		txtSDT = new JTextField();
		txtSDT.setBounds(150, 100, 215, 30);
		add(txtSDT);

		JLabel lbNgaySinh = new JLabel("Ngày sinh");
		lbNgaySinh.setBounds(30, 130, 100, 30);
		add(lbNgaySinh);
//		150 130 215 30;
		modelNgayKH = new SqlDateModel();
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(modelNgayKH, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(150, 130, 215, 30);
		datePicker.setBackground(new Color(248, 248, 248));
		datePicker.setToolTipText("Chọn ngày sinh");
		modelNgayKH.setDate(1990, 0, 1);
		modelNgayKH.setSelected(true);
		add(datePicker);

		JLabel lbDiaChi = new JLabel("Địa chỉ");
		lbDiaChi.setBounds(30, 160, 100, 30);
		add(lbDiaChi);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(150, 160, 215, 30);
		add(txtDiaChi);

		JLabel lbGioiTinh = new JLabel("Giới tính");
		lbGioiTinh.setBounds(30, 190, 100, 30);
		add(lbGioiTinh);
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		radNam.setBounds(150, 190, 70, 30);
		radNu.setBounds(230, 190, 70, 30);
		radNam.setBackground(new Color(248, 248, 248));
		radNu.setBackground(new Color(248, 248, 248));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		radNam.setSelected(true);
		add(radNam);
		add(radNu);

		btnThemKH = new JButton("Thêm khách hàng");
		btnThemKH.setBounds(125, 230, 150, 35);
		btnThemKH.setBackground(new Color(191, 247, 249));
		btnThemKH.setForeground(Color.DARK_GRAY);
		btnThemKH.setToolTipText("Thêm khách hàng mới");
		add(btnThemKH);

		add(border1);

//		nhap thong tin thuoc kh mua
		JPanel border2 = new JPanel();
		border2.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc khách hàng cần mua"));
		border2.setBounds(395, 50, 375, 230);
		border2.setBackground(new Color(248, 248, 248));
		border2.setToolTipText("Nhập thông tin thuốc cần mua");

		JLabel lbLoaiThuoc = new JLabel("Loại thuốc");
		lbLoaiThuoc.setBounds(415, 70, 100, 30);
		add(lbLoaiThuoc);
		cboLoaiThuoc = new JComboBox<String>();
		cboLoaiThuoc.setBounds(515, 70, 235, 30);
		ArrayList<LoaiThuoc> lsLThuoc = loaithuoc_dao.getallLoaiThuoc();
		for (LoaiThuoc t : lsLThuoc) {
			cboLoaiThuoc.addItem(t.getTenLoai());
		}
		add(cboLoaiThuoc);

		JLabel lbTenThuoc = new JLabel("Tên thuốc");
		lbTenThuoc.setBounds(415, 110, 100, 30);
		add(lbTenThuoc);
		cboTenThuoc = new JComboBox<String>();
		cboTenThuoc.setBounds(515, 110, 235, 30);
		add(cboTenThuoc);

		JLabel lbSoLuongThuoc = new JLabel("Số lượng thuốc");
		lbSoLuongThuoc.setBounds(415, 150, 100, 30);
		add(lbSoLuongThuoc);
		txtSoLuongThuoc = new JTextField();
		txtSoLuongThuoc.setBounds(515, 150, 235, 30);
		add(txtSoLuongThuoc);
		chkGiam = new JCheckBox("Giảm số lượng");
		chkGiam.setBounds(515, 190, 200, 20);
		chkGiam.setBackground(new Color(248, 248, 248));
		add(chkGiam);

		btnThemVaoHD = new JButton("Thêm vào hoá đơn");
		btnThemVaoHD.setBounds(495, 230, 180, 35);
		btnThemVaoHD.setBackground(new Color(191, 247, 249));
		btnThemVaoHD.setForeground(Color.DARK_GRAY);
		btnThemVaoHD.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnThemVaoHD.setToolTipText("Thêm thuốc vào danh sách cần mua");
		add(btnThemVaoHD);

		add(border2);

//		btn
		btnXoaHD = new JButton("Xoá sản phẩm");
		btnXoaHD.setBounds(200, 290, 120, 35);
		btnXoaHD.setBackground(new Color(191, 247, 249));
		btnXoaHD.setForeground(Color.DARK_GRAY);
		btnXoaHD.setToolTipText("Xóa sản phẩm không trong danh sách");
		btnXoaHD.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		add(btnXoaHD);

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setBounds(340, 290, 100, 35);
		btnXoaRong.setBackground(new Color(191, 247, 249));
		btnXoaRong.setForeground(Color.DARK_GRAY);
		btnXoaRong.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnXoaRong.setToolTipText("Làm mới lại trang");
		add(btnXoaRong);

		btnSua = new JButton("Sửa");
		btnSua.setBounds(460, 290, 100, 35);
		btnSua.setBackground(new Color(191, 247, 249));
		btnSua.setForeground(Color.DARK_GRAY);
		btnSua.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnSua.setToolTipText("Sửa thông tin khách hàng");
		add(btnSua);

//		
		JLabel lbTenKHHD = new JLabel("Mã khách hàng: ");
		lbTenKHHD.setBounds(10, 330, 150, 30);
		add(lbTenKHHD);
		lbXuatTenKH = new JLabel();
		lbXuatTenKH.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		lbXuatTenKH.setBounds(120, 332, 200, 30);
		add(lbXuatTenKH);

		JLabel lbNgayLapHD = new JLabel("Ngày lập hoá đơn: ");
		lbNgayLapHD.setBounds(395, 330, 150, 30);
		add(lbNgayLapHD);

		modelNgayLapHD = new SqlDateModel();
		p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(modelNgayLapHD, p1);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.setBounds(545, 330, 225, 30);
		datePicker1.setBackground(new Color(248, 248, 248));
		modelNgayLapHD.setDate(2021, 04, 27);
		modelNgayLapHD.setSelected(true);
		add(datePicker1);

//		table
		modelBanHang = new DefaultTableModel(column, 0);
		tableBanHang = new JTable(modelBanHang);
		JScrollPane spBanHang = new JScrollPane(tableBanHang);
		spBanHang.setBounds(20, 390, 740, 150);
		tableBanHang.setToolTipText("Thông tin thuốc khách hàng cần mua");
		add(spBanHang);

		JPanel border3 = new JPanel();
		border3.setBorder(BorderFactory.createTitledBorder("Thông tin đơn thuốc"));
		border3.setBounds(10, 370, 760, 180);
		border3.setBackground(new Color(248, 248, 248));
		add(border3);

//		Tong tien
		JLabel lbTongTienBH = new JLabel("Tổng tiền:");
		lbTongTienBH.setBounds(450, 550, 100, 40);
		add(lbTongTienBH);
		txtTongTienBH = new JTextField();
		txtTongTienBH.setBounds(580, 555, 190, 40);
		add(txtTongTienBH);

		JLabel lbmaNVNhap = new JLabel("Mã nhân viên nhập hóa đơn: ");
		lbmaNVNhap.setBounds(20, 570, 200, 30);
		add(lbmaNVNhap);
		cbmaNVNhap = new JComboBox<String>();
		cbmaNVNhap.setBounds(200, 570, 100, 30);
//		ArrayList<NhanVien> lsNVNhap = nv_dao.getalltbNhanVien();
//		for (NhanVien n : lsNVNhap) {
//			cbmaNVNhap.addItem(n.getMaNV());
//			;
//		}
		add(cbmaNVNhap);

//		thanh toan
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(580, 600, 190, 35);
		btnThanhToan.setBackground(new Color(191, 247, 249));
		btnThanhToan.setForeground(Color.DARK_GRAY);
		btnThanhToan.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		btnThanhToan.setToolTipText("Thanh toán tiền thuốc khách hàng mua");
		add(btnThanhToan);

//		Events
		btnSua.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThemKH.addActionListener(this);
		btnThemVaoHD.addActionListener(this);
		btnTimSDT.addActionListener(this);
		btnXoaHD.addActionListener(this);
		btnXoaRong.addActionListener(this);
		cboLoaiThuoc.addItemListener(this);
		cboTenThuoc.addItemListener(this);
		tableBanHang.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableBanHang.getSelectedRow();
		txtSoLuongThuoc.setText(modelBanHang.getValueAt(row, 2).toString());
		cboLoaiThuoc.setSelectedItem(modelBanHang.getValueAt(row, 1));
		cboTenThuoc.setSelectedItem(modelBanHang.getValueAt(row, 0));

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnXoaRong))
			xoaRong();
		if (o.equals(btnThemKH))
			themKH();
		if (o.equals(btnThemVaoHD))
			themVaoHD();
		if (o.equals(btnTimSDT))
			timSDTKH();
		if (o.equals(btnThanhToan))
			thanhToan();
		if (o.equals(btnXoaHD))
			xoaCTHD();
		if (o.equals(btnSua)) {
			suaKH();
		}
	}

	private void xoaRong() {
		txtTimSDT.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		radNam.setSelected(true);

		txtSoLuongThuoc.setText("");
		modelNgayKH.setDate(1990, 0, 1);
		tableBanHang.clearSelection();
		lbXuatTenKH.setText("");
		clearTable();
		cboTenThuoc.removeAllItems();
		txtTenKH.requestFocus();
		cbmaNVNhap.removeAllItems();
//		ArrayList<NhanVien> lsNVNhap = nv_dao.getalltbNhanVien();
//		for (NhanVien n : lsNVNhap) {
//			cbmaNVNhap.addItem(n.getMaNV());
//			;
//		}
	}

	private void themKH() {
		if (kiemTraKH()) {
			PhatSinhMa makh = new PhatSinhMa();
			String tenKH = txtTenKH.getText();
			String sDT = txtSDT.getText();
			String diaChi = txtDiaChi.getText();
			boolean gioiTinh = radNam.isSelected();
			Date ngaySinh = (Date) datePicker.getModel().getValue();
//			KhachHang kh = new KhachHang(makh.maKH(), tenKH, ngaySinh, gioiTinh, diaChi, sDT);
//			KhachHang kh = new KhachHang(tenKH, ngaySinh, gioiTinh, diaChi, sDT);
//			if (kh_dao.getKhachHangTheoSDT(sDT) == null)
//				if (kh_dao.createKH(kh)) {
//					JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công!");
//					lbXuatTenKH.setText(kh.getMaKH());
//
//				} else
//					JOptionPane.showMessageDialog(this, "Trùng mã khách hàng");
//
//			else
//				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");

		}
	}

	private void themVaoHD() {
		if (kiemTraThuoc() && kiemTraKH()) {
			KhachHang kh = kh_dao.getKhachHangTheoSDT(txtSDT.getText());
			lbXuatTenKH.setText(kh.getMaKH());
			PhatSinhMa maCTHD = new PhatSinhMa();
			int soLuongThuoc = Integer.parseInt(txtSoLuongThuoc.getText().trim());
			String tenT = (String) cboTenThuoc.getSelectedItem();
			String loaiT = (String) cboLoaiThuoc.getSelectedItem();
			Thuoc thuoc = thuoc_dao.getThuocTheoTen(tenT);
			NhanVien nhanvien = new NhanVien("Nguyễn Văn Lâm", "0123456789", new Date(1998, 12, 11), true, "TP.HCM",
					10000000);
			HoaDon hoadon = new HoaDon(new Date(2020, 11, 24), nhanvien, kh);
			// ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD.maCTHD(), soLuongThuoc, thuoc);
			ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, thuoc, soLuongThuoc);
			if (soLuongThuoc > 0) {
				if (timRow() != -1) {

					double soluong = soLuong();

					modelBanHang.removeRow(timRow());
					modelBanHang.addRow(new Object[] { thuoc.getTenThuoc(), loaiT, df.format(soluong),
							df.format(thuoc.getDonGia()), df.format(thuoc.getDonGia() * soluong) });
				} else {
					modelBanHang.addRow(new Object[] { thuoc.getTenThuoc(), loaiT, df.format(cthd.getSoLuong()),
							df.format(thuoc.getDonGia()), df.format(thuoc.getDonGia() * cthd.getSoLuong()) });

				}
				tongTien();
			} else
				JOptionPane.showMessageDialog(this, "Số lượng thuốc phải lớn hơn 0");

		}
	}

	private double soLuong() {
		double SL = 0;
		if (!chkGiam.isSelected()) {
			SL = Double.parseDouble(modelBanHang.getValueAt(timRow(), 2).toString())
					+ Double.parseDouble(txtSoLuongThuoc.getText());
			return SL;
		} else {
			SL = Double.parseDouble(modelBanHang.getValueAt(timRow(), 2).toString())
					- Double.parseDouble(txtSoLuongThuoc.getText());
			if (SL <= 0)
				return 0;
			return SL;
		}
	}

	private int timRow() {
		int row = tableBanHang.getRowCount();
		for (int i = 0; i < row; i++) {
			if (modelBanHang.getValueAt(i, 1).toString().equalsIgnoreCase(cboLoaiThuoc.getSelectedItem().toString())
					&& modelBanHang.getValueAt(i, 0).toString()
							.equalsIgnoreCase(cboTenThuoc.getSelectedItem().toString())) {
				return i;

			}
		}
		return -1;
	}

//	thêm vào chi tiết hóa đơn
	private void themCTHD(String maCTHD, int vtRow) {
//		if (kiemTraThuoc() && kiemTraKH()) {
//			int soLuongThuoc = Integer.parseInt(modelBanHang.getValueAt(vtRow, 2).toString());
//			String tenT = modelBanHang.getValueAt(vtRow, 0).toString();
//			Thuoc thuoc = thuoc_dao.getThuocTheoTen(tenT);
//			ChiTietHoaDon cthd = new ChiTietHoaDon(hoadon, thuoc, soLuongThuoc);
//			if (!cthd_dao.createCTHD(cthd) || soLuongThuoc <= 0) {
//				JOptionPane.showMessageDialog(this, "Số lượng thuốc phải lớn hơn 0");
//			}
//		}

	}

// Thanh toán
	private void thanhToan() {
		int soRow = tableBanHang.getRowCount();
		PhatSinhMa ma = new PhatSinhMa();
		if (!regex.kiemTraNgayLapHD(modelNgayLapHD) && kiemTraKH() && kiemTraThuoc()) {
			for (int i = 0; i < soRow; i++) {
				String maCTHD = ma.maCTHD();
				themCTHD(maCTHD, i);
				themhd(maCTHD, i);

			}
			JOptionPane.showMessageDialog(this, "Thanh toan thanh cong");
		}
	}

	private void themhd(String maCTHD, int row) {

		PhatSinhMa ma = new PhatSinhMa();
		Date ngaylap = (Date) datePicker1.getModel().getValue();
//		NhanVien nvl = nv_dao.get1NhanVienTheoMaNV(cbmaNVNhap.getSelectedItem().toString());
		KhachHang kh = kh_dao.getKhachHangTheoSDT(txtSDT.getText());
		// ChiTietHoaDon cthd = cthd_dao.getCTHDTheoMa(maCTHD);
//		ChiTietHoaDon cthd = cthd_dao.getCTHDTheoMa(maCTHD);
		Thuoc thuoc = thuoc_dao.getThuocTheoTen("abc");
		NhanVien nhanvien = new NhanVien("Nguyễn Văn Lâm", "0123456789", new Date(1998, 12, 11), true, "TP.HCM",
				10000000);
		HoaDon hoadon = new HoaDon(new Date(2020, 11, 24), nhanvien, kh);
		HoaDon hd = new HoaDon(ngaylap, nhanvien, kh);
		if (hoadon_dao.createHD(hd))
			System.out.println("Success");
	}

	private void timSDTKH() {

		String sdt = txtTimSDT.getText().trim();
		KhachHang kh = kh_dao.getKhachHangTheoSDT(sdt);
		if (kh != null) {

			lbXuatTenKH.setText(kh.getMaKH());
			txtTenKH.setText(kh.getHoTen());
			txtSDT.setText(kh.getSDT());
//			modelNgayKH.setValue(kh.getNgaySinh());
			txtDiaChi.setText(kh.getDiaChi());

			if (kh.isGioiTinh()) {
				radNam.setSelected(true);
				radNu.setSelected(false);
			} else {
				radNam.setSelected(false);
				radNu.setSelected(true);
			}
		} else
			JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
	}

	private void xoaCTHD() {
		if (tableBanHang.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm cần xóa");
		} else {
			int tl;
			tl = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sản phẩm này không ?", "Cảnh báo",
					JOptionPane.YES_OPTION);
			if (tl == JOptionPane.YES_OPTION) {
				int index = tableBanHang.getSelectedRow();
				modelBanHang.removeRow(index);
				tongTien();
			}
		}
	}

	// sửa
	private void suaKH() {

		if (kiemTraKH()) {

//			String tenkh = txtTenKH.getText().trim();
//			String sdt = txtSDT.getText().trim();
//			Date ngaysinh = (Date) datePicker.getModel().getValue();
//			String diachi = txtDiaChi.getText().trim();
//			boolean gioitinh = radNam.isSelected();
//			KhachHang ktkh = kh_dao.getKhachHangTheoSDT(txtSDT.getText());
//			KhachHang skh = new KhachHang(lbXuatTenKH.getText(), tenkh, ngaysinh, gioitinh, diachi, sdt);
//			if (ktkh != null && !ktkh.getMaKH().equalsIgnoreCase(lbXuatTenKH.getText())) {
//				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại ở khách hàng khác");
//			} else if (kh_dao.update(skh)) {
//				JOptionPane.showMessageDialog(this, "Cập nhật thành công thông tin khách hàng");
//				txtTimSDT.setText(skh.getSDT());
//			}

		}

	}

	public String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}

	private void clearTable() {
		while (tableBanHang.getRowCount() > 0) {
			modelBanHang.removeRow(0);
		}
	}

	private boolean kiemTraKH() {
		if (regex.kiemTraRong(txtTenKH))
			return false;
		if (regex.RegexTen(txtTenKH))
			return false;
		if (regex.kiemTraRong(txtSDT))
			return false;
		if (regex.RegexSDT(txtSDT))
			return false;
		if (regex.kiemTraRong(txtDiaChi))
			return false;
		if (regex.RegexDiaChi(txtDiaChi))
			return false;
		if (regex.kiemTraTuoi(modelNgayKH))
			return false;
		return true;
	}

	private boolean kiemTraThuoc() {
		if (regex.kiemTraRong(txtSoLuongThuoc))
			return false;
		if (regex.regexSoLuong(txtSoLuongThuoc))
			return false;
		return true;
	}

	private void tongTien() {
		int row = tableBanHang.getRowCount();
		double tong = 0;
		for (int i = 0; i < row; i++) {
			tong += Double.parseDouble(modelBanHang.getValueAt(i, 4).toString());
		}
		txtTongTienBH.setText(" " + tong + " VNĐ");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getItem() == cboLoaiThuoc.getSelectedItem()) {
			String s = (String) cboLoaiThuoc.getSelectedItem();
			String MaLT = loaithuoc_dao.getMaLoaiThuocTheoTen(s);
			ArrayList<Thuoc> dsThuoc = thuoc_dao.getThuocTheoLoai(MaLT);
			cboTenThuoc.removeAllItems();
			for (Thuoc t : dsThuoc) {

				cboTenThuoc.addItem(t.getTenThuoc());
			}
		}

	}

}
