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
      setSize(300, 200);// '새로운 방 만들기'프레임 크기
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 상단 닫기누르면 '새로운 방 추가' 프레임만 종료
      setTitle("새로운 방 만들기");// '새로운 방 만들기'프레임 이름

      JPanel panel = new JPanel();

      JLabel nameLabel = new JLabel("방 이름 : ");// 방 이름 표시
      nameLabel.setBounds(20, 20, 70, 30);// 위치 및 사이즈 조정

      nameText = new JTextField(25);// 방 이름 적는 곳
      nameText.setBounds(75, 20, 200, 30);// 위치 및 사이즈 조정

      JLabel sizeLabel = new JLabel("크     기 : ");// 크기 표시
      sizeLabel.setBounds(20, 60, 70, 30);// 위치 및 사이즈 조정

      widthText = new JTextField(10);// 가로 크기 적는 곳
      widthText.setBounds(75, 60, 78, 30);// 위치 및 사이즈 조정

      JLabel starLabel = new JLabel(" * ");// *기호 표시
      starLabel.setBounds(156, 60, 20, 30);// 위치 및 사이즈 조정

      columnText = new JTextField(10);// 세로 크기 적는 곳
      columnText.setBounds(170, 60, 78, 30);// 위치 및 사이즈 조정

      JLabel cmLabel = new JLabel(" cm");// cm단위 표시
      cmLabel.setBounds(250, 60, 30, 30);// 위치 및 사이즈 조정

      JLabel widthLabel = new JLabel("가            로");// 가로 안내 표시
      widthLabel.setBounds(79, 87, 78, 30);// 위치 및 사이즈 조정

      JLabel columnLabel = new JLabel("세            로");// 세로 안내 표시
      columnLabel.setBounds(178, 87, 78, 30);// 위치 및 사이즈 조정

      okButton = new JButton("확인");// 확인 버튼
      okButton.setBounds(60, 120, 70, 30);// 위치 및 사이즈 조정
      okButton.addActionListener(this);

      cancelButton = new JButton("취소");// 취소 버튼
      cancelButton.setBounds(160, 120, 70, 30);// 위치 및 사이즈 조정
      cancelButton.addActionListener(this);// 취소 버튼에 이벤트 발생

      panel.setLayout(null);// 임의 치수 및 위치 지정 시 필요
      panel.add(nameLabel);// 여기부터
      panel.add(nameText);

      panel.add(sizeLabel);
      panel.add(widthText);
      panel.add(starLabel);
      panel.add(columnText);
      panel.add(cmLabel);

      panel.add(widthLabel);
      panel.add(columnLabel);

      panel.add(okButton);
      panel.add(cancelButton);// 여기까지 panel에 부착

      add(panel);// 없으면 위의 항목들 적용 안됨
      setResizable(false);// '새로운 방 추가'프레임 크기 고정
      setVisible(true);// '새로운 방 추가'프레임 보이기(없으면 '새로운 방 추가'프레임 실행안됨)
      setLocationRelativeTo(null);// 윈도우 창 가운데에 '새로운 방 만들기'프레임 생성

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == okButton) { // 확인 버튼 누를 시
         flag = true;

         name = nameText.getText();// 방 이름 적는 곳에 입력된 텍스트를 불러옴
         width = widthText.getText();// 가로 크기 적는 곳에 입력된 텍스트를 불러옴
         column = columnText.getText();// 세로 크기 적는 곳에 입력된 텍스트를 불러옴

         dispose();// 위에 동작 후 창 종료
      } else if (e.getSource() == cancelButton) {// 취소 버튼을 누르면 동작
         dispose();// 취소 버튼 누르면 '새로운 방 추가' 프레임만 종료
      }

   }
}

public class GaguTab{
   RoomAdd ra;// RoomAdd는 ra로 선언
   JPanel panel0;
   JTabbedPane tabbedPane;
   int numTabs = 0;
   boolean flag = true;

   GaguTab() {
	  
      createJTabbedPane();
      tabbedPane.setSize(2120, 1300);
//      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//      this.setLayout(null);// 임의 치수 및 위치 지정 시 필요
//      this.setLocationRelativeTo(null);
//      //this.setSize(1000, 1000);
//      this.setVisible(true);
   }

      private void createJTabbedPane() {
      // 자바탭팬 생성
      tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      // 첫번째 탭 추가
      tabbedPane.add(panel); // 여기서 방이름 받아서 출력하면 될 듯
      
      JTextArea ta = new JTextArea();
      ta.setEditable(false);
      ta.setText("Jabang은 가구 배치 프로그램으로써 방 하나당 탭을 추가하여 방을 크기를 설정할 수 있으며 오른쪽 목록에서 가구 선택 후 사이즈를 추가하여 방에 가구를 배치할 수 있습니다.");
      
      panel.add(ta, "Center");
      
      // 클릭했을때 탭 추가
      tabbedPane.add(new JPanel(), "+", numTabs++);

      tabbedPane.addChangeListener(changeListener);
   }

   // 패널 안에 라벨 넣기
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
         
         //플러스 탭을 눌렀는 지 확인(flag는 방을 생성할 시에 플러스 탭이 한번 더 불러지므로 방지하기 위함)
         if (addNum == selNum && flag) {
            System.out.println("플러스 탭 클릭");
            //한번 더 호출 방지
            flag = false;
            ra = new RoomAdd();
            ra.addWindowListener(new WindowAdapter() {
               // Window가 닫혔을 경우 실행됨
               @Override
               public void windowClosed(WindowEvent e) {
                  // 확인 버튼을 눌렀을 시에 방 이름 표시
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
      
      /* 새로운 탭 생성 */
      tabbedPane.add(panel, " " + String.valueOf(name), index);
      JPanel room = createJPanel(width, height);
      panel.add(room);
      
      //현재 탭의 패널 가져오기
      JPanel curPanel = (JPanel) tabbedPane.getSelectedComponent();
      //패널의 가운데에 Room 추가
      room.setLocation(curPanel.getWidth()/2-width/2, curPanel.getHeight()/2-height/2);

      // 설정탭은 사용자 설정탭
      tabbedPane.setTabComponentAt(index, new DemoCustomTab(this));
      System.out.println(index);
      tabbedPane.setSelectedIndex(index);
      
      //플러스탭 활성화
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

   /** 패널안에 라벨을 넣고 그리고 꺼지는 버튼 넣음 */
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
         // 투명하게 만들기
         setContentAreaFilled(false);
         // 버튼에 경계(보드)만들기
         setBorder(new EtchedBorder());
         // 테두리 안보이게
         setBorderPainted(false);
         // 마우스 이벤트 추가
         addMouseListener(this);

      }

      // 버튼을 눌렀을 때, 탭이 꺼지게 만들기
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

