package pack_smart_home;

import java.awt.Color;

import javax.swing.JButton;

public class Ventanas extends JButton{
	private boolean is_open;
	private boolean is_door;
	private String room_name;
	private Color closed_color = new Color(255,0,0);
	private Color open_color = new Color(0,255,0);

	public Ventanas(String name, Boolean is_open_2) {
		is_open= is_open_2;
		room_name= name;
		setText(room_name);
		setVisible(true);
		setBackground(open_color);
	}

}
