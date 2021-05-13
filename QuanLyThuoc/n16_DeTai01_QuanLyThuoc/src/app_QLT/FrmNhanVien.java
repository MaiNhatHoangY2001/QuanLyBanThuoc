package app_QLT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV, txtTenNV, txtNgaySinh, txtLuong, txtDiaChi, txtSDT, txtTim;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cboLoaiTK;
	private JButton btnTim, btnThem, btnXoa, btnXoaRong, btnSua;
	private String column[] = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Lương", "Số điện thoại",
			"Địa chỉ" };
	private JTable tableNV;
	private DefaultTableModel modelNV;

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
		txtNgaySinh = new JTextField();
		txtNgaySinh.setBounds(180, 80, 200, 30);
		add(txtNgaySinh);

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
}
