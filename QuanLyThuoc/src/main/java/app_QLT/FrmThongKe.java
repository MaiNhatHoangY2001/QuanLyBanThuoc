package app_QLT;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import connectDB.ConnectDB;
import dao.CTHoaDon_DAO;
import dao.KhachHang_DAO;
import dao.Thuoc_DAO;
import entity.KhachHang;
import entity.Thuoc;



public class FrmThongKe extends JPanel implements ActionListener,MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Integer> cbNgay,cbThang,cbNam;
	private JComboBox<String> cbTimKiem;
	private DefaultTableModel dfKhachHang,dfThuoc;
	private JTable tableKhachHang,tableThuoc;
	private JTextField txtDoanhThu,txttimKiem,txtTongKH,txtTongSoThuoc,txtMaKH;
	private JButton btnThongKe,btnTimKiem,btnThongKeAll;
	private KhachHang_DAO kh_dao;
	private Thuoc_DAO thuoc_dao;
	private CTHoaDon_DAO ctHD_dao;
	private DecimalFormat df = new DecimalFormat("#,###.0");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmThongKe().setVisible(true);
	}
	
	public FrmThongKe() {
		setLayout(null);
		setBackground(new Color(248,248,248));
		//lb tim kiem
		JLabel lbtimkiem=new JLabel("Chọn loại tìm kiếm");
		lbtimkiem.setBounds(15, 30, 150, 30);
		add(lbtimkiem);
		
		//them du lieu vao combobox
		cbTimKiem=new JComboBox<String>();
		cbTimKiem.addItem("Tìm theo tên");
		cbTimKiem.addItem("Tìm theo mã");
		cbTimKiem.addItem("Tìm theo số điện thoại");
		
		//combo box
		cbTimKiem.setBounds(180, 30, 190, 30);
		add(cbTimKiem);
		
		//lb Nhap thong tin tim kiem
		JLabel txtlbtimkiem=new JLabel("Nhập thông tin tìm kiếm");
		txtlbtimkiem.setBounds(15, 70, 160, 30);
		add(txtlbtimkiem);
		
		//txt tim kiem
		txttimKiem=new JTextField();
		txttimKiem.setBounds(180, 70, 190, 30);
		add(txttimKiem);
		
		//btn tim kiem
		btnTimKiem=new JButton("Tìm kiếm");
		btnTimKiem.setBounds(160, 110, 100, 30);
		add(btnTimKiem);
		
		//Khung thong tin tim kem
		JPanel border1 = new JPanel();
		border1.setBorder(BorderFactory.createTitledBorder("Nhập thông tin tìm kiếm"));
		border1.setBounds(10,5 , 375 , 150);
		border1.setBackground(new Color(248,248,248));
		add(border1);
		
		//khung tim kiem theo ngay
		JPanel border2 = new JPanel();
		border2.setBorder(BorderFactory.createTitledBorder("Thống kê"));
		border2.setBackground(new Color(248,248,248));
		border2.setBounds(395, 5 , 375 , 150);
	
		//them du lieu vao combobox ngay
		cbNgay=new JComboBox<Integer>() ;
		for(int i=1;i<=31;i++) {
			
			cbNgay.addItem(i);
		}
		
		//them du lieu vao combobox thang
		cbThang=new JComboBox<Integer>() ;
		for(int i=1;i<=12;i++) {
			
			cbThang.addItem(i);
		}
		
		//them du lieu vao combobox nam
		cbNam=new JComboBox<Integer>() ;
		for(int i=LocalDate.now().getYear();i>=LocalDate.now().getYear()-10;i--) {
			
			cbNam.addItem(i);
		}
		
		//lb ngay, combobox ngay
		JLabel lbngay=new JLabel("Ngày");
		lbngay.setBounds(410, 35, 70, 30);
		add(lbngay);
		cbNgay.setBounds(440, 35, 70, 30);
		cbNgay.setSelectedItem(27);
		add(cbNgay);
		
		//lb thang,combox thang
		JLabel lbthang=new JLabel("Tháng");
		lbthang.setBounds(530, 35, 70, 30);
		add(lbthang);
		cbThang.setBounds(570, 35, 70, 30);
		cbThang.setSelectedItem(5);
		add(cbThang);
		
		//lb nam, combobox nam
		JLabel lbnam=new JLabel("Năm");
		lbnam.setBounds(660, 35, 50, 30);
		add(lbnam);
		cbNam.setBounds(690, 35, 70, 30);
		cbNam.setSelectedItem(2021);
		add(cbNam);
		
		//button thong ke theo ngay
		btnThongKe=new JButton("Thống kê theo ngày");
		btnThongKe.setBounds(430, 100, 150, 30);
		add(btnThongKe);
		
		//button thong ke theo ngay
		btnThongKeAll=new JButton("Thống kê tất cả");
		btnThongKeAll.setBounds(600, 100, 130, 30);
		add(btnThongKeAll);
		add(border2);
		
		//bang thong tin khach hang
		String [] headernv={"Mã khách hàng","Tên khách hàng","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại"};
		dfKhachHang=new DefaultTableModel(headernv,0);
		tableKhachHang=new JTable(dfKhachHang);
		tableKhachHang.setRowHeight(20);
		JScrollPane scroll;
		scroll=new JScrollPane(tableKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		scroll.setBounds(5,160 , 770, 190);
		scroll.setBackground(new Color(248,248,248));
		add(scroll);
		
		//bang thong tin thuoc ma khach hang da mua
		String []headerthuoc= {"Mã thuốc","Tên thuốc","Đơn giá","Số lượng","Tên nhà cung cấp","Loại thuốc","Nước sản xuất"};
		dfThuoc=new DefaultTableModel(headerthuoc,0);
		tableThuoc=new JTable(dfThuoc);
		tableThuoc.setRowHeight(20);
		JScrollPane scrollthuoc;
		scrollthuoc=new JScrollPane(tableThuoc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollthuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc khách hàng đã mua"));
		scrollthuoc.setBounds(5,360 , 770, 190);
		scrollthuoc.setBackground(new Color(248,248,248));
		add(scrollthuoc);
		
		//phan tong so luong khach hang
		JLabel lbtongkh=new JLabel("Tổng khách hàng đã mua thuốc");
		lbtongkh.setBounds(10, 570, 200, 30);
		add(lbtongkh);
		txtTongKH=new JTextField();
		txtTongKH.setBounds(230, 570, 150, 30);
		add(txtTongKH);
		
		//phan tong so thuoc ban duoc
		JLabel lbtongsothuoc=new JLabel("Tổng số thuốc khách hàng đã mua");
		lbtongsothuoc.setBounds(10, 630, 200, 30);
		add(lbtongsothuoc);
		txtTongSoThuoc=new JTextField();
		txtTongSoThuoc.setBounds(230, 630, 150, 30);
		add(txtTongSoThuoc);
		
		//phan tong doanh thu
		JLabel lbtongdoanhthu=new JLabel("Tổng doanh thu");
		lbtongdoanhthu.setBounds(480, 600, 150, 30);
		add(lbtongdoanhthu);
		txtDoanhThu=new JTextField();
		txtDoanhThu.setBounds(600, 600, 150, 30);
		add(txtDoanhThu);
		
		//ToolTip
		btnTimKiem.setToolTipText("Tìm kiếm thông tin khách hàng");
		btnThongKeAll.setToolTipText("Thống kê tất cả khách hàng đã mua thuốc và tổng doanh thu");
		btnThongKe.setToolTipText("Thống kê thông tin khách hàng và tổng doanh thu trong ngày");
		
		
		
		btnThongKe.setBackground(new Color(191, 247, 249));
		btnThongKe.setForeground(Color.DARK_GRAY);
		btnThongKe.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		btnThongKeAll.setBackground(new Color(191, 247, 249));
		btnThongKeAll.setForeground(Color.DARK_GRAY);
		btnThongKeAll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		btnTimKiem.setBackground(new Color(191, 247, 249));
		btnTimKiem.setForeground(Color.DARK_GRAY);
		btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		//Them su kien
		btnThongKe.addActionListener(this);
		btnThongKeAll.addActionListener(this);
		btnTimKiem.addActionListener(this);
		tableKhachHang.addMouseListener(this);
		
		/**
		 * DataBase
		 */
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		kh_dao = new KhachHang_DAO();
		thuoc_dao=new Thuoc_DAO();
		ctHD_dao=new CTHoaDon_DAO();
		txtMaKH=new JTextField(10);//luu ma khach hang
	}

	

	//xu li su kien
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnTimKiem)) {
			clearTableThuoc();
			timKH();
			txtTongKH.setText(""+dfKhachHang.getRowCount());
			if (cbTimKiem.getSelectedIndex() == 1) {
				txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoMa(txttimKiem.getText()));
				txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoMa(txttimKiem.getText())));
			}
			else {
				if (cbTimKiem.getSelectedIndex() == 0) {
					txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoTen(txttimKiem.getText()));
					txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoTen(txttimKiem.getText())));
				}
				else {
					txtTongSoThuoc.setText(""+thuoc_dao.getTongSoLuongThuocTheoSDT(txttimKiem.getText()));
					txtDoanhThu.setText(""+df.format(ctHD_dao.getTongDoanhTheoSDT(txttimKiem.getText())));
				}
				
			}
			
		}
		//thong ke theo ngay
		if(o.equals(btnThongKe)) {
			clearTableThuoc();
			timKHTheoNgay();
			txtTongKH.setText(""+dfKhachHang.getRowCount());
			txtTongSoThuoc.setText(""+tongThuocTheoNgay());
			txtDoanhThu.setText(""+df.format(tongDoanhThuTheoNgay()));
			
			
		}
		if(o.equals(btnThongKeAll)) {
			clearTableThuoc();
			thongKeTatCaKH();
			txtTongKH.setText(""+dfKhachHang.getRowCount());
			txtTongSoThuoc.setText(""+tongThuocBan());
			txtDoanhThu.setText(""+df.format(tongDoanhThu()));
			
		}
	}
	//Tim thong tin khach hang
	private void timKH() {
		ArrayList<KhachHang> dskh;
			if (cbTimKiem.getSelectedIndex() == 1)
				dskh = kh_dao.getKhachHangTheoMa(txttimKiem.getText());
			else {
				if (cbTimKiem.getSelectedIndex() == 0)
				dskh = kh_dao.getKhachHangTheoTen(txttimKiem.getText());
				else
				dskh=kh_dao.getDSKhachHangTheoSDT(txttimKiem.getText());
			}
			clearTable();// xóa bảng
			if (!dskh.isEmpty()) {
				for (KhachHang kh : dskh) {
					dfKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
							strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT()});
				}
			} 
			else {
				JOptionPane.showMessageDialog(this, "Không có khách hàng nào theo thông tin tìm");
				return;
			}
	}
	//Thong ke khach hang theo ngay
	private void timKHTheoNgay() {
		ArrayList<KhachHang> dskh;
		dskh=kh_dao.getKhachHangDaMuaThuocTheoNgay((int)cbNgay.getSelectedItem(), (int)cbThang.getSelectedItem(), (int)cbNam.getSelectedItem());
		clearTable();// xóa bảng
		if (!dskh.isEmpty()) {
			for (KhachHang kh : dskh) {
				dfKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
						strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT()});
			}
		} 
		else {
			JOptionPane.showMessageDialog(this, "Không có khách hàng nào theo thông tin tìm");
			return;
		}
	}
	//Thong ke tat ca khach hang da mua thuoc
	private void thongKeTatCaKH() {
		ArrayList<KhachHang> dskh;
		dskh=kh_dao.getAllKhachHang();
		clearTable();// xóa bảng
			for (KhachHang kh : dskh) {
				dfKhachHang.addRow(new Object[] { kh.getMaKH(), kh.getHoTen(), kh.getNgaySinh(),
						strGioiTinh(kh.isGioiTinh()), kh.getDiaChi(), kh.getSDT()});
			}
	}
	//Xoa bang khach hang
	private void clearTable() {
		while (tableKhachHang.getRowCount() > 0) {
			dfKhachHang.removeRow(0);
		}
	}
	//Xoa bang thuoc
	private void clearTableThuoc() {
		while (tableThuoc.getRowCount() > 0) {
			dfThuoc.removeRow(0);
		}
	}
	
	private String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}
	//Tong so thuoc da ban
	private int tongThuocBan() {
		int i=0;
		i=thuoc_dao.getTongSoLuongThuoc();
		return i;
	}
	//So thuoc da ban theo ngay
	private int tongThuocTheoNgay() {
		int i=0;
		i=thuoc_dao.getTongSoLuongThuocTheoNgay((int)cbNgay.getSelectedItem(), (int)cbThang.getSelectedItem(), (int)cbNam.getSelectedItem());
		return i;
	}
	//Thong ke tong doanh thu
	private double tongDoanhThu() {
		double i=0;
		i=ctHD_dao.getTongDoanhThuThuoc();
		return i;
	}
	//Thong ke tong doanh thu theo ngay
	private double tongDoanhThuTheoNgay() {
		double i=0;
		i=ctHD_dao.getTongDoanhThuThuocTheoNgay((int)cbNgay.getSelectedItem(), (int)cbThang.getSelectedItem(), (int)cbNam.getSelectedItem());
		return i;
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(tableKhachHang)) {
			int row = tableKhachHang.getSelectedRow();
			txtMaKH.setText(dfKhachHang.getValueAt(row, 0).toString());
			ArrayList<Thuoc> dsthuoc;
			dsthuoc=thuoc_dao.getThuocKhachHangDaMua(txtMaKH.getText());
			clearTableThuoc();// xóa bảng
				for (Thuoc thuoc : dsthuoc) {
					dfThuoc.addRow(new Object[] {
							thuoc.getMaThuoc(),thuoc.getTenThuoc(),df.format(thuoc.getDonGia()),thuoc.getSLTon(),
							thuoc.getNcc().getMaNCC(),thuoc.getLoaiThuoc().getMaLoai(),thuoc.getNuocSX().getIdNuoc()
					});
				}
		}
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

}

