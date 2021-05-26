package app_QLT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
	private JPanel pphai,pduoi,ptrai;
	private DefaultTableModel dfKhachHang,dfThuoc;
	private JTable tableKhachHang,tableThuoc;
	private JTextField txtDoanhThu,txttimKiem,txtTongKH,txtTongSoThuoc,txtMaKH;
	private JButton btnThongKe,btnTimKiem,btnThongKeAll;
	private KhachHang_DAO kh_dao;
	private Thuoc_DAO thuoc_dao;
	private CTHoaDon_DAO ctHD_dao;
	private DecimalFormat df = new DecimalFormat("#,###.0");
	public FrmThongKe() {
		setLayout(new BorderLayout());
		
		Box b,b1,b2,b3,b4,btren,btren1,btren2,btren3;
		b=Box.createVerticalBox();
		b1=Box.createHorizontalBox();
		b2=Box.createHorizontalBox();
		b3=Box.createHorizontalBox();
		b4=Box.createHorizontalBox();
		btren=Box.createVerticalBox();
		btren1=Box.createHorizontalBox();
		btren2=Box.createHorizontalBox();
		btren3=Box.createHorizontalBox();
		pphai=new JPanel();
		ptrai=new JPanel();
		pduoi=new JPanel(new BorderLayout());

		
		pphai.setBackground(new Color(248,248,248));
		ptrai.setBackground(new Color(248,248,248));
		pduoi.setBackground(new Color(248,248,248));
		
		//Thong ke theo ten/ma/sdt
		//them du lieu vao combobox

		
		//them du lieu vao combobox
		cbTimKiem=new JComboBox<String>();
		cbTimKiem.addItem("Tìm theo tên");
		cbTimKiem.addItem("Tìm theo mã");
		cbTimKiem.addItem("Tìm theo số điện thoại");

		
		//Thong ke theo ten/ma/sdt
		btren1.add(new JLabel("Chọn loại tìm kiếm:"));
		btren1.add(Box.createHorizontalStrut(40));
		btren1.add(cbTimKiem);
		btren2.add(new JLabel("Nhập thông tin tìm kiếm: "));
		btren2.add(Box.createHorizontalStrut(5));
		btren2.add(txttimKiem=new JTextField(10));
		btren3.add(btnTimKiem=new JButton("Tìm kiếm"));
		btren.add(btren1);
		btren.add(Box.createVerticalStrut(10));
		btren.add(btren2);
		btren.add(Box.createVerticalStrut(10));
		btren.add(btren3);
		ptrai.add(btren);
		ptrai.setBorder(BorderFactory.createTitledBorder("Nhập thông tin tìm kiếm"));
		
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
		//Giao dien thong ke
		Box bphai,bphai1,bphai2;
		bphai=Box.createVerticalBox();
		bphai1=Box.createHorizontalBox();
		bphai2=Box.createHorizontalBox();
		
		bphai1.add(new JLabel("Ngày"));
		bphai1.add(cbNgay);
		bphai1.add(new JLabel("Tháng"));
		bphai1.add(cbThang);
		bphai1.add(new JLabel("Năm"));
		bphai1.add(cbNam);
		bphai2.add(btnThongKe=new JButton("Thống kê theo ngày"));
		bphai2.add(Box.createHorizontalStrut(40));
		bphai2.add(btnThongKeAll=new JButton("Thống kê tất cả"));
		bphai.add(bphai1);
		bphai.add(Box.createVerticalStrut(25));
		bphai.add(bphai2);
		pphai.add(bphai);
		pphai.setBorder(BorderFactory.createTitledBorder("Thống kê"));
		b1.add(ptrai);
		b1.add(pphai);
		
		//bang thong tin khach hang
		String [] headernv={"Mã khách hàng","Tên khách hàng","Ngày sinh","Giới tính","Địa chỉ","Số điện thoại"};
		dfKhachHang=new DefaultTableModel(headernv,0);
		tableKhachHang=new JTable(dfKhachHang);
		tableKhachHang.setRowHeight(20);
		JScrollPane scroll;
		scroll=new JScrollPane(tableKhachHang,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		b2.add(scroll,BorderLayout.CENTER);
		
		//bang thong tin thuoc ma khach hang da mua
		String []headerthuoc= {"Mã thuốc","Tên thuốc","Đơn giá","Số lượng","Tên nhà cung cấp","Loại thuốc","Nước sản xuất"};
		dfThuoc=new DefaultTableModel(headerthuoc,0);
		tableThuoc=new JTable(dfThuoc);
		tableThuoc.setRowHeight(20);
		JScrollPane scrollthuoc;
		scrollthuoc=new JScrollPane(tableThuoc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollthuoc.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc khách hàng đã mua"));
		b3.add(scrollthuoc,BorderLayout.SOUTH);
		

		//giao dien thong ke tong doanh thu

		//giao dien thong ke duoi cung ben phai
		Box d,d1;
		d=Box.createVerticalBox();
		d1=Box.createHorizontalBox();
		d1.add(new JLabel("Tổng doanh thu:  "));
		d1.add(txtDoanhThu=new JTextField(15));
		d.add(Box.createVerticalStrut(20));
		d.add(d1);
		d.add(Box.createVerticalStrut(20));
		pduoi.add(d,BorderLayout.EAST);
		b4.add(pduoi);
		
		//giao dien thong ke so luong kh,tong thuoc da ban
		Box btrai,btrai1,btrai2;
		btrai=Box.createVerticalBox();
		btrai1=Box.createHorizontalBox();
		btrai2=Box.createHorizontalBox();
		btrai1.add(new JLabel("Tổng số khách hàng đã mua"));
		btrai1.add(Box.createHorizontalStrut(10));
		btrai1.add(txtTongKH=new JTextField(15));
		btrai2.add(new JLabel("Tổng số thuốc đã bán"));
		btrai2.add(Box.createHorizontalStrut(45));
		btrai2.add(txtTongSoThuoc=new JTextField(15));
		btrai.add(Box.createVerticalStrut(5));
		btrai.add(btrai1);
		btrai.add(Box.createVerticalStrut(15));
		btrai.add(btrai2);
		btrai.add(Box.createVerticalStrut(5));
		pduoi.add(btrai,BorderLayout.WEST);
		
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		add(b);
		
		
//		
//		btnThongKe.setBackground(new Color(191, 247, 249));
//		btnThongKe.setForeground(Color.DARK_GRAY);
//		btnThongKe.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//		
//		btnThongKeAll.setBackground(new Color(191, 247, 249));
//		btnThongKeAll.setForeground(Color.DARK_GRAY);
//		btnThongKeAll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//		
//		btnTimKiem.setBackground(new Color(191, 247, 249));
//		btnTimKiem.setForeground(Color.DARK_GRAY);
//		btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//		
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmThongKe().setVisible(true);
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
	@Override
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

