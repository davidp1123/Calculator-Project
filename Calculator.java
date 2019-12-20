import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import javax.swing.ButtonGroup;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class Calculator extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField InputText;
	private JTextField OutputText;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private String inputNum = "";
	private String inputString = " ";
	private String calcString = "";
	private String num1, num2;
	private String operator;
	private String copyString = "";
	private String tempString = "";
	private String tempNum = "";
	private String hexa = "", decimal = "", octal = "", binary = "";
	private int negative = 0;

	private JCheckBox chckbxHistory = new JCheckBox("History");
	History h = new History(chckbxHistory);
	private JCheckBox chckbxConverter = new JCheckBox("Converter");
	Converter c = new Converter(chckbxConverter, h.listmodel);
	
	private final JCheckBox chckbxDateCalc = new JCheckBox("Date");
	DateCalc d = new DateCalc(chckbxDateCalc, h.listmodel);

	private static Calculator frame = new Calculator();
	
	private JButton btnC = new JButton("C");
	private JButton btnD = new JButton("D");
	private JButton btnE = new JButton("E");
	private JButton btnF = new JButton("F");
	private JButton btn8 = new JButton("8");
	private JButton btn9 = new JButton("9");
	private JButton btnA = new JButton("A");
	private JButton btnB = new JButton("B");
	private JButton btn4 = new JButton("4");
	private JButton btn5 = new JButton("5");
	private JButton btn6 = new JButton("6");
	private JButton btn7 = new JButton("7");
	private JButton btn0 = new JButton("0");
	private JButton btn1 = new JButton("1");
	private JButton btn2 = new JButton("2");
	private JButton btn3 = new JButton("3");
	private JButton btnPoint = new JButton(".");
	private JButton btnPLUS = new JButton("+");
	private JButton btnSHL = new JButton("<<");
	private JButton btnCc = new JButton(" C ");
	private JButton btnCE = new JButton("CE");
	private JButton btnBksp = new JButton("Bksp");
	private JButton btnMOD = new JButton("MOD");
	private JButton btnOpen = new JButton("(");
	private JButton btnAND = new JButton("AND");
	private JButton btnMINUS = new JButton("-");
	private JButton btnClose = new JButton(")");
	private JButton btnOR = new JButton("OR");
	private JButton btnMUL = new JButton("*");
	private JButton btnChange = new JButton("+/-");
	private JButton btnNOT = new JButton("NOT");
	private JButton btnDIV = new JButton("/");
	private JButton btnXOR = new JButton("XOR");
	private JButton btnEqual = new JButton("=");
	private JButton btnSHR = new JButton(">>");
	private JRadioButton rdbtnOctal = new JRadioButton("Octal");
	private JRadioButton rdbtnDecimal = new JRadioButton("Decimal");
	private JRadioButton rdbtnHexa = new JRadioButton("Hexa");
	private JRadioButton rdbtnBinary = new JRadioButton("Binary");
	private final JTextField textField = new JTextField();
	private JLabel labelHexa;
	private final JLabel labelDecimal = new JLabel("");
	private final JLabel labelOctal = new JLabel("");
	private final JLabel labelBinary = new JLabel("");

	private String calcoption;
	private final JTextField BinaryText = new JTextField();
	private JLabel BinaryCipher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setLocation(100, 20);
					frame.setResizable(false);
					frame.setVisible(true);
					frame.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				String keypress = KeyEvent.getKeyText(keyCode);

				// shift�� ���� ������ ��츦 Ȯ�����ݴϴ�.
				switch (e.getKeyCode()) {
				case KeyEvent.VK_9:
					if (e.isShiftDown())
						keypress = "(";
					break;
				case KeyEvent.VK_0:
					if (e.isShiftDown())
						keypress = ")";
					break;
				case KeyEvent.VK_8:
					if (e.isShiftDown())
						keypress = "*";
					break;
				case KeyEvent.VK_5:
					if (e.isShiftDown())
						keypress = "MOD";
					break;
				case KeyEvent.VK_EQUALS:
					if (e.isShiftDown())
						keypress = "+";
					break;
				}

				if (keypress.contains("NumPad"))
					keypress = keypress.substring(7);

				if (keypress.equals("Minus"))
					keypress = "-";

				if (keypress.equals("Slash"))
					keypress = "/";

				
				// �����ڸ� �Է����� ���
				if (keypress.equals("+") || keypress.equals("-")
						|| keypress.equals("*") || keypress.equals("/")
						|| keypress.equals("MOD")) {
					operator(keypress);

				}
				// ( ��ȣ�� �Է����� ���
				else if (keypress.equals("(")) {
					// �� �Է� �ٷ� �ڿ��� �۵����� �ʽ��ϴ�.
					if (inputString.endsWith(" ") || inputString.equals(" ")) {

						inputString += "( ";
					}
				}
				// ) ��ȣ�� �Է����� ���
				else if (keypress.equals(")")) {
					// = ���� ���Ŀ��� �۵����� �ʽ��ϴ�.
					if (operator != "=")
						inputString += " )";
				}
				// = �̳� ���͸� �Է����� ���
				else if (keypress.equals("Equals") || keypress.equals("Enter")) {
					enter();
				}
				// backspace�� �Է����� ���
				else if (keypress.equals("Backspace")) {
					backspace();
				} else {
					// ���ڸ� �Է����� ���
					if (rdbtnHexa.isSelected()) {
						if ((keypress.compareToIgnoreCase("0") >= 0 && keypress
								.compareToIgnoreCase("9") <= 0)
								|| (keypress.compareToIgnoreCase("A") >= 0 && keypress
										.compareToIgnoreCase("F") <= 0)) {
							inputString += keypress;

						}
					} else if (rdbtnDecimal.isSelected()) {
						if (keypress.compareToIgnoreCase("0") >= 0
								&& keypress.compareToIgnoreCase("9") <= 0) {
							inputString += keypress;
						} else if (keypress.equals("Period")) {
							inputString += ".";
						}
					} else if (rdbtnOctal.isSelected()) {
						if (keypress.compareToIgnoreCase("0") >= 0
								&& keypress.compareToIgnoreCase("7") <= 0) {
							inputString += keypress;

						}
					} else {
						if ((keypress.compareToIgnoreCase("0") >= 0 && keypress
								.compareToIgnoreCase("1") <= 0)) {
							inputString += keypress;

						}
					}
				}
				if (inputString.endsWith("Enter"))
					inputString.substring(0, inputString.length() - 5);
				InputText.setText(inputString);
			}
		});

		BinaryText.setBounds(0, 0, 453, 46);
		BinaryText.setEditable(false);
		BinaryText.setHorizontalAlignment(SwingConstants.CENTER);
		BinaryText
				.setText("0000          0000          0000          0000          0000          0000          0000          0000");
		BinaryText.setColumns(10);

		textField.setBounds(0, 0, 0, 0);
		textField.setColumns(10);
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 675);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 248));
		contentPane.setForeground(new Color(0, 102, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel Input = new JPanel();
		Input.setBorder(new LineBorder(new Color(102, 51, 255)));
		Input.setBounds(14, 12, 453, 40);
		contentPane.add(Input);
		Input.setLayout(new GridLayout(0, 1, 0, 0));

		InputText = new JTextField();
		InputText.setEditable(false);
		InputText.setHorizontalAlignment(SwingConstants.RIGHT);
		Input.add(InputText);
		InputText.setColumns(10);

		JPanel Output = new JPanel();
		Output.setBorder(new LineBorder(new Color(0, 204, 255)));
		Output.setBounds(14, 51, 453, 40);
		contentPane.add(Output);
		Output.setLayout(new GridLayout(0, 1, 0, 0));

		OutputText = new JTextField();
		OutputText.setEditable(false);
		OutputText.setHorizontalAlignment(SwingConstants.RIGHT);
		Output.add(OutputText);
		OutputText.setColumns(10);

		JPanel Output2 = new JPanel();
		Output2.setBounds(14, 191, 453, 71);
		contentPane.add(Output2);
		Output2.setLayout(null);

		Output2.add(BinaryText);

		BinaryCipher = new JLabel(
				"31                27               23              19               15               11              7                 3          ");
		BinaryCipher.setHorizontalAlignment(SwingConstants.RIGHT);
		BinaryCipher.setBounds(0, 46, 453, 25);
		Output2.add(BinaryCipher);

		JPanel Number = new JPanel();
		Number.setBounds(14, 262, 453, 354);
		Number.setBackground(new Color(248, 248, 248));
		contentPane.add(Number);
		Number.setLayout(new GridLayout(5, 6, 0, 0));

		Number.add(btnSHL);
		btnSHL.addActionListener(this);

		Number.add(btnSHR);
		btnSHR.addActionListener(this);

		btnCc.setForeground(new Color(255, 0, 0));
		btnCc.setFont(new Font("Arial Unicode MS", Font.BOLD, 19));
		Number.add(btnCc);
		btnCc.addActionListener(this);

		btnCE.setForeground(new Color(210, 105, 30));
		btnCE.setFont(new Font("Arial Unicode MS", Font.BOLD, 19));
		Number.add(btnCE);
		btnCE.addActionListener(this);

		btnBksp.setFont(new Font("�������", Font.BOLD, 14));
		Number.add(btnBksp);
		btnBksp.addActionListener(this);

		btnMOD.setFont(new Font("����", Font.PLAIN, 13));
		Number.add(btnMOD);
		btnMOD.addActionListener(this);

		btnPLUS.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		Number.add(btnPLUS);
		btnPLUS.addActionListener(this);

		btnC.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnC);
		btnC.addActionListener(this);

		btnD.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnD);

		btnD.addActionListener(this);

		btnE.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnE);
		btnE.addActionListener(this);

		btnF.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnF);
		btnF.addActionListener(this);

		btnOpen.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		Number.add(btnOpen);
		btnOpen.addActionListener(this);

		btnAND.setFont(new Font("����", Font.PLAIN, 13));
		Number.add(btnAND);
		btnAND.addActionListener(this);

		btnMINUS.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		Number.add(btnMINUS);
		btnMINUS.addActionListener(this);

		btn8.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn8);
		btn8.addActionListener(this);

		btn9.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn9);
		btn9.addActionListener(this);

		btnA.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnA);
		btnA.addActionListener(this);

		btnB.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnB);
		btnB.addActionListener(this);

		btnClose.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		Number.add(btnClose);
		btnClose.addActionListener(this);

		btnXOR.setFont(new Font("����", Font.PLAIN, 13));
		Number.add(btnXOR);
		btnXOR.addActionListener(this);

		btnMUL.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		Number.add(btnMUL);
		btnMUL.addActionListener(this);

		btn4.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn4);
		btn4.addActionListener(this);

		btn5.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn5);
		btn5.addActionListener(this);

		btn6.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn6);
		btn6.addActionListener(this);

		btn7.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn7);
		btn7.addActionListener(this);

		Number.add(btnChange);
		btnChange.addActionListener(this);

		btnOR.setFont(new Font("����", Font.PLAIN, 15));
		Number.add(btnOR);
		btnOR.addActionListener(this);

		btnDIV.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		Number.add(btnDIV);
		btnDIV.addActionListener(this);

		btn0.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn0);
		btn0.addActionListener(this);

		btn1.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn1);
		btn1.addActionListener(this);

		btn2.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn2);
		btn2.addActionListener(this);

		btn3.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btn3);
		btn3.addActionListener(this);

		btnPoint.setFont(new Font("Arial Black", Font.PLAIN, 18));
		Number.add(btnPoint);
		btnPoint.addActionListener(this);

		btnNOT.setFont(new Font("����", Font.PLAIN, 13));
		Number.add(btnNOT);
		btnNOT.addActionListener(this);

		btnEqual.setFont(new Font("����", Font.BOLD, 20));
		Number.add(btnEqual);
		btnEqual.addActionListener(this);

		JPanel System = new JPanel();
		System.setBorder(new LineBorder(new Color(0, 204, 255)));
		System.setBounds(14, 93, 310, 97);
		System.setBackground(new Color(248, 248, 248));
		contentPane.add(System);
		System.setLayout(new GridLayout(4, 2, 0, 0));
		// Octal ���� ��ư�� �������
		rdbtnOctal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				requestFocus();
				calcoption = rdbtnOctal.getText();
				OctalSet();
			}
		});
		// Hexa ���� ��ư�� �������
		rdbtnHexa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				requestFocus();
				calcoption = rdbtnHexa.getText();
				HexaSet();
			}
		});
		buttonGroup.add(rdbtnHexa);
		rdbtnHexa.setFont(new Font("Consolas", Font.PLAIN, 20));
		rdbtnHexa.setBackground(new Color(248, 248, 248));
		System.add(rdbtnHexa);

		rdbtnDecimal.setSelected(true);
		calcoption = rdbtnDecimal.getText();
		// Decimal ���� ��ư�� �������
		rdbtnDecimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				requestFocus();
				calcoption = rdbtnDecimal.getText();
				DecimalSet();
			}
		});

		labelHexa = new JLabel("");
		labelHexa.setHorizontalAlignment(SwingConstants.LEFT);
		System.add(labelHexa);
		buttonGroup.add(rdbtnDecimal);

		rdbtnDecimal.setFont(new Font("Consolas", Font.PLAIN, 20));
		rdbtnDecimal.setBackground(new Color(248, 248, 248));
		System.add(rdbtnDecimal);
		labelDecimal.setHorizontalAlignment(SwingConstants.LEFT);

		System.add(labelDecimal);
		buttonGroup.add(rdbtnOctal);
		rdbtnOctal.setFont(new Font("Consolas", Font.PLAIN, 20));
		rdbtnOctal.setBackground(new Color(248, 248, 248));
		System.add(rdbtnOctal);
		DecimalSet();
		// Binary ���� ��ư�� �������
		rdbtnBinary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				requestFocus();
				calcoption = rdbtnBinary.getText();
				binarySet();

			}
		});
		labelOctal.setHorizontalAlignment(SwingConstants.LEFT);

		System.add(labelOctal);
		buttonGroup.add(rdbtnBinary);
		rdbtnBinary.setFont(new Font("Consolas", Font.PLAIN, 20));
		rdbtnBinary.setBackground(new Color(248, 248, 248));
		System.add(rdbtnBinary);
		labelBinary.setHorizontalAlignment(SwingConstants.LEFT);

		System.add(labelBinary);

		JPanel Extra = new JPanel();
		Extra.setBorder(new LineBorder(new Color(0, 204, 255)));
		Extra.setBounds(323, 93, 144, 97);
		contentPane.add(Extra);
		Extra.setLayout(new GridLayout(0, 1, 0, 0));
		// Histoy üũ�ڽ� ��ư�� ������ ���
		chckbxHistory.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				requestFocus();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					h.setResizable(false);
					h.setLocation(frame.getX()+482, frame.getY());
					h.setVisible(true);

				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					h.setVisible(false);
				}
			}
		});
		chckbxHistory.setFont(new Font("Consolas", Font.PLAIN, 20));
		chckbxHistory.setBackground(new Color(248, 248, 248));
		Extra.add(chckbxHistory);
		// Converter üũ�ڽ� ��ư�� ������ ���
		chckbxConverter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				requestFocus();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					c.setResizable(false);
					c.setLocation(frame.getX(), frame.getY()+671);
					c.setVisible(true);

				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					c.setVisible(false);
				}
				;
			}
		});
		chckbxConverter.setFont(new Font("Consolas", Font.PLAIN, 20));
		chckbxConverter.setBackground(new Color(248, 248, 248));
		Extra.add(chckbxConverter);

		chckbxDateCalc.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				requestFocus();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					d.setResizable(false);
					d.setLocation(frame.getX()+482, frame.getY()+433);
					d.setVisible(true);

				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					d.setVisible(false);
				}
				;
			}
		});
		chckbxDateCalc.setFont(new Font("Consolas", Font.PLAIN, 20));
		chckbxDateCalc.setBackground(new Color(248, 248, 248));
		
		Extra.add(chckbxDateCalc);
		
		
		contentPane.add(textField);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		requestFocus();
		/*
		 * Bksp�� CE�� ��� InputText(inputString)�� ������ ���
		 */
		if (inputString.equals(" ") || OutputText.getText().equals("Error")) {
			reset();
		}
		/*
		 * ���׿����ڸ� �Է����� ���
		 * 
		 * ����� << �� >> ���� ���׿����ڿ� �߰��߽��ϴ�.���� ��� 2 << 6 �̶� �Ѵٸ� 2 * 2^6�� ���ݴϴ�.�Ʒ�
		 * doMath()�� �������ּ���.
		 */
		if (e.getActionCommand().equals("+")
				|| e.getActionCommand().equals("-")
				|| e.getActionCommand().equals("*")
				|| e.getActionCommand().equals("/")
				|| e.getActionCommand().equals("MOD")
				|| e.getActionCommand().equals("<<")
				|| e.getActionCommand().equals(">>")
				|| e.getActionCommand().equals("AND")
				|| e.getActionCommand().equals("XOR")
				|| e.getActionCommand().equals("OR")) {
			String press = e.getActionCommand();
			operator(press);

		}

		// ( ��ȣ�� �Է����� ���
		else if (e.getActionCommand().equals("(")) {
			// �� �Է� �ٷ� �ڿ��� �۵����� �ʽ��ϴ�.
			if (inputString.endsWith(" ") || inputString.equals(" ")) {

				inputString += "( ";
			}
		}

		// ) ��ȣ�� �Է����� ���
		else if (e.getActionCommand().equals(")")) {
			// = ���� ���Ŀ��� �۵����� �ʽ��ϴ�.
			if (operator != "=")
				inputString += " )";
		}

		// +/- ���� �����ڸ� �Է����� ���
		else if (e.getActionCommand().equals("+/-")) {
			int memory1 = 0;
			int memory2 = 0;
			/*
			 * = ���� ����(��� ����) +/-�� �Է����� ��� inputString �� �տ� �ڵ����� ��갪�� �߰����ݴϴ�.
			 */
			if (operator == "=") {
				tempNum = num1;
				reset();
				num1 = tempNum;
				inputString = " -" + num1;
			} else {
				/*
				 * ������ �Է� ����, inputString�� ����� ��쿡�� �۵����� �ʽ��ϴ�.
				 */
				if (!inputString.endsWith(" ") && !inputString.equals(" ")) {
					/*
					 * ���� ��ȣ�� ������������ ( ��ȣ �տ� -�� �߰����ݴϴ�.
					 */
					if (inputString.endsWith(" )")) {
						tempString = inputString;
						while (tempString.endsWith(" )")) {
							memory1 = tempString.lastIndexOf("( ");
							memory2 = tempString.substring(memory1).indexOf(
									" )")
									+ 1 + memory1;
							tempString = replaceString(tempString, memory1, "|");
							tempString = replaceString(tempString, memory2, "|");
						}
						inputString = insertString(inputString, "-",
								memory1 - 1);

					} else {
						if (inputString.substring(inputString.lastIndexOf(" "))
								.contains("-")) {
							inputString = replaceString(inputString,
									inputString.lastIndexOf("-"), "");
						} else
							inputString = insertString(inputString, "-",
									inputString.lastIndexOf(" "));
					}
				}
			}

		}
		// NOT ��ư�� ������ ���
		else if (e.getActionCommand().equals("NOT")) {
			int memory1 = 0;
			int memory2 = 0;
			// = �������Ŀ��� ������� NOT�� �߰��Ͽ� inputText�� �����ݴϴ�.
			if (operator == "=") {
				tempNum = num1;
				reset();
				num1 = tempNum;
				inputString = " NOT(" + num1 + ")";
			} else {
				if (!inputString.endsWith(" ") && !inputString.equals(" ")) {

					// ��ȣ�� �׿��ִ� ���� NOT�� ���� ���
					if (inputString.endsWith(" )")) {
						tempString = inputString;
						/*
						 * ��ȣ�� ���� ��� ��ȣ�տ� NOT�� �ٿ��ݴϴ�.
						 */
						while (tempString.endsWith(" )")) {
							memory1 = tempString.lastIndexOf("( ");
							memory2 = tempString.substring(memory1).indexOf(
									" )")
									+ 1 + memory1;
							tempString = replaceString(tempString, memory1, "|");
							tempString = replaceString(tempString, memory2, "|");
						}
						inputString = insertString(inputString, "NOT(",
								memory1 - 1);

					} else {
						inputString = insertString(inputString, "NOT(",
								inputString.lastIndexOf(" "));
					}
					inputString += ")";
				}
			}
		}
		// = ��ư�� ������ ���
		else if (e.getActionCommand().equals("=")) {
			enter();
		}
		// CE ��ư�� ������ ���
		else if (e.getActionCommand().equals("CE")) {
			// ���Է��� ���߰ų� �ʱ�ȭ�� ���� ��� Ȥ�� = ������ �� ��찡 �ƴ� ��쿡�� �۵��մϴ�.
			if (!inputString.endsWith(" ") && operator != "=") {
				if (inputString.endsWith(" )")) {
					inputString = inputString.substring(0,
							inputString.lastIndexOf("( "));
				} else if (inputString.endsWith(")")) {
					inputString = inputString.substring(0,
							inputString.lastIndexOf(" ") + 1);
				} else {
					inputString = inputString.substring(0,
							inputString.lastIndexOf(" ") + 1);
				}
			}
		}
		// Bksp ��ư�� ������ ���
		else if (e.getActionCommand().equals("Bksp")) {
			backspace();
		}
		// C ��ư�� ������ ���
		else if (e.getActionCommand().equals(" C ")) {
			reset();
		}
		// ���� �Է����� ���
		else {
			if (operator == "=" && !e.getActionCommand().equals(".")) {
				reset();
				inputString = " " + e.getActionCommand();
			} else if (operator == "=" && e.getActionCommand().equals(".")) {
				inputNum = num1 + ".";
				inputString = " " + inputNum;
			} else if (!inputString.endsWith(")")) {
				inputNum += e.getActionCommand();
				inputString += e.getActionCommand();
			}
			operator = "";
		}
		InputText.setText(inputString);

	}

	/*
	 * [���� �켱����] ('+/-', 'NOT')-> ('MOD', '/', '>>', '*','<<')-> ('-', '+')->
	 * (AND,XOR, OR)
	 * 
	 * 
	 * [��� ���] 
	 * 1. ���� �켱������ ���� �����ڸ� ã���ϴ�. 
	 * 2. �����ڸ� ã�� ��� �����ڰ� ������ ���� ���� num1�� ����, �����ڰ� ���� ���� ���� num2�� �����մϴ�. 
	 * ex) 1 + 24 * 35 - 3 �� ��� 
	 * ���� �켱������ ���� *�� �����ڷ� ��� �� 
	 * ������ �A�� �� 24�� num1��, ���� �� 35�� num2�� �����մϴ�. 
	 * 3. doMath�Լ��� ���� num1�� num2�� �����մϴ�.
	 * 
	 * 
	 * [��� ����] 
	 * 1.�켱 ��ȣ�� �ִ��� Ȯ���մϴ�.(���� ��� 2. ���� ��� 3.����) 
	 * 2.��ȣ�� ���� ��� ��� ��Ŀ� ���� ��ȣ���� ������ �������ְ� ��� ������ ������� ��ȣ�� �����մϴ�. 
	 * 3.��ȣ�� ���� ��� ��� ��Ŀ� ���� ������ �����մϴ�.
	 * 4.OutputText�� ������� ����մϴ�.
	 * 
	 * 
	 * ����� >> �� << �� ������ ����� �����ϰ� ���� �ΰ��� �޾ƿɴϴ�.(���� ����� �ƴմϴ�.)
	 */
	private void calculate() {
		int start, end;
		int Nstart, Nend;
		copyString = "";

		/* ()������ �ִ� ��� */
		while (calcString.contains("( ") || calcString.contains(" )")) {
			start = calcString.lastIndexOf("( ");
			end = calcString.substring(start).indexOf(" )") + 1 + start;
			copyString = calcString.substring(start + 1, end);
			System.out.println(copyString);
			try {
				// ���Ŀ� NOT�� ���� ���
				while (copyString.contains("NOT")) {
					Nstart = copyString.lastIndexOf("NOT");
					Nend = copyString.substring(Nstart).indexOf(")") + Nstart;
					num1 = copyString.substring(Nstart + 4, Nend);
					num1 = doMath(num1, "NOT", "0");
					copyString = deleteString(copyString, Nstart, Nend);
					copyString = insertString(copyString, num1, Nstart - 1);
				}
			} catch (Exception ex) {
				// ���� NOT ����� �Ұ����ϴٸ� �ٸ� ������ ���� ���ݴϴ�.
			}
			// ���Ŀ� MOD�� ���� ���
			while (copyString.contains("MOD")) {
				doCopyString("MOD", 1, 4);
			}
			// ���Ŀ� /�� ���� ���
			while (copyString.contains("/")) {
				doCopyString("/", 1, 2);
			}
			// ���Ŀ� >>�� ���� ���
			while (copyString.contains(">>")) {
				doCopyString(">>", 1, 3);
			}
			// ���Ŀ� *�� ���� ���
			while (copyString.contains("*")) {
				doCopyString("*", 1, 2);
			}
			// ���Ŀ� <<�� ���� ���
			while (copyString.contains("<<")) {
				doCopyString("<<", 1, 3);
			}
			/*
			 * ���Ŀ� -�� ���� ���������� Ư���ϰ� " - "�� �ִ��� ã���ϴ�.����� �������� �����ϱ� �����Դϴ�.
			 */
			while (copyString.contains(" - ")) {
				doCopyString(" - ", 0, 3);
			}
			// ���Ŀ� +�� ���� ���
			while (copyString.contains("+")) {
				doCopyString("+", 1, 2);
			}
			// ���Ŀ� AND�� ���� ���
			while (copyString.contains("AND")) {
				doCopyString("AND", 1, 4);
			}
			// ���Ŀ� XOR�� ���� ���
			while (copyString.contains("XOR")) {
				doCopyString("XOR", 1, 4);
			}
			// ���Ŀ� OR�� ���� ���
			while (copyString.contains("OR")) {
				doCopyString("OR", 1, 3);
			}
			/*
			 * ()���� ��� ������ ������� ��ü �Ŀ��� ��ȣ���� ������� ���ΰ� ��ȣ�� �����ݴϴ�. ex) 1 + ( 6 + 3
			 * ) -> 1 + 9
			 */
			calcString = deleteString(calcString, start, end);
			calcString = insertString(calcString, num1, start - 1);
			System.out.println(calcString);

		}
		/* ()������ ���� ��� */
		while (calcString.contains("NOT")) {
			Nstart = calcString.lastIndexOf("NOT");
			Nend = calcString.substring(Nstart).indexOf(")") + Nstart;
			num1 = calcString.substring(Nstart + 4, Nend);
			num1 = doMath(num1, "NOT", "0");
			calcString = deleteString(calcString, Nstart, Nend);
			calcString = insertString(calcString, num1, Nstart - 1);
		}
		// ���Ŀ� MOD�� ���� ���
		while (calcString.contains("MOD")) {
			doCalcString("MOD", 1, 4);
		}
		// ���Ŀ� /�� ���� ���
		while (calcString.contains("/")) {
			doCalcString("/", 1, 2);
		}
		// ���Ŀ� >>�� ���� ���
		while (calcString.contains(">>")) {
			doCalcString(">>", 1, 3);
		}
		// ���Ŀ� *�� ���� ���
		while (calcString.contains("*")) {
			doCalcString("*", 1, 2);
		}
		// ���Ŀ� <<�� ���� ���
		while (calcString.contains("<<")) {
			doCalcString("<<", 1, 3);
		}
		/*
		 * ���Ŀ� -�� ���� ���������� Ư���ϰ� " - "�� �ִ��� ã���ϴ�.����� �������� �����ϱ� �����Դϴ�.
		 */
		while (calcString.contains(" - ")) {
			doCalcString(" - ", 0, 3);
		}
		// ���Ŀ� +�� ���� ���
		while (calcString.contains("+")) {
			doCalcString("+", 1, 2);
		}
		// ���Ŀ� AND�� ���� ���
		while (calcString.contains("AND")) {
			doCalcString("AND", 1, 4);
		}
		// ���Ŀ� XOR�� ���� ���
		while (calcString.contains("XOR")) {
			doCalcString("XOR", 1, 4);
		}
		// ���Ŀ� OR�� ���� ���
		while (calcString.contains("OR")) {
			doCalcString("OR", 1, 3);
		}

		// ������� num1�� �����մϴ�.
		num1 = calcString.substring(1, calcString.indexOf("=") - 1);
		System.out.println(num1);

	}

	/*
	 * [���ڿ� �����ڸ� �и��Ͽ� ����ϴ� ���] 
	 * Space�ٸ� ���� Ȯ���մϴ�. 
	 * 
	 * ex)(���ظ� ���� space�ٸ� %�� ǥ���մϴ�.)
	 * %6%+%3% 
	 * 1.������ + ���� %�� ã���ϴ�. 
	 * 2.%6%�� ã���� %�� ����� ���� 6�� num1�� �����ϴ�. 
	 * 3.������ +  �Ŀ� %�� ã���ϴ�. 
	 * 4.%3%�� ã���� %�� ����� ���� 3�� num2�� �����ϴ�. 
	 * 5.doMath�Լ��� num1�� ������, num2�� �����ϴ�.
	 * 6.���
	 */

	// doCalcString�Լ��� ()��ȣ�� ���� ���Ŀ����� ����մϴ�.
	private void doCalcString(String op, int number1, int number2) {
		int Cstart, Cend;
		Cstart = (calcString.substring(0, calcString.indexOf(op) - number1))
				.lastIndexOf(" ");
		Cend = (calcString.substring(calcString.indexOf(op) + number2))
				.indexOf(" ") + calcString.indexOf(op) + number2;
		if (Cstart >= 0)
			num1 = calcString.substring(Cstart, calcString.indexOf(op)
					- number1);
		else {
			Cstart = 0;
			num1 = "0";
		}
		num2 = calcString.substring(calcString.indexOf(op) + number2, Cend);
		num1 = doMath(num1, op, num2);
		calcString = deleteString(calcString, Cstart + 1, Cend - 1);
		calcString = insertString(calcString, num1, Cstart);

	}

	// doCopyString�Լ��� ()��ȣ�� ���� ���Ŀ����� ����մϴ�.
	private void doCopyString(String op, int number1, int number2) {
		int Cstart, Cend;
		Cstart = (copyString.substring(0, copyString.indexOf(op) - number1))
				.lastIndexOf(" ");
		Cend = (copyString.substring(copyString.indexOf(op) + number2))
				.indexOf(" ") + copyString.indexOf(op) + number2;
		if (Cstart >= 0)
			num1 = copyString.substring(Cstart, copyString.indexOf(op)
					- number1);
		else {
			Cstart = 0;
			num1 = "0";
		}
		num2 = copyString.substring(copyString.indexOf(op) + number2, Cend);
		num1 = doMath(num1, op, num2);
		copyString = deleteString(copyString, Cstart + 1, Cend - 1);
		copyString = insertString(copyString, num1, Cstart);

	}

	/*
	 * startS�κк��� word��� ���ڸ� ����ϴ�.(�ڵ� © �� ���� �ϱ� ���� ���� String��ȯ�Լ��Դϴ�.)
	 */
	private String replaceString(String text, int startS, String word) {
		text = deleteString(text, startS, startS + word.length() - 1);
		text = insertString(text, word, startS - 1);

		return text;
	}

	/*
	 * startS���� endS������ ���ڸ� �����մϴ�.(�ڵ� © �� ���� �ϱ� ���� ���� String��ȯ�Լ��Դϴ�.)
	 */
	private String deleteString(String text, int startS, int endS) {
		String firstString = text.substring(0, startS);
		String lastString = text.substring(endS + 1);

		text = firstString + lastString;

		return text;
	}

	/*
	 * �Է��� ���� ���� ������ �߰������մϴ�.(�ڵ� © �� ���� �ϱ� ���� ���� String��ȯ�Լ��Դϴ�.)
	 */
	private static String insertString(String text, String newtext,
			int locationS) {
		String firstString = text.substring(0, locationS + 1);
		String lastString = text.substring(locationS + 1);
		text = firstString + newtext + lastString;
		return text;
	}

	// ���� ���ִ� �Լ��Դϴ�.
	private String doMath(String num1, String op, String num2) {
		// String�� ������ double�� ������ ��ȯ���ش�.
		boolean Doubletype = true;
		double Dnumber1, Dnumber2;
		int number1, number2;
		if (rdbtnHexa.isSelected()) {
			if(num1.length() == 9 && num1.startsWith(" F"))
				number1 = negative;
			else
				number1 = Integer.valueOf(num1.trim(), 16);
			
			number2 = Integer.valueOf(num2.trim(), 16);
			Dnumber1 = (double) number1;
			Dnumber2 = (double) number2;
			Doubletype = false;
		} else if (rdbtnOctal.isSelected()) {
			if(num1.length() == 12&& num1.startsWith(" 3"))
				number1 = negative;
			else
			number1 = Integer.valueOf(num1.trim(), 8);
			
			number2 = Integer.valueOf(num2.trim(), 8);
			Dnumber1 = (double) number1;
			Dnumber2 = (double) number2;
			Doubletype = false;
		} else if (rdbtnBinary.isSelected()) {
			if(num1.length() == 33)
				number1 = negative;
			else
			number1 = Integer.valueOf(num1.trim(), 2);
			
			number2 = Integer.valueOf(num2.trim(), 2);
			Dnumber1 = (double) number1;
			Dnumber2 = (double) number2;
			Doubletype = false;
		} else {
			Dnumber1 = Double.parseDouble(num1.trim());
			Dnumber2 = Double.parseDouble(num2.trim());
			number1 = (int) Dnumber1;
			number2 = (int) Dnumber2;
		}
		// �����ڸ� �����մϴ�.
		switch (op) {
		case "+":
			Dnumber1 = Dnumber1 + Dnumber2;
			number1 = (int) Dnumber1;
			break;
		case " - ":
			Dnumber1 = Dnumber1 - Dnumber2;
			number1 = (int) Dnumber1;
			break;
		case "*":
			Dnumber1 = Dnumber1 * Dnumber2;
			number1 = (int) Dnumber1;
			break;
		case "/":
			Dnumber1 = Dnumber1 / Dnumber2;
			number1 = (int) Dnumber1;
			break;
		case "MOD":
			Dnumber1 = Dnumber1 % Dnumber2;
			number1 = (int) Dnumber1;
			break;
		case "<<":
			number1 = number1 << number2;
			Doubletype = false;
			break;
		case ">>":
			number1 = number1 >> number2;
			Doubletype = false;
			break;
		case "AND":
			number1 = number1 & number2;
			Doubletype = false;
			break;
		case "XOR":
			number1 = number1 ^ number2;
			Doubletype = false;
			break;
		case "OR":
			number1 = number1 | number2;
			Doubletype = false;
			break;
		case "NOT":
			number1 = ~number1;
			Doubletype = false;
			break;
		default:
			break;
		}

		// num1���� ������ ���
		if (Dnumber1 == (double) number1)
			Doubletype = false;

		else
			Dnumber1 = Double.parseDouble((String.format("%.3f", Dnumber1)));

		hexa = Integer.toHexString(number1).toUpperCase();
		decimal = "" + number1;
		octal = Integer.toOctalString(number1);
		binary = Integer.toBinaryString(number1);
		//
		if (Doubletype)
			return ("" + Dnumber1);
		else {
			// �������� ��ư�� ��� �����ִ��� Ȯ���մϴ�.
			if (rdbtnHexa.isSelected()) {
				return Integer.toHexString(number1).toUpperCase();
			} else if (rdbtnOctal.isSelected()) {
				return Integer.toOctalString(number1);
			} else if (rdbtnBinary.isSelected()) {
				return Integer.toBinaryString(number1);
			} else
				return ("" + number1);
		}

	}

	// �����ڸ� ������ ��� (Ű���峪 ��ư����) �����ϴ� �Լ�.
	public void operator(String press) {
		//�߸��� ���� ���Ŀ��� �۵����� �ʽ��ϴ�.
		if (!OutputText.getText().equals("Error")) {
			/*
			 * = ���� ����(��� ����) �����ڸ� �Է����� ��� inputString �� �տ� �ڵ����� ��갪�� �߰����ݴϴ�.
			 */
			if (operator == "=") {
				tempNum = num1;
				reset();
				num1 = tempNum;
				inputString = " " + num1;
			}
			/*
			 * � �� �Էµ� ���� �ٷ� �����ڸ� ������ ��� �ڵ����� �� �տ� 0�� �߰����ݴϴ�.
			 */
			if (inputString == " " || InputText.getText().equals(" ")) {
				inputString = " 0 " + press + " ";
			}
			/*
			 * �����ڸ� �����ؼ� �Է��� ��� ���� �����ڷ� �ٲ��ݴϴ�.
			 */
			if (inputString.endsWith("MOD ") || inputString.endsWith("AND ")
					|| inputString.endsWith("XOR ")) {
				inputString = inputString
						.substring(0, inputString.length() - 5)
						+ " "
						+ press
						+ " ";
			} else if (inputString.endsWith("<< ")
					|| inputString.endsWith(">> ")
					|| inputString.endsWith("OR ")) {
				inputString = inputString
						.substring(0, inputString.length() - 4)
						+ " "
						+ press
						+ " ";
			}

			else if (inputString.endsWith(" ") && !inputString.endsWith("( ")
					&& !inputString.equals(" ")) {

				inputString = inputString
						.substring(0, inputString.length() - 3)
						+ " "
						+ press
						+ " ";

			} else
				inputString += " " + press + " ";

			inputNum = "";
			operator = press;
		}
	}

	// Backspace�� ������ ��� (Ű���峪 ��ư����) �����ϴ� �Լ�.
	public void backspace() {
		// ���Է��� ���߰ų� �ʱ�ȭ�� ���� ��� Ȥ�� = ������ �� ��찡 �ƴҰ�쿡�� �۵��մϴ�.
		if (!inputString.equals(" ") && operator != "=") {
			// Bksp�� �ѹ� ���� ������ �� �ϳ��� ����ų� �����ڸ� ���� �� �ֽ��ϴ�.
			if (inputString.endsWith("MOD ") || inputString.endsWith("AND ")
					|| inputString.endsWith("XOR ")) {
				inputString = inputString
						.substring(0, inputString.length() - 5);
			} else if (inputString.endsWith("<< ")
					|| inputString.endsWith(">> ")
					|| inputString.endsWith("OR ")) {
				inputString = inputString
						.substring(0, inputString.length() - 4);
				btnPoint.setEnabled(true);
			} else if (inputString.endsWith(" )") || inputString.endsWith("( ")) {
				inputString = inputString
						.substring(0, inputString.length() - 2);
			} else if (inputString.endsWith(")")) {
				inputString = inputString.substring(0,
						inputString.lastIndexOf(" ") + 1);
			} else if (inputString.endsWith(" ")) {
				inputString = inputString
						.substring(0, inputString.length() - 3);
			} else if (inputString.endsWith(".")) {
				inputString = inputString
						.substring(0, inputString.length() - 1);
			} else {
				if (inputString.substring(inputString.lastIndexOf(" "))
						.contains("-"))
					inputString = inputString.substring(0,
							inputString.length() - 2);
				else
					inputString = inputString.substring(0,
							inputString.length() - 1);
				if (!inputNum.equals(""))
					inputNum = inputNum.substring(0, inputNum.length() - 1);

			}
		}
	}

	// = �̳� Enter�� ������ ��� (Ű���峪 ��ư����) �����ϴ� �Լ�.
	public void enter() {
		String hex, dec, oct, bin;
		int i;
		// =�� �����Է��� �Ұ��մϴ�.
		if (operator != "=") {
			// �߸��� ������ ���� ��쵵 Ȯ���մϴ�.
			try {

				inputString += " =";
				calcString = inputString;
				System.out.println(calcString);

				calculate();

				hex = hexa;
				dec = decimal;
				oct = octal;
				bin = binary;
				/*
				 * ������ȯ ��ư ���� �ߴ� ���� �� �б� ���� ����ϱ� �����Դϴ�.
				 */
				if (hexa.length() - 5 >= 0) {
					for (i = hexa.length() - 5; i >= 0; i = i - 4) {
						hex = insertString(hex, " ", i);
					}
				}
				if (decimal.length() - 4 >= 0 && Integer.parseInt(decimal) >= 0) {
					for (i = decimal.length() - 4; i >= 0; i = i - 3) {
						dec = insertString(dec, ",", i);
					}
				}
				if (octal.length() - 4 >= 0) {
					for (i = octal.length() - 4; i >= 0; i = i - 3) {
						oct = insertString(oct, " ", i);
					}
				}
				if (binary.length() - 5 >= 0) {
					for (i = binary.length() - 5; i >= 0; i = i - 4) {
						bin = insertString(bin, " ", i);
					}
				}

				operator = "=";
				OutputText.setText(num1);
				
				if(Integer.parseInt(decimal)<0){
					negative = Integer.parseInt(decimal);
				}
				
				/*
				 * ���� ��ȯ ��ư ���� ��� ���� ������� �߰� �մϴ�.
				 */
				labelHexa.setText(hex);
				labelDecimal.setText(dec);
				labelOctal.setText(oct);
				labelBinary.setText(bin);

				/*
				 * 2���� ������� ������ȯ ��ư �Ʒ��� �߰� �մϴ�.
				 */
				bin = "";
				if (binary.length() < 32) {
					for (i = 0; i < 32 - binary.length(); i++) {
						bin += "0";
					}
				}
				bin += binary;
				for (i = bin.length() - 5; i >= 0; i = i - 4) {
					bin = insertString(bin, "          ", i);
				}
				BinaryText.setText(bin);

				// History�� ������� �����ϴ�.
				h.listmodel.addElement("Calculator - " + calcoption);
				h.listmodel.addElement(inputString.substring(1,
						inputString.length() - 2));
				h.listmodel.addElement("= " + num1);
				h.listmodel.addElement(" ");

			} // �߸��� ������ ��� �Ʒ��� ���� Error�� ����մϴ�.
			catch (Exception ex) {
				inputString = "Wrong fomula";
				OutputText.setText("Error");
			}
		}
	}

	// �ʱ�ȭ�� �����ִ� �Լ�.
	public void reset() {
		inputNum = "";
		inputString = " ";
		calcString = "";
		num1 = "";
		num2 = "";
		operator = "";
		hexa = "";
		decimal = "";
		octal = "";
		binary = "";
		InputText.setText("");
		OutputText.setText("");
		labelHexa.setText("0");
		labelDecimal.setText("0");
		labelOctal.setText("0");
		labelBinary.setText("0");
		BinaryText
				.setText("0000          0000          0000          0000          0000          0000          0000          0000");

	}

	public void binarySet() {
		String result = binary;
		reset();
		inputString = " " + result;
		InputText.setText(inputString);
		btn0.setEnabled(true);
		btn1.setEnabled(true);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(false);
		btn6.setEnabled(false);
		btn7.setEnabled(false);
		btn8.setEnabled(false);
		btn9.setEnabled(false);
		btnA.setEnabled(false);
		btnB.setEnabled(false);
		btnC.setEnabled(false);
		btnD.setEnabled(false);
		btnE.setEnabled(false);
		btnF.setEnabled(false);
		btnPoint.setEnabled(false);
	}

	public void OctalSet() {
		String result = octal;
		reset();
		inputString = " " + result;
		InputText.setText(inputString);
		btn0.setEnabled(true);
		btn1.setEnabled(true);
		btn2.setEnabled(true);
		btn3.setEnabled(true);
		btn4.setEnabled(true);
		btn5.setEnabled(true);
		btn6.setEnabled(true);
		btn7.setEnabled(true);
		btn8.setEnabled(false);
		btn9.setEnabled(false);
		btnA.setEnabled(false);
		btnB.setEnabled(false);
		btnC.setEnabled(false);
		btnD.setEnabled(false);
		btnE.setEnabled(false);
		btnF.setEnabled(false);
		btnPoint.setEnabled(false);
	}

	public void DecimalSet() {
		String result = decimal;
		reset();
		inputString = " " + result;
		InputText.setText(inputString);
		btn0.setEnabled(true);
		btn1.setEnabled(true);
		btn2.setEnabled(true);
		btn3.setEnabled(true);
		btn4.setEnabled(true);
		btn5.setEnabled(true);
		btn6.setEnabled(true);
		btn7.setEnabled(true);
		btn8.setEnabled(true);
		btn9.setEnabled(true);
		btnA.setEnabled(false);
		btnB.setEnabled(false);
		btnC.setEnabled(false);
		btnD.setEnabled(false);
		btnE.setEnabled(false);
		btnF.setEnabled(false);
		btnPoint.setEnabled(true);
	}

	public void HexaSet() {
		String result = hexa;
		reset();
		inputString = " " + result;
		InputText.setText(inputString);
		btn0.setEnabled(true);
		btn1.setEnabled(true);
		btn2.setEnabled(true);
		btn3.setEnabled(true);
		btn4.setEnabled(true);
		btn5.setEnabled(true);
		btn6.setEnabled(true);
		btn7.setEnabled(true);
		btn8.setEnabled(true);
		btn9.setEnabled(true);
		btnA.setEnabled(true);
		btnB.setEnabled(true);
		btnC.setEnabled(true);
		btnD.setEnabled(true);
		btnE.setEnabled(true);
		btnF.setEnabled(true);
		btnPoint.setEnabled(false);
	}
}