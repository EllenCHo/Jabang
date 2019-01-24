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
      setTitle("방에 가구두기");
      setSize(FurnitureSize.WidthSize+30, FurnitureSize.ColumnSize+50);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      RoomItem = new JLabel();
      add(Room);
      Room.add(RoomItem);
      
//      setUndecorated(true); //상단 바 없애는 것
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
      setTitle("가구 사이즈 입력");
      setSize(350, 150);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setVisible(true);

      sizeLB = new JLabel("크 기 :");
      sizeLB.setBounds(20, 60, 70, 30);

      WidthTT = new JTextField(10);
      WidthTT.setBounds(75, 60, 78, 30);

      StarLB = new JLabel(" * ");
      StarLB.setBounds(156, 60, 20, 30);

      ColumnTT = new JTextField(10);
      ColumnTT.setBounds(170, 60, 78, 30);

      cmLB = new JLabel(" cm");
      cmLB.setBounds(250, 60, 30, 30);

      WidthLB = new JLabel("               가        로                     ");
      WidthLB.setBounds(79, 87, 78, 30);

      ColumnLB = new JLabel("            세        로            ");
      ColumnLB.setBounds(195, 87, 78, 30);

      OkButton = new JButton("확인");
      OkButton.setBounds(60, 120, 70, 30);

      CnButton = new JButton("취소");
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
         
         //화면에 표시 임시적으로 새 창을 띄어서 해 봐야됨
         //class Room 으로 해봄
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

      setTitle("가구 추가하기");
      setSize(360, 200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      Noimg = new ImageIcon("./Button_Image/Noimage.png");
      itemName = new String("이름 없음");

      panel = new JPanel();

      nameLabel = new JLabel("가구 이름 : ");
      nameLabel.setBounds(20, 20, 70, 30);

      nameText = new JTextField(25);
      nameText.setBounds(75, 20, 200, 30);

      imageLabel = new JLabel("이미지 : ");

      imageBtn = new JButton("파일 선택");

      okBtn = new JButton("확인");

      cancelBtn = new JButton("취소");

      // 미리보기
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
            // 파일 선택 창이 SWING 말고 AWT로 보이는 방법
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

   // Button_Image 폴더에 있는 이미지 불러와 객체 생성
   static ImageIcon img1 = new ImageIcon("./Button_Image/쇼파.png");
   static ImageIcon img2 = new ImageIcon("./Button_Image/식탁.png");
   static ImageIcon img3 = new ImageIcon("./Button_Image/옷장.png");
   static ImageIcon img4 = new ImageIcon("./Button_Image/의자.png");
   static ImageIcon img5 = new ImageIcon("./Button_Image/책상.png");
   static ImageIcon img6 = new ImageIcon("./Button_Image/침대.png");

   static boolean A = true;

   public NameSizeFrame5() {

      setTitle("가구 리스트");// 프레임 타이틀바 텍스트 지정
      setBounds(300, 100, 500, 500);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 메인 패널 생성
      main_panel = new JPanel();
      main_panel.setLayout(new BorderLayout());

      // 메인 패널 EAST 부분에 들어갈 H패널 생성
      H_panel = new JPanel();
      H_panel.setLayout(new BoxLayout(H_panel, BoxLayout.Y_AXIS));

      // 세로형 박스 생성 (ScrollBar를 갖고있는 박스를 만들기 위해 여기에 버튼을 넣어줄 예정)
      b = Box.createVerticalBox();
      b.setBounds(300, 100, 500, 500);

      // 박스에 버튼 생성
      bt_img1 = new JButton("쇼파", img1);
      b.add(bt_img1);
      bt_img2 = new JButton("식탁", img2);
      b.add(bt_img2);
      bt_img3 = new JButton("옷장", img3);
      b.add(bt_img3);
      bt_img4 = new JButton("의자", img4);
      b.add(bt_img4);
      bt_img5 = new JButton("책상", img5);
      b.add(bt_img5);
      bt_img6 = new JButton("침대", img6);
      b.add(bt_img6);

      // H패널에 세로 스크롤바만 생성 된 박스를 넣어주기
      H_panel.add(new JScrollPane(b, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

      add(main_panel);// 메인 프레임에 메인패널을 붙여주는 작업

      // 메인 패널 EAST 부분에 H패널 붙이기
      main_panel.add(H_panel, BorderLayout.EAST);

      // 메인 패널 SOUTH 부분에 들어갈 L패널 생성
      L_panel = new JPanel(new GridLayout(1, 2));

      // 추가 삭제버튼 생성
      ItemAdd = new JButton("추가");
      ItemDel = new JButton("삭제");
      L_panel.add(ItemAdd);
      L_panel.add(ItemDel);

      // 메인 패널 SOUTH 부분에 L패널 붙이기
      main_panel.add(L_panel, BorderLayout.SOUTH);

      bt_img1.addActionListener(this);
      bt_img2.addActionListener(this);
      bt_img3.addActionListener(this);
      bt_img4.addActionListener(this);
      bt_img5.addActionListener(this);
      bt_img6.addActionListener(this);

      setResizable(false);// 화면 고정
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