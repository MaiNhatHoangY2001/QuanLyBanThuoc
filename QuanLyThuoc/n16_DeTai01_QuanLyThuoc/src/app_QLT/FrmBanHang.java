package app_QLT;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmBanHang extends JPanel implements ActionListener, MouseListener{

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
	public FrmBanHang() {
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
		txtNgaySinh = new JTextField();
		txtNgaySinh.setBounds(150, 130, 215, 30);
		add(txtNgaySinh);
		
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
		add(cboLoaiThuoc);
		
		JLabel lbTenThuoc = new JLabel("Tên thuốc");
		lbTenThuoc.setBounds(415, 100, 100, 30);
		add(lbTenThuoc);
		cboTenThuoc = new JComboBox<String>();
		cboTenThuoc.setBounds(515, 100, 235, 30);
		add(cboTenThuoc);
		
		JLabel lbSoLuongThuoc = new JLabel("Số lượng thuốc");
		lbSoLuongThuoc.setBounds(415, 130, 100, 30);
		add(lbSoLuongThuoc);
		txtSoLuongThuoc = new JTextField();
		txtSoLuongThuoc.setBounds(515, 130, 235, 30);
		add(txtSoLuongThuoc);
		
		btnThemVaoHD = new JButton("Thêm vào hoá đơn");
		btnThemVaoHD.setBounds(495, 230, 180, 35);
		add(btnThemVaoHD);
		
		add(border2);
		
//		btn
		btnXoaHD = new JButton("Xoá hoá đơn");
		btnXoaHD.setBounds(220, 290, 100, 35);
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
		lbXuatTenKH.setText("DEMO");
		lbXuatTenKH.setBounds(120, 332, 200, 30);
		add(lbXuatTenKH);
		
		JLabel lbNgayLapHD = new JLabel("Ngày lập hoá đơn: ");
		lbNgayLapHD.setBounds(395, 330, 150, 30);
		add(lbNgayLapHD);
		txtNgayLapHD = new JTextField();
		txtNgayLapHD.setBounds(545, 330, 225, 30);
		add(txtNgayLapHD);
		
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
		txtTongTienBH.setBounds(580, 550, 190, 40);
		add(txtTongTienBH);
		
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
		
	}

}
