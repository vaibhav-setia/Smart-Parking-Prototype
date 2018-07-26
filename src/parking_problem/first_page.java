package parking_problem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.google.zxing.WriterException;


//import Parking_problem.user_pge;
import java.awt.Font;
import java.awt.Color;


public class first_page extends JFrame {

	public static first_page frame ;
    private JPanel contentPane;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new first_page();
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
    public first_page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(Color.PINK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel place = new JLabel("Select the place");
        place.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 16));
        place.setBounds(65, 139, 123, 16);
        contentPane.add(place);

        String Place[]={"Select place","Elante","North country mall","17 sector"};
        JComboBox comboBox = new JComboBox(Place);
        comboBox.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 16));
        comboBox.setBounds(252, 136, 118, 22);
        contentPane.add(comboBox);
        
        JLabel lblChooseAParking = new JLabel("Choose a Parking place : ");
        lblChooseAParking.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 20));
        lblChooseAParking.setBounds(86, 39, 250, 43);
        contentPane.add(lblChooseAParking);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Elante"))
					try {
						user_pge.main(null);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				else if(selected.toString().equals("North country mall"))
                {}
                else if(selected.toString().equals("17 sector"))
                {}

            }
        });
        
        
    }
}