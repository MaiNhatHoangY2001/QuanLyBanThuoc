package app_QLT;

import java.awt.Color;

import javax.swing.JPanel;

import chucNang.RoundedPanel;

import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

public class FrmTest extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5384818405392700507L;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTable table_1;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Create the panel.
	 */
	public FrmTest() {
		setSize(1600, 1002);
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		/*
		 * Phan: Chuc Nang
		 */
		// Background Chuc Nang
		JPanel pnlNgang = new JPanel();
		pnlNgang.setBounds(new Rectangle(10, 10, 10, 10));
		pnlNgang.setLayout(null);
		pnlNgang.setBackground(Color.WHITE);
		pnlNgang.setBounds(0, 92, 1600, 910);
		add(pnlNgang);
		
		JPanel panel = new RoundedPanel(20);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 1054, 400);
		pnlNgang.add(panel);
		panel.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setMargin(new Insets(2, 14, 2, 2));
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(237, 11, 524, 40);
		panel.add(textField_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tìm theo tên", "Tìm theo mã"}));
		comboBox.setBounds(771, 11, 273, 40);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setBounds(10, 62, 290, 40);
		panel.add(comboBox_1);
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_1.setBounds(310, 62, 290, 40);
		panel.add(comboBox_1_1);
		
		JComboBox comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1_1_1.setBounds(610, 62, 290, 40);
		panel.add(comboBox_1_1_1);
		
		JButton btnNewButton_1 = new JButton("Lọc");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(20, 140, 255));
		btnNewButton_1.setBounds(910, 62, 134, 40);
		panel.add(btnNewButton_1);
		
		textField_5 = new JTextField();
		textField_5.setMargin(new Insets(2, 14, 2, 2));
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_5.setColumns(10);
		textField_5.setBounds(584, 349, 200, 39);
		panel.add(textField_5);
		
		JButton btnNewButton_1_1 = new JButton("Thêm vào giỏ hàng");
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBackground(new Color(20, 140, 255));
		btnNewButton_1_1.setBounds(794, 348, 250, 40);
		panel.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 1034, 231);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNhpThngTin = new JLabel("Nhập thông tin cần tìm:");
		lblNhpThngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpThngTin.setBounds(10, 10, 217, 40);
		panel.add(lblNhpThngTin);
		
		JLabel lblNhpSLng = new JLabel("Nhập số lượng:");
		lblNhpSLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpSLng.setBounds(434, 348, 140, 40);
		panel.add(lblNhpSLng);
		
		JButton btnNewButton = new JButton("Tìm kiếm");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(20,140,255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(1434, 49, 150, 40);
		pnlNgang.add(btnNewButton);
		
		textField = new JTextField();
		textField.setMargin(new Insets(2, 14, 2, 2));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(1074, 50, 350, 40);
		pnlNgang.add(textField);
		textField.setColumns(10);
		
		RoundedPanel panel_1 = new RoundedPanel(20);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(1074, 100, 510, 189);
		pnlNgang.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Tên khách hàng:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 17, 154, 40);
		panel_1.add(lblNewLabel);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSinThoi.setBounds(10, 74, 129, 40);
		panel_1.add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblaCh.setBounds(10, 131, 75, 40);
		panel_1.add(lblaCh);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(174, 17, 326, 40);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(149, 74, 351, 40);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("New label");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(95, 131, 405, 40);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnThmThngTin = new JButton("Thêm thông tin khách hàng");
		btnThmThngTin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThmThngTin.setForeground(Color.WHITE);
		btnThmThngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThmThngTin.setBackground(new Color(20, 140, 255));
		btnThmThngTin.setBounds(1074, 300, 510, 50);
		pnlNgang.add(btnThmThngTin);
		
		JButton btnCpNhtThng = new JButton("Cập nhật thông tin khách hàng");
		btnCpNhtThng.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCpNhtThng.setForeground(Color.WHITE);
		btnCpNhtThng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCpNhtThng.setBackground(new Color(20, 140, 255));
		btnCpNhtThng.setBounds(1074, 361, 510, 50);
		pnlNgang.add(btnCpNhtThng);
		
		RoundedPanel panel_1_1 = new RoundedPanel(20);
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(10, 735, 488, 164);
		pnlNgang.add(panel_1_1);
		
		JLabel lblMNhnVin = new JLabel("Mã nhân viên:");
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMNhnVin.setBounds(10, 11, 134, 40);
		panel_1_1.add(lblMNhnVin);
		
		JLabel lblTnNhnVin = new JLabel("Tên nhân viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnNhnVin.setBounds(10, 62, 140, 40);
		panel_1_1.add(lblTnNhnVin);
		
		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChcV.setBounds(10, 113, 83, 40);
		panel_1_1.add(lblChcV);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("New label");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(154, 11, 324, 40);
		panel_1_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("New label");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1.setBounds(154, 62, 324, 40);
		panel_1_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("New label");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1_1_1.setBounds(103, 113, 375, 40);
		panel_1_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 422, 1574, 302);
		pnlNgang.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		RoundedPanel panel_1_1_1 = new RoundedPanel(20);
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(Color.WHITE);
		panel_1_1_1.setBounds(508, 735, 556, 164);
		pnlNgang.add(panel_1_1_1);
		
		textField_9 = new JTextField();
		textField_9.setMargin(new Insets(2, 14, 2, 2));
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_9.setColumns(10);
		textField_9.setBounds(189, 12, 357, 40);
		panel_1_1_1.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setMargin(new Insets(2, 14, 2, 2));
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_10.setColumns(10);
		textField_10.setBounds(189, 63, 357, 40);
		panel_1_1_1.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setMargin(new Insets(2, 14, 2, 2));
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_11.setColumns(10);
		textField_11.setBounds(189, 114, 357, 40);
		panel_1_1_1.add(textField_11);
		
		JLabel lblTngThnhTin = new JLabel("Tổng thành tiền:");
		lblTngThnhTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTngThnhTin.setBounds(10, 11, 154, 40);
		panel_1_1_1.add(lblTngThnhTin);
		
		JLabel lblKhchHngTr = new JLabel("Khách hàng trả:");
		lblKhchHngTr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhchHngTr.setBounds(10, 62, 154, 40);
		panel_1_1_1.add(lblKhchHngTr);
		
		JLabel lblTinTrLi = new JLabel("Tiền trả lại:");
		lblTinTrLi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTinTrLi.setBounds(10, 113, 154, 40);
		panel_1_1_1.add(lblTinTrLi);
		
		JButton btnThanhTonV = new JButton("Thanh toán và in hóa đơn");
		btnThanhTonV.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhTonV.setForeground(Color.WHITE);
		btnThanhTonV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThanhTonV.setBackground(new Color(20, 140, 255));
		btnThanhTonV.setBounds(1074, 735, 510, 75);
		pnlNgang.add(btnThanhTonV);
		
		JButton btnThanhTon = new JButton("Thanh toán");
		btnThanhTon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnThanhTon.setForeground(Color.WHITE);
		btnThanhTon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThanhTon.setBackground(new Color(20, 140, 255));
		btnThanhTon.setBounds(1074, 821, 250, 75);
		pnlNgang.add(btnThanhTon);
		
		JButton btnHyB = new JButton("Hủy bỏ");
		btnHyB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHyB.setForeground(Color.WHITE);
		btnHyB.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHyB.setBackground(new Color(248,96,96));
		btnHyB.setBounds(1334, 821, 250, 75);
		pnlNgang.add(btnHyB);
		
		JLabel lblNhp = new JLabel("Nhập số điện thoại hoặc CMND của khách hàng:");
		lblNhp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhp.setBounds(1074, 11, 510, 40);
		pnlNgang.add(lblNhp);

		


	}
}
