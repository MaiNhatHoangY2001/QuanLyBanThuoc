package app_QLT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabPane = new JTabbedPane();
	FrmThuoc frmThuoc = new FrmThuoc();
	FrmNhanVien frmNhanVien = new FrmNhanVien();
	FrmBanHang frmBanHang = new FrmBanHang();
	FrmThongKe frmThongKe = new FrmThongKe();
 
	public FrmMain() {
		super("Hệ thống quản lý thuốc");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel pNorth = new JPanel();
		JLabel tieuDe = new JLabel();
		pNorth.setLayout(new GridBagLayout());
		pNorth.setPreferredSize(new Dimension(800, 60));
		pNorth.add(tieuDe = new JLabel("Hệ Thống Quản Lý thuốc"));
		tieuDe.setFont(new Font("Roboto", Font.BOLD, 30));
		tieuDe.setForeground(Color.DARK_GRAY);
		pNorth.setBackground(new Color(191,247,249));
		add(pNorth, BorderLayout.NORTH);
		
		tabPane.setFont(new Font("Roboto", Font.BOLD, 15));
		tabPane.setBackground(new Color(144, 238, 237));
		tabPane.add("Quản lý thuốc", frmThuoc);
		tabPane.add("Quản lý nhân viên", frmNhanVien);
		tabPane.add("Quản lý bán hàng", frmBanHang);
		tabPane.add("Thống kê", frmThongKe);
		super.getContentPane().setBackground(new Color(191,247,249));
		super.getContentPane().add(tabPane);
	}

	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("icon/thuoc.png");
		FrmMain frm = new FrmMain();
		frm.setVisible(true);
		frm.setIconImage(icon.getImage());
	}
}
