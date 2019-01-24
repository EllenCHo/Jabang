/*package FurnitureLayout;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

class Room extends JFrame {
   
   private JPanel Room;
   JLabel RoomItem;
   public Room() {
      
      Room = new JPanel();
      setTitle("�濡 �����α�");
      setSize(FurnitureSize.WidthSize+30, FurnitureSize.ColumnSize+50);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      RoomItem = new JLabel();
      add(Room);
      Room.add(RoomItem);
      
//      setUndecorated(true); //��� �� ���ִ� ��
      setVisible(true);      
      RoomItem.setIcon(FurnitureSize.FIcon);
         
   }
}

class FurnitureSize extends JFrame implements ActionListener {
   JButton OkButton;
   JButton CnButton;
   JPanel Serve;
   JTextField WidthTT, ColumnTT;
   JLabel sizeLB, cmLB, WidthLB, ColumnLB, StarLB;
   static int WidthSize;
   static int ColumnSize;
   private Image newImg;
   Room rm;
   static ImageIcon FIcon;

   public FurnitureSize() {
      
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
         WidthSize = Integer.parseInt(WidthTT.getText());
         ColumnSize = Integer.parseInt(ColumnTT.getText());
         
         if (NameSizeFrame5.A) {
            newImg = NameSizeFrame5.img.getScaledInstance(WidthSize, ColumnSize, Image.SCALE_SMOOTH);
            FIcon = new ImageIcon(newImg);
            rm = new Room();
            dispose();
         } else {
            Image img = FurnitureAdd.Noimg.getImage();
            newImg = img.getScaledInstance(WidthSize, ColumnSize, Image.SCALE_SMOOTH);
            FIcon = new ImageIcon(newImg);
            rm = new Room();
            dispose();
         }
         
      }else if (e.getSource() == CnButton) {
         dispose();
         
         //ȭ�鿡 ǥ�� �ӽ������� �� â�� �� �� ���ߵ�
         //class Room ���� �غ�
      }
   }

}

class FurnitureAdd extends JFrame implements ActionListener {

   private JButton okBtn, cancelBtn, imageBtn;
   JButton itemBtn;
   private JPanel panel;
   private JLabel nameLabel, imageLabel, Viewer;
   private JTextField nameText;
   private NameSizeFrame5 na;
   static ImageIcon Noimg;
   private ImageIcon image;
   private Image newImg;
   String itemName;

   public FurnitureAdd() {

      setTitle("���� �߰��ϱ�");
      setSize(360, 200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      Noimg = new ImageIcon("./Button_Image/Noimage.png");
      itemName = new String("�̸� ����");

      panel = new JPanel();

      nameLabel = new JLabel("���� �̸� : ");
      nameLabel.setBounds(20, 20, 70, 30);

      nameText = new JTextField(25);
      nameText.setBounds(75, 20, 200, 30);

      imageLabel = new JLabel("�̹��� : ");

      imageBtn = new JButton("���� ����");

      okBtn = new JButton("Ȯ��");

      cancelBtn = new JButton("���");

      // �̸�����
      Viewer = new JLabel();

      panel.add(nameLabel);
      panel.add(nameText);
      panel.add(imageLabel);
      panel.add(imageBtn);
      panel.add(okBtn);
      panel.add(cancelBtn);
      panel.add(Viewer);

      add(panel);

      imageBtn.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            // ���� ���� â�� SWING ���� AWT�� ���̴� ���
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
            file.setCurrentDirectory(new File(System.getProperty("user.home")));
            // filter the files
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);
            // if the user click on save in Jfilechooser

            if (result == JFileChooser.APPROVE_OPTION) {
               File selectedFile = file.getSelectedFile();
               String path = selectedFile.getAbsolutePath();
               Viewer.setIcon(ResizeImage(path));
               Noimg = new ImageIcon(newImg);

            } else if (result == JFileChooser.CANCEL_OPTION) {
               System.out.println("No File Select");
            }
         }
      });

      setResizable(false);
      setVisible(true);
      setLocationRelativeTo(null);

      okBtn.addActionListener(this);
      nameText.addActionListener(this);
      cancelBtn.addActionListener(this);

   }

   public ImageIcon ResizeImage(String ImagePath) {
      ImageIcon MyImage = new ImageIcon(ImagePath);
      Image img = MyImage.getImage();
      newImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
      image = new ImageIcon(newImg);
      return image;
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource() == okBtn) {
         itemName = nameText.getText();
         itemBtn = new JButton(itemName, Noimg);
         NameSizeFrame5.b.add(itemBtn);
         dispose();
         new FurnitureSize();

      } else if (e.getSource() == cancelBtn) {
         dispose();
      }

   }
}

class FurnitureDel extends JFrame {

   public FurnitureDel() {

   }
}

public class NameSizeFrame5 extends JFrame implements ActionListener {

   JPanel main_panel, H_panel, L_panel;

   private JButton ItemAdd, ItemDel;
   FurnitureAdd fa;
   FurnitureDel fd;
   FurnitureSize fs;
   static JButton bt_img1, bt_img2, bt_img3, bt_img4, bt_img5, bt_img6;
   static Box b;
   static Image img;

   // Button_Image ������ �ִ� �̹��� �ҷ��� ��ü ����
   static ImageIcon img1 = new ImageIcon("./Button_Image/����.png");
   static ImageIcon img2 = new ImageIcon("./Button_Image/��Ź.png");
   static ImageIcon img3 = new ImageIcon("./Button_Image/����.png");
   static ImageIcon img4 = new ImageIcon("./Button_Image/����.png");
   static ImageIcon img5 = new ImageIcon("./Button_Image/å��.png");
   static ImageIcon img6 = new ImageIcon("./Button_Image/ħ��.png");

   static boolean A = true;

   public NameSizeFrame5() {

      setTitle("���� ����Ʈ");// ������ Ÿ��Ʋ�� �ؽ�Ʈ ����
      setBounds(300, 100, 500, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // ���� �г� ����
      main_panel = new JPanel();
      main_panel.setLayout(new BorderLayout());

      // ���� �г� EAST �κп� �� H�г� ����
      H_panel = new JPanel();
      H_panel.setLayout(new BoxLayout(H_panel, BoxLayout.Y_AXIS));

      // ������ �ڽ� ���� (ScrollBar�� �����ִ� �ڽ��� ����� ���� ���⿡ ��ư�� �־��� ����)
      b = Box.createVerticalBox();
      b.setBounds(300, 100, 500, 500);

      // �ڽ��� ��ư ����
      bt_img1 = new JButton("����", img1);
      b.add(bt_img1);
      bt_img2 = new JButton("��Ź", img2);
      b.add(bt_img2);
      bt_img3 = new JButton("����", img3);
      b.add(bt_img3);
      bt_img4 = new JButton("����", img4);
      b.add(bt_img4);
      bt_img5 = new JButton("å��", img5);
      b.add(bt_img5);
      bt_img6 = new JButton("ħ��", img6);
      b.add(bt_img6);

      // H�гο� ���� ��ũ�ѹٸ� ���� �� �ڽ��� �־��ֱ�
      H_panel.add(new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

      add(main_panel);// ���� �����ӿ� �����г��� �ٿ��ִ� �۾�

      // ���� �г� EAST �κп� H�г� ���̱�
      main_panel.add(H_panel, BorderLayout.EAST);

      // ���� �г� SOUTH �κп� �� L�г� ����
      L_panel = new JPanel(new GridLayout(1, 2));

      // �߰� ������ư ����
      ItemAdd = new JButton("�߰�");
      ItemDel = new JButton("����");
      L_panel.add(ItemAdd);
      L_panel.add(ItemDel);

      // ���� �г� SOUTH �κп� L�г� ���̱�
      main_panel.add(L_panel, BorderLayout.SOUTH);

      bt_img1.addActionListener(this);
      bt_img2.addActionListener(this);
      bt_img3.addActionListener(this);
      bt_img4.addActionListener(this);
      bt_img5.addActionListener(this);
      bt_img6.addActionListener(this);

      setResizable(false);// ȭ�� ����
      setVisible(true);
      ItemAdd.addActionListener(this);
   }

   public static void main(String[] args) {
      NameSizeFrame5 na = new NameSizeFrame5();

   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (bt_img1 == e.getSource()) {
         img = img1.getImage();
         fs = new FurnitureSize();
      } else if (bt_img2 == e.getSource()) {
         img = img2.getImage();
         fs = new FurnitureSize();
      } else if (bt_img3 == e.getSource()) {
         img = img3.getImage();
         fs = new FurnitureSize();
      } else if (bt_img4 == e.getSource()) {
         img = img4.getImage();
         fs = new FurnitureSize();
      } else if (bt_img5 == e.getSource()) {
         img = img5.getImage();
         fs = new FurnitureSize();
      } else if (bt_img6 == e.getSource()) {
         img = img6.getImage();
         fs = new FurnitureSize();
      } else {
         A = false;
      }

      if (e.getSource() == ItemAdd) {
         fa = new FurnitureAdd();
      } else if (e.getSource() == ItemDel) {
         fd = new FurnitureDel();
      }

   }

}*/