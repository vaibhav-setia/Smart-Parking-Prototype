package parking_problem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class entrypage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    String qrcode;
    static Connection conn;
    PreparedStatement pst;
    ResultSet rst;
    String parkingSlot1;
    private JButton mapb;
	/**
	 * Launch the application.
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					entrypage frame = new entrypage();
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
	public entrypage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ENTER THE QR CODE OF THE ETICKET");
		lblNewLabel.setBounds(106, 43, 216, 20);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(151, 96, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				qrcode=textField.getText().toString();
				Boolean exists=false;
				try {
					pst=conn.prepareStatement("select * from pptable where qrcode=?");
					pst.setString(1,qrcode);
					rst=pst.executeQuery();
					if(rst.next())
						exists=true;
				
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				if(exists==true)
				{
				try {
					pst=conn.prepareStatement("update pptable set pcheck=? where qrcode=?");
					pst.setBoolean(1,true);
					pst.setString(2,qrcode);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
								
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
					System.out.println(parkingSlot1+"<<<<<<<<<");
					array.parking[(int)parkingSlot1.charAt(0) - 49][(int)parkingSlot1.charAt(2) - 65][(int)parkingSlot1.charAt(3)-49]=2;
					array.main(null);
					System.out.println(((int)parkingSlot1.charAt(0) - 49)+"  "+((int)parkingSlot1.charAt(2) - 65)+"  "+((int)parkingSlot1.charAt(3)-49));
					//System.out.println(((parkingSlot1.charAt(0)) - 49) + "   " + ((int)parkingSlot1.charAt(2)-65) + "  "+parkingSlot1.charAt(3)-49);
				
				
			}
			}
		});
		btnEnter.setBounds(151, 173, 89, 23);
		contentPane.add(btnEnter);
		
		mapb = new JButton("map");
		mapb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				map_admin.main(null);
			}
		});
		mapb.setBounds(148, 207, 89, 23);
		contentPane.add(mapb);
		
		
	}

}
