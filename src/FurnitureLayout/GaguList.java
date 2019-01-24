package FurnitureLayout;
//�ϼ�

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GaguList implements ActionListener, WindowListener, MouseListener {

   GaguList gl;
   FurnitureAdd fa;

   FurnitureSize fs = new FurnitureSize();

   static String[] path = new String[100];// �⺻ ���� ��� �迭
   ImageIcon[] img = new ImageIcon[100];// �⺻ ���� �̹��� ������ �迭
   String[] name = new String[100];// �⺻ ���� �̸� �迭
   JPanel main_panel;// ����, ���, �ϴ� �г�
   JPanel H_panel;
   JPanel L_panel;
   JScrollPane Scroller;// ��ũ�� ����
   JButton[] GaguBtn = new JButton[100];// H_panel�� �� ������ư �迭
   JButton ItemAdd, ItemDel;// L�г� ���� �߰� ���� ��ư
   int start = 6;// ���λ����Ǵ� �迭�� index 6���� �߰�
   int i = 6;

   JButton focus;
   JPopupMenu pm;
   JMenuItem delete, cancel;
   
   //ES
   JTabbedPane tp;
   Gagu[] g = new Gagu[100];
   int GaguCount = 0;
   
   public GaguList(JTabbedPane tp) {
	  this.tp = tp;
	  
      // �⺻ ���� ���� ���
      path[0] = "./Button_Image/����.png";
      path[1] = "./Button_Image/��Ź.png";
      path[2] = "./Button_Image/����.png";
      path[3] = "./Button_Image/����.png";
      path[4] = "./Button_Image/å��.png";
      path[5] = "./Button_Image/ħ��.png";

      // �⺻ ���� ���� �̹��� ������ �迭 ����
      for (int i = 0; i < 6; i++) {
         img[i] = new ImageIcon(path[i]);
      }

      // �⺻ ���� ���� �̸�
      name[0] = "����";
      name[1] = "��Ź";
      name[2] = "����";
      name[3] = "����";
      name[4] = "å��";
      name[5] = "ħ��";

      // ���� �г� ����
      main_panel = new JPanel();
      main_panel.setLayout(new BorderLayout());

      // ���� �г� Center �κп� �� H�г� ����
      H_panel = new JPanel();
      H_panel.setLayout(new BoxLayout(H_panel, BoxLayout.Y_AXIS));

      // H_panel�� ��ũ�� ����
      Scroller = new JScrollPane(H_panel);
      // ���ν�ũ���� �׻� ���ν�ũ���� �ʿ��
      Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      // H_panel�� �� �⺻ ���� ��ư ������ H_panel�� ���̱�
      for (int i = 0; i < 6; i++) {
         GaguBtn[i] = new JButton(name[i], img[i]);
         H_panel.add(GaguBtn[i]);
      }


      // ���� �г� Center �κп� H�г� �� Scroller ���̱�
      main_panel.add(Scroller, "Center");

      // ���� �г� SOUTH �κп� �� L�г� ����
      L_panel = new JPanel(new GridLayout(1, 2));

      // �߰� ������ư ����
      ItemAdd = new JButton("�߰�");
      ItemDel = new JButton("������ư ���� �ȳ�");
      L_panel.add(ItemAdd);
      L_panel.add(ItemDel);

      // ���� �г� �ϴ� �κп� L�г� ���̱�
      main_panel.add(L_panel, "South");

      // ���� �߰� ���� ��ư �׼Ǹ�����
      ItemAdd.addActionListener(this);
      ItemDel.addActionListener(this);

      // �⺻ ������ ���Ǹ�����
      for (int i = 0; i < 6; i++) {
         GaguBtn[i].addActionListener(this);
      }
      
      // �⺻ ������ ���콺 ������ (ù��° ������ư�� ���� ���ϰ� ����)
      for (int i = 1; i < 6; i++) {
          GaguBtn[i].addMouseListener(this);
       }

      //������ư ��Ŭ���� �˾��޴� ����
      pm = new JPopupMenu();
      delete = new JMenuItem("����");
      cancel = new JMenuItem("���");

      // �׼� ������
      delete.addActionListener(this);
      cancel.addActionListener(this);
      pm.add(delete);
      pm.addSeparator();
      pm.add(cancel);

   }
   // ���� ���̾ƿ����� �г��� �Ѱ��ֱ� ���� �޼ҵ�
   public JPanel getJPanel() {
	   return main_panel;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {

      // ���� �߰�, ���� ��ư
      if (e.getSource() == ItemAdd) {
         // �߰� �˾�â���� �̵�
         fa = new FurnitureAdd(start);

         // ���� �߰� �˾�â�� ������ �� ����
         fa.addWindowListener(this);

      } else if (e.getSource() == ItemDel) {
       JOptionPane.showMessageDialog(null, "��������Ʈ���� ������ ��ư�� ��Ŭ�� �ϼ���\n!!!  ù��° ������ ������ �ȵ˴ϴ�  !!!");  
      }
      
      // �߰� ������ ��ư �׼Ǹ�����
      // ��ư�迭 index�� ��ġ�ϰ�, ������� ������
      for (int i = 0; i < 100; i++) {
         if (e.getSource() == GaguBtn[i]) {
        	 fs = new FurnitureSize(path[i]);
        	 fs.addWindowListener(this);
         }
      }
      
     //��Ŭ�� �˾��޴����� ���� �������� 
      if (e.getSource() == delete) {
          System.out.println("����");
         
          H_panel.remove(focus);
          
          focus = null;
          // Garbage Collector�� ��û�Ͽ� null���� ���� �����Ⱚ ����
          System.gc();

          //H_panel.repaint();
          H_panel.revalidate();
       } else if (e.getSource() == cancel) {
          // ���Ļ�
      }

   }
   /*
   public void setFunitureSize(FurnitureSize fs) {
	   fs = this.fs;
   }*/

   @Override
   public void windowActivated(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub
	   if(e.getSource() == fa) {
		   if (fa.getGaguBtn() != null) {
		         GaguBtn[start] = fa.getGaguBtn();
		         H_panel.add(GaguBtn[start]);
		         GaguBtn[start].addActionListener(this);
		         GaguBtn[start].addMouseListener(this);

		         start = fa.getGaguNum();
		      } //��ҳ� �˾�â �ݾ��� ��� �����ʹ� null�� �ǹǷ� ���� ����, �迭 ���� �ȵǰ� ��
		      else {
		         System.out.println("��Ҵ��� �����߰� �ȵ�");
		      }
		      //H_panel.repaint();
		      H_panel.revalidate();
	   }else if(e.getSource() == fs) {
		   System.out.println("���� ������");
		   if (tp.getSelectedIndex() != (tp.getTabCount()-1)) {
			   JPanel panel = (JPanel) tp.getSelectedComponent();
			   JPanel roomPanel = (JPanel) panel.getComponent(0);
			   
			   g[GaguCount] = new Gagu(roomPanel, fs.PathResize, fs.WidthSize, fs.ColumnSize);
			  	   
			   roomPanel.add(g[GaguCount].getJLabel());
			   
			   GaguCount++;
			   
			   System.out.println("GaguCount:" + GaguCount);
			   
			   roomPanel.repaint();
		   }
	   }
      
   }

   @Override
   public void windowClosing(WindowEvent e) {
      // TODO Auto-generated method stub
   }
   
   @Override
   public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub
   }
   
   @Override
   public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      
      focus = (JButton) e.getSource();
      System.out.println("����");

      // ���� ��ư ��Ŭ������ �ÿ� �����˾� â ��Ÿ���� �ϱ�
      if (e.getButton() == MouseEvent.BUTTON3) {
         System.out.println("click");
         pm.show(focus, e.getX(), e.getY());
      }

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
   }
   
   
} // GaguList end


