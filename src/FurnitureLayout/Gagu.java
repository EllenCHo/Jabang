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
	//�� x, y ��ǥ
	int x, y;

	/*
	 * panel : �̹����� ���� �г� 
	 * url : �̹��� ��� 
	 * width : ����ڰ� ������ ���� ���� ���� 
	 * height : ����ڰ� ������
	 * ���� ���� ����
	 */
	public Gagu(JPanel panel, String url, int width, int height) {
		Image image = null;
		p = panel;
		
		//���� x,y��ǥ �ʱ�ȭ
		x = 5;
		y = 5;
		this.url = url;
		this.width = width;
		this.height = height;
		
		try {
			// �̹��� �о����
			image = ImageIO.read(new File(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon ic = new ImageIcon(image);
		lb = new RotateLabel(ic);
		
		// ���콺 ������
		lb.addMouseListener(new MyMouseListener());
		lb.addMouseMotionListener(new MyMouseListener());
		lb.setBorder(new LineBorder(Color.BLACK, 1));

		pm = new JPopupMenu();
		delete = new JMenuItem("����");
		rot = new JMenuItem("ȸ��");
		cancel = new JMenuItem("���");

		// �׼� ������
		delete.addActionListener(this);
		rot.addActionListener(this);
		cancel.addActionListener(this);
		pm.add(delete);
		pm.add(rot);
		pm.addSeparator();
		pm.add(cancel);

		lb.setBounds(20, 20, width, height);
	}

	//���� �ҷ��� �� �����
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
			// �̹��� �о����
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
		
		// ���콺 ������
		this.lb.addMouseListener(new MyMouseListener());
		this.lb.addMouseMotionListener(new MyMouseListener());

		pm = new JPopupMenu();
		delete = new JMenuItem("����");
		rot = new JMenuItem("ȸ��");
		cancel = new JMenuItem("���");
		
		// �׼� ������
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

	// label �ű��
	class MyMouseListener extends MouseAdapter implements MouseMotionListener, MouseListener {
		public void mousePressed(MouseEvent e) { // ��������
			focus = (JLabel) e.getSource();
			System.out.println("����");

			// ��Ŭ������ �ÿ� �˾� â ��Ÿ���� �ϱ�
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

		public void mouseDragged(MouseEvent e) { // �巡���Ͻ�
			int x = e.getX();
			int y = e.getY();

			// ���� ��ġ ��ǥ
			int lx = x + focus.getX() - focus.getWidth() / 2;
			int rx = x + focus.getX() + focus.getWidth() / 2;
			int ty = y + focus.getY() - focus.getHeight() / 2;
			int by = y + focus.getY() + focus.getHeight() / 2;

			// ������ ����� ���� ��쿡�� �巡�� ���
			if (lx > 0 && rx < p.getWidth() && ty > 0 && by < p.getHeight() ) {
				focus.setLocation(x + focus.getX() - focus.getWidth() / 2, y + focus.getY() - focus.getHeight() / 2); // ��ġ																							// ����
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// ���� ��ư�� ������ ��� ���� ���� �� repaint()
			
		if (e.getSource() == delete) {
			System.out.println("����");
			p.remove(lb);

			lb = null;
			// Garbage Collector�� ��û�Ͽ� null���� ���� �����Ⱚ ����
			System.gc();

			p.repaint();
		} else if (e.getSource() == rot) {
			System.out.println("ȸ��");
			angle += 45;
			
			System.out.println(angle);
		} else if (e.getSource() == cancel) {
			// ���Ļ�
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