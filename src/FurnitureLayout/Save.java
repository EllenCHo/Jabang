package FurnitureLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import FurnitureLayout.Gagu.RotateLabel;

//Gagu Ŭ������ �����ϵ��� �����ؾ���
public class Save implements Serializable {
	JPanel p;
	JPanel p2;
	JButton bt;
	JButton open;
	RotateLabel bt2[] = new RotateLabel[4];
	JTabbedPane tp;
	JLabel la;
	JLabel focus;
	Gagu g[] = new Gagu[3];
	/*
	 * // ����ȭ�� �Ұ����ϹǷ� transient ���� private transient JFileChooser fc;
	 */

	public Save(JTabbedPane tp) {
		this.tp = tp;
		/*
		 * fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		 * // ���丮 ���� fc.setCurrentDirectory(new
		 * File("/javaworkspace/FurnitureLayout/")); // ���� ��� ���丮�� ����
		 * fc.setAcceptAllFileFilterUsed(true); // Filter ��� ���� ����
		 * fc.setDialogTitle("Ÿ��Ʋ"); // â�� ����
		 * fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ���� ���� ���
		 * 
		 * FileNameExtensionFilter filter = new FileNameExtensionFilter("Save File",
		 * "ser"); // filter Ȯ���� �߰� fc.setFileFilter(filter); // ���� ���͸� �߰�
		 *//*
			 * tp = new JTabbedPane(); JPanel p1 = new JPanel(); p1.setLayout(null);
			 * p1.setName("Tab1"); p2 = new JPanel(); // �̸� ���� p2.setName("Tab2");
			 * p2.setLayout(null); p2.setBackground(Color.CYAN); JPanel p3 = new JPanel();
			 * 
			 * tp.addTab("TAB 1", p1); tp.addTab("TAB 2", p2); tp.addTab("TAB 3", p3);
			 * 
			 * JPanel p4 = new JPanel(); bt = new JButton("����"); open = new JButton("�ҷ�����");
			 * 
			 * p4.add(open); p4.add(bt);
			 * 
			 * g[0] = new Gagu(p2, "./bed.jpg", 120, 70); bt2[0] = g[0].getJLabel();
			 * p2.add(bt2[0]);
			 * 
			 * g[1] = new Gagu(p2, "./desk.JPG", 50, 30); bt2[1] = g[1].getJLabel();
			 * p2.add(bt2[1]);
			 * 
			 * g[2] = new Gagu(p2, "./cloth.jpg", 70, 120); bt2[2] = g[2].getJLabel();
			 * p2.add(bt2[2]);
			 * 
			 * bt.addActionListener(this); open.addActionListener(this); add(p4, "North");
			 * add(tp, "Center"); setSize(900, 600); setVisible(true);
			 */
	}

	/*
	 * @Override public void actionPerformed(ActionEvent e) { if (e.getSource() ==
	 * bt) { // ���� saveImage(); saveFile(g); System.out.println("����Ϸ�"); } else if
	 * (e.getSource() == open) { // �ҷ����� System.out.println("����");
	 * 
	 * JPanel panel = (JPanel) tp.getSelectedComponent(); // ���õ� �� �������� int
	 * returnVal = fc.showOpenDialog(this);
	 * 
	 * if (returnVal == JFileChooser.APPROVE_OPTION) { String filename =
	 * fc.getSelectedFile().getName(); System.out.println(filename);
	 * 
	 * Gagu[] gagu = openFile(filename); System.out.println(gagu.length);
	 * 
	 * panel.removeAll(); // ���� �ִ� �� �����
	 * 
	 * int i = 0; while(gagu[i] != null) { g[i] = new Gagu(panel, gagu[i]); bt2[i] =
	 * g[i].getJLabel(); System.out.println(bt2[i]); panel.add(bt2[i]); i++; }
	 * 
	 * }
	 * 
	 * revalidate(); // ȭ�� �缳�� repaint(); } else { System.out.println("�۹���"); } }
	 */
	// JPanel �̹��� ���� (���õ� ���� ����ǵ���)
	public void saveImage() {
		System.out.println("�̹��� ����");
		JPanel panel = (JPanel) tp.getSelectedComponent(); // ���õ� �� ��������
		String name = tp.getSelectedComponent().getName();
		System.out.println(name);
		BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
		panel.paint(image.getGraphics());

		try {

			ImageIO.write(image, "png", new File(name + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ü ����(���� �̸�, ��ü�迭)
	// ser�� ����ȭ�� ��ü�� ������ �� ����ϴ� Ȯ����
	public void saveFile(Gagu[] gagu) {
		Gagu[] gaguSave = new Gagu[100];

		// ������ ������ ���� �ÿ� �迭 ������ ����
		int j = 0;
		for (int i = 0; i < gagu.length; i++) {
			try {
				if (gagu[i].getJLabel() != null) {
					gaguSave[j] = gagu[i];

					j++;
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("JLabel ����");
			}
		}

		String name = tp.getSelectedComponent().getName();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(name + ".ser")));
			oos.writeObject(gaguSave);

			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//�� �г� ����
	public void saveLayout(JPanel jpanel) {
		String name = tp.getSelectedComponent().getName();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(name + ".txt")));
			oos.writeObject(jpanel);

			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ��ü ����
	public Gagu[] openFile(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			System.out.println("����?");
			Gagu gaguFile[] = (Gagu[]) ois.readObject();

			System.out.println("����?");

			ois.close();

			return gaguFile;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("������Ʈ �б� ����");

			return null;
		}

	}
	
	public JPanel openLayout(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			System.out.println("����?");
			JPanel jpanel = (JPanel) ois.readObject();

			System.out.println("����?");

			ois.close();

			return jpanel;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("������Ʈ �б� ����");

			return null;
		}
	}
}
