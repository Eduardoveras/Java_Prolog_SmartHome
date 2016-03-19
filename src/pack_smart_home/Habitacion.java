package pack_smart_home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class Habitacion extends JButton {
	
	private String room_name;
	private String person=null;
	private String room_type;
	private String temperature;
	private boolean lights_on;
	final JPopupMenu menu = new JPopupMenu();
    public JMenuItem item_ON = new JMenuItem("Encender luz");
    public JMenuItem item_OFF = new JMenuItem("Apagar luz");
    public JMenuItem item_ir_hab = new JMenuItem("ir a esta habitacion");
    public JMenuItem item_ver_consumo = new JMenuItem("ver consumo");
    public JMenuItem item_apagar_clima = new JMenuItem("apagar/encender clima");
    
	//public ImageIcon icon_human =  new ImageIcon(getClass().getResource("/resources/user.png"));



	public Habitacion(String name,String tipo,boolean light_stat){
		//setIcon(icon_human);
		item_ON.setFont(new Font("Roboto", Font.PLAIN, 21));
		item_OFF.setFont(new Font("Roboto", Font.PLAIN, 21));
		item_ir_hab.setFont(new Font("Roboto", Font.PLAIN, 21));
		item_apagar_clima.setFont(new Font("Roboto", Font.PLAIN, 21));
		item_ver_consumo.setFont(new Font("Roboto", Font.PLAIN, 21));
		
		
		item_ON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Query q= new Query(
						new Compound("switchL", new Term[]{new Atom(room_name)}));
				q.hasSolution();
				
				if (lights_on) {
					lights_on=false;
				} else {
					lights_on=true;
				}
				set_room_color();
			}
		});
		
		item_OFF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query q= new Query(
						new Compound("switchL", new Term[]{new Atom(room_name)}));
				q.hasSolution();
				
				if (lights_on) {
					lights_on=false;
				} else {
					lights_on=true;
				}
				set_room_color();
			}
		});
		
		item_ir_hab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query kery  = new Query(new Compound("family" ,
						new Term[]{new Atom(person),new Variable("_"),new Variable("Awa")}));
				kery.hasSolution();
				if(((Hashtable[])kery.allSolutions())[0].get("Awa").toString().compareTo("awake")==0)
				{
					Query q = new Query(new Compound("switchFamL" ,
							new Term[]{
										new Atom(person),new Atom(name)
								}));
								q.hasSolution();
				}
						
					
				}
			}
		);
		
		item_ver_consumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomC yee = new RoomC(name,(getFactsRoom(name).get(2)));

			}
		});
		
		item_apagar_clima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		//menu.add(item);
		lights_on= light_stat;
		room_name=name;
		room_type=tipo;
		setText(room_name);
		set_room_color();
		
		setFont(new Font("Roboto Lt", Font.PLAIN, 27));
		
		
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Query q= new Query(
						new Compound("switchL", new Term[]{new Atom(room_name)}));
				q.hasSolution();
				//System.out.println("yee");
				if (lights_on) {
					lights_on=false;
				} else {
					lights_on=true;
				}
				set_room_color();
				
			}
		});
		
		
		addMouseListener(new MouseAdapter() {
		      public void mouseReleased(MouseEvent evt) {
		        boolean encontrado = false;
		    	menu.removeAll();
		    	if (evt.isPopupTrigger()) 
		        {
		        	if (lights_on) {
		        		menu.add(item_OFF);
					}else {
						menu.add(item_ON);
					}
		        	
		        	//menu.add(item_apagar_clima);
		        	menu.add(item_ver_consumo);
					Query location = new Query(new Compound("family", new Term[] {new Variable("Name"),new Variable("Loc"),new Variable("_")}));
					
					Hashtable[] solutions = location.allSolutions();
					
					for(int i = 0; i < solutions.length; i++) 
					{
						//System.out.println(solutions[i].get("Name").toString()+" person "+person+ " Location " +(solutions[i].get("Loc").toString()+ " room name: "+room_name));
						if((solutions[i].get("Name").toString().compareTo(person)==0) && (solutions[i].get("Loc").toString().compareTo(room_name)==0)) 
						{
							encontrado= true;
							break;
						}
					}
					
					if(!encontrado)
					{
						menu.add(item_ir_hab);
					}
					//stem.out.println();
		          menu.show(evt.getComponent(), evt.getX(), evt.getY());
		        
		      }}
		    });
		

		
	}
	
	public void set_room_color() {
		if (lights_on) {
			setBackground(new Color(255, 255, 240));
		}else if (lights_on==false) {
			setBackground(new Color(230, 230, 255));
		}
		
	}
	
	public void setPerson(String p)
	{
		person=p;
	}
	public void updateBtn()
	{
		Query q= new Query(
				new Compound("room", 
						new Term[]{new Atom(room_name),new Variable("Temp"),new Variable("State")}));
		q.hasSolution();
		Hashtable ht= q.allSolutions()[0];
		
		Query alarm = new Query(new Compound("house", new Term[] {new Variable("A")}));
    	alarm.hasSolution();
    	String s = ((Hashtable[])alarm.allSolutions())[0].get("A").toString();
		if(s.compareTo("alarm")==0)
		{
			lights_on= true;
			set_room_color();
			return;
		}
		if(ht.get("State").toString().compareTo("on")==0)
		{
			lights_on= true;
		}
		else
			lights_on= false;
		temperature=ht.get("Temp").toString();
		set_room_color();
	}
	public ArrayList<String> getFactsRoom(String room)
	{
		//Temperatura, luz, consumo total
		ArrayList<String> resp = new ArrayList<String>(); 
		 float sum=0;
		Query q= new Query(new Compound("room",new Term[]{new Atom(room),new Variable("Temp"),new Variable("S")}));
		resp.add(((Hashtable[]) q.allSolutions())[0].get("Temp").toString());
		resp.add(((Hashtable[]) q.allSolutions())[0].get("S").toString());
		
		if(((Hashtable[])q.allSolutions())[0].get("S").toString().compareTo("on")==0)
		{
			sum+=0.1;
		}
		Query k= new Query(new Compound("appliance",new Term[]{new Variable("A"),new Atom(room),new Variable("C")}));
		k.hasSolution();
		for(Hashtable ht: k.allSolutions())
		{
			Query onoff= new Query(new Compound("state",new Term[]{new Atom(ht.get("A").toString()),new Variable("State")}));
			if(onoff.hasSolution())
			{
				String s=((Hashtable[])onoff.allSolutions())[0].get("State").toString();
				if(s.compareTo("on")==0)
				sum+=Integer.valueOf(ht.get("C").toString());
			}
		}
		resp.add(Float.toString(sum));
		//System.out.println(resp);
		return resp;
	}

}
