package chucNang;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class ChucNang {
	/**
	 * chá»‰nh giá» cho lable
	 * 
	 * @param lblGio
	 */
	public static void setGio(JLabel lblGio,JLabel lblNgay) {
		TimerTask timerTask = new TimerTask() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
				lblNgay.setText(formatter.format(LocalDate.now()));
				lblGio.setText((new Date().getHours() >= 10 ? "" : "0") + (new Date().getHours())
						+ ((new Date().getSeconds() % 2) != 0 ? " " : ":")
						+ ((new Date().getMinutes() >= 10 ? "" : "0") + (new Date().getMinutes())));
			}

		};
		long delay = 1000L;
		Timer timer = new Timer("Timer");
		timer.schedule(timerTask, 0, delay);
	}

	/**
	 * GiÃºp di chuyá»ƒn giao diá»‡n khi kÃ©o tháº£ giao diá»‡n
	 * 
	 * @param frame
	 */
	public static void setDiChuyenGD(JFrame frame) {

		AtomicInteger xClicked = new AtomicInteger(0);
		AtomicInteger yClicked = new AtomicInteger(0);

		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xClicked.set(e.getX());
				yClicked.set(e.getY());
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				frame.setLocation(x - xClicked.get(), y - yClicked.get());
			}
		});
	}

	/**
	 * Chá»©c nÄƒng giÃºp lÃ m cÃ¡ch 1 hÃ ng cá»§a báº£ng cÃ³ mÃ u
	 */
	public static void setTableAlternateRow() {
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(255, 240, 226));
	}

	/**
	 * chá»©c nÄƒng giÃºp xÃ³a táº¥t cáº£ dá»¯ liá»‡u cÃ³ trong báº£ng
	 * 
	 * @param model
	 */
	public static void clearDataTable(DefaultTableModel model) {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	/**
	 * Chá»©c nÄƒng giÃºp thÃªm vÃ o nhá»¯ng hÃ ng rá»—ng cho Ä‘áº¹p báº£ng
	 * 
	 * @param model
	 */
	public static void addNullDataTable(DefaultTableModel model) {
		for (int i = 0; i < 15; i++) {
			model.addRow(new Object[] { null, null, null, null, null, null, null });
		}
	}

	/**
	 * Chá»©c nÄƒng cÄƒn pháº£i cÃ¡c cá»™t trong báº£ng
	 * 
	 * @param list
	 */
	public static void setRightAlignmentTable(int[] list, JTable table) {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		for (int i : list) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}

	}

	/**
	 * Chá»©c nÄƒng cÄƒn giá»¯a cÃ¡c cá»™t trong báº£ng
	 * 
	 * @param list
	 */
	public static void setCenterAlignmentTable(int[] list, JTable table) {
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i : list) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}

	}
	
//	public static void setLabelName(String name,JPanel panel,Gui_Chinh fChinh,Gui_DangNhap fDangNhap){
//		/**
//		 * TÃªn Ä‘Äƒng nháº­p
//		 */
//		
//		JLabel lblTenDN = new JLabel(name);
//		lblTenDN.setHorizontalTextPosition(SwingConstants.LEFT);
//		lblTenDN.setHorizontalAlignment(SwingConstants.RIGHT);
//		lblTenDN.setForeground(Color.WHITE);
//		lblTenDN.setFont(new Font("Tahoma", Font.PLAIN, 24));
//		lblTenDN.setBounds(1272, 11, 260, 33);
//		panel.add(lblTenDN);
//
//		JLabel lblIconUser = new JLabel("");
//		Image imgUser = new ImageIcon("img\\userNho.png").getImage();
//		lblIconUser.setIcon(new ImageIcon(imgUser));
//		lblIconUser.setBounds(1536, 10, 38, 30);
//		panel.add(lblIconUser);
//
//		/**
//		 * ÄÄƒng xuáº¥t
//		 */
//		JLabel lblDangXuat = new JLabel("ÄÄƒng xuáº¥t");
//		lblDangXuat.setForeground(Color.WHITE);
//		lblDangXuat.setFont(new Font("Arial", Font.PLAIN, 24));
//		lblDangXuat.setBounds(1419, 55, 110, 24);
//		panel.add(lblDangXuat);
//
//		JLabel lblIconDX = new JLabel("");
//		Image imgDX = new ImageIcon("img\\thoatNho.png").getImage();
//		lblIconDX.setIcon(new ImageIcon(imgDX));
//		lblIconDX.setBounds(1536, 50, 38, 30);
//		panel.add(lblIconDX);
//		lblDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		
//		lblDangXuat.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseReleased(MouseEvent e) {
//				
//			}
//			
//			@Override
//			public void mousePressed(MouseEvent e) {
//				int temp= JOptionPane.showConfirmDialog(fChinh, "Báº¡n cÃ³ muá»‘n Ä‘Äƒng xuáº¥t","ThÃ´ng bÃ¡o", JOptionPane.YES_NO_OPTION);
//				if(temp==JOptionPane.YES_OPTION) {
//					fDangNhap.setVisible(true);
//					fChinh.setVisible(false);
//				}
//				
//			}
//			
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		
//	}
}
