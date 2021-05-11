package app_QLT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FrmNhanVien extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV, txtTenNV, txtNgaySinh, txtLuong, txtDiaChi, txtSDT;
	private JRadioButton radNam, radNu;
	public FrmNhanVien() {
		
		setLayout(null);
		
		JPanel pThongTin = new JPanel();
		pThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		pThongTin.setBounds(10, 10, 760, 220);
		
		JLabel lblMa = new JLabel("Mã nhân viên");
		lblMa.setBounds(50, 40, 100, 20);
		add(lblMa);
		JLabel lblNgay = new JLabel("Ngày sinh");
		lblNgay.setBounds(50, 80, 100, 20);
		add(lblNgay);
		JLabel lblLuong = new JLabel("Lương nhân viên");
		lblLuong.setBounds(50, 120, 100, 20);
		add(lblLuong);
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setBounds(50, 160, 100, 20);
		add(lblDiaChi);
		
		txtMaNV = new JTextField();
		txtMaNV.setBounds(180, 40, 200, 30);
		add(txtMaNV);
		txtNgaySinh = new JTextField();
		txtNgaySinh.setBounds(180, 80, 200, 30);
		add(txtNgaySinh);
		txtLuong = new JTextField();
		txtLuong.setBounds(180, 120, 200, 30);
		add(txtLuong);
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(180, 160, 550, 30);
		add(txtDiaChi);
		
		JLabel lblTen = new JLabel("Tên nhân viên");
		lblTen.setBounds(400, 40, 100, 20);
		add(lblTen);
		txtTenNV = new JTextField();
		txtTenNV.setBounds(510, 40, 220, 30);
		add(txtTenNV);
		
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
		
		JLabel lblSDT = new JLabel("Số điện thoại");
		lblSDT.setBounds(400, 120, 100, 20);
		add(lblSDT);
		txtSDT = new JTextField();
		txtSDT.setBounds(510, 120, 220, 30);
		add(txtSDT);
		
		add(pThongTin);
		
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
