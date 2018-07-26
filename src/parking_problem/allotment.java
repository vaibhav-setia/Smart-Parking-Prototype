package parking_problem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class allotment {

    public static String ticket,parkingslotnew;
    public static String current_time,current_time1;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");

    public static final SimpleDateFormat ft = new SimpleDateFormat ("HH:mm:ss a");
     static final String DB_URL = "jdbc:mysql://localhost:3307/parkingproblem";
     static Connection conn;
     static ResultSet rst;
     static PreparedStatement pst;
 	static int basement_no;
	static int row_no;
	static int column_no;
	static Boolean pcheckrr;

 static void fun() throws ClassNotFoundException, SQLException
 {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/parkingproblem?","root","");
		pst=conn.prepareStatement("select timeslot,qrcode,notimeslotincrease,carno,phoneno,pcheck,parkingslot from pptable");
        try {
			pst=conn.prepareStatement("select parkingslot from pptable");
			rst=pst.executeQuery();
			while(rst.next())
			{
				    parkingslotnew=rst.getString(1);
				    //pcheckrr=rst.getBoolean(6);
		    		basement_no=(int)parkingslotnew.charAt(0) - 49;
		    		row_no=(int)parkingslotnew.charAt(2) - 65;
		    		column_no=(int)parkingslotnew.charAt(3) - 49;
		    		array.parking[basement_no][row_no][column_no]=1;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 }
    allotment()  {
    	try {
			fun();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Date date = new Date();
        current_time=sdf.format(new Timestamp(date.getTime()));
        Date dNow = new Date(System.currentTimeMillis()+30*60*1000 ); // Instantiate a Date object
        current_time1=ft.format(new Timestamp(dNow.getTime()));
        
			
        int i=0,j=0,k=0;
        boolean alotment = false;
        for(i=0;i<2;i++)
        {
            for(j=0;j<3;j++)
            {
                for(k=0;k<7;k++)
                {
                    if(array.parking[i][j][k]==0)
                        {
                    	char pillar = (char) (65+j);
                        ticket=(i+1)+" "+pillar+(k+1);
                        array.parking[i][j][k]=1;
                        alotment=true;
                        break;
                        }
                }
                if(alotment==true)
                    break;
            }
            if(alotment==true)
                break;
        }
        if(alotment== false)
            ticket="waiting ";

        
        System.out.println(ticket);
    }
}
