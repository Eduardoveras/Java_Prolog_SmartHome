package pack_smart_home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class RoomC extends JFrame{
	/**
	 * 
	 */
	String room=null,consumo=null;
	private static final long serialVersionUID = 1L;


	public RoomC(String nam,String C) {
		room=nam;
		consumo= C;
		getContentPane().setBackground(new Color(105, 105, 105));
		getContentPane().setLayout(null);
		
		setUndecorated(true);
		setSize(450, 360);
		JLabel lblRightNow = new JLabel("Consumption:");
		lblRightNow.setForeground(new Color(255, 255, 255));
		lblRightNow.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 24));
		lblRightNow.setBounds(80, 22, 207, 31);
		getContentPane().add(lblRightNow);
		family_status();

		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBack.setBounds(10, 11, 46, 31);
		getContentPane().add(btnBack);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	void family_status()
	{		
		
		int posY=40;
			
			getContentPane().add(new JSeparator(JSeparator.VERTICAL), BorderLayout.CENTER);
			posY+=50;
			JLabel lblRightNow = new JLabel("Consumo en " + room+" es "+consumo+ " kw");
			//lblRightNow.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
			
			lblRightNow.setForeground(new Color(255, 255, 255));
			lblRightNow.setFont(new Font("Roboto Lt", Font.PLAIN, 20));
			lblRightNow.setBounds(50, posY, 400, 40);
			getContentPane().add(lblRightNow);		
			
	}
}
