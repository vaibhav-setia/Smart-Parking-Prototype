package parking_problem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class map_admin extends JFrame {

	private JPanel contentPane;

	int b,r,c,ro;
	int i,j,k;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					map_admin frame = new map_admin();
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
	public map_admin() {

		
		try {
			allotment.fun();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton done = new JButton("DONE");
		done.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
		done.setBounds(294, 527, 89, 23);
		contentPane.add(done);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resources/parking.jpg"));
		lblNewLabel.setBounds(10, 10, 650, 504);
		contentPane.add(lblNewLabel);
		
		for(i=0;i<array.basement;i++)
		{
			for(j=0;j<array.row;j++)
			{
				for(k=0;k<array.column;k++)
				{
					b=i;
					r=j;
					c=k;
		ro=100*r + 50*(r/2);
		if(r%2 !=0)
		{
			ro=ro+50;
		}
		JLabel indicate = new JLabel("");
		System.out.println(b+" "+r+" "+c);
		if(array.parking[b][r][c]!=0)
		lblNewLabel.add(indicate);
		if(array.parking[b][r][c]==2)
			indicate.setBackground(Color.RED);
		else if(array.parking[b][r][c]==1)
			indicate.setBackground(Color.GREEN);
		indicate.setBounds(79 + ro, 382-(50*c), 89, 41);
		indicate.setOpaque(true);
				}
			}
		}

		array.main(null);
	}
}