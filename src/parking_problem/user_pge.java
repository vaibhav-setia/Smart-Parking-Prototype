package parking_problem;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.sql.*;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Connection;

import javax.swing.Timer;

import com.google.zxing.WriterException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
public class user_pge extends JFrame {

	public static user_pge frame;
	public static int basement=2;
	public static int rows=3;

	public static int columns=7;
    private JPanel contentPane;
    public static JTextField textField;
    public static JTextField textField_1;
    private JTextField textField_2;
    String carno,phoneno;
    static final String DB_URL = "jdbc:mysql://localhost:3307/parkingproblem";
    static Connection conn;
    Timer timer;
    PreparedStatement pst=null;
    ResultSet rst;
    long current_time;
	long compare_time;
    static boolean freeslot=true;
    static String parkingslot;
	boolean paymentsuccessful=true,alreadyexists=false;
	int basement_no,row_no,column_no;
	private JButton btnNewButton_1;
	private JTextField textField_3;
	private JLabel lblNewLabel;
	String email;
	Boolean validemail=false,validphone=false,validcar=true;
    /**
     * Launch the application.
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException{

		first_page.frame.setVisible(false);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new user_pge();
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
    public user_pge() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel car_num = new JLabel("Enter car number");
        car_num.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        car_num.setBounds(40, 57, 269, 47);
        contentPane.add(car_num);
        
        textField = new JTextField();
        textField.setBounds(349, 78, 200, 22);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel ph_num = new JLabel("Enter phone number");
        ph_num.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        ph_num.setBounds(35, 117, 303, 47);
        contentPane.add(ph_num);
        
        textField_1 = new JTextField();
        textField_1.setBounds(349, 138, 200, 22);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton = new JButton("SUBMIT");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		
        		 
        		
        	
        		//parkingslot=allotment.ticket;

        		carno=textField.getText().toString();
        		phoneno=textField_1.getText().toString();
        		email=textField_3.getText().toString();
        		
        		if(phoneno.length()==10)
        		{
        			int p=0;
        			for(int i=0;i<10;i++)
        				if(phoneno.charAt(i)>='0'&&phoneno.charAt(i)<='9')
        					p++;
        			
        			if(p==10)
        				validphone=true;
        			else 
        				validphone=false;		
        		}
        		else
        		{
        			validphone=false;
        		}
        		
        		int p=0;
        		for(int i=0;i<email.length();i++)
        			if(email.charAt(i)=='@')
        				p++;
        		
        		if(p>0)
        			validemail=true;
        		else
        			validemail=false;
        		
        		if(validemail&&validphone&&validcar)
        		{
        			try {
                        GenerateQRCode.qrCodeText=textField.getText()+"/"+textField_1.getText();
                        GenerateQRCode.main(null);
                        new allotment();
                        ticket.main(null);
                    } catch (WriterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
        			parkingslot=allotment.ticket;
        		try {
					pst=conn.prepareStatement("select * from pptable where carno=? and phoneno=? and email=?");
					pst.setString(1,carno);
					pst.setString(2,phoneno);
					pst.setString(3,email);
					rst=pst.executeQuery();
					if(rst.next())
						alreadyexists=true;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        			
        		
        		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        		current_time=  (timestamp.getTime()/1000);
        		
                //System.out.println(current_time);
               if(freeslot==false)
               {
            	   JOptionPane.showMessageDialog(null,"PARKING FULL","ERROR",JOptionPane.ERROR_MESSAGE);
               }
               else 
               {
        		try {
        			
					pst=conn.prepareStatement("insert into pptable(carno,phoneno,qrcode,timeslot,parkingslot,pcheck,email) values(?,?,?,?,?,?,?)");
					pst.setString(1,carno);
					pst.setString(2,phoneno);
					pst.setString(3,carno+"/"+phoneno);
					pst.setLong(4,current_time);
					pst.setString(5,parkingslot);
					pst.setBoolean(6,false);
					pst.setString(7,email);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		try {
        			pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
               }
               mail.main(null);
        		}
        	
        	else
        		JOptionPane.showMessageDialog(null,"EITHER OF THE ENTRIES IS INCORRECT","ERROR",JOptionPane.ERROR_MESSAGE);
        	}
        });
        btnNewButton.setBounds(157, 252, 89, 23);
        contentPane.add(btnNewButton);
        
        btnNewButton_1 = new JButton("ADMIN LOGIN");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					adminLOGIN.main(null);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        btnNewButton_1.setBounds(315, 252, 123, 23);
        contentPane.add(btnNewButton_1);
        
        textField_3 = new JTextField();
        textField_3.setBounds(349, 185, 200, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);
        
        lblNewLabel = new JLabel(" Email Id");
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        lblNewLabel.setBounds(23, 188, 253, 34);
        contentPane.add(lblNewLabel);
        
        timer=new Timer(6000,new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{

        		Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());

                //return number of milliseconds since January 1, 1970, 00:00:00 GMT
        		compare_time=  (timestamp2.getTime()/1000);

        		System.out.println(compare_time);
        		
        		try {
					pst=conn.prepareStatement("select timeslot,qrcode,notimeslotincrease,carno,phoneno,pcheck,parkingslot from pptable");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		try {
					rst=pst.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		String carnot,phonenot,parkingslott,qrcodet,notimeslott;
        		boolean pcheckt;
        		long timeslott;
        		try {
					while(rst.next())
					{
						timeslott=rst.getLong(1);
						qrcodet=rst.getString(2);
						notimeslott=rst.getString(3);
							carnot=rst.getString(4);
							phonenot=rst.getString(5);
								pcheckt=rst.getBoolean(6);
						    parkingslott=rst.getString(7);
						    
						   if(compare_time-timeslott>=60 &&pcheckt==false)
						    {
						    		/*pst=conn.prepareStatement("update pptable set timeslot=?");
						    		pst.setInt(1,timeslott+10);
					    		pst.executeQuery();*/
							   
						    	pst=conn.prepareStatement("delete from pptable where timeslot=?");
						    	pst.setLong(1,timeslott);
					    		pst.executeUpdate();
					    		basement_no=(int)parkingslott.charAt(0) - 49;
					    		row_no=(int)parkingslott.charAt(2) - 65;
					    		column_no=(int)parkingslott.charAt(3) - 49;
					    		array.parking[basement_no][row_no][column_no]=0;
					    		System.out.println(basement_no+" "+row_no+" "+ column_no);
						    }
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
        	}
        });
        timer.start();
    }
}