package FurnitureLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import FurnitureLayout.Gagu.RotateLabel;

public class GaguLayout extends JFrame implements ActionListener {
	JPanel LEFT_PANEL = new JPanel(new BorderLayout()); // 탭 레이아웃 들어갈 패널
	JPanel RIGHT_PANEL = new JPanel(); // 가구 배치 레이아웃 들어갈 패널
	JPanel TOP_PANEL = new JPanel(new BorderLayout()); // 탭 레이아웃 추가/삭제 패널
	JPanel CENTER_PANEL = new JPanel(); // 탭 레이아웃 패널
	JPanel BOTTOM_PANEL = new JPanel(new BorderLayout()); // 메시지 창 패널
	JPanel ALIGNMENT_PANEL = new JPanel(); // 버튼 정렬을 위한 패널
	JPanel ALIGNMENT_PANEL2 = new JPanel(); // 텍스트필드 정렬을 위한 패널
	JTextField Warning = new JTextField(100); // 예외 처리 메세지 띄우기
	JButton SAVE_BUTTON = new JButton("배치 저장");
	JButton LOAD_BUTTON = new JButton("배치 불러오기");
	JLabel label = new JLabel();// 이미지를 갖고오기 위한 라벨

	// 메인 에서의 연계를 위한 객체 생성
	GaguTab gt = new GaguTab();
	GaguList gl = new GaguList(gt.getJTab());

	// ES
	JFileChooser fc;
	Save save = new Save(gt.getJTab());
	JTabbedPane tp;
	RotateLabel gaguBt[] = new RotateLabel[100];
	FurnitureSize fs = new FurnitureSize();

	public GaguLayout() {
		setTitle("가구 배치 프로그램");
		// 사용자 모니터 가로 세로 길이 구하기
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() * 0.98;

		setSize((int) width, (int) height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ES
		fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // 디렉토리 설정
		fc.setCurrentDirectory(new File("/javaworkspace/FurnitureLayout/")); // 현재 사용 디렉토리를 지정
		fc.setAcceptAllFileFilterUsed(true); // Filter 모든 파일 적용
		fc.setDialogTitle("타이틀"); // 창의 제목
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 파일 선택 모드

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save File", "ser"); // filter 확장자 추가
		fc.setFileFilter(filter); // 파일 필터를 추가
		// ES_end

		// TOP,CENTER패널을 LEFT 패널에 추가
		LEFT_PANEL.add(TOP_PANEL, "North");
		LEFT_PANEL.add(CENTER_PANEL, "Center");
		CENTER_PANEL.setLayout(null);
		tp = gt.getJTab();
		CENTER_PANEL.add(tp);

		// TOP 패널에 ALIGNMENT 패널 추가
		TOP_PANEL.add(ALIGNMENT_PANEL, "East");

		// ALIGNMENT PANEL에 저장, 불러오기 버튼 추가
		ALIGNMENT_PANEL.add(SAVE_BUTTON);
		ALIGNMENT_PANEL.add(LOAD_BUTTON);

		SAVE_BUTTON.addActionListener(this);
		LOAD_BUTTON.addActionListener(this);

		// LEFT 패널에 BOTTOM 패널을 추가, BOTTOM 패널에 정렬패널 2 추가
		LEFT_PANEL.add(BOTTOM_PANEL, "South");
		BOTTOM_PANEL.add(ALIGNMENT_PANEL2, "West");

		// Warning 메시지 텍스트필드를 정렬패널 2에 추가
		String str = ("가로 길이 = " + (int) width + ", 세로 길이 = " + (int) height);
		Warning.setText(str);
		Warning.setEditable(false);
		ALIGNMENT_PANEL2.add(Warning);

		// 가구배치 레이아웃을 RIGHT 패널에 추가
		RIGHT_PANEL.add(gl.getJPanel());

		add(LEFT_PANEL, "Center");
		add(RIGHT_PANEL, "East");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == SAVE_BUTTON) {
			// 안내 메시지 탭일 경우 저장 불가
			if (tp.getSelectedIndex() != (tp.getTabCount() - 1)) {
				System.out.println(tp.getSelectedIndex());
				save.saveImage();
				save.saveFile(gl.g);

				//현재 방의 탭을 찾아서 그 중에서 탭의 방 레이아웃을 가져오는 것
				JPanel panel = (JPanel) tp.getSelectedComponent();
				JPanel roomPanel = (JPanel) panel.getComponent(0);
				
				save.saveLayout(roomPanel);
				
				System.out.println("저장완료");
			} else {
				JOptionPane.showMessageDialog(null, "방을 생성한 뒤에 저장해주세요");
			}

		} else if (e.getSource() == LOAD_BUTTON) {
			System.out.println("열기");
			
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String path = fc.getCurrentDirectory().toString();
				String filename = fc.getSelectedFile().getName();
				//파일 이름 알아내기
				filename = filename.substring(0, filename.indexOf("."));

				String gaguPath = path + "\\" + filename + ".ser";
				String layoutPath = path + "\\" + filename + ".txt";

				Gagu[] gagu = save.openFile(gaguPath);
				JPanel panel = save.openLayout(layoutPath);
				
				gt.addNewTab(panel.getWidth(), panel.getHeight(), filename);
				
				JPanel curPanel = (JPanel) tp.getSelectedComponent();
				JPanel roomPanel = (JPanel) curPanel.getComponent(0);
				   
				int i = 0;
				while (gagu[i] != null) {
					gl.g[i] = new Gagu(panel, gagu[i]);
					gaguBt[i] = gl.g[i].getJLabel();
					
					roomPanel.add(gaguBt[i]);
					i++;
				}
				
				gl.GaguCount = i;
				
			}

			revalidate(); // 화면 재설정
			repaint();
		}
	}

	public static void main(String[] args) throws NullPointerException {
		new GaguLayout();
	}

}
