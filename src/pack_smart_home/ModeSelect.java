package pack_smart_home;

import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;
import jpl.fli.qid_t;

public class ModeSelect extends JDialog{
	String currentMode= null;
	
	public String getCurrentMode() {
		return currentMode;
	}
	
	Query INI;
	public ModeSelect() {
		INI = new Query(new Compound("consult", new Term[] {new Atom("final.pl")}));
		INI.hasSolution();
		setModal(true);
		setUndecorated(true);
        // Determine if the GraphicsDevice supports translucency.
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //If translucent windows aren't supported, exit.
        if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
            System.err.println(
                "Translucency is not supported");
                System.exit(0);
        }
        else {
            setOpacity(0.95f);
		}
                
		setSize(500, 600);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		
		

		
		JButton btnAway = new JButton("AWAY");
		btnAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query aux= new Query(new Compound("house", new Term[]{new Variable("X")}));
				if(aux.hasSolution())
					currentMode=((Hashtable[])aux.allSolutions())[0].get("X").toString();
				else return;
				
				Query q= new Query(new Compound("manualModeChange",
						new Term[]{new Atom(currentMode), new Atom("away")}));
				q.hasSolution();
				aux.close();
				q.close();
				dispose();
			}
		});
		btnAway.setForeground(Color.WHITE);
		btnAway.setBackground(Color.DARK_GRAY);
		btnAway.setFont(new Font("HP Simplified Light", Font.PLAIN, 34));
		btnAway.setBorder(null);
		getContentPane().add(btnAway);
		
		JButton btnStay = new JButton("NORMAL");
		btnStay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Query aux= new Query(new Compound("house", new Term[]{new Variable("X")}));
				if(aux.hasSolution())
					currentMode=((Hashtable[])aux.allSolutions())[0].get("X").toString();
				else return;
				//System.out.println(currentMode);
				Query q= new Query(new Compound("manualModeChange",
						new Term[]{new Atom(currentMode), new Atom("normal")}));
				q.hasSolution();
				aux.close();
				q.close();
			
				dispose();
				
			}
		});
		btnStay.setForeground(Color.WHITE);
		btnStay.setBackground(Color.DARK_GRAY);
		btnStay.setFont(new Font("HP Simplified Light", Font.PLAIN, 34));
		btnStay.setBorder(null);
		getContentPane().add(btnStay);
		
		JButton btnNight = new JButton("NIGHT");
		btnNight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query aux= new Query(new Compound("house", new Term[]{new Variable("X")}));
				if(aux.hasSolution())
					currentMode=((Hashtable[])aux.allSolutions())[0].get("X").toString();
				else return;
				
				Query q= new Query(new Compound("manualModeChange",
						new Term[]{new Atom(currentMode), new Atom("asleep")}));
				q.hasSolution();
				aux.close();
				q.close();
				dispose();
			}
		});
		btnNight.setForeground(Color.WHITE);
		btnNight.setBackground(Color.DARK_GRAY);
		btnNight.setFont(new Font("HP Simplified Light", Font.PLAIN, 34));
		btnNight.setBorder(null);
		getContentPane().add(btnNight);
		
		//pack();
		setVisible(true);
	}
	

}
