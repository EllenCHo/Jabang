package FurnitureLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

public class Gagu implements ActionListener, Serializable {
	JPanel p;
	RotateLabel lb;
	JLabel focus;
	JPopupMenu pm;
	JMenuItem delete, rot, cancel;
	int angle = 0;
	String url;
	int width;
	int height;
	//라벨 x, y 좌표
	int x, y;

	/*
	 * panel : 이미지를 넣을 패널 
	 * url : 이미지 경로 
	 * width : 사용자가 지정한 가구 가로 길이 
	 * height : 사용자가 지정한
	 * 가구 세로 길이
	 */
	public Gagu(JPanel panel, String url, int width, int height) {
		Image image = null;
		p = panel;
		
		//가구 x,y좌표 초기화
		x = 5;
		y = 5;
		this.url = url;
		this.width = width;
		this.height = height;
		
		try {
			// 이미지 읽어오기
			image = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon ic = new ImageIcon(image);
		lb = new RotateLabel(ic);
		
		// 마우스 리스너
		lb.addMouseListener(new MyMouseListener());
		lb.addMouseMotionListener(new MyMouseListener());
		lb.setBorder(new LineBorder(Color.BLACK, 1));

		pm = new JPopupMenu();
		delete = new JMenuItem("삭제");
		rot = new JMenuItem("회전");
		cancel = new JMenuItem("취소");

		// 액션 리스너
		delete.addActionListener(this);
		rot.addActionListener(this);
		cancel.addActionListener(this);
		pm.add(delete);
		pm.add(rot);
		pm.addSeparator();
		pm.add(cancel);

		lb.setBounds(20, 20, width, height);
	}

	//가구 불러올 때 재생성
	public Gagu(JPanel panel, Gagu g) {
		this.p = panel;
		
		Image image = null;
		
		this.url = g.url;
		this.width = g.width;
		this.height = g.height;
		this.x = g.x;
		this.y = g.y;
		
		System.out.println(url + width + height);
		
		try {
			// 이미지 읽어오기
			image = ImageIO.read(new File(this.url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image = image.getScaledInstance(this.width, this.height, Image.SCALE_SMOOTH);
		ImageIcon ic = new ImageIcon(image);
		this.lb = new RotateLabel(ic);
		lb.setLocation(g.x, g.y);
		lb.setSize(this.width, this.height);
		this.angle = g.angle;

		lb.setBorder(new LineBorder(Color.BLACK, 1));
		
		// 마우스 리스너
		this.lb.addMouseListener(new MyMouseListener());
		this.lb.addMouseMotionListener(new MyMouseListener());

		pm = new JPopupMenu();
		delete = new JMenuItem("삭제");
		rot = new JMenuItem("회전");
		cancel = new JMenuItem("취소");
		
		// 액션 리스너
		delete.addActionListener(this);
		rot.addActionListener(this);
		cancel.addActionListener(this);
		pm.add(delete);
		pm.add(rot);
		pm.addSeparator();
		pm.add(cancel);
	}

	public RotateLabel getJLabel() {
		return lb;
	}

	// label 옮기기
	class MyMouseListener extends MouseAdapter implements MouseMotionListener, MouseListener {
		public void mousePressed(MouseEvent e) { // 눌린순간
			focus = (JLabel) e.getSource();
			System.out.println("선택");

			// 우클릭했을 시에 팝업 창 나타나게 하기
			if (e.getButton() == MouseEvent.BUTTON3) {
				System.out.println("click");
				pm.show(focus, e.getX(), e.getY());		
			}
			
			int x = focus.getX();
			int y = focus.getY();
			focus.setLocation(x, y);
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			x = lb.getX();
			y = lb.getY();		
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			lb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		public void mouseDragged(MouseEvent e) { // 드래그일시
			int x = e.getX();
			int y = e.getY();

			// 사진 위치 좌표
			int lx = x + focus.getX() - focus.getWidth() / 2;
			int rx = x + focus.getX() + focus.getWidth() / 2;
			int ty = y + focus.getY() - focus.getHeight() / 2;
			int by = y + focus.getY() + focus.getHeight() / 2;

			// 범위를 벗어나지 않을 경우에만 드래그 허용
			if (lx > 0 && rx < p.getWidth() && ty > 0 && by < p.getHeight() ) {
				focus.setLocation(x + focus.getX() - focus.getWidth() / 2, y + focus.getY() - focus.getHeight() / 2); // 위치																							// 조정
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 삭제 버튼을 눌렀을 경우 가구 삭제 후 repaint()
			
		if (e.getSource() == delete) {
			System.out.println("삭제");
			p.remove(lb);

			lb = null;
			// Garbage Collector를 요청하여 null값을 가진 쓰레기값 삭제
			System.gc();

			p.repaint();
		} else if (e.getSource() == rot) {
			System.out.println("회전");
			angle += 45;
			
			System.out.println(angle);
		} else if (e.getSource() == cancel) {
			// 형식상
		}

	}

	class RotateLabel extends JLabel {
		public RotateLabel(ImageIcon icon) {
			super(icon);
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			AffineTransform aT = g2.getTransform();
			Shape oldshape = g2.getClip();
			double x = getWidth() / 2.0;
			double y = getHeight() / 2.0;
			aT.rotate(Math.toRadians(angle), x, y);
			g2.setTransform(aT);
			g2.setClip(oldshape);
			super.paintComponent(g);
			
			lb.getParent().repaint();	
		}

	}
}