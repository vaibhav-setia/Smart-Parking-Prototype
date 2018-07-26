package parking_problem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class adminLOGIN extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtPassword;
	boolean status;
	static Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    String username,adminuser,password,passuser;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminLOGIN frame = new adminLOGIN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/parkingproblem?","root","");
	}

	/**
	 * Create the frame.
	 */
	public adminLOGIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(217, 65, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(217, 127, 111, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username=textField.getText().toString();
				password=textField_1.getText().toString();
				
				try {
					pst=conn.prepareStatement("select * from admindetails where username=? and password=?");
					pst.setString(1,username);
					pst.setString(2,password);
					rst=pst.executeQuery();
					if(rst.next())
						status=true;
					else
						status=false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(status==true)
					adminpage.main(null);
				else
        			JOptionPane.showMessageDialog(null,username+password+"  "+adminuser+passuser,"ERROR",JOptionPane.ERROR_MESSAGE);

			
			}
		});
		btnNewButton.setBounds(157, 196, 89, 23);
		contentPane.add(btnNewButton);
		
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setText("USERNAME:");
		txtrUsername.setBounds(73, 63, 94, 20);
		contentPane.add(txtrUsername);
		
		txtPassword = new JTextField();
		txtPassword.setText("PASSWORD:");
		txtPassword.setBounds(73, 127, 94, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
	}
}
