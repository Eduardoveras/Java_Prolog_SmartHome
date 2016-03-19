package pack_smart_home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.Hashtable;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

import javax.swing.SwingConstants;

public class Apliances_menu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel(new GridLayout(0, 4,7,5));
	JPanel panel_east = new JPanel();
	JPanel panel_west = new JPanel();
	private int ON_apliances;
	private int OFF_apliances;
	JLabel label_titulo= new JLabel();


	public Apliances_menu() {
		getContentPane().setForeground(new Color(255, 255, 255));

				getContentPane().setBackground(new Color(102, 102, 102));
				getContentPane().setLayout(new BorderLayout());
				
				setUndecorated(true);
				setSize(1000, 620);
				add_apliances();

				
				JButton btnBack = new JButton("back");
				btnBack.setForeground(new Color(255, 255, 255));
				btnBack.setFont(new Font("Roboto Lt", Font.PLAIN, 34));
				btnBack.setBackground(new Color(102, 102, 102));
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//-----AQUI SE DEBE GUARDAR EN LA BASE DE DATOS---------//
						dispose();
					}
				});
				panel_east.setBackground(new Color(102, 102, 102));
				panel_east.setPreferredSize(new Dimension(100,0));
				panel_west.setBackground(new Color(102, 102, 102));
				panel_west.setPreferredSize(new Dimension(100,0));

				btnBack.setBounds(10, 11, 60, 31);
				btnBack.setPreferredSize(new Dimension(0,80));
				btnBack.setBorder(null);
				getContentPane().add(btnBack,BorderLayout.SOUTH);
				getContentPane().add(panel_east,BorderLayout.EAST);
				getContentPane().add(panel_west,BorderLayout.WEST);
				
				
				get_house_consumption();
				label_titulo.setForeground(new Color(255, 255, 255));
				label_titulo.setBackground(new Color(102, 102, 102));
				label_titulo.setFont(new Font("Roboto Lt", Font.PLAIN, 31));
				label_titulo.setPreferredSize(new Dimension(0,90));
				label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
				getContentPane().add(label_titulo,BorderLayout.NORTH);
				panel.setBackground(new Color(102, 102, 102));
				panel.setForeground(new Color(255, 255, 255));

				
				
				panel.setAlignmentY(CENTER_ALIGNMENT);
				
			    
			    getContentPane().add(panel, BorderLayout.CENTER);
				panel.setBounds(35, 85, 379, 449);
				getContentPane().add(panel);

				setLocationRelativeTo(null);
				//setVisible(true);
			}
			
			



			void add_apliances()
			{
				Query family = new Query(new Compound("state", new Term[] {new Variable("Nam"),new Variable("Stat")}));
				
				Hashtable[] solutions = family.allSolutions();

				for(int i = 0; i < solutions.length; i++) {
					
					String name = solutions[i].get("Nam").toString();
					String tru = solutions[i].get("Stat").toString();
					if (tru.equals(new String("on"))) {
						ON_apliances++;
					}else if (tru.equals(new String("off"))) {
						OFF_apliances++;
					}
					
					//System.out.println(name+"+"+tru);
					if (tru.equals(new String("on")) || tru.equals(new String("off"))) {	
					Apliance apl = new Apliance(name, tru.equals(new String("on"))?true:false);
					apl.setBorder(null);
					apl.setFocusPainted(false);
					
					panel.add(apl);

					}

				}
					
			}
			public int getON_apliances() {
				return ON_apliances;
			}




	public int getOFF_apliances() {
		return OFF_apliances;
		
		
	}

			public void get_house_consumption() {
				String val="0";
				Query kery = new Query(new Compound("energyConsumption", new Term[] {new Variable("Nam")}));

				if (kery.hasSolution()) {
				
					Hashtable[] solutions = kery.allSolutions();

			
						val = solutions[0].get("Nam").toString();
						//System.out.println(val);
						label_titulo.setText("LIGHTS & APLIANCES    Total consumption: "+  (val==null?"none":val)+" Kw");
					}
				else label_titulo.setText("LIGHTS & APLIANCES    Total consumption: none");

					
				
			}}