///////////////////////////////////////////////////////////////////////////////////////////////

class FurnitureAdd extends JFrame implements ActionListener {

   FurnitureAdd fa;
   String NoimgPath;// �̹��� ���� ���� ���
   ImageIcon Noimg;// �̹��� ���� ����

   JPanel Addpanel;
   JLabel nameLabel, imageLabel, Viewer;// �̸�,�̹���,�̸�����
   JTextField nameText;// �̸��Է�ĭ
   JButton okBtn, cancelBtn, CooserBtn;// Ȯ��, ���, �̹������� ��ư

   int start;

   String[] itemName = new String[100];// �Էµ� ���� �̸� �޾ƿ�
   String[] Addpath = new String[100];// �����
   ImageIcon[] MyImage = new ImageIcon[100];// ��� �ҷ��� �̹��� ������ ����
   Image[] img_m = new Image[100];// MyImage �̹��� �����ܿ��� �̹��� �����
   Image[] newImg = new Image[100]; // ��ư�� �� ũ��� ������� ������
   ImageIcon[] image = new ImageIcon[100];// ������ �̹��� �̹��� ���������� ��ư������ ���

   JButton[] GaguBtnAdd = new JButton[100];// ���Ӱ� ������ ���� ��ư

   public FurnitureAdd(int index) {
      start = index;//GaguList���� �޾ƿ� ��
      
      // ���� ���� ���ϰ� �̸� �Է� �������� �⺻ ��
      NoimgPath = "./Button_Image/Noimage.png";
      Noimg = new ImageIcon(NoimgPath);
      Addpath[start] = NoimgPath;

      setTitle("���� �߰��ϱ�");
      setSize(360, 200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      // �߰� ��ư Ŭ���� ���ο� �˾�â
      Addpanel = new JPanel();
      nameLabel = new JLabel("���� �̸� : ");
      nameLabel.setBounds(20, 20, 70, 30);
      nameText = new JTextField(25);
      nameText.setBounds(75, 20, 200, 30);
      imageLabel = new JLabel("�̹��� : ");
      CooserBtn = new JButton("���� ����");
      okBtn = new JButton("Ȯ��");
      cancelBtn = new JButton("���");
      Viewer = new JLabel();// Ȯ�� ������ �� ���õ� �̹��� �̸� �����ֱ�

      add(Addpanel);
      Addpanel.add(nameLabel);
      Addpanel.add(nameText);
      Addpanel.add(imageLabel);
      Addpanel.add(CooserBtn);
      Addpanel.add(okBtn);
      Addpanel.add(cancelBtn);
      Addpanel.add(Viewer);

      // �̹��� ���� �������� ���� ����â ������
      CooserBtn.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            // ���� ���� â�� SWING ���� AWT�� ���̴� ���??
            try {
               UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException e1) {
               e1.printStackTrace();
            } catch (InstantiationException e1) {
               e1.printStackTrace();
            } catch (IllegalAccessException e1) {
               e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
               e1.printStackTrace();
            }

            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File(System.getProperty("user.home")));// ���� ���ý� ����Ʈ ���
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);

            // ���� ������ �Ѵٸ�
            if (result == JFileChooser.APPROVE_OPTION) {
               File selectedFile = file.getSelectedFile();// ���õ� ����
               Addpath[start] = selectedFile.getAbsolutePath();// �����

               // ���� �ٸ� �̹����� �ҷ��� ���簢��ũ��� ������
               MyImage[start] = new ImageIcon(Addpath[start]);// ��� �ҷ��� �̹��� ������ ����
               img_m[start] = MyImage[start].getImage();// MyImage �̹��� �����ܿ��� �̹��� �����
               newImg[start] = img_m[start].getScaledInstance(100, 100, Image.SCALE_SMOOTH);// ��ư�� �� ũ��� �������
                                                                           // ������
               image[start] = new ImageIcon(newImg[start]);// ������ �̹��� �̹��� ���������� ��ư������ ���

               Viewer.setIcon(image[start]);// ���õ� ���� �̸� �����ֱ�

            } // ���� ������ ���ϰ� ��Ҵ����ٸ�
            else if (result == JFileChooser.CANCEL_OPTION) {
               System.out.println("���õ� �̹��� ����");
               Addpath[start] = NoimgPath;
            }
         }
      });

      setResizable(false);
      setVisible(true);
      setLocationRelativeTo(null);

      okBtn.addActionListener(this);
      cancelBtn.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() == okBtn) {

         itemName[start] = nameText.getText(); // �Էµ� ���� �̸� �޾ƿ�

         Addpath[start] = Addpath[start];// ���õ� �̹��� �����
         MyImage[start] = new ImageIcon(Addpath[start]);// ��� �ҷ��� �̹��� ������ ����
         img_m[start] = MyImage[start].getImage();// MyImage �̹��� �����ܿ��� �̹��� �����
         newImg[start] = img_m[start].getScaledInstance(100, 100, Image.SCALE_SMOOTH);// ��ư�� �� ũ��� ������� ������
         image[start] = new ImageIcon(newImg[start]);// ������ �̹��� �̹��� ���������� ��ư������ ���

         // ������ư ����
         GaguList.path[start] = Addpath[start];
         GaguBtnAdd[start] = new JButton(itemName[start], image[start]);

         repaint();
         dispose();         
         start++;
      } else if (e.getSource() == cancelBtn)
         dispose();
   }

   public JButton getGaguBtn() {
      return GaguBtnAdd[start - 1];
   }

   public String getPath() {
      return Addpath[start - 1];
   }

   public int getGaguNum() {
      return start;
   }

}//FurnitureAdd End


