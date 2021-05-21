package app_QLT;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.Thuoc_DAO;
import entity.KhachHang;
import entity.NhanVien;
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
	private JTextField txtDoanhThu,txtLoiNhuan,txtTienGoc,txttimKiem,txtTongKH,txtTongSoThuoc,txtMaKH;
	private JButton btnThongKe,btnTimKiem,btnThongKeAll;
	private JLabel lbTimKiem,lbNhapTK,lbtongthuoc,lbtongkh;
	private KhachHang_DAO kh_dao;
	private Thuoc_DAO thuoc_dao;
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
		//Thong ke theo ten/ma/sdt
		//them du lieu vao combobox
		
		cbTimKiem=new JComboBox<String>();
		cbTimKiem.addItem("Tìm theo tên");
		cbTimKiem.addItem("Tìm theo mã");
		cbTimKiem.addItem("Tìm theo số điện thoại");
		btren1.add(lbTimKiem=new JLabel("Chọn loại tìm kiếm:"));
		btren1.add(Box.createHorizontalStrut(40));
		btren1.add(cbTimKiem);
		btren2.add(lbNhapTK=new JLabel("Nhập thông tin tìm kiếm: "));
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
		//Giao dien thong ke theo ngay
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
		pphai.setBorder(BorderFactory.createTitledBorder("Thống kê theo ngày"));
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
		
		//giao dien thong ke duoi cung ben phai
		Box d,d1,d2,d3;
		d=Box.createVerticalBox();
		d1=Box.createHorizontalBox();
		d2=Box.createHorizontalBox();
		d3=Box.createHorizontalBox();
		
		d1.add(new JLabel("Tổng doanh thu:  "));
		d1.add(txtDoanhThu=new JTextField(15));
		d.add(Box.createVerticalStrut(20));
		d.add(d1);
		d.add(Box.createVerticalStrut(20));
//		d.add(Box.createVerticalStrut(10));
//		d2.add(new JLabel("Tiền gốc:              "));
//		d2.add(txtTienGoc=new JTextField(15));
//		d.add(d2);
//		d.add(Box.createVerticalStrut(10));
//		d3.add(new JLabel("Lợi nhuận:            "));
//		d3.add(txtLoiNhuan=new JTextField(15));
//		d.add(d3);
		pduoi.add(d,BorderLayout.EAST);
		b4.add(pduoi);
		
		//giao dien thong ke ben trai
		Box btrai,btrai1,btrai2;
		btrai=Box.createVerticalBox();
		btrai1=Box.createHorizontalBox();
		btrai2=Box.createHorizontalBox();
		btrai1.add(lbtongkh=new JLabel("Tổng số khách hàng đã mua"));
		btrai1.add(Box.createHorizontalStrut(10));
		btrai1.add(txtTongKH=new JTextField(15));
		btrai2.add(lbtongthuoc=new JLabel("Tổng số thuốc đã bán"));
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
		txtMaKH=new JTextField(10);//luu ma khach hang
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmThongKe().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnTimKiem)) {
			timKH();
		}
		if(o.equals(btnThongKe)) {
			timKHTheoNgay();
			
		}
		if(o.equals(btnThongKeAll)) {
			thongKeTatCaKH();
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
				dskh=kh_dao.getKhachHangTheoSDT(txttimKiem.getText());
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
	private void clearTable() {
		while (tableKhachHang.getRowCount() > 0) {
			dfKhachHang.removeRow(0);
		}
	}
	private void clearTableThuoc() {
		while (tableThuoc.getRowCount() > 0) {
			dfThuoc.removeRow(0);
		}
	}
	public String strGioiTinh(Boolean nv) {
		if (nv) {
			return "Nam";
		}
		return "Nữ";
	}
//	public int tongKH() {
//		int i=0;
//		while(tableKhachHang.) {
//			i=i+1;
//		}
//	}

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
							thuoc.getMaThuoc(),thuoc.getTenThuoc(),thuoc.getDonGia(),thuoc.getSLTon(),
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

