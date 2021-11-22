package app_QLT;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FrmDangNhap extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDangNhap frame = new FrmDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmDangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel pnlHead = new JPanel();
		pnlHead.setBounds(0, 0, 663, 78);
		pnlHead.setBackground(new Color(68, 142, 255));
		contentPane.add(pnlHead);
		pnlHead.setLayout(null);

		JLabel lblheader = new JLabel("ĐĂNG NHẬP");
		lblheader.setForeground(Color.WHITE);
		lblheader.setBounds(0, 0, 653, 78);
		pnlHead.add(lblheader);
		lblheader.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblheader.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 78, 663, 299);
		contentPane.add(panel);

	}
}
