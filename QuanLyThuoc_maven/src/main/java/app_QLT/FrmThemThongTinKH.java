package app_QLT;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chucNang.RoundedPanel;
import entity.KhachHang;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmThemThongTinKH extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885586881253507338L;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtCMND;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JComboBox cmbNgay;
	private JComboBox cmbThang;
	private JComboBox cmbNam;
	private JButton btnLoc;
	private JButton btnXoaRong;
	private JButton btnHuyBo;

	/**
	 * Create the frame.
	 */
	public FrmThemThongTinKH() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int tl = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát không?", "Thông báo hủy bỏ", JOptionPane.YES_NO_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		setSize(1016,839);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setResizable(false);
		
		RoundedPanel pnlInputKH = new RoundedPanel();
		pnlInputKH.setBackground(Color.WHITE);
		pnlInputKH.setShady(false);
		pnlInputKH.setBounds(85, 130, 830, 540);
		getContentPane().add(pnlInputKH);
		pnlInputKH.setLayout(null);
		
		JLabel lblTileInput = new JLabel("Nhập thông tin khách hàng");
		lblTileInput.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTileInput.setBounds(20, 10, 320, 40);
		pnlInputKH.add(lblTileInput);
		
		JPanel pnlInput = new JPanel();
		pnlInput.setBackground(Color.WHITE);
		pnlInput.setBorder(new MatteBorder(2, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnlInput.setBounds(20, 61, 790, 477);
		pnlInputKH.add(pnlInput);
		pnlInput.setLayout(null);
		
		JLabel lblTen = new JLabel("Tên khách hàng:");
		lblTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTen.setBounds(0, 33, 149, 40);
		pnlInput.add(lblTen);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiaChi.setBounds(0, 252, 149, 40);
		pnlInput.add(lblDiaChi);
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgaySinh.setBounds(0, 179, 149, 40);
		pnlInput.add(lblNgaySinh);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGioiTinh.setBounds(0, 106, 149, 40);
		pnlInput.add(lblGioiTinh);
		
		JLabel lblCMND = new JLabel("CMND:");
		lblCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCMND.setBounds(0, 398, 149, 40);
		pnlInput.add(lblCMND);
		
		txtTen = new JTextField();
		txtTen.setMargin(new Insets(2, 14, 2, 2));
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtTen.setBounds(159, 33, 631, 40);
		pnlInput.add(txtTen);
		txtTen.setColumns(10);
		
		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setBounds(0, 325, 149, 40);
		pnlInput.add(lblSDT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtDiaChi = new JTextField();
		txtDiaChi.setMargin(new Insets(2, 14, 2, 2));
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(159, 252, 631, 40);
		pnlInput.add(txtDiaChi);
		
		txtSDT = new JTextField();
		txtSDT.setMargin(new Insets(2, 14, 2, 2));
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBounds(159, 325, 631, 40);
		pnlInput.add(txtSDT);
		
		txtCMND = new JTextField();
		txtCMND.setMargin(new Insets(2, 14, 2, 2));
		txtCMND.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCMND.setColumns(10);
		txtCMND.setBounds(159, 398, 631, 40);
		pnlInput.add(txtCMND);
		
		radNam = new JRadioButton("Nam");
		radNam.setSelected(true);
		radNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radNam.setMargin(new Insets(2, 14, 2, 2));
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNam.setBounds(159, 106, 200, 40);
		pnlInput.add(radNam);
		
		radNu = new JRadioButton("Nữ");
		radNu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		radNu.setMargin(new Insets(2, 14, 2, 2));
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radNu.setBounds(374, 106, 200, 40);
		pnlInput.add(radNu);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radNam);
		group.add(radNu);
		
		DefaultComboBoxModel<String> modelNgay = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 31; i++) {
			modelNgay.addElement(i + "");
		}
		cmbNgay = new JComboBox();
		cmbNgay.setModel(modelNgay);
		cmbNgay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNgay.setBounds(159, 179, 200, 40);
		pnlInput.add(cmbNgay);
		
		DefaultComboBoxModel<String> modelThang = new DefaultComboBoxModel<String>();
		for (int i = 1; i <= 12; i++) {
			modelThang.addElement("Tháng " + i);
		}
		cmbThang = new JComboBox();
		cmbThang.setModel(modelThang);
		cmbThang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbThang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbThang.setBounds(374, 179, 200, 40);
		pnlInput.add(cmbThang);
		
		DefaultComboBoxModel<String> modelNam = new DefaultComboBoxModel<String>();
		for (int i = LocalDate.now().getYear(); i >= 1900; i--) {
			modelNam.addElement(i + "");
		}
		cmbNam = new JComboBox();
		cmbNam.setModel(modelNam);
		cmbNam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cmbNam.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmbNam.setBounds(590, 179, 200, 40);
		pnlInput.add(cmbNam);
		
		JPanel pnlTrang = new JPanel();
		pnlTrang.setBackground(Color.WHITE);
		pnlTrang.setBounds(0, 400, 1000, 400);
		getContentPane().add(pnlTrang);
		pnlTrang.setLayout(null);
		
		btnLoc = new JButton("lưu");
		btnLoc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = getThongTinKhachHang();
				System.out.println(kh);
			}
		});
		btnLoc.setForeground(Color.WHITE);
		btnLoc.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoc.setBackground(new Color(20, 140, 255));
		btnLoc.setBounds(100, 287, 200, 50);
		pnlTrang.add(btnLoc);
		
		btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaRong();
			}
		});
		btnXoaRong.setForeground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoaRong.setBackground(new Color(20, 140, 255));
		btnXoaRong.setBounds(400, 287, 200, 50);
		pnlTrang.add(btnXoaRong);
		
		btnHuyBo = new JButton("Hủy Bỏ");
		btnHuyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tl = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thoát không?", "Thông báo hủy bỏ", JOptionPane.YES_NO_OPTION);
				if (tl == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnHuyBo.setForeground(Color.WHITE);
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHuyBo.setBackground(new Color(248,96,96));
		btnHuyBo.setBounds(700, 287, 200, 50);
		pnlTrang.add(btnHuyBo);
		
		JPanel pnlXanh = new JPanel();
		pnlXanh.setBackground(new Color(20, 140, 255));
		pnlXanh.setBounds(0, 0, 1000, 400);
		getContentPane().add(pnlXanh);
		pnlXanh.setLayout(null);
		
		JLabel lblTitle = new JLabel("Thêm Thông Tin Khách Hàng");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblTitle.setBounds(209, 30, 582, 73);
		pnlXanh.add(lblTitle);
		
	}
	
	/**
	 *	Xóa rỗng các JTextField và default selected JButtonRadius và JComboBox 
	 */
	public void xoaRong() {
		txtTen.setText("");
		txtDiaChi.setText("");
		txtSDT.setText("");
		txtCMND.setText("");
		radNam.setSelected(true);
		cmbNgay.setSelectedIndex(0);
		cmbThang.setSelectedIndex(0);
		cmbNam.setSelectedIndex(0);
		txtTen.requestFocus();
	}
	
	/**
	 * Get KhachHang in JTextField and JButtoRadius
	 * 
	 * @return KhachHang: Thông tin khách hàng
	 */
	public KhachHang getThongTinKhachHang() {
		String ma = "KH001";
		String ten = txtTen.getText();
		Date ngaysinh = getDate();
		boolean gioitinh = radNam.isSelected();
		String diachi = txtDiaChi.getText();
		String sdt = txtSDT.getText();
		return new KhachHang(ma, ten, ngaysinh, gioitinh, diachi, sdt);
	}
	
	/**
	 * Get Date in JComboBox
	 * 
	 * @return Date: ngày, thángm năm
	 */
	public Date getDate() {
		int nam = Integer.parseInt(cmbNam.getSelectedItem().toString());
		String word[] = cmbThang.getSelectedItem().toString().split(" ");
		int thang = Integer.parseInt(word[1]);
		int ngay = Integer.parseInt(cmbNgay.getSelectedItem().toString());
		return new Date(nam, thang, ngay);
	}
}
