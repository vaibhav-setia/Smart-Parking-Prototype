package parking_problem;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class exitpage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	String qrcode;
    static Connection conn;
    PreparedStatement pst;
    String parkingSlot1;
    ResultSet rst;
    Boolean pcheck1;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exitpage frame = new exitpage();
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
	public exitpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(166, 79, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ENTER THE QRCODE ON ETICKET");
		lblNewLabel.setBounds(56, 35, 218, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				qrcode=textField.getText().toString();
				
				Boolean exists=false;
				try {
					pst=conn.prepareStatement("select * from pptable where qrcode=?");
					pst.setString(1,qrcode);
					System.out.println(pst.toString());
					rst=pst.executeQuery();
					//pcheck1=false;
					if(rst.next()) {
					exists=true;
					System.out.println(exists+"");
					}
					
					//array.parking[(int)parkingSlot1.charAt(0) - 49][(int)parkingSlot1.charAt(2) - 65][(int)parkingSlot1.charAt(3)-49]=0;
					
					
				
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(exists)
				{
					try {
						pst=conn.prepareStatement("select parkingslot from pptable where qrcode=?");
						pst.setString(1,qrcode);
						rst=pst.executeQuery();
						if(rst.next())
						parkingSlot1=rst.getString(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				try {
					pst=conn.prepareStatement("delete from pptable where qrcode =?");
					pst.setString(1,qrcode);
					pst.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnExit.setBounds(166, 158, 89, 23);
		contentPane.add(btnExit);
	}

}
