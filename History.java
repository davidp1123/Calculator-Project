import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Window.Type;
import java.awt.GridLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class History extends JFrame {

   private JPanel contentPane;
   private JPanel MainPanel;

   final DefaultListModel listmodel = new DefaultListModel();
   
   //History ������ : Calculator�� Historyüũ�ڽ��� ���ڷ� ����
   public History(final JCheckBox chckbxHistory) {
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            chckbxHistory.setSelected(false);//â�� ������ Historyüũ�ڽ��� üũ������
         }
      });
      setTitle("History");
      setType(Type.UTILITY);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//X ��ư�� ������ â�� ������
      setBounds(100, 100, 340, 435);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setBackground(new Color(248, 248, 248));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel panel_1 = new JPanel();
      panel_1.setBounds(74, 351, 259, 39);
      contentPane.add(panel_1);
      panel_1.setLayout(new GridLayout(0, 2, 15, 0));
      panel_1.setBackground(new Color(248, 248, 248));

      JButton btnNewButton = new JButton("Clear History");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {//Clear History��ư�� ������ listmodel�� �ʱ�ȭ��
            listmodel.removeAllElements();
         }
      });
      panel_1.add(btnNewButton);

      JButton btnNewButton_1 = new JButton("Save to file");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {//Save to file��ư�� ������ JFileChooser�� ������ ���Ͽ� listmodel�� ����Ǿ��ִ� ������ ������
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               File file = fc.getSelectedFile();
               PrintWriter pw = null;
               try {
                  pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                  for (int i = 0; i < listmodel.getSize(); i++) {
                     System.out.println((listmodel.getElementAt(i).toString()));
                     pw.println(listmodel.getElementAt(i).toString());
                  }
               } catch (UnsupportedEncodingException | FileNotFoundException e1) {
                  e1.printStackTrace();
               }
               pw.close();
            }
         }
      });
      panel_1.add(btnNewButton_1);

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setBounds(0, 0, 333, 339);
      contentPane.add(scrollPane);

      MainPanel = new JPanel();
      scrollPane.setViewportView(MainPanel);
      MainPanel.setLayout(new GridLayout(0, 1, 0, 5));

      JList list = new JList(listmodel);
      list.setCellRenderer(new DefaultListCellRenderer() {
          public int getHorizontalAlignment() {//History�� ����İ� ����� �����ʿ� ��µǰ� ����
             return RIGHT;
          }
       });
      MainPanel.add(list);
   }
}