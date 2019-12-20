import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class Converter extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	double d;
	String[] LWT = { "Length", "Weight", "Time" };

	JComboBox<String> CB_1 = new JComboBox<String>();
	JComboBox<String> CB_2 = new JComboBox<String>();
	private JRadioButton rdbtnFT;
	private JRadioButton rdbtnTF;

	private boolean isCalcStateFromTo = true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;

	//Converter 생성자 : Calculator의 Converter체크박스와 History의 listmodel을 인자로 받음
	public Converter(final JCheckBox chckbxConverter, final DefaultListModel<String> listmodel) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent w) {
				chckbxConverter.setSelected(false);//창을 닫으면 Converter체크박스를 체크해제함
			}
		});
		setType(Type.UTILITY);
		setTitle("Converter");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//X 버튼을 누르면 창이 숨겨짐
		setBounds(100, 100, 624, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(248, 248, 248));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 26, 181, 35);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		final JComboBox<?> CB_LWT = new JComboBox<Object>(LWT);
		setCBL();//처음에 기본값으로 Length가 선택되어 있음
		CB_LWT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = (String) CB_LWT.getSelectedItem();
				if (str == "Length")
					setCBL();//Length를 선택하면 나머지 두 콤보박스의 내용을 바꿈
				else if (str == "Weight")
					setCBW();//Weight를 선택하면 나머지 두 콤보박스의 내용을 바꿈
				else if (str == "Time")
					setCBT();//Time을 선택하면 나머지 두 콤보박스의 내용을 바꿈
			}
		});
		panel.add(CB_LWT);

		textFieldFrom = new JTextField();
		textFieldFrom.setBounds(14, 140, 370, 32);
		contentPane.add(textFieldFrom);
		textFieldFrom.setColumns(10);
		textFieldFrom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {//textFieldFrom에 키가 입력되면 그 값을 d에 저장함
				if (isCalcStateFromTo) {//라디오 버튼이 어디에 표시되어 있는지 확인하여 From -> To에 표시되어있으면 연산수행
					try {
						d = Double.parseDouble(textFieldFrom.getText());
					} catch (Exception ex) {//숫자와 .(dot)이외의 값이면 예외처리되어 d = 0으로 설정됨
						d = 0;
					}
					printFrom_To();//입력받은 값으로 연산 수행
				}
			}
		});

		textFieldTo = new JTextField();
		textFieldTo.setBounds(14, 205, 370, 32);
		contentPane.add(textFieldTo);
		textFieldTo.setColumns(10);
		textFieldTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {//textFieldTo에 키가 입력되면 그 값을 d에 저장함
				if (!isCalcStateFromTo) {//라디오 버튼이 어디에 표시되어 있는지 확인하여 To -> From에 표시되어있으면 연산수행
					try {
						d = Double.parseDouble(textFieldTo.getText());
					} catch (Exception ex) {//숫자와 .(dot)이외의 값이면 예외처리되어 d = 0으로 설정됨
						d = 0;
					}
					printTo_From();//입력받은 값으로 연산 수행
				}
			}
		});

		CB_2.setBounds(435, 205, 155, 32);
		contentPane.add(CB_2);

		JLabel lblNewLabel = new JLabel("To:");
		lblNewLabel.setBounds(14, 184, 106, 22);
		contentPane.add(lblNewLabel);
		CB_1.setBounds(435, 140, 155, 32);
		contentPane.add(CB_1);
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(14, 117, 106, 22);
		contentPane.add(lblFrom);

		rdbtnFT = new JRadioButton("From \u2192 To");
		rdbtnFT.setSelected(true);
		textFieldTo.setEditable(false);
		buttonGroup.add(rdbtnFT);
		rdbtnFT.addActionListener(new ActionListener() {//From -> To 버튼이 선택되면 isCalcStateFromTo를 true으로 설정하고 textFieldFrom을 수정가능하게 바꾸고 textFieldTo를 수정불가능하게 바꾼다
			public void actionPerformed(ActionEvent e) {
				isCalcStateFromTo = true;
				textFieldFrom.setEditable(true);
				textFieldTo.setEditable(false);

			}
		});
		rdbtnFT.setBackground(new Color(248, 248, 248));
		rdbtnFT.setBounds(14, 86, 121, 23);
		contentPane.add(rdbtnFT);

		rdbtnTF = new JRadioButton("To \u2192 From");
		buttonGroup.add(rdbtnTF);
		rdbtnTF.addActionListener(new ActionListener() {//To -> From 버튼이 선택되면 isCalcStateFromTo를 false으로 설정하고 textFieldTo을 수정가능하게 바꾸고 textFieldFrom를 수정불가능하게 바꾼다
			public void actionPerformed(ActionEvent e) {
				isCalcStateFromTo = false;
				textFieldFrom.setEditable(false);
				textFieldTo.setEditable(true);
			}
		});
		rdbtnTF.setBackground(new Color(248, 248, 248));
		rdbtnTF.setBounds(172, 86, 121, 23);
		contentPane.add(rdbtnTF);
		
		btnNewButton = new JButton("Add to History");
		btnNewButton.addActionListener(new ActionListener() {//Add to History 버튼을 누르면 현재 변환하는 단위의 종류, 입력값, 출력값을 History의 listmodel에 추가한다
			public void actionPerformed(ActionEvent e) {
				listmodel.addElement("Converter - " + CB_LWT.getSelectedItem());
				if(isCalcStateFromTo)
					listmodel.addElement(textFieldFrom.getText() + " " + CB_1.getSelectedItem() + "s \u2192 " + textFieldTo.getText() + " " + CB_2.getSelectedItem() + "s");
				else if(!isCalcStateFromTo)
					listmodel.addElement(textFieldTo.getText() + " " + CB_2.getSelectedItem() + "s \u2192 " + textFieldFrom.getText() + " " + CB_1.getSelectedItem() + "s");
				listmodel.addElement(" ");
			}
		});
		btnNewButton.setBounds(435, 37, 155, 35);
		contentPane.add(btnNewButton);
	}

	public void setCBL() {//Length를 선택했을 때 콤보박스 두 개의 단위를 이에 맞게 설정
		CB_1.removeAllItems();
		CB_1.addItem("Feet");
		CB_1.addItem("Inch");
		CB_1.addItem("Kilometer");
		CB_1.addItem("Meter");
		CB_1.addItem("Mile");
		CB_1.addItem("Yard");
		CB_2.removeAllItems();
		CB_2.addItem("Feet");
		CB_2.addItem("Inch");
		CB_2.addItem("Kilometer");
		CB_2.addItem("Meter");
		CB_2.addItem("Mile");
		CB_2.addItem("Yard");

	}

	public void setCBW() {//Weight를 선택했을 때 콤보박스 두 개의 단위를 이에 맞게 설정
		CB_1.removeAllItems();
		CB_1.addItem("Carat");
		CB_1.addItem("Gram");
		CB_1.addItem("Ounce");
		CB_1.addItem("Pound");
		CB_2.removeAllItems();
		CB_2.addItem("Carat");
		CB_2.addItem("Gram");
		CB_2.addItem("Ounce");
		CB_2.addItem("Pound");
	}

	public void setCBT() {//Time을 선택했을 때 콤보박스 두 개의 단위를 이에 맞게 설정
		CB_1.removeAllItems();
		CB_1.addItem("Second");
		CB_1.addItem("Minute");
		CB_1.addItem("Hour");
		CB_1.addItem("Day");
		CB_1.addItem("Week");
		CB_2.removeAllItems();
		CB_2.addItem("Second");
		CB_2.addItem("Minute");
		CB_2.addItem("Hour");
		CB_2.addItem("Day");
		CB_2.addItem("Week");
	}

	public void printFrom_To() {//From -> To 버튼이 선택되었을 때 연산하는 함수
		if (CB_1.getSelectedItem() == CB_2.getSelectedItem() && isCalcStateFromTo)
			textFieldTo.setText(String.format("%.3f", Double.valueOf(d)));
		else {
			if (CB_1.getSelectedItem() == "Feet")
				d *= 0.3048;
			else if (CB_1.getSelectedItem() == "Inch")
				d *= 0.0254;
			else if (CB_1.getSelectedItem() == "Kilometer")
				d *= 1000;
			else if (CB_1.getSelectedItem() == "Meter")
				;
			else if (CB_1.getSelectedItem() == "Mile")
				d *= 1609.344;
			else if (CB_1.getSelectedItem() == "Yard")
				d *= 0.9144;
			else if (CB_1.getSelectedItem() == "Carat")
				d *= 0.2;
			else if (CB_1.getSelectedItem() == "Gram")
				;
			else if (CB_1.getSelectedItem() == "Ounce")
				d *= 28.349523;
			else if (CB_1.getSelectedItem() == "Pound")
				d *= 453.59237;
			else if (CB_1.getSelectedItem() == "Second")
				d /= 60;
			else if (CB_1.getSelectedItem() == "Minute")
				;
			else if (CB_1.getSelectedItem() == "Hour")
				d *= 60;
			else if (CB_1.getSelectedItem() == "Day")
				d *= 60 * 24;
			else if (CB_1.getSelectedItem() == "Week")
				d *= 60 * 24 * 7;

			if (CB_2.getSelectedItem() == "Feet")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 3.28084)));
			else if (CB_2.getSelectedItem() == "Inch")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 39.37)));
			else if (CB_2.getSelectedItem() == "Kilometer")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 0.001)));
			else if (CB_2.getSelectedItem() == "Meter")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_2.getSelectedItem() == "Mile")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 0.000621)));
			else if (CB_2.getSelectedItem() == "Yard")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 1.093613)));
			else if (CB_2.getSelectedItem() == "Carat")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 5)));
			else if (CB_2.getSelectedItem() == "Gram")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_2.getSelectedItem() == "Ounce")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 0.035274)));
			else if (CB_2.getSelectedItem() == "Pound")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 0.000267)));
			else if (CB_2.getSelectedItem() == "Second")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d * 60)));
			else if (CB_2.getSelectedItem() == "Minute")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_2.getSelectedItem() == "Hour")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d / 60)));
			else if (CB_2.getSelectedItem() == "Day")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d / (60 * 24))));
			else if (CB_2.getSelectedItem() == "Week")
				textFieldTo.setText(String.format("%.3f", Double.valueOf(d / (60 * 24 * 7))));
		}
	}
	public void printTo_From() {//To -> From 버튼이 선택되었을 때 연산하는 함수
		if (CB_2.getSelectedItem() == CB_1.getSelectedItem())
			textFieldFrom.setText(String.format("%.3f", Double.valueOf(d)));
		else {
			if (CB_2.getSelectedItem() == "Feet")
				d *= 0.3048;
			else if (CB_2.getSelectedItem() == "Inch")
				d *= 0.0254;
			else if (CB_2.getSelectedItem() == "Kilometer")
				d *= 1000;
			else if (CB_2.getSelectedItem() == "Meter")
				;
			else if (CB_2.getSelectedItem() == "Mile")
				d *= 1609.344;
			else if (CB_2.getSelectedItem() == "Yard")
				d *= 0.9144;
			else if (CB_2.getSelectedItem() == "Carat")
				d *= 0.2;
			else if (CB_2.getSelectedItem() == "Gram")
				;
			else if (CB_2.getSelectedItem() == "Ounce")
				d *= 28.349523;
			else if (CB_2.getSelectedItem() == "Pound")
				d *= 453.59237;
			else if (CB_2.getSelectedItem() == "Second")
				d /= 60;
			else if (CB_2.getSelectedItem() == "Minute")
				;
			else if (CB_2.getSelectedItem() == "Hour")
				d *= 60;
			else if (CB_2.getSelectedItem() == "Day")
				d *= 60 * 24;
			else if (CB_2.getSelectedItem() == "Week")
				d *= 60 * 24 * 7;

			if (CB_1.getSelectedItem() == "Feet")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 3.28084)));
			else if (CB_1.getSelectedItem() == "Inch")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 39.37)));
			else if (CB_1.getSelectedItem() == "Kilometer")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 0.001)));
			else if (CB_1.getSelectedItem() == "Meter")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_1.getSelectedItem() == "Mile")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 0.000621)));
			else if (CB_1.getSelectedItem() == "Yard")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 1.093613)));
			else if (CB_1.getSelectedItem() == "Carat")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 5)));
			else if (CB_1.getSelectedItem() == "Gram")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_1.getSelectedItem() == "Ounce")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 0.035274)));
			else if (CB_1.getSelectedItem() == "Pound")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 0.000267)));
			else if (CB_1.getSelectedItem() == "Second")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d * 60)));
			else if (CB_1.getSelectedItem() == "Minute")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d)));
			else if (CB_1.getSelectedItem() == "Hour")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d / 60)));
			else if (CB_1.getSelectedItem() == "Day")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d / (60 * 24))));
			else if (CB_1.getSelectedItem() == "Week")
				textFieldFrom.setText(String.format("%.3f", Double.valueOf(d / (60 * 24 * 7))));
		}
	}
}