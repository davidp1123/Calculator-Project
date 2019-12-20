import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class DateCalc extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JComboBox CBFromYear;
	private JComboBox CBFromMonth;
	private JComboBox CBFromDay;
	private JLabel lblTo;
	private JComboBox CBToYear;
	private JComboBox CBToMonth;
	private JComboBox CBToDay;
	private JTextField textFieldResult;
	private JLabel lblResult;
	private JButton btnNewButton;

	int year = 2016;
	int month = 1;
	int day = 1;
	int[] dateFrom = new int[3];
	int[] dateTo = new int[3];
	int[] dateDifference = new int[3];
	private JButton btnNewButton_1;
	
	//DateCalc 생성자 : Calculator의 Date체크박스와 History의 listmodel을 인자로 받음
	public DateCalc(final JCheckBox chckbxDateCalc, final DefaultListModel<String> listmodel) {
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent w) {
				chckbxDateCalc.setSelected(false);// 창을 닫으면 Date체크박스를 체크해제함
			}
		});
		setTitle("Date Calculator");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//X 버튼을 누르면 창이 숨겨짐
		setBounds(100, 100, 603, 242);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("From:");
		label.setBounds(12, 10, 106, 22);
		contentPane.add(label);

		CBFromYear = new JComboBox();
		CBFromYear.setBounds(12, 30, 106, 21);
		contentPane.add(CBFromYear);

		//Month가 선택되면 이에 맞춰 Date가 설정됨 윤년과 월별 날짜수에 따라 Date ComboBox에 입력되는 날짜 수가 다름
		CBFromMonth = new JComboBox();
		CBFromMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CBFromMonth.getSelectedIndex() + 1 == 1 || CBFromMonth.getSelectedIndex() + 1 == 3
						|| CBFromMonth.getSelectedIndex() + 1 == 5 || CBFromMonth.getSelectedIndex() + 1 == 7
						|| CBFromMonth.getSelectedIndex() + 1 == 8 || CBFromMonth.getSelectedIndex() + 1 == 10
						|| CBFromMonth.getSelectedIndex() + 1 == 12){
					CBFromDay.removeAllItems();
					for(int i = 1; i < 32; i++)
						CBFromDay.addItem(i);
				}else if(CBFromMonth.getSelectedIndex() + 1 == 4 ||
						CBFromMonth.getSelectedIndex() + 1 == 6 ||
						CBFromMonth.getSelectedIndex() + 1 == 9 ||
						CBFromMonth.getSelectedIndex() + 1 == 11){
					CBFromDay.removeAllItems();
					for(int i = 1; i < 31; i++)
						CBFromDay.addItem(i);
				}else if(CBFromMonth.getSelectedIndex() == 1)
					if(CBFromYear.getSelectedIndex() % 4 == 0){
						CBFromDay.removeAllItems();
						for(int i = 1;i < 30;i++)
							CBFromDay.addItem(i);
					}else{
						CBFromDay.removeAllItems();
						for(int i = 1;i < 29;i++)
							CBFromDay.addItem(i);
					}
				
					
			}
		});
		CBFromMonth.setBounds(151, 30, 106, 21);
		contentPane.add(CBFromMonth);

		CBFromDay = new JComboBox();
		CBFromDay.setBounds(292, 30, 106, 21);
		contentPane.add(CBFromDay);

		lblTo = new JLabel("To:");
		lblTo.setBounds(12, 63, 106, 22);
		contentPane.add(lblTo);

		CBToYear = new JComboBox();
		CBToYear.setBounds(12, 86, 106, 21);
		contentPane.add(CBToYear);

		//Month가 선택되면 이에 맞춰 Date가 설정됨 윤년과 월별 날짜수에 따라 Date ComboBox에 입력되는 날짜 수가 다름
		CBToMonth = new JComboBox();
		CBToMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (CBToMonth.getSelectedIndex() + 1 == 1 || CBToMonth.getSelectedIndex() + 1 == 3
						|| CBToMonth.getSelectedIndex() + 1 == 5 || CBToMonth.getSelectedIndex() + 1 == 7
						|| CBToMonth.getSelectedIndex() + 1 == 8 || CBToMonth.getSelectedIndex() + 1 == 10
						|| CBToMonth.getSelectedIndex() + 1 == 12){
					CBToDay.removeAllItems();
					for(int i = 1; i < 32; i++)
						CBToDay.addItem(i);
				}else if(CBToMonth.getSelectedIndex() + 1 == 4 ||
						CBToMonth.getSelectedIndex() + 1 == 6 ||
						CBToMonth.getSelectedIndex() + 1 == 9 ||
						CBToMonth.getSelectedIndex() + 1 == 11){
					CBToDay.removeAllItems();
					for(int i = 1; i < 31; i++)
						CBToDay.addItem(i);
				}else if(CBToMonth.getSelectedIndex() == 1)
					if(CBToYear.getSelectedIndex() % 4 == 0){
						CBToDay.removeAllItems();
						for(int i = 1;i < 30;i++)
							CBToDay.addItem(i);
					}else{
						CBToDay.removeAllItems();
						for(int i = 1;i < 29;i++)
							CBToDay.addItem(i);
					}
				
					
			}
		});
		CBToMonth.setBounds(151, 86, 106, 21);
		contentPane.add(CBToMonth);

		CBToDay = new JComboBox();
		CBToDay.setBounds(292, 86, 106, 21);
		contentPane.add(CBToDay);

		textFieldResult = new JTextField();
		textFieldResult.setEditable(false);
		textFieldResult.setBounds(10, 160, 247, 22);
		contentPane.add(textFieldResult);
		textFieldResult.setColumns(10);

		lblResult = new JLabel("Difference:");
		lblResult.setBounds(12, 138, 106, 22);
		contentPane.add(lblResult);

		//Calculate 버튼을 누르면 날짜 차이가 계산됨
		btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateFrom[0] = (int) CBFromYear.getSelectedItem();
				dateFrom[1] = (int) CBFromMonth.getSelectedItem();
				dateFrom[2] = (int) CBFromDay.getSelectedItem();
				dateTo[0] = (int) CBToYear.getSelectedItem();
				dateTo[1] = (int) CBToMonth.getSelectedItem();
				dateTo[2] = (int) CBToDay.getSelectedItem();
				CalculateTimeDifference(dateFrom, dateTo);
				textFieldResult.setText(
						dateDifference[0] + " Years " + dateDifference[1] + " Months " + dateDifference[2] + " Days");
			}
		});
		btnNewButton.setBounds(430, 85, 130, 23);
		contentPane.add(btnNewButton);

		//Add to History 버튼을 누르면 History에 내용이 저장됨
		btnNewButton_1 = new JButton("Add to History");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listmodel.addElement("Date Calculator");
				listmodel.addElement("From " + CBFromYear.getSelectedItem() + " / " + CBFromMonth.getSelectedItem()
						+ " / " + CBFromDay.getSelectedItem() + " to " + CBToYear.getSelectedItem() + " / "
						+ CBToMonth.getSelectedItem() + " / " + CBToDay.getSelectedItem());
				listmodel.addElement("Difference : " + textFieldResult.getText());
				listmodel.addElement(" ");
			}
		});
		btnNewButton_1.setBounds(430, 29, 130, 22);
		contentPane.add(btnNewButton_1);
		//연도는 2000년부터 2050년까지 선택가능
		for (year = 2000; year <= 2050; year++) {
			CBFromYear.addItem(year);
			CBToYear.addItem(year);
		}
		//날짜계산기 첫 실행 후 초기화
		//1월부터 12월까지 선택가능
		for (month = 1; month <= 12; month++) {
			CBFromMonth.addItem(month);
			CBToMonth.addItem(month);
		}
		//1일부터 월별 최대 31일까지 선택가능
		for (day = 1; day <= 31; day++) {
			CBFromDay.addItem(day);
			CBToDay.addItem(day);
		}
		
		CBFromDay.removeAllItems();
		CBToDay.removeAllItems();
		for(int i = 1; i < 32; i++){
			CBFromDay.addItem(i);
			CBToDay.addItem(i);
		}
	}

	public void CalculateTimeDifference(int[] dateFrom, int[] dateTo) {
		dateDifference[0] = dateTo[0] - dateFrom[0];// 연도끼리 뺀다

		dateDifference[1] = dateTo[1] - dateFrom[1];// 월끼리 뺀다
		if (dateDifference[1] < 0) {// 만약 뺀 값이 0보다 작으면 연도에서 1을 빼고 12개월을 더해준다
			dateDifference[0]--;
			dateDifference[1] += 12;
		}
		dateDifference[2] = dateTo[2] - dateFrom[2];
		if (dateDifference[2] < 0) {
			if (dateDifference[1] >= 1) {
				dateDifference[1]--;
				dateDifference[2] += 31;
			} else if (dateDifference[1] == 0) {
				dateDifference[0]--;
				dateDifference[1] += 11;
				dateDifference[2] += 31;
			}
		}
	}

}
