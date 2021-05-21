package app_QLT;

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
import java.util.Iterator;
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
import dao.CTHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiThuoc_DAO;
import dao.PhatSinhMa;
import dao.Thuoc_DAO;
import entity.ChiTietHoaDon;
import entity.DateLabelFormatter;
import entity.KhachHang;
import entity.LoaiThuoc;
import entity.Regex;
import entity.Thuoc;

public class FrmBanHang extends JPanel implements ActionListener, MouseListener,ItemListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimSDT;
	private JButton btnTimSDT;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
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
	private JTextField txtNgayLapHD;
	private String column[] = {"Tên thuốc","Loại thuốc","Số lượng","Đơn giá","Thành tiền"};
	private DefaultTableModel modelBanHang;
	private JTable tableBanHang;
	private JTextField txtTongTienBH;
	private JButton btnThanhToan;
	private JComboBox<String> cboMaNVNhap;
	private JTextField txtmaNVNhap;
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
	public FrmBanHang() {
		
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		kh_dao = new KhachHang_DAO();
		thuoc_dao = new Thuoc_DAO();
		loaithuoc_dao = new LoaiThuoc_DAO();
		hoadon_dao = new HoaDon_DAO();
		cthd_dao = new CTHoaDon_DAO();
		regex = new Regex();
		
		
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
		add(btnTimSDT);
		
//		nhap thong tin hoa don
		JPanel border1 = new JPanel();
		border1.setBorder(BorderFactory.createTitledBorder("Nhập thông tin khách hàng"));
		border1.setBounds(10,50 , 375 , 230);
		
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
		ButtonGroup bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		radNam.setSelected(true);
		add(radNam);
		add(radNu);
		
		btnThemKH = new JButton("Thêm khách hàng");
		btnThemKH.setBounds(125, 230, 150, 35);
		add(btnThemKH);

		add(border1);
		
//		nhap thong tin thuoc kh mua
		JPanel border2 = new JPanel();
		border2.setBorder(BorderFactory.createTitledBorder("Nhập thông tin thuốc khách hàng đã mua"));
		border2.setBounds(395, 50 , 375 , 230);
		
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
		
		btnThemVaoHD = new JButton("Thêm vào hoá đơn");
		btnThemVaoHD.setBounds(495, 230, 180, 35);
		add(btnThemVaoHD);
		
		add(border2);
		
//		btn
		btnXoaHD = new JButton("Xoá hoá đơn");
		btnXoaHD.setBounds(200, 290, 120, 35);
		add(btnXoaHD);
		
		btnXoaRong = new JButton("Xoá rỗng");
		btnXoaRong.setBounds(340, 290, 100, 35);
		add(btnXoaRong);
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(460, 290, 100, 35);
		add(btnSua);
		
//		
		JLabel lbTenKHHD = new JLabel("Tên khách hàng: ");
		lbTenKHHD.setBounds(10, 330, 150, 30);
		add(lbTenKHHD);
		lbXuatTenKH = new JLabel();
		lbXuatTenKH.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		lbXuatTenKH.setBounds(120, 332, 200, 30);
		add(lbXuatTenKH);
		
		JLabel lbNgayLapHD = new JLabel("Ngày lập hoá đơn: ");
		lbNgayLapHD.setBounds(395, 330, 150, 30);
		add(lbNgayLapHD);
		
//		txtNgayLapHD.setBounds(545, 330, 225, 30);
		modelNgayLapHD = new SqlDateModel();
		p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		datePanel1 = new JDatePanelImpl(modelNgayLapHD, p1);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		datePicker1.setBounds(545, 330,225, 30);
		modelNgayLapHD.setDate(2000, 0, 1);
		modelNgayLapHD.setSelected(true);
		add(datePicker1);
		
//		table
		modelBanHang = new DefaultTableModel(column,0);
		tableBanHang = new JTable(modelBanHang);
		JScrollPane spBanHang = new JScrollPane(tableBanHang);
		spBanHang.setBounds(20, 390, 740, 150);
		add(spBanHang);
		
		JPanel border3 = new JPanel();
		border3.setBorder(BorderFactory.createTitledBorder("Thông tin đơn thuốc"));
		border3.setBounds(10, 370, 760, 180);
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
		add(cbmaNVNhap);
		
//		thanh toan
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBounds(580, 600, 190, 35);
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
		
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		if(o.equals(btnXoaRong))
			xoaRong();
		if(o.equals(btnThemKH))
			themKH();
		if(o.equals(btnThemVaoHD))
			themVaoHD();
		if(o.equals(btnTimSDT))
			timSDTKH();
	}
	
	private void xoaRong() {
		txtTimSDT.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDiaChi.setText("");
		radNam.setSelected(false);
		radNu.setSelected(false);
		txtSoLuongThuoc.setText("");
		modelNgayKH.setDate(1990, 0, 1);
		tableBanHang.clearSelection();
		lbXuatTenKH.setText("");
		clearTable();
		cboTenThuoc.removeAllItems();
		txtTenKH.requestFocus();
	}
	
	private void themKH() {
		if(kiemTraKH()) {
			PhatSinhMa makh = new PhatSinhMa();
			String tenKH =txtTenKH.getText();
			String sDT = txtSDT.getText();
			String diaChi = txtDiaChi.getText();
			boolean gioiTinh = radNam.isSelected();
			Date ngaySinh = (Date) datePicker.getModel().getValue();
			KhachHang kh = new KhachHang(makh.maKH(), tenKH, ngaySinh, gioiTinh, diaChi, sDT);
			if(kh_dao.getKhachHangTheoSDT(sDT) == null)
				if(kh_dao.createKH(kh)) {
					JOptionPane.showMessageDialog(this, "Them khach hang thanh cong!");
					lbXuatTenKH.setText(kh.getHoTen());
					
				} else
					JOptionPane.showMessageDialog(this, "Trùng mã khách hàng");
			
			else 
				JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
				
		}
	}
	
	private void themVaoHD() {
		if(kiemTraThuoc()&&kiemTraKH())
		{
			PhatSinhMa maCTHD = new PhatSinhMa();
			int soLuongThuoc = Integer.parseInt(txtSoLuongThuoc.getText().trim());
			String tenT = (String) cboTenThuoc.getSelectedItem();
			String loaiT = (String) cboLoaiThuoc.getSelectedItem();
			Thuoc thuoc = thuoc_dao.getThuocTheoTen(tenT);
			ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD.maCTHD(), soLuongThuoc, thuoc);
			if(soLuongThuoc>0)
				modelBanHang.addRow(new Object [] {
						thuoc.getTenThuoc(), loaiT,df.format(cthd.getSoLuong()),df.format( thuoc.getDonGia()),df.format(thuoc.getDonGia()*cthd.getSoLuong()) });
			else JOptionPane.showMessageDialog(this, "Số lượng thuốc phải lớn hơn 0");
			
		}
		
	}
//	thêm vào chi tiết hóa đơn
	private void themCTHD() {
		if(kiemTraThuoc()&&kiemTraKH())
		{
			PhatSinhMa maCTHD = new PhatSinhMa();
			int soLuongThuoc = Integer.parseInt(txtSoLuongThuoc.getText().trim());
			String tenT = (String) cboTenThuoc.getSelectedItem();
			String loaiT = (String) cboLoaiThuoc.getSelectedItem();
			Thuoc thuoc = thuoc_dao.getThuocTheoTen(tenT);
			ChiTietHoaDon cthd = new ChiTietHoaDon(maCTHD.maCTHD(), soLuongThuoc, thuoc);
			if(!cthd_dao.createCTHD(cthd)||soLuongThuoc<=0)
			{	
				JOptionPane.showMessageDialog(this, "Số lượng thuốc phải lớn hơn 0");
			}
		}
		
	}
	
	private void timSDTKH()
	{
		ArrayList<KhachHang> dsKH;
		String sdt = txtTimSDT.getText().trim();
		KhachHang kh = kh_dao.getKhachHangTheoSDT(sdt);
		if(kh!=null)
		{
			txtTenKH.setText(kh.getHoTen());
			txtSDT.setText(kh.getSDT());
			modelNgayKH.setValue(kh.getNgaySinh());
			txtDiaChi.setText(kh.getDiaChi());
			
			if (kh.isGioiTinh()) {
				radNam.setSelected(true);
				radNu.setSelected(false);
			} else {
				radNam.setSelected(false);
				radNu.setSelected(true);
			}
		}
		else JOptionPane.showMessageDialog(this, "Khách hàng không tồn tại");
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
		if(regex.kiemTraRong(txtSoLuongThuoc))
			return false;
		if(regex.regexSoLuong(txtSoLuongThuoc))
			return false;
		return true;	
	}
	
	private void nhapCBTen() {
		String s = (String) cboLoaiThuoc.getSelectedItem();
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getItem() == cboLoaiThuoc.getSelectedItem()) {
			String s = (String) cboLoaiThuoc.getSelectedItem();
			String MaLT = loaithuoc_dao.getMaLoaiThuocTheoTen(s);
			ArrayList<Thuoc> dsThuoc = thuoc_dao.getThuocTheoLoai(MaLT);
			cboTenThuoc.removeAllItems();
			for (Thuoc t : dsThuoc)
			{
				
				cboTenThuoc.addItem(t.getTenThuoc());
			}
		}
		
		
	}
	

}
