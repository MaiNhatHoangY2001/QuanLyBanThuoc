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
	 * chỉnh giờ cho lable
	 * 
	 * @param lblGio
	 */
	public static void setGio(JLabel lblGio, JLabel lblNgay) {
		TimerTask timerTask = new TimerTask() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
	 * Giúp di chuyển giao diện khi kéo thả giao diện
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
	 * Chức năng giúp làm cách 1 hàng của bảng có màu
	 */
	public static void setTableAlternateRow() {
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(255, 240, 226));
	}

	/**
	 * chức năng giúp xóa tất cả dữ liệu có trong bảng
	 * 
	 * @param model
	 */
	public static void clearDataTable(DefaultTableModel model) {
		while (model.getRowCount() > 0) {
			model.removeRow(0);
		}
	}

	/**
	 * Chức năng giúp thêm vào những hàng rỗng cho đẹp bảng
	 * 
	 * @param model
	 */
	public static void addNullDataTable(DefaultTableModel model) {
		for (int i = 0; i < 15; i++) {
			model.addRow(new Object[] { null, null, null, null, null, null, null });
		}
	}

	/**
	 * Chức năng căn phải các cột trong bảng
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
	 * Chức năng căn giữa các cột trong bảng
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
}
