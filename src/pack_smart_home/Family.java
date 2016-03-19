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

public class Family extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Family() {
		getContentPane().setBackground(new Color(105, 105, 105));
		getContentPane().setLayout(null);
		
		setUndecorated(true);
		setSize(450, 560);
		JLabel lblRightNow = new JLabel("Right now:");
		lblRightNow.setForeground(new Color(255, 255, 255));
		lblRightNow.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 24));
		lblRightNow.setBounds(80, 22, 207, 31);
		getContentPane().add(lblRightNow);
		family_status();

		
		JButton btnBack = new JButton("back");
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
		Query family = new Query(new Compound("family", new Term[] {new Variable("Nam"),new Variable("Pos"),new Variable("State")}));
		
		Hashtable[] solutions = family.allSolutions();
		
		
		int posY=40;
		for(int i = 0; i < solutions.length; i++) {
			
			getContentPane().add(new JSeparator(JSeparator.VERTICAL), BorderLayout.CENTER);
			posY+=50;
			String Nombre = solutions[i].get("Nam").toString();
			String Posicion = solutions[i].get("Pos").toString();
			String Estado = solutions[i].get("State").toString();
			
			JLabel lblRightNow = new JLabel(Nombre + " is " + Estado + " in the " +Posicion);
			//lblRightNow.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
			
			lblRightNow.setForeground(new Color(255, 255, 255));
			lblRightNow.setFont(new Font("Roboto Lt", Font.PLAIN, 20));
			lblRightNow.setBounds(50, posY, 400, 40);
			getContentPane().add(lblRightNow);		
			


		
	}
}
}
