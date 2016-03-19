package pack_smart_home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;

public class Motion_Sensor extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String room_name;
	private boolean is_motion;
	final public ImageIcon open_image =  new ImageIcon(getClass().getResource("/resources/open.png"));
	final public ImageIcon closed_image =  new ImageIcon(getClass().getResource("/resources/closed.png"));
	

	public Motion_Sensor(String the_name, Boolean is_MOTION) {
		setBorder(null);
		//setIcon(open_image);
		//setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		setFont(new Font("Roboto Lt", Font.PLAIN, 26));
		room_name=the_name;
		is_motion=is_MOTION;
		setText(room_name);
		change_color(is_motion);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Query q= new Query(new Compound("applianceSwitch",
						new Term[]{new Atom(room_name)}));
				//System.out.println("yeee: "+ name+ " "+ q.hasSolution());
				q.hasSolution();
				
				if (is_motion) {
					is_motion=false;
				}else {
					is_motion=true;
				}
				change_color(is_motion);
			}
		});
		setEnabled(false);
		setVisible(true);
	}
	
	public void change_color(boolean val)
	{
		if (val) {
			setBackground(new Color(180,255,180));
			//setIcon(open_image);
		}
		else {
			setBackground(new Color(255,180,180));
			//setIcon(closed_image);
		}
	}

	public boolean isIs_motion() {
		return is_motion;
	}

	public void setIs_motion(boolean is_motion) {
		this.is_motion = is_motion;
	}
	
	
	

}
