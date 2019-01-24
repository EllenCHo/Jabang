package FurnitureLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class furAdd extends JFrame implements ActionListener {
	JPanel MainPanel = new JPanel();
	JPanel TopPanel = new JPanel();
	JPanel BotPanel = new JPanel();
	JScrollPane Scroller; // 스크롤바 생성
	JButton[] BtnImg = new JButton[100];
	int i =0;
	
	ImageIcon NoImg;
	ImageIcon[] img = new ImageIcon[100];
	String NoName;
	String[] name = new String[100];
	
	public furAdd() {
			
		setTitle("가구 배치");
		// Top 패널에 박스 레이아웃 적용
		TopPanel.setLayout(new BoxLayout(TopPanel, BoxLayout.Y_AXIS));

		// Top 패널에 스크롤바 추가
		Scroller = new JScrollPane(TopPanel);
		Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//////////////////프레임 및 패널 추가 하는 부분
		BtnCreate();
		
		add(MainPanel); // 프레임에 메인 패널 추가
		
		MainPanel.add(Scroller, "East");//메인 패널 오른쪽에 스크롤바를 붙여라
		
		MainPanel.add(TopPanel);// 메인 패널에 탑 패널 추가
		MainPanel.add(BotPanel, "South");// 메인 패널에 봇 패널 추가 & 아래쪽으로 정렬
				 
		 setBounds(300, 100, 600, 600);
		 setVisible(true);
		
	}
	
	public void BtnCreate() {
		NoImg = new ImageIcon("./Button_Image/Noimage.png");
		NoName = "이름 없음";
		
		//기본 이미지 세팅
		 img[0] = new ImageIcon("./Button_Image/쇼파.png");
		 img[1] = new ImageIcon("./Button_Image/식탁.png");
		 img[2] = new ImageIcon("./Button_Image/옷장.png");
		 img[3] = new ImageIcon("./Button_Image/의자.png");
		 img[4] = new ImageIcon("./Button_Image/책상.png");
		 img[5] = new ImageIcon("./Button_Image/침대.png");
		//기본 이미지에 맞는 라벨 세팅
		 name[0] = "쇼파";
		 name[1] = "식탁";
		 name[2] = "옷장";
		 name[3] = "의자";
		 name[4] = "책상";
		 name[5] = "침대";
		 
		 for (int i=0; i<6; i++) {
			 BtnImg[i] = new JButton(name[i], img[i]);
			 TopPanel.add(BtnImg[i]);
			 BtnImg[i].addActionListener(this);
		 }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
