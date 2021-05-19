package app_QLT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class frmThemNCC extends JFrame implements ActionListener {

	private DefaultTableModel dm;
	private JTable tb;
	private JTextField txtMaNCC;
	private JTextField txtTenNCC;
	private JTextField txtIDNuoc;
	private JTextField txtDiaChi;
	private JTextField txtTenNUoc;
	private JButton btnThem;
	private JButton btnXoaRong;
	private JButton btnXoa;
	private JButton btnSua;

	public frmThemNCC() {
		// TODO Auto-generated constructor stub
		setSize(700, 630);
		setTitle("Thêm nhà cung cấp");
		setLocationRelativeTo(null);

		setLayout(null);

		/**
		 * Bảng danh sách nhà cung cấp
		 */
		String truong[] = { "Mã NCC", "Tên NCC", "Địa chỉ", "ID Nước", "Tên Nước" };
		dm = new DefaultTableModel(truong, 0);
		tb = new JTable(dm);
		JScrollPane thanhTruoc = new JScrollPane(tb);
		thanhTruoc.setBounds(20, 30, 645, 250);
		add(thanhTruoc);

		/**
		 * Các lable
		 */
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		lblMaNCC.setBounds(100, 330, 120, 30);
		add(lblMaNCC);
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
		lblTenNCC.setBounds(100, 370, 120, 30);
		add(lblTenNCC);
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		lblDiaChi.setBounds(100, 410, 120, 20);
		add(lblDiaChi);
		JLabel lblIDNuoc = new JLabel("ID Nước SX:");
		lblIDNuoc.setBounds(100, 450, 120, 20);
		add(lblIDNuoc);
		JLabel lblTenNuoc = new JLabel("Tên Nước SX:");
		lblTenNuoc.setBounds(100, 490, 120, 20);
		add(lblTenNuoc);

		/**
		 * Các JTextField
		 */
		txtMaNCC = new JTextField();
		txtMaNCC.setBounds(220, 330, 360, 30);
		add(txtMaNCC);
		txtTenNCC = new JTextField();
		txtTenNCC.setBounds(220, 370, 360, 30);
		add(txtTenNCC);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(220, 410, 360, 30);
		add(txtDiaChi);
		txtIDNuoc = new JTextField();
		txtIDNuoc.setBounds(220, 450, 360, 30);
		add(txtIDNuoc);
		txtTenNUoc = new JTextField();
		txtTenNUoc.setBounds(220, 490, 360, 30);
		add(txtTenNUoc);

		/**
		 * Các button
		 */
		btnThem = new JButton("Thêm");
		btnThem.setBounds(105, 530, 100, 35);
		add(btnThem);
		btnXoaRong = new JButton("Xóa Rổng");
		btnXoaRong.setBounds(230, 530, 100, 35);
		add(btnXoaRong);
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(355, 530, 100, 35);
		add(btnXoa);
		btnSua = new JButton("Sữa");
		btnSua.setBounds(480, 530, 100, 35);
		add(btnSua);

		/**
		 * set bordertitle cho bảng
		 */
		JPanel pBang = new JPanel();
		pBang.setBorder(BorderFactory.createTitledBorder("Danh sách các nhà cung cấp"));
		pBang.setBounds(10, 10, 665, 280);
		add(pBang);

		JPanel pChucNang = new JPanel();
		pChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		pChucNang.setBounds(10, 300, 665, 280);
		add(pChucNang);

		btnThem.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {

		} else if (o.equals(btnXoaRong)) {
			txtMaNCC.setText("");
			txtTenNCC.setText("");
			txtDiaChi.setText("");
			txtIDNuoc.setText("");
			txtTenNCC.setText("");
			txtMaNCC.requestFocus();
		} else if (o.equals(btnXoa)) {

		} else if (o.equals(btnSua)) {

		}
	}

}