////////////////////////////////////////////////////////////////////////////////
class FurnitureSize extends JFrame implements ActionListener {

   Room rm;

   JPanel Serve;
   JLabel sizeLB, cmLB, WidthLB, ColumnLB, StarLB;// ũ��, cm, ����, ����, *
   JTextField WidthTT, ColumnTT;// ����, ���� ������ �ʵ�
   JButton OkButton, CnButton;// Ȯ��, ��� ��ư
   int WidthSize, ColumnSize; // �ؽ�Ʈ�� �Էµ� ������ integer�� �޾ƿ�

   String PathResize;// �������� �� �̹��� ���
   ImageIcon NewImgResize;
   Image ImgResize;
   Image ResizeNewImg;
   ImageIcon FIcon;

   public FurnitureSize() {
	   
   }
   
   public FurnitureSize(String Gagupath) {
      PathResize = Gagupath;

      Serve = new JPanel();
      setTitle("���� ������ �Է�");
      setSize(350, 150);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);

      sizeLB = new JLabel("ũ �� :");
      sizeLB.setBounds(20, 60, 70, 30);

      WidthTT = new JTextField(10);
      WidthTT.setBounds(75, 60, 78, 30);

      StarLB = new JLabel(" * ");
      StarLB.setBounds(156, 60, 20, 30);

      ColumnTT = new JTextField(10);
      ColumnTT.setBounds(170, 60, 78, 30);

      cmLB = new JLabel(" cm");
      cmLB.setBounds(250, 60, 30, 30);

      WidthLB = new JLabel("               ��        ��                     ");
      WidthLB.setBounds(79, 87, 78, 30);

      ColumnLB = new JLabel("            ��        ��            ");
      ColumnLB.setBounds(195, 87, 78, 30);

      OkButton = new JButton("Ȯ��");
      OkButton.setBounds(60, 120, 70, 30);

      CnButton = new JButton("���");
      CnButton.setBounds(160, 120, 70, 30);

      Serve.add(sizeLB);
      Serve.add(WidthTT);
      Serve.add(StarLB);
      Serve.add(ColumnTT);
      Serve.add(cmLB);
      Serve.add(WidthLB);
      Serve.add(ColumnLB);

      Serve.add(OkButton);
      Serve.add(CnButton);
      add(Serve);

      OkButton.addActionListener(this);
      CnButton.addActionListener(this);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == OkButton) {
         // �Էµ� ���μ��� ������ �޾ƿ���
         WidthSize = Integer.parseInt(WidthTT.getText());
         ColumnSize = Integer.parseInt(ColumnTT.getText());

         /*NewImgResize = new ImageIcon(PathResize);
         ImgResize = NewImgResize.getImage();

         ResizeNewImg = ImgResize.getScaledInstance(WidthSize, ColumnSize, Image.SCALE_SMOOTH);
         FIcon = new ImageIcon(ResizeNewImg);
         rm = new Room(FIcon);*/
         dispose();

      } else if (e.getSource() == CnButton) {
         dispose();

      }
   }

}//FurnitureSize End

class Room extends JFrame {

   private JPanel Room;
   JLabel RoomItem;
   ImageIcon RoomGagu;

   public Room(ImageIcon FI) {
      RoomGagu = FI;

      Room = new JPanel();
      setTitle("�濡 �����α�");
      setSize(500, 500);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      RoomItem = new JLabel();
      add(Room);
      Room.add(RoomItem);

      // setUndecorated(true); //��� �� ���ִ� ��
      setVisible(true);
      RoomItem.setIcon(RoomGagu);

   }
}