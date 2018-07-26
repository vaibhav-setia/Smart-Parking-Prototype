package parking_problem;
//import Parking_problem.user_pge;
//import Parking_problem.allotment;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class ticket extends JFrame {

    public static JPanel contentPane;

    /**
     * Launch the application.
     */
    

    /**
     * Create the frame.
     */
    public ticket() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 500);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Car number: "+user_pge.textField.getText());
        lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 18));
        lblNewLabel.setBounds(12, 35, 408, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Phone number: "+user_pge.textField_1.getText());
        lblNewLabel_1.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_1.setBounds(12, 75, 408, 20);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Slot number :"+allotment.ticket);
        lblNewLabel_2.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_2.setBounds(12, 115, 408, 20);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Booking time: "+allotment.current_time);
        lblNewLabel_3.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_3.setBounds(12, 155, 408, 20);
        contentPane.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Arrive by : "+ allotment.current_time1);
        lblNewLabel_4.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_4.setBounds(12, 195, 408, 20);
        contentPane.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("New label");
        lblNewLabel_5.setBounds(116, 236, 125, 125);
        contentPane.add(lblNewLabel_5);

        ImageIcon iconLogo = new ImageIcon("resources/QR.png");
        lblNewLabel_5.setIcon(iconLogo);
        
        JLabel lblNewLabel_6 = new JLabel("Note : Please take screenshot of QR code.");
        lblNewLabel_6.setBounds(12, 426, 347, 27);
        contentPane.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("Elante");
        lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_7.setBounds(144, 10, 125, 20);
        contentPane.add(lblNewLabel_7);
        
        JButton btnNewButton = new JButton("Proceed to map");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
				try {
					map frame = new map();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        });
        btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 19));
        btnNewButton.setBounds(71, 392, 237, 25);
        contentPane.add(btnNewButton);
    }
    public static void main(String[] args) {
    	user_pge.frame.setVisible(false);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	user_pge.frame.dispose();
                    ticket frame = new ticket();
                    frame.setVisible(true);
                    BufferedImage image = new BufferedImage(contentPane.getWidth(), contentPane.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics2D = image.createGraphics();
                    frame.paint(graphics2D);
                    ImageIO.write(image,"jpeg", new File("resources/ticket.jpeg"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}