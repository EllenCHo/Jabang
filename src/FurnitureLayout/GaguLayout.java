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
	JPanel LEFT_PANEL = new JPanel(new BorderLayout()); // �� ���̾ƿ� �� �г�
	JPanel RIGHT_PANEL = new JPanel(); // ���� ��ġ ���̾ƿ� �� �г�
	JPanel TOP_PANEL = new JPanel(new BorderLayout()); // �� ���̾ƿ� �߰�/���� �г�
	JPanel CENTER_PANEL = new JPanel(); // �� ���̾ƿ� �г�
	JPanel BOTTOM_PANEL = new JPanel(new BorderLayout()); // �޽��� â �г�
	JPanel ALIGNMENT_PANEL = new JPanel(); // ��ư ������ ���� �г�
	JPanel ALIGNMENT_PANEL2 = new JPanel(); // �ؽ�Ʈ�ʵ� ������ ���� �г�
	JTextField Warning = new JTextField(100); // ���� ó�� �޼��� ����
	JButton SAVE_BUTTON = new JButton("��ġ ����");
	JButton LOAD_BUTTON = new JButton("��ġ �ҷ�����");
	JLabel label = new JLabel();// �̹����� ������� ���� ��

	// ���� ������ ���踦 ���� ��ü ����
	GaguTab gt = new GaguTab();
	GaguList gl = new GaguList(gt.getJTab());

	// ES
	JFileChooser fc;
	Save save = new Save(gt.getJTab());
	JTabbedPane tp;
	RotateLabel gaguBt[] = new RotateLabel[100];
	FurnitureSize fs = new FurnitureSize();

	public GaguLayout() {
		setTitle("���� ��ġ ���α׷�");
		// ����� ����� ���� ���� ���� ���ϱ�
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight() * 0.98;

		setSize((int) width, (int) height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ES
		fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); // ���丮 ����
		fc.setCurrentDirectory(new File("/javaworkspace/FurnitureLayout/")); // ���� ��� ���丮�� ����
		fc.setAcceptAllFileFilterUsed(true); // Filter ��� ���� ����
		fc.setDialogTitle("Ÿ��Ʋ"); // â�� ����
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // ���� ���� ���

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Save File", "ser"); // filter Ȯ���� �߰�
		fc.setFileFilter(filter); // ���� ���͸� �߰�
		// ES_end

		// TOP,CENTER�г��� LEFT �гο� �߰�
		LEFT_PANEL.add(TOP_PANEL, "North");
		LEFT_PANEL.add(CENTER_PANEL, "Center");
		CENTER_PANEL.setLayout(null);
		tp = gt.getJTab();
		CENTER_PANEL.add(tp);

		// TOP �гο� ALIGNMENT �г� �߰�
		TOP_PANEL.add(ALIGNMENT_PANEL, "East");

		// ALIGNMENT PANEL�� ����, �ҷ����� ��ư �߰�
		ALIGNMENT_PANEL.add(SAVE_BUTTON);
		ALIGNMENT_PANEL.add(LOAD_BUTTON);

		SAVE_BUTTON.addActionListener(this);
		LOAD_BUTTON.addActionListener(this);

		// LEFT �гο� BOTTOM �г��� �߰�, BOTTOM �гο� �����г� 2 �߰�
		LEFT_PANEL.add(BOTTOM_PANEL, "South");
		BOTTOM_PANEL.add(ALIGNMENT_PANEL2, "West");

		// Warning �޽��� �ؽ�Ʈ�ʵ带 �����г� 2�� �߰�
		String str = ("���� ���� = " + (int) width + ", ���� ���� = " + (int) height);
		Warning.setText(str);
		Warning.setEditable(false);
		ALIGNMENT_PANEL2.add(Warning);

		// ������ġ ���̾ƿ��� RIGHT �гο� �߰�
		RIGHT_PANEL.add(gl.getJPanel());

		add(LEFT_PANEL, "Center");
		add(RIGHT_PANEL, "East");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == SAVE_BUTTON) {
			// �ȳ� �޽��� ���� ��� ���� �Ұ�
			if (tp.getSelectedIndex() != (tp.getTabCount() - 1)) {
				System.out.println(tp.getSelectedIndex());
				save.saveImage();
				save.saveFile(gl.g);

				//���� ���� ���� ã�Ƽ� �� �߿��� ���� �� ���̾ƿ��� �������� ��
				JPanel panel = (JPanel) tp.getSelectedComponent();
				JPanel roomPanel = (JPanel) panel.getComponent(0);
				
				save.saveLayout(roomPanel);
				
				System.out.println("����Ϸ�");
			} else {
				JOptionPane.showMessageDialog(null, "���� ������ �ڿ� �������ּ���");
			}

		} else if (e.getSource() == LOAD_BUTTON) {
			System.out.println("����");
			
			int returnVal = fc.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String path = fc.getCurrentDirectory().toString();
				String filename = fc.getSelectedFile().getName();
				//���� �̸� �˾Ƴ���
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

			revalidate(); // ȭ�� �缳��
			repaint();
		}
	}

	public static void main(String[] args) throws NullPointerException {
		new GaguLayout();
	}

}
