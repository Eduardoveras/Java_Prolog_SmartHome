package pack_smart_home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class Motion_menu extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel(new GridLayout(0, 4,7,5));
	JPanel panel_east = new JPanel();
	JPanel panel_west = new JPanel();
	private int motion_count=0;



	public Motion_menu() {
		getContentPane().setForeground(new Color(255, 255, 255));

				getContentPane().setBackground(new Color(102, 102, 102));
				getContentPane().setLayout(new BorderLayout());
				
				setUndecorated(true);
				setSize(1000, 620);
				add_sensor();

				
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
				
				
				JLabel label_titulo = new JLabel("DOORS & LOCKS");
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
			
			



			public void add_sensor()
			{
				Query state = new Query(new Compound("state", new Term[] {new Variable("Nam"),new Variable("Stat")}));
				
				Hashtable[] solutions = state.allSolutions();
				
				

				for(int i = 0; i < solutions.length; i++) {
					
					String name = solutions[i].get("Nam").toString();
					String tru = solutions[i].get("Stat").toString();

					if (tru.equals(new String("motion")) || tru.equals(new String("no_motion"))) {	
					Motion_Sensor apl = new Motion_Sensor(name, tru.equals(new String("motion"))?true:false);

					apl.setBorder(null);
					apl.setFocusPainted(false);
					
					panel.add(apl);

					}

				}
				
			}
			
			public int get_motion_count()
			{

				Query state = new Query(new Compound("state", new Term[] {new Variable("Nam"),new Variable("Stat")}));
				Hashtable[] solutions = state.allSolutions();
				for(int i = 0; i < solutions.length; i++) {
					
					String name = solutions[i].get("Nam").toString();
					String tru = solutions[i].get("Stat").toString();

					if (tru.equals(new String("motion"))) {	
						motion_count++;
						}
					}
				return motion_count;
			}
			
			



	
			
			
	}


