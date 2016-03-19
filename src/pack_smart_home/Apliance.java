package pack_smart_home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;

public class Apliance extends JButton{
	private String name;
	private boolean is_on;
	

	public Apliance(String the_name, Boolean is_ON) {
		setBorder(null);
		//setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		setFont(new Font("Roboto Lt", Font.PLAIN, 26));
		name=the_name;
		is_on=is_ON;
		setText(the_name);
		change_color(is_on);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Query q= new Query(new Compound("applianceSwitch",
						new Term[]{new Atom(name)}));
				//System.out.println("yeee: "+ name+ " "+ q.hasSolution());
				q.hasSolution();
				
				if (is_on) {
					is_on=false;
				}else {
					is_on=true;
				}
				change_color(is_on);
			}
		});
		setVisible(true);
	}
	
	public void change_color(boolean val)
	{
		if (val) {
			setBackground(new Color(200,255,200));
		}
		else {
			setBackground(new Color(255,200,200));
		}
	}
	
	
	

}
