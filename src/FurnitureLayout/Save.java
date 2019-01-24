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

//Gagu 클래스로 저장하도록 수정해야함
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
	 * // 직렬화가 불가능하므로 transient 선언 private transient JFileChooser fc;
	 */

	public Save(JTabbedPane tp) {
		this.tp = tp;
		/*
		 * fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		 * // 디렉토리 설정 fc.setCurrentDirectory(new
		 * File("/javaworkspace/FurnitureLayout/")); // 현재 사용 디렉토리를 지정
		 * fc.setAcceptAllFileFilterUsed(true); // Filter 모든 파일 적용
		 * fc.setDialogTitle("타이틀"); // 창의 제목
		 * fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드
		 * 
		 * FileNameExtensionFilter filter = new FileNameExtensionFilter("Save File",
		 * "ser"); // filter 확장자 추가 fc.setFileFilter(filter); // 파일 필터를 추가
		 *//*
			 * tp = new JTabbedPane(); JPanel p1 = new JPanel(); p1.setLayout(null);
			 * p1.setName("Tab1"); p2 = new JPanel(); // 이름 지정 p2.setName("Tab2");
			 * p2.setLayout(null); p2.setBackground(Color.CYAN); JPanel p3 = new JPanel();
			 * 
			 * tp.addTab("TAB 1", p1); tp.addTab("TAB 2", p2); tp.addTab("TAB 3", p3);
			 * 
			 * JPanel p4 = new JPanel(); bt = new JButton("저장"); open = new JButton("불러오기");
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
	 * bt) { // 저장 saveImage(); saveFile(g); System.out.println("저장완료"); } else if
	 * (e.getSource() == open) { // 불러오기 System.out.println("열기");
	 * 
	 * JPanel panel = (JPanel) tp.getSelectedComponent(); // 선택된 탭 가져오기 int
	 * returnVal = fc.showOpenDialog(this);
	 * 
	 * if (returnVal == JFileChooser.APPROVE_OPTION) { String filename =
	 * fc.getSelectedFile().getName(); System.out.println(filename);
	 * 
	 * Gagu[] gagu = openFile(filename); System.out.println(gagu.length);
	 * 
	 * panel.removeAll(); // 원래 있던 것 지우기
	 * 
	 * int i = 0; while(gagu[i] != null) { g[i] = new Gagu(panel, gagu[i]); bt2[i] =
	 * g[i].getJLabel(); System.out.println(bt2[i]); panel.add(bt2[i]); i++; }
	 * 
	 * }
	 * 
	 * revalidate(); // 화면 재설정 repaint(); } else { System.out.println("멍뭉이"); } }
	 */
	// JPanel 이미지 저장 (선택된 탭이 저장되도록)
	public void saveImage() {
		System.out.println("이미지 저장");
		JPanel panel = (JPanel) tp.getSelectedComponent(); // 선택된 탭 가져오기
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

	// 객체 저장(파일 이름, 객체배열)
	// ser은 직렬화된 객체를 저장할 때 사용하는 확장자
	public void saveFile(Gagu[] gagu) {
		Gagu[] gaguSave = new Gagu[100];

		// 삭제한 가구가 있을 시에 배열 앞으로 당기기
		int j = 0;
		for (int i = 0; i < gagu.length; i++) {
			try {
				if (gagu[i].getJLabel() != null) {
					gaguSave[j] = gagu[i];

					j++;
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("JLabel 없음");
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

	//방 패널 저장
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
	// 객체 열기
	public Gagu[] openFile(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			System.out.println("열음?");
			Gagu gaguFile[] = (Gagu[]) ois.readObject();

			System.out.println("읽음?");

			ois.close();

			return gaguFile;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("오브젝트 읽기 실패");

			return null;
		}

	}
	
	public JPanel openLayout(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			System.out.println("열음?");
			JPanel jpanel = (JPanel) ois.readObject();

			System.out.println("읽음?");

			ois.close();

			return jpanel;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("오브젝트 읽기 실패");

			return null;
		}
	}
}
