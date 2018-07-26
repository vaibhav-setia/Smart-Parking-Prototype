package parking_problem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class map extends JFrame {

	public static JPanel contentPane;

	int b,r,c,ro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					map frame = new map();
					frame.setVisible(true);
					BufferedImage image = new BufferedImage(contentPane.getWidth(), contentPane.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = image.createGraphics();
                    frame.paint(graphics2D);
                    ImageIO.write(image,"jpeg", new File("resources/map.jpeg"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public map() {

		b=(int)allotment.ticket.charAt(0) - 49;
		r=(int)allotment.ticket.charAt(2) - 65;
		c=(int)allotment.ticket.charAt(3) - 49;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 690, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton done = new JButton("DONE");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user_pge.main(null);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		done.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
		done.setBounds(294, 527, 89, 23);
		contentPane.add(done);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("resources/parking.jpg"));
		lblNewLabel.setBounds(10, 10, 650, 504);
		contentPane.add(lblNewLabel);

		ro=100*r + 50*(r/2);
		if(r%2 !=0)
		{
			ro=ro+50;
		}
		JLabel indicate = new JLabel("");
		lblNewLabel.add(indicate);
		indicate.setBackground(Color.RED);
		
		indicate.setBounds(79 + ro, 382-(50*c), 89, 41);
		indicate.setOpaque(true);
	}
}