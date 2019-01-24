package FurnitureLayout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class RoomAdd extends JFrame implements ActionListener {
   public JButton okButton;
   public JButton cancelButton;
   public String name, width, column;
   JTextField nameText;
   JTextField widthText;
   JTextField columnText;
   boolean flag = false;

   public RoomAdd() {
      setSize(300, 200);// '���ο� �� �����'������ ũ��
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ��� �ݱ⴩���� '���ο� �� �߰�' �����Ӹ� ����
      setTitle("���ο� �� �����");// '���ο� �� �����'������ �̸�

      JPanel panel = new JPanel();

      JLabel nameLabel = new JLabel("�� �̸� : ");// �� �̸� ǥ��
      nameLabel.setBounds(20, 20, 70, 30);// ��ġ �� ������ ����

      nameText = new JTextField(25);// �� �̸� ���� ��
      nameText.setBounds(75, 20, 200, 30);// ��ġ �� ������ ����

      JLabel sizeLabel = new JLabel("ũ     �� : ");// ũ�� ǥ��
      sizeLabel.setBounds(20, 60, 70, 30);// ��ġ �� ������ ����

      widthText = new JTextField(10);// ���� ũ�� ���� ��
      widthText.setBounds(75, 60, 78, 30);// ��ġ �� ������ ����

      JLabel starLabel = new JLabel(" * ");// *��ȣ ǥ��
      starLabel.setBounds(156, 60, 20, 30);// ��ġ �� ������ ����

      columnText = new JTextField(10);// ���� ũ�� ���� ��
      columnText.setBounds(170, 60, 78, 30);// ��ġ �� ������ ����

      JLabel cmLabel = new JLabel(" cm");// cm���� ǥ��
      cmLabel.setBounds(250, 60, 30, 30);// ��ġ �� ������ ����

      JLabel widthLabel = new JLabel("��            ��");// ���� �ȳ� ǥ��
      widthLabel.setBounds(79, 87, 78, 30);// ��ġ �� ������ ����

      JLabel columnLabel = new JLabel("��            ��");// ���� �ȳ� ǥ��
      columnLabel.setBounds(178, 87, 78, 30);// ��ġ �� ������ ����

      okButton = new JButton("Ȯ��");// Ȯ�� ��ư
      okButton.setBounds(60, 120, 70, 30);// ��ġ �� ������ ����
      okButton.addActionListener(this);

      cancelButton = new JButton("���");// ��� ��ư
      cancelButton.setBounds(160, 120, 70, 30);// ��ġ �� ������ ����
      cancelButton.addActionListener(this);// ��� ��ư�� �̺�Ʈ �߻�

      panel.setLayout(null);// ���� ġ�� �� ��ġ ���� �� �ʿ�
      panel.add(nameLabel);// �������
      panel.add(nameText);

      panel.add(sizeLabel);
      panel.add(widthText);
      panel.add(starLabel);
      panel.add(columnText);
      panel.add(cmLabel);

      panel.add(widthLabel);
      panel.add(columnLabel);

      panel.add(okButton);
      panel.add(cancelButton);// ������� panel�� ����

      add(panel);// ������ ���� �׸�� ���� �ȵ�
      setResizable(false);// '���ο� �� �߰�'������ ũ�� ����
      setVisible(true);// '���ο� �� �߰�'������ ���̱�(������ '���ο� �� �߰�'������ ����ȵ�)
      setLocationRelativeTo(null);// ������ â ����� '���ο� �� �����'������ ����

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == okButton) { // Ȯ�� ��ư ���� ��
         flag = true;

         name = nameText.getText();// �� �̸� ���� ���� �Էµ� �ؽ�Ʈ�� �ҷ���
         width = widthText.getText();// ���� ũ�� ���� ���� �Էµ� �ؽ�Ʈ�� �ҷ���
         column = columnText.getText();// ���� ũ�� ���� ���� �Էµ� �ؽ�Ʈ�� �ҷ���

         dispose();// ���� ���� �� â ����
      } else if (e.getSource() == cancelButton) {// ��� ��ư�� ������ ����
         dispose();// ��� ��ư ������ '���ο� �� �߰�' �����Ӹ� ����
      }

   }
}

public class GaguTab{
   RoomAdd ra;// RoomAdd�� ra�� ����
   JPanel panel0;
   JTabbedPane tabbedPane;
   int numTabs = 0;
   boolean flag = true;

   GaguTab() {
	  
      createJTabbedPane();
      tabbedPane.setSize(2120, 1300);
//      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//      this.setLayout(null);// ���� ġ�� �� ��ġ ���� �� �ʿ�
//      this.setLocationRelativeTo(null);
//      //this.setSize(1000, 1000);
//      this.setVisible(true);
   }

      private void createJTabbedPane() {
      // �ڹ����� ����
      tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      // ù��° �� �߰�
      tabbedPane.add(panel); // ���⼭ ���̸� �޾Ƽ� ����ϸ� �� ��
      
      JTextArea ta = new JTextArea();
      ta.setEditable(false);
      ta.setText("Jabang�� ���� ��ġ ���α׷����ν� �� �ϳ��� ���� �߰��Ͽ� ���� ũ�⸦ ������ �� ������ ������ ��Ͽ��� ���� ���� �� ����� �߰��Ͽ� �濡 ������ ��ġ�� �� �ֽ��ϴ�.");
      
      panel.add(ta, "Center");
      
      // Ŭ�������� �� �߰�
      tabbedPane.add(new JPanel(), "+", numTabs++);

      tabbedPane.addChangeListener(changeListener);
   }

   // �г� �ȿ� �� �ֱ�
   JPanel createJPanel(int width, int height) {
      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setSize(width, height);
      panel.setBackground(Color.pink);

      return panel;
   }

 

   ///////////////////////////////////////////////////////////////////////////////////

   ChangeListener changeListener = new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
         int addNum = tabbedPane.getTabCount() - 2;
         int selNum = tabbedPane.getSelectedIndex();
         
         //�÷��� ���� ������ �� Ȯ��(flag�� ���� ������ �ÿ� �÷��� ���� �ѹ� �� �ҷ����Ƿ� �����ϱ� ����)
         if (addNum == selNum && flag) {
            System.out.println("�÷��� �� Ŭ��");
            //�ѹ� �� ȣ�� ����
            flag = false;
            ra = new RoomAdd();
            ra.addWindowListener(new WindowAdapter() {
               // Window�� ������ ��� �����
               @Override
               public void windowClosed(WindowEvent e) {
                  // Ȯ�� ��ư�� ������ �ÿ� �� �̸� ǥ��
                  if (ra.flag == true) {
                     System.out.println("Closing");
                     int wid = Integer.parseInt(ra.width);
                     int col = Integer.parseInt(ra.column);

                     addNewTab(wid, col, ra.name);
                     tabbedPane.repaint();

                  }
               }
            });

         }
      }
   };

   public void addNewTab(int width, int height, String name) {
      int index = numTabs - 1;
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
      panel.setName(name);
      
      /* ���ο� �� ���� */
      tabbedPane.add(panel, " " + String.valueOf(name), index);
      JPanel room = createJPanel(width, height);
      panel.add(room);
      
      //���� ���� �г� ��������
      JPanel curPanel = (JPanel) tabbedPane.getSelectedComponent();
      //�г��� ����� Room �߰�
      room.setLocation(curPanel.getWidth()/2-width/2, curPanel.getHeight()/2-height/2);

      // �������� ����� ������
      tabbedPane.setTabComponentAt(index, new DemoCustomTab(this));
      System.out.println(index);
      tabbedPane.setSelectedIndex(index);
      
      //�÷����� Ȱ��ȭ
      flag = true;
      numTabs++;

   }

   ///////////////////////////////////////////////////////////////////////////////////////

   public void removeTab(int index) {
     flag = false;
      tabbedPane.remove(index);
      System.out.println(index);
      numTabs--;

      if (index == numTabs - 1 && index > 0) {
         tabbedPane.setSelectedIndex(numTabs - 2);
         flag = true;
      } else {
         tabbedPane.setSelectedIndex(index);
         flag = true;
         
      }

      if (numTabs == 1) {
         //addNewTab();
         tabbedPane.setSelectedIndex(1);
      }
   }

   public JTabbedPane getJTab() {
	   return tabbedPane;	   
   }
   
}

class DemoCustomTab extends JPanel {

   GaguTab customJTabbedPane;

   /** �гξȿ� ���� �ְ� �׸��� ������ ��ư ���� */
   public DemoCustomTab(GaguTab customJTabbedPane) {
      this.customJTabbedPane = customJTabbedPane;
      setOpaque(false);
      addLabel();
      add(new CustomButton("x"));
   }

   private void addLabel() {
      JLabel label = new JLabel() {

         public String getText() {
            int index = customJTabbedPane.tabbedPane.indexOfTabComponent(DemoCustomTab.this);
            if (index != -1) {
               return customJTabbedPane.tabbedPane.getTitleAt(index);
            }
            return null;
         }
      };

      add(label);
   }

   class CustomButton extends JButton implements MouseListener {
      public CustomButton(String text) {
         // int size = 15;
         setText(text);
         // �����ϰ� �����
         setContentAreaFilled(false);
         // ��ư�� ���(����)�����
         setBorder(new EtchedBorder());
         // �׵θ� �Ⱥ��̰�
         setBorderPainted(false);
         // ���콺 �̺�Ʈ �߰�
         addMouseListener(this);

      }

      // ��ư�� ������ ��, ���� ������ �����
      @Override
      public void mouseClicked(MouseEvent e) {
         int index = customJTabbedPane.tabbedPane.indexOfTabComponent(DemoCustomTab.this);
         if (index != -1) {
            customJTabbedPane.removeTab(index);
         }
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      /** show border button when mouse hover */
      @Override
      public void mouseEntered(MouseEvent e) {
         setBorderPainted(true);
         setForeground(Color.RED);
      }

      /** hide border when mouse not hover */
      @Override
      public void mouseExited(MouseEvent e) {
         setBorderPainted(false);
         setForeground(Color.BLACK);
      }
   }
}

