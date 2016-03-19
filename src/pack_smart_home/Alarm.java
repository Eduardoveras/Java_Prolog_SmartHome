package pack_smart_home;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class Alarm extends JButton{
	
	private boolean is_on=false;
	private String mode=null;
	private String current_mode=null;

	public Alarm(String text) {

		mode=getmode();
				
		setColor();
		setText(text);
	
	}
	
	
	public void setColor()
	{
		getmode();
		if (current_mode.compareTo(("armed"))==0) {
			setBackground(new Color(0,255,0));
		}else if (current_mode.compareTo("disarmed")==0) {
			setBackground(new Color(150,150,150));
		}else
		{
			setBackground(new Color(255,0,0));
		}

	}
	
	public String getmode()
	{
		
		Query query = new Query(new Compound("house", new Term[]{new Variable ("C")}));
		query.hasSolution();
		mode=((Hashtable[])query.allSolutions())[0].get("C").toString();
		
		switch (mode) {
		case "away":
			current_mode="armed";
			break;
		case "normal":
			current_mode="disarmed";
			break;

		case "asleep":
			current_mode="armed";
			break;
		case "fired":
			current_mode="fired";
			break;

		default:
			current_mode="armed";
			break;
		}
		return current_mode;
		
	}
	
	public void set_mode(String the_mode) {
		mode=the_mode;
		setColor();
	}
	
	


}
