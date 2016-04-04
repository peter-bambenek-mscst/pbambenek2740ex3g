package pbambenek2740ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import java.awt.event.FocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame implements ActionListener, FocusListener, WindowListener {

	private JPanel contentPane;
	private JList employeeList;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	
	private DefaultListModel employeeListModel;
	private JTextField empIdTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton updateButton;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private PayrollObjMapper payrollObjMapper;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(this);
		setTitle("PBambenek 2740 Ex3G Payroll I/O");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 37, 397, 130);
		contentPane.add(scrollPane);
		
		// click event listener for employeeList JList
		//emplyeeList = new Jlist();
		//employeeListModel = new DefaultListModel();
		//employeeListModel.addElement(new Payroll(101, "Pete Bambenek", 10.0));
		//employeeListModel.addElement(new Payroll(102, "Tim Carroll", 20.0));
		//employeeListModel.addElement(new Payroll(103, "Matt Volker", 30.0));
		//employeeListModel.addElement(new Payroll(104, "Kathy Clickner", 40.0));
		//employeeListModel.addElement(new Payroll(105, "Eric Ostrander", 50.0));
		
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		employeeList = new JList(employeeListModel);
		employeeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				do_employeeList_mouseClicked(arg0);
			}
		});
		employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(employeeList);
		
		
		// Start permanent labels- that do not need any reference
		JLabel lblSelectEmployee = new JLabel("Select employee:");
		lblSelectEmployee.setBounds(27, 11, 105, 14);
		contentPane.add(lblSelectEmployee);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (>100):");
		lblEmployeeId.setBounds(27, 187, 180, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee name:");
		lblEmployeeName.setBounds(27, 212, 114, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayrate = new JLabel("Pay rate (7.25 - 100):");
		lblPayrate.setBounds(27, 237, 180, 14);
		contentPane.add(lblPayrate);
		
		JLabel lblEnterHours = new JLabel("Enter hours (0.1 - 20.0):");
		lblEnterHours.setBounds(27, 262, 180, 14);
		contentPane.add(lblEnterHours);
		
		JLabel lblTotalHours = new JLabel("Total hours:");
		lblTotalHours.setBounds(27, 287, 180, 14);
		contentPane.add(lblTotalHours);
		
		JLabel lblGrossPay = new JLabel("Gross pay:");
		lblGrossPay.setBounds(27, 312, 180, 14);
		contentPane.add(lblGrossPay);
		// End permanent labels- that do not need any reference
		
		//Start text field settings
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(this);
		hoursTextField.setText("0.00");
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setBounds(201, 259, 80, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		empIdTextField = new JTextField();
		empIdTextField.addFocusListener(this);
		empIdTextField.setText("000");
		empIdTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empIdTextField.setBounds(201, 184, 80, 20);
		contentPane.add(empIdTextField);
		empIdTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.addFocusListener(this);
		empNameTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		empNameTextField.setBounds(151, 209, 130, 20);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.addFocusListener(this);
		payRateTextField.setText("7.25");
		payRateTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateTextField.setBounds(201, 234, 80, 20);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
		//End text field settings
		
		//Start JLabel settings
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(205, 287, 76, 14);
		contentPane.add(totalHoursLabel);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(205, 312, 76, 14);
		contentPane.add(grossPayLabel);
		//End JLabel settings
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_addHoursButton_actionPerformed(e);
			}
		});
		addHoursButton.setBounds(291, 258, 46, 23);
		contentPane.add(addHoursButton);
				
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(347, 258, 77, 23);
		contentPane.add(clearHoursButton);
				
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClearForm_actionPerformed(e);
			}
		});
		btnClearForm.setBounds(291, 337, 133, 23);
		contentPane.add(btnClearForm);
				
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(this);
		updateButton.setBounds(192, 337, 89, 23);
		contentPane.add(updateButton);
		
	}// End of PayrollForm 
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == updateButton) {
			do_updateButton_actionPerformed(arg0);
		}
	}
	
	protected void do_employeeList_mouseClicked(MouseEvent arg0) {
		Payroll payroll = (Payroll) employeeList.getSelectedValue();            // reference to JList box employees		
		this.empIdTextField.setText(Integer.toString(payroll.getId()));             // set empIdTextField to selected employee ID
		this.empNameTextField.setText(payroll.getName());                           // set empNameLabel to selected employee NAME
		
		DecimalFormat payRatefmt = new DecimalFormat("##0.00");                // set format for payRateLabel
		this.payRateTextField.setText(payRatefmt.format(payroll.getPayRate()));     // set payRateLabel to selected employee PAYRATE
		
		hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		
		DecimalFormat hoursfmt = new DecimalFormat ("##0.00");
        this.totalHoursLabel.setText(hoursfmt.format(payroll.getHours()));
        DecimalFormat grossPayfmt = new DecimalFormat ("$#,##0.00");
		this.grossPayLabel.setText(grossPayfmt.format(payroll.calcGrossPay()));
		this.hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		addHoursButton.setEnabled(true);
		clearHoursButton.setEnabled(true);
		updateButton.setEnabled(true);
		
	}
	
	protected void do_addHoursButton_actionPerformed(ActionEvent e) {
		double checkedHours = Double.parseDouble(hoursTextField.getText());   //Convert hoursTextField to local variable
		//Action for hours validation
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		if (!payroll.addHours(checkedHours)) {
			JOptionPane.showMessageDialog(null,  "Invalid hours. \nMust be >= 0.1 and <= 20.0");
			hoursTextField.setText("0.0");
			hoursTextField.requestFocus();
		}
				
		DecimalFormat hoursfmt = new DecimalFormat ("##0.00");
        this.totalHoursLabel.setText(hoursfmt.format(payroll.getHours()));
        DecimalFormat grossPayfmt = new DecimalFormat ("$#,##0.00");
		this.grossPayLabel.setText(grossPayfmt.format(payroll.calcGrossPay()));
		this.hoursTextField.setText("0.0");
		this.hoursTextField.requestFocus();
		
	}
	
	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		// clear out selected employee 'hours' accumulator
		Payroll payroll = (Payroll) employeeList.getSelectedValue();
		payroll.setHours(0.0);
				
		//Display 0.00 in totalHoursLabel, grossPayLabel, and hoursTextField*/
		this.hoursTextField.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("$0.00");
		this.hoursTextField.requestFocus();
	}
	
	protected void do_btnClearForm_actionPerformed(ActionEvent e) {
		// set labels and text fields back to initial values
		this.empIdTextField.setText("100");
		this.empNameTextField.setText("");
		this.payRateTextField.setText("7.25");
		this.hoursTextField.setText("0.00");
		this.totalHoursLabel.setText("0.00");
		this.grossPayLabel.setText("0.00");
		this.hoursTextField.requestFocus();
		this.employeeList.clearSelection(); // removes any selections by the cursor
		addHoursButton.setEnabled(false);
		clearHoursButton.setEnabled(false);
		updateButton.setEnabled(false);
	}
			
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIdTextField.getText());            //Convert empIdTextField to local variable
		String checkName = empNameTextField.getText();                  //Store name string in local variable
		double rate = Double.parseDouble(payRateTextField.getText());   //Convert payRateTextField to local variable
				
		//Action for employee ID validation
		Payroll payroll = (Payroll) employeeList.getSelectedValue();    //Current selected name from list and original values
			
		if (!payroll.setId(id)) {
			JOptionPane.showMessageDialog(null,  "Invalid employee ID. \nMust be > 100");
			DecimalFormat idFmt = new DecimalFormat("###");
			empIdTextField.setText(idFmt.format(payroll.getId()));
			empIdTextField.requestFocus();
		}
			
		//Action for employee name validation
		else if (!payroll.setName(checkName)) {
			JOptionPane.showMessageDialog(null,  "Invalid employee name. \nMust enter a name. \nAlphabet letters only.");
			empNameTextField.setText(payroll.getName());
			empNameTextField.requestFocus();
		}	
			
		//Action for payRate validation
		else if (!payroll.setPayRate(rate)) {
			JOptionPane.showMessageDialog(null,  "Invalid pay rate. \nMust be >= 7.25 and <= 100");
			DecimalFormat rateFmt = new DecimalFormat("##0.00");
	        payRateTextField.setText(rateFmt.format(payroll.getPayRate()));
			payRateTextField.requestFocus();
		}
		
		employeeList.repaint();
		
	}
		
	public void focusLost(FocusEvent e) {
	}
	
	public void focusGained(FocusEvent e) {
		if (e.getSource() == hoursTextField) {
			do_hoursTextField_focusGained(e);
		}
		if (e.getSource() == payRateTextField) {
			do_payRateTextField_focusGained(e);
		}
		if (e.getSource() == empNameTextField) {
			do_empNameTextField_focusGained(e);
		}
		if (e.getSource() == empIdTextField) {
			do_empIdTextField_focusGained(e);
		}
	}
	
	protected void do_empIdTextField_focusGained(FocusEvent e) {
		this.empIdTextField.selectAll();
	}
	protected void do_empNameTextField_focusGained(FocusEvent e) {
		this.empNameTextField.selectAll();
	}
	protected void do_payRateTextField_focusGained(FocusEvent e) {
		this.payRateTextField.selectAll();
	}
	protected void do_hoursTextField_focusGained(FocusEvent e) {
		this.hoursTextField.selectAll();
	}

	
	////// ex3g window closing //////////////////////////////////////////////////////////
	
	public void windowClosing(WindowEvent e) {
		if (e.getSource() == this) {
			do_this_windowClosing(e);
		}
	}
	protected void do_this_windowClosing(WindowEvent e) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(employeeListModel);
	}
	
	//////////////////////// following added by eclipse ///////////////////////////////////
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
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
}
