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
	JScrollPane Scroller; // ��ũ�ѹ� ����
	JButton[] BtnImg = new JButton[100];
	int i =0;
	
	ImageIcon NoImg;
	ImageIcon[] img = new ImageIcon[100];
	String NoName;
	String[] name = new String[100];
	
	public furAdd() {
			
		setTitle("���� ��ġ");
		// Top �гο� �ڽ� ���̾ƿ� ����
		TopPanel.setLayout(new BoxLayout(TopPanel, BoxLayout.Y_AXIS));

		// Top �гο� ��ũ�ѹ� �߰�
		Scroller = new JScrollPane(TopPanel);
		Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//////////////////������ �� �г� �߰� �ϴ� �κ�
		BtnCreate();
		
		add(MainPanel); // �����ӿ� ���� �г� �߰�
		
		MainPanel.add(Scroller, "East");//���� �г� �����ʿ� ��ũ�ѹٸ� �ٿ���
		
		MainPanel.add(TopPanel);// ���� �гο� ž �г� �߰�
		MainPanel.add(BotPanel, "South");// ���� �гο� �� �г� �߰� & �Ʒ������� ����
				 
		 setBounds(300, 100, 600, 600);
		 setVisible(true);
		
	}
	
	public void BtnCreate() {
		NoImg = new ImageIcon("./Button_Image/Noimage.png");
		NoName = "�̸� ����";
		
		//�⺻ �̹��� ����
		 img[0] = new ImageIcon("./Button_Image/����.png");
		 img[1] = new ImageIcon("./Button_Image/��Ź.png");
		 img[2] = new ImageIcon("./Button_Image/����.png");
		 img[3] = new ImageIcon("./Button_Image/����.png");
		 img[4] = new ImageIcon("./Button_Image/å��.png");
		 img[5] = new ImageIcon("./Button_Image/ħ��.png");
		//�⺻ �̹����� �´� �� ����
		 name[0] = "����";
		 name[1] = "��Ź";
		 name[2] = "����";
		 name[3] = "����";
		 name[4] = "å��";
		 name[5] = "ħ��";
		 
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
