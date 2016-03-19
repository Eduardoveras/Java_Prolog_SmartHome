package pack_smart_home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;

public class Door extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private boolean is_open;

	final public ImageIcon open_image =  new ImageIcon(getClass().getResource("/resources/open.png"));
	final public ImageIcon closed_image =  new ImageIcon(getClass().getResource("/resources/closed.png"));
	

	public Door(String the_name, Boolean is_OPEN) {
		
		
		setBorder(null);
		setFont(new Font("Roboto Lt", Font.PLAIN, 26));
		name=the_name;
		is_open=is_OPEN;
		
		setText(name);
		change_color(is_open);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Query q= new Query(new Compound("applianceSwitch",
						new Term[]{new Atom(name)}));
				//System.out.println("yeee: "+ name+ " "+ q.hasSolution());
				q.hasSolution();
				
				if (is_open) {
					is_open=false;
				}else {
					is_open=true;
				}
				change_color(is_open);
			}
		});
		setVisible(true);
	}
	
	public void change_color(boolean val)
	{
		if (val) {
			setBackground(new Color(180,255,180));
			setIcon(open_image);
		}
		else {
			setBackground(new Color(255,180,180));
			setIcon(closed_image);
		}
	}
	
	
	

}
