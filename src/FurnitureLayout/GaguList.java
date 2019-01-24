package FurnitureLayout;
//완성

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

   static String[] path = new String[100];// 기본 사진 경로 배열
   ImageIcon[] img = new ImageIcon[100];// 기본 사진 이미지 아이콘 배열
   String[] name = new String[100];// 기본 사진 이름 배열
   JPanel main_panel;// 메인, 상단, 하단 패널
   JPanel H_panel;
   JPanel L_panel;
   JScrollPane Scroller;// 스크롤 생성
   JButton[] GaguBtn = new JButton[100];// H_panel에 들어갈 가구버튼 배열
   JButton ItemAdd, ItemDel;// L패널 가구 추가 삭제 버튼
   int start = 6;// 새로생성되는 배열은 index 6부터 추가
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
	  
      // 기본 가구 사진 경로
      path[0] = "./Button_Image/쇼파.png";
      path[1] = "./Button_Image/식탁.png";
      path[2] = "./Button_Image/옷장.png";
      path[3] = "./Button_Image/의자.png";
      path[4] = "./Button_Image/책상.png";
      path[5] = "./Button_Image/침대.png";

      // 기본 가구 사진 이미지 아이콘 배열 생성
      for (int i = 0; i < 6; i++) {
         img[i] = new ImageIcon(path[i]);
      }

      // 기본 가구 사진 이름
      name[0] = "쇼파";
      name[1] = "식탁";
      name[2] = "옷장";
      name[3] = "의자";
      name[4] = "책상";
      name[5] = "침대";

      // 메인 패널 생성
      main_panel = new JPanel();
      main_panel.setLayout(new BorderLayout());

      // 메인 패널 Center 부분에 들어갈 H패널 생성
      H_panel = new JPanel();
      H_panel.setLayout(new BoxLayout(H_panel, BoxLayout.Y_AXIS));

      // H_panel에 스크롤 생성
      Scroller = new JScrollPane(H_panel);
      // 세로스크롤은 항상 가로스크롤은 필요시
      Scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      Scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      // H_panel에 들어갈 기본 가구 버튼 생성후 H_panel에 붙이기
      for (int i = 0; i < 6; i++) {
         GaguBtn[i] = new JButton(name[i], img[i]);
         H_panel.add(GaguBtn[i]);
      }


      // 메인 패널 Center 부분에 H패널 들어간 Scroller 붙이기
      main_panel.add(Scroller, "Center");

      // 메인 패널 SOUTH 부분에 들어갈 L패널 생성
      L_panel = new JPanel(new GridLayout(1, 2));

      // 추가 삭제버튼 생성
      ItemAdd = new JButton("추가");
      ItemDel = new JButton("가구버튼 삭제 안내");
      L_panel.add(ItemAdd);
      L_panel.add(ItemDel);

      // 메인 패널 하단 부분에 L패널 붙이기
      main_panel.add(L_panel, "South");

      // 가구 추가 삭제 버튼 액션리스너
      ItemAdd.addActionListener(this);
      ItemDel.addActionListener(this);

      // 기본 가구의 엑션리스너
      for (int i = 0; i < 6; i++) {
         GaguBtn[i].addActionListener(this);
      }
      
      // 기본 가구의 마우스 리스너 (첫번째 가구버튼은 삭제 못하게 했음)
      for (int i = 1; i < 6; i++) {
          GaguBtn[i].addMouseListener(this);
       }

      //가구버튼 우클릭시 팝업메뉴 구현
      pm = new JPopupMenu();
      delete = new JMenuItem("삭제");
      cancel = new JMenuItem("취소");

      // 액션 리스너
      delete.addActionListener(this);
      cancel.addActionListener(this);
      pm.add(delete);
      pm.addSeparator();
      pm.add(cancel);

   }
   // 메인 레이아웃으로 패널을 넘겨주기 위한 메소드
   public JPanel getJPanel() {
	   return main_panel;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {

      // 가구 추가, 삭제 버튼
      if (e.getSource() == ItemAdd) {
         // 추가 팝업창으로 이동
         fa = new FurnitureAdd(start);

         // 가구 추가 팝업창이 닫혔을 때 실행
         fa.addWindowListener(this);

      } else if (e.getSource() == ItemDel) {
       JOptionPane.showMessageDialog(null, "가구리스트에서 삭제할 버튼을 우클릭 하세요\n!!!  첫번째 가구는 삭제가 안됩니다  !!!");  
      }
      
      // 추가 생성된 버튼 액션리스너
      // 버튼배열 index와 일치하게, 사진경로 보내기
      for (int i = 0; i < 100; i++) {
         if (e.getSource() == GaguBtn[i]) {
        	 fs = new FurnitureSize(path[i]);
        	 fs.addWindowListener(this);
         }
      }
      
     //우클릭 팝업메뉴에서 삭제 눌렀을때 
      if (e.getSource() == delete) {
          System.out.println("삭제");
         
          H_panel.remove(focus);
          
          focus = null;
          // Garbage Collector를 요청하여 null값을 가진 쓰레기값 삭제
          System.gc();

          //H_panel.repaint();
          H_panel.revalidate();
       } else if (e.getSource() == cancel) {
          // 형식상
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
		      } //취소나 팝업창 닫았을 경우 데이터는 null이 되므로 오류 방지, 배열 증가 안되게 함
		      else {
		         System.out.println("취소눌러 가구추가 안됨");
		      }
		      //H_panel.repaint();
		      H_panel.revalidate();
	   }else if(e.getSource() == fs) {
		   System.out.println("가구 사이즈");
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
      System.out.println("선택");

      // 가구 버튼 우클릭했을 시에 삭제팝업 창 나타나게 하기
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
   String NoimgPath;// 이미지 없는 사진 경로
   ImageIcon Noimg;// 이미지 없는 사진

   JPanel Addpanel;
   JLabel nameLabel, imageLabel, Viewer;// 이름,이미지,미리보기
   JTextField nameText;// 이름입력칸
   JButton okBtn, cancelBtn, CooserBtn;// 확인, 취소, 이미지선택 버튼

   int start;

   String[] itemName = new String[100];// 입력된 가구 이름 받아옴
   String[] Addpath = new String[100];// 상대경로
   ImageIcon[] MyImage = new ImageIcon[100];// 경로 불러와 이미지 아이콘 생성
   Image[] img_m = new Image[100];// MyImage 이미지 아이콘에서 이미지 갖고옴
   Image[] newImg = new Image[100]; // 버튼에 들어갈 크기로 사이즈로 재조정
   ImageIcon[] image = new ImageIcon[100];// 조정된 이미지 이미지 아이콘으로 버튼생성시 사용

   JButton[] GaguBtnAdd = new JButton[100];// 새롭게 생성된 가구 버튼

   public FurnitureAdd(int index) {
      start = index;//GaguList에서 받아온 값
      
      // 사진 선택 안하고 이름 입력 안했을때 기본 값
      NoimgPath = "./Button_Image/Noimage.png";
      Noimg = new ImageIcon(NoimgPath);
      Addpath[start] = NoimgPath;

      setTitle("가구 추가하기");
      setSize(360, 200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      // 추가 버튼 클릭시 새로운 팝업창
      Addpanel = new JPanel();
      nameLabel = new JLabel("가구 이름 : ");
      nameLabel.setBounds(20, 20, 70, 30);
      nameText = new JTextField(25);
      nameText.setBounds(75, 20, 200, 30);
      imageLabel = new JLabel("이미지 : ");
      CooserBtn = new JButton("파일 선택");
      okBtn = new JButton("확인");
      cancelBtn = new JButton("취소");
      Viewer = new JLabel();// 확인 누르기 전 선택된 이미지 미리 보여주기

      add(Addpanel);
      Addpanel.add(nameLabel);
      Addpanel.add(nameText);
      Addpanel.add(imageLabel);
      Addpanel.add(CooserBtn);
      Addpanel.add(okBtn);
      Addpanel.add(cancelBtn);
      Addpanel.add(Viewer);

      // 이미지 선택 눌렀을때 파일 선택창 리스너
      CooserBtn.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            // 파일 선택 창이 SWING 말고 AWT로 보이는 방법??
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
            file.setCurrentDirectory(new File(System.getProperty("user.home")));// 파일 선택시 디폴트 경로
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);

            // 파일 선택을 한다면
            if (result == JFileChooser.APPROVE_OPTION) {
               File selectedFile = file.getSelectedFile();// 선택된 파일
               Addpath[start] = selectedFile.getAbsolutePath();// 상대경로

               // 각기 다른 이미지를 불러와 정사각형크기로 재조정
               MyImage[start] = new ImageIcon(Addpath[start]);// 경로 불러와 이미지 아이콘 생성
               img_m[start] = MyImage[start].getImage();// MyImage 이미지 아이콘에서 이미지 갖고옴
               newImg[start] = img_m[start].getScaledInstance(100, 100, Image.SCALE_SMOOTH);// 버튼에 들어갈 크기로 사이즈로
                                                                           // 재조정
               image[start] = new ImageIcon(newImg[start]);// 조정된 이미지 이미지 아이콘으로 버튼생성시 사용

               Viewer.setIcon(image[start]);// 선택된 파일 미리 보여주기

            } // 파일 선택을 안하고 취소누른다면
            else if (result == JFileChooser.CANCEL_OPTION) {
               System.out.println("선택된 이미지 없음");
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

         itemName[start] = nameText.getText(); // 입력된 가구 이름 받아옴

         Addpath[start] = Addpath[start];// 선택된 이미지 상대경로
         MyImage[start] = new ImageIcon(Addpath[start]);// 경로 불러와 이미지 아이콘 생성
         img_m[start] = MyImage[start].getImage();// MyImage 이미지 아이콘에서 이미지 갖고옴
         newImg[start] = img_m[start].getScaledInstance(100, 100, Image.SCALE_SMOOTH);// 버튼에 들어갈 크기로 사이즈로 재조정
         image[start] = new ImageIcon(newImg[start]);// 조정된 이미지 이미지 아이콘으로 버튼생성시 사용

         // 가구버튼 생성
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
   JLabel sizeLB, cmLB, WidthLB, ColumnLB, StarLB;// 크기, cm, 가로, 세로, *
   JTextField WidthTT, ColumnTT;// 가로, 세로 사이즈 필드
   JButton OkButton, CnButton;// 확인, 취소 버튼
   int WidthSize, ColumnSize; // 텍스트에 입력된 사이즈 integer로 받아옴

   String PathResize;// 리사이즈 할 이미지 경로
   ImageIcon NewImgResize;
   Image ImgResize;
   Image ResizeNewImg;
   ImageIcon FIcon;

   public FurnitureSize() {
	   
   }
   
   public FurnitureSize(String Gagupath) {
      PathResize = Gagupath;

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
         // 입력된 가로세로 사이즈 받아오기
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
      setTitle("방에 가구두기");
      setSize(500, 500);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      RoomItem = new JLabel();
      add(Room);
      Room.add(RoomItem);

      // setUndecorated(true); //상단 바 없애는 것
      setVisible(true);
      RoomItem.setIcon(RoomGagu);

   }
}