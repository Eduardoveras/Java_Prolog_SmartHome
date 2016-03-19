package pack_smart_home;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;


public class MainWindow extends JFrame {
	/**
	 * 
	 */
	boolean alarma=false;
	boolean subibaja=false;
	Color c= new Color(255,0,0); 
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JPanel panel_izq = new JPanel();
	JPanel casa = new JPanel();
	boolean selectPer=false;
	JLabel lblCurrentMode, lblPeoplePresent;
	JComboBox<String> comboBox_fam = new JComboBox<String>();
	JLabel lblAllDoorsAre ;
	JLabel lblAllLightsAre;
	JButton btnDormir= new JButton();
	int doorsOpen=0;
	Query INI;
	JLabel lblCurrentLocation ;
	Habitacion btnSala,btnCocina,
	btnComedor,btnHabP,btnHab1,btnHab2,btnPatio,btnMarquesina;
	JButton btnSalirllegarDeLa= new JButton();
	Alarm btnAlarm ;
	public ImageIcon icon_human =  new ImageIcon(getClass().getResource("/resources/user.png"));
	JButton btnHacerFuego ;

	
	
	public MainWindow() {
		
		                       
		INI = new Query(new Compound("consult", new Term[] {new Atom("final.pl")}));
		INI.hasSolution();
		
			
		cargar();
		fillCombo();
		
		ActionListener updateToh = new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
			    //myComponent.repaint();
				  UpdateAll();
			  }
			};
			
			ActionListener colore= new ActionListener() {
				  public void actionPerformed(ActionEvent evt) {
					color();
				  }
				};	
			
		new Timer(2000, updateToh).start();
		new Timer(10, colore).start();
		
		
		 btnAlarm = new Alarm("ALARM");
		 btnAlarm.setFont(new Font("Roboto Lt", Font.PLAIN, 21));
		 btnAlarm.setPreferredSize(new Dimension(0,25));
		 if (new Motion_menu().get_motion_count()>0) {
			btnAlarm.set_mode("fired");
		}
		 
		btnSala = new Habitacion("living_room","normal",getFactsRoom("living_room").get(1).equals(new String("on"))?true:false);
		btnComedor = new Habitacion("dining_room","normal",getFactsRoom("dining_room").get(1).equals(new String("on"))?true:false);
		btnCocina = new Habitacion("kitchen","normal",getFactsRoom("kitchen").get(1).equals(new String("on"))?true:false);
		btnHab1 = new Habitacion("room_1","normal",getFactsRoom("room_1").get(1).equals(new String("on"))?true:false);
		btnHab2 = new Habitacion("room_2","normal",getFactsRoom("room_2").get(1).equals(new String("on"))?true:false);
		btnPatio = new Habitacion("backyard","normal",getFactsRoom("backyard").get(1).equals(new String("on"))?true:false);
		btnHabP = new Habitacion("principal_room","normal",getFactsRoom("principal_room").get(1).equals(new String("on"))?true:false);
		btnMarquesina = new Habitacion("marquesina","normal",getFactsRoom("marquesina").get(1).equals(new String("on"))?true:false);	
			
		getContentPane().setLayout(new BorderLayout(0, 0));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		setSize(1600, 1100);
		GridBagConstraints gbc_btnSala = new GridBagConstraints();
		gbc_btnSala.fill = GridBagConstraints.BOTH;
		gbc_btnSala.insets = new Insets(0, 0, 5, 0);
		gbc_btnSala.gridx = 0;
		gbc_btnSala.gridy = 0;
		GridBagConstraints gbc_btnComedor = new GridBagConstraints();
		gbc_btnComedor.fill = GridBagConstraints.BOTH;
		gbc_btnComedor.insets = new Insets(0, 0, 5, 0);
		gbc_btnComedor.gridx = 0;
		gbc_btnComedor.gridy = 8;
		GridBagConstraints gbc_btnCocina = new GridBagConstraints();
		gbc_btnCocina.fill = GridBagConstraints.BOTH;
		gbc_btnCocina.insets = new Insets(0, 0, 5, 0);
		gbc_btnCocina.gridx = 0;
		gbc_btnCocina.gridy = 9;
		GridBagConstraints gbc_btnHab1 = new GridBagConstraints();
		gbc_btnHab1.fill = GridBagConstraints.BOTH;
		gbc_btnHab1.insets = new Insets(0, 0, 5, 0);
		gbc_btnHab1.gridx = 0;
		gbc_btnHab1.gridy = 10;
		GridBagConstraints gbc_btnHab2 = new GridBagConstraints();
		gbc_btnHab2.fill = GridBagConstraints.BOTH;
		gbc_btnHab2.insets = new Insets(0, 0, 5, 0);
		gbc_btnHab2.gridx = 0;
		gbc_btnHab2.gridy = 11;
		GridBagConstraints gbc_btnPatio = new GridBagConstraints();
		gbc_btnPatio.fill = GridBagConstraints.BOTH;
		gbc_btnPatio.insets = new Insets(0, 0, 5, 0);
		gbc_btnPatio.gridx = 0;
		gbc_btnPatio.gridy = 12;
		GridBagConstraints gbc_btnHabP = new GridBagConstraints();
		gbc_btnHabP.fill = GridBagConstraints.BOTH;
		gbc_btnHabP.insets = new Insets(0, 0, 5, 0);
		gbc_btnHabP.gridx = 0;
		gbc_btnHabP.gridy = 13;
		GridBagConstraints gbc_btnMarquesina = new GridBagConstraints();
		gbc_btnMarquesina.fill = GridBagConstraints.BOTH;
		gbc_btnMarquesina.gridx = 0;
		gbc_btnMarquesina.gridy = 14;
		
				//setResizable(false);
		
				panel.setBackground(new Color(40, 40, 40));
				getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				casa.setBorder(new LineBorder(new Color(0, 0, 0), 2));
				casa.setBounds(58, 11, 733, 472);
				
				
				casa.setBackground(new Color(153, 153, 153));
				panel.add(casa);
				casa.setLayout(null);
				
				btnSala.setBounds(215, 175, 276, 142);
				casa.add(btnSala);
				
				btnComedor.setBounds(29, 328, 151, 120);
				casa.add(btnComedor);
				
				btnCocina.setBounds(29, 175, 176, 142);
				casa.add(btnCocina);
				
				btnHab1.setBounds(190, 328, 127, 120);
				casa.add(btnHab1);


				btnHab2.setBounds(279, 23, 212, 141);
				casa.add(btnHab2);
	
				btnPatio.setBounds(510, 23, 201, 425);
				casa.add(btnPatio);
				
				
				btnHabP.setBounds(327, 328, 164, 120);
				casa.add(btnHabP);
				
				
				btnMarquesina.setBounds(29, 23, 240, 141);
				casa.add(btnMarquesina);
				
				lblCurrentMode = new JLabel(house_state());
				lblCurrentMode.setFont(new Font("Roboto", Font.PLAIN, 29));
				lblCurrentMode.setForeground(new Color(204, 204, 204));
				lblCurrentMode.setBounds(58, 494, 481, 28);
				panel.add(lblCurrentMode);
				
				lblPeoplePresent = new JLabel(family_status());
				lblPeoplePresent.setFont(new Font("Roboto", Font.PLAIN, 29));
				lblPeoplePresent.setForeground(new Color(204, 204, 204));
				lblPeoplePresent.setBounds(58, 565, 753, 28);
				panel.add(lblPeoplePresent);
				
				lblAllLightsAre = new JLabel(Integer.toString(new Apliances_menu().getON_apliances())+" Apliances ON");
				lblAllLightsAre.setFont(new Font("Roboto", Font.PLAIN, 29));
				lblAllLightsAre.setForeground(new Color(204, 204, 204));
				lblAllLightsAre.setBounds(58, 604, 310, 28);
				panel.add(lblAllLightsAre);
				
				
				lblAllDoorsAre = new JLabel(Integer.toString(new Doors_Window().getOPEN_doors())+" doors OPEN");
				lblAllDoorsAre.setFont(new Font("Roboto", Font.PLAIN, 29));
				lblAllDoorsAre.setForeground(new Color(204, 204, 204));
				lblAllDoorsAre.setBounds(58, 657, 310, 28);
				panel.add(lblAllDoorsAre);
				
				
				
		panel_izq.setForeground(new Color(255, 255, 255));
		
		
		panel_izq.setBackground(new Color(51, 51, 51));
		panel_izq.setPreferredSize(new Dimension(240,0));
		getContentPane().add(panel_izq, BorderLayout.WEST);
		panel_izq.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnSetHouseMode = new JButton("Set House Mode");
		btnSetHouseMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(new Keypad().is_this_correct())
				{
					new ModeSelect();
					//UpdateAll();
					 //the_mode_you_got= new String(new ModeSelect().currentMode) ;
					 //lblCurrentMode.setText(the_mode_you_got);
				}
				UpdateAll();

			}
		});
		btnSetHouseMode.setForeground(new Color(255, 255, 255));
		btnSetHouseMode.setBackground(new Color(51, 51, 51));
		btnSetHouseMode.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnSetHouseMode.setBorder(null);
		btnSetHouseMode.setFocusPainted(false);
		panel_izq.add(btnSetHouseMode);
		
		JButton btnHomeFamily = new JButton("Home & Family");
		btnHomeFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Family fam_window = new Family();
			}
		});
		btnHomeFamily.setForeground(new Color(255, 255, 255));
		btnHomeFamily.setBackground(new Color(51, 51, 51));
		btnHomeFamily.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnHomeFamily.setBorder(null);
		panel_izq.add(btnHomeFamily);
		
		JButton btnLightsApliances = new JButton("Lights & Apliances");
		btnLightsApliances.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Apliances_menu the_stuff = new Apliances_menu();
				the_stuff.setVisible(true);
			}
		});
		btnLightsApliances.setForeground(new Color(255, 255, 255));
		btnLightsApliances.setBackground(new Color(51, 51, 51));
		btnLightsApliances.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnLightsApliances.setBorder(null);
		panel_izq.add(btnLightsApliances);
		
		JButton btnDoorsLocks = new JButton("Doors & Locks");
		btnDoorsLocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Doors_Window window = new Doors_Window();
				window.setVisible(true);
			}
		});
		btnDoorsLocks.setForeground(new Color(255, 255, 255));
		btnDoorsLocks.setBackground(new Color(51, 51, 51));
		btnDoorsLocks.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnDoorsLocks.setBorder(null);
		panel_izq.add(btnDoorsLocks);
		
		JButton btnMotion = new JButton("Motion");
		btnMotion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Motion_menu menu = new Motion_menu();
				menu.setVisible(true);
			}
		});
		btnMotion.setForeground(new Color(255, 255, 255));
		btnMotion.setBackground(new Color(51, 51, 51));
		btnMotion.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnMotion.setBorder(null);
		panel_izq.add(btnMotion);
		
		JButton btnSolarEnergy = new JButton("Solar Energy");
		btnSolarEnergy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_window windo = new panel_window();
				windo.setVisible(true);
			}
		});
		btnSolarEnergy.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnSolarEnergy.setBackground(new Color(51, 51, 51));
		btnSolarEnergy.setForeground(new Color(255, 255, 255));
		btnSolarEnergy.setBorder(null);
		panel_izq.add(btnSolarEnergy);
		
		JButton btnexit = new JButton("Exit System");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
				
				dispose();
				System.exit(0);
			}
		});
		btnexit.setForeground(new Color(255, 255, 255));
		btnexit.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnexit.setBorder(null);
		btnexit.setBackground(new Color(51, 51, 51));
		panel_izq.add(btnexit);
		
		JPanel panel_der = new JPanel();
		panel_der.setForeground(new Color(255, 255, 255));
		panel_der.setBackground(new Color(51, 51, 51));
		panel_der.setPreferredSize(new Dimension(240,0));
		getContentPane().add(panel_der, BorderLayout.EAST);
		getContentPane().add(btnAlarm, BorderLayout.SOUTH);
		panel_der.setLayout(new GridLayout(0, 1, 0, 0));
		comboBox_fam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateAll();
			}
		});
		
		
		comboBox_fam.setForeground(new Color(255, 255, 255));
		comboBox_fam.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		comboBox_fam.setBackground(new Color(51, 51, 51));
		comboBox_fam.setBorder(null);
		
		panel_der.add(comboBox_fam);
		comboBox_fam.setPreferredSize(new Dimension(241,10));

		
		
		btnDormir = new JButton();
		btnDormir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(getStatus().compareTo("awake")==0)
				{
					Query kuery = new Query(new Compound("gotobed", 
							new Term[]{new Atom(comboBox_fam.getSelectedItem().toString())}));
					kuery.hasSolution();
					//Query prueba = new Query(new Compound("room", new Term[]{new Atom("room_1"),new Variable("_"),new Variable("X")}));prueba.hasSolution();System.out.println(((Hashtable)prueba.allSolutions()[0]).get("X").toString());
				}
				else {
						Query q = new Query(new Compound("switchAS", 
								new Term[]{new Atom(comboBox_fam.getSelectedItem().toString())}));
						q.hasSolution();
					
				}
				
				
				
				
				UpdateAll();

			}
		});
		
		lblCurrentLocation = new JLabel("Location: "+getStatus());
		lblCurrentLocation.setForeground(Color.WHITE);
		panel_der.add(lblCurrentLocation);

		btnDormir.setForeground(new Color(255, 255, 255));
		btnDormir.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnDormir.setBackground(new Color(51, 51, 51));
		btnDormir.setBorder(null);
		panel_der.add(btnDormir);
		
		btnSalirllegarDeLa = new JButton("Salir/Llegar de la casa");
		btnSalirllegarDeLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SalirLlegar();
				UpdateAll();
			}
		});
		btnSalirllegarDeLa.setForeground(new Color(255, 255, 255));
		btnSalirllegarDeLa.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnSalirllegarDeLa.setBackground(new Color(51, 51, 51));
		btnSalirllegarDeLa.setBorder(null);
		panel_der.add(btnSalirllegarDeLa);
		
		btnHacerFuego = new JButton("Disparar alarma");
		btnHacerFuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Query q= new Query(new Compound("warning", new Term[] {new Variable("_")}));
				q.hasSolution();
				
				if(alarma)
				alarma=false;
				else alarma= true;
				if(alarma)
					{
						btnHacerFuego.setText("Desactivar Alarma");
					}
				else 
					{
					Query ala= new Query(new Compound("shutAlarmD", new Term[] {new Variable("_")}));
					ala.hasSolution();
					
						btnHacerFuego.setText("Disparar Alarma");
						casa.setBackground(new Color(153, 153, 153));
					}
				//Query m= new Query(new Compound("room", new Term[] {new Atom("backyard"),new Variable("_"),new Variable("X")}));
				//m.hasSolution();String s= ((Hashtable[])m.allSolutions())[0].get("X").toString();System.out.println(s);
			}
		});
		btnHacerFuego.setForeground(new Color(255, 255, 255));
		btnHacerFuego.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		btnHacerFuego.setBackground(new Color(51, 51, 51));
		btnHacerFuego.setBorder(null);
		panel_der.add(btnHacerFuego);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//pack();
		UpdateAll();
		setVisible(true);
		
		
		
		
	}
	
	void color()
	{
		if(alarma)
		casa.setBackground(c);
		if(c.getRed()<=4)
		{
			subibaja=false;
		}
		if(c.getRed()>=250)
		{
			subibaja=true;
		}
		if(subibaja)
			c=new Color(c.getRed()-3,0,0);
		else 
			c=new Color(c.getRed()+3,0,0);
	}
	
	void cargar() 
	{	
	INI = new Query(new Compound("consult", new Term[] {new Atom("final.pl")}));
	INI.hasSolution();	
		FileReader	 in = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader("db.pl"))) 
		{
			String line;
			//Query lector= new Query(new Compound("consult", new Term[] {new Atom("db.pl")}));lector.hasSolution();
			while ((line = br.readLine()) != null) 
			{
				if(line.compareTo("")==0)continue;
				if(line.toCharArray()[0]=='%')continue;
				//System.out.println(line);
				
				line= line.substring(0, line.length()-1);
				
				line = line.replace('(', ',').replace(')', ',').trim();
				String[] arr= line.split(",");
				//Compound comp = new Compound("knows", new Term[] {Agent, not});
				Term[] terminos= new Term[arr.length-1];
				for(int i=0; i< arr.length-1;i++)
				{
					if(arr[i+1].toCharArray()[0]<='9')
						terminos[i]=new jpl.Float(
										Double.valueOf((arr[i+1].toString()))
									);
					else
					terminos[i]=new Atom(arr[i+1]);
				}
				
				Compound comp= new Compound(arr[0],terminos);

				Query q=new Query(new Compound("assertz", new Term[]{comp}));
				q.hasSolution();
				

			}

			
		}
		catch (IOException e1)
		{
			//System.out.println("
			
				try {
					PrintWriter writer = new PrintWriter("db.pl", "UTF-8");
					writer.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}finally {
		
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
	}
	
	void salvar()
	{
		//BufferedWriter in= new BufferedWriter(); 
		PrintWriter writer = null;
		try {
			Variable X= new Variable("X");
			Variable Y= new Variable("Y");
			Variable Z= new Variable("Z");
			Hashtable[] ht;
			Query q;
			writer = new PrintWriter("db.pl");
			writer.print("");
			
			
			// :- dynamic state/2.      %para house, appliance,  door, window, electronic, lights, sensor%
			q = new Query(new Compound("state",new Term[]{X,Y}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="state("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString() +").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
			writer.append("\n");
		//:- dynamic room/3.       %room, temperature,lights on off stat%
		
			q = new Query(new Compound("room",new Term[]{X,Y,Z}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="room("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString()+"," +ht[i].get("Z").toString() +").";
			
				writer.append(hecho+"\n");
			}
			writer.append("\n");
		
		//:- dynamic currenttime/3. %time,day,month----------------solar panels%
			q = new Query(new Compound("currenttime",new Term[]{X,Y,Z,new Variable("A")}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho=
						"currenttime("+ht[i].get("X").toString()+ "," 
				+ht[i].get("Y").toString()+","
								+ht[i].get("Z").toString()+","
				+ht[i].get("A").toString() +").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
		
			//:- dynamic currentseason/2. %season, temperature%
			q = new Query(new Compound("currentseason",new Term[]{X,Y}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="currentseason("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString()+").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
			writer.append("\n");
		//:- dynamic family/3.     %member, location, state%
			q = new Query(new Compound("family",new Term[]{X,Y,Z}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="family("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString()+"," +ht[i].get("Z").toString() +").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
			writer.append("\n");
		//:- dynamic guest/2.	 %guest, location%
			q = new Query(new Compound("guest",new Term[]{X,Y}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="guest("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString()+").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
			writer.append("\n");
		//:- dynamic solar/4.      %panel, angle, output%
			q = new Query(new Compound("solar",new Term[]{X,Y,Z}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="solar("+ht[i].get("X").toString()+ "," +ht[i].get("Y").toString()+"," +ht[i].get("Z").toString() +").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
			writer.append("\n");
			
		// HOUSE
			q = new Query(new Compound("house",new Term[]{X}));
			
			ht= q.allSolutions();
			
			for(int i=0;i< ht.length;i++)
			{
				String hecho="house("+ht[i].get("X").toString()+").";
				//stem.out.println(hecho);
				writer.append(hecho+"\n");
			}
		//:- dynamic special/2.	 %device, location%		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			

			writer.close();
		}
		

		
	}
	
	void fillCombo()
	{
		//Query INI = new Query(new Compound("consult", new Term[] {new Atom("final.pl")}));		INI.hasSolution();
		
		
		Query family = new Query(new Compound("family", new Term[] {new Variable("X"),new Variable("_"),new Variable("_")}));
		
		Hashtable[] solutions = family.allSolutions();
		
		for(int i = 0; i < solutions.length; i++) {
			comboBox_fam.addItem(  solutions[i].get("X").toString());
		}
		
		
	}
	
	public String family_status()
	{
		Query family = new Query(new Compound("family", new Term[] {new Variable("_"),new Variable("_"),new Variable("X")}));
		
		Hashtable[] solutions = family.allSolutions();
		int sleep=0;
		int awake=0;
		int away=0;
		String sleep_str = "error";
		String awake_str = "error";
		String away_str = "error";
		
		
		for(int i = 0; i < solutions.length; i++) {
			String sol = solutions[i].get("X").toString();
			//System.out.println("sol es " +sol);
			if(sol.compareTo("sleep")==0)
			{
			sleep++;
			}else if (sol.compareTo("awake")==0) {
				awake++;
			}else {
				away++;
			}
		}
		
		if (sleep==0&&awake==0) {
			return "Everyone is aways";
		} else if(awake==0&&away==0){
			return "Everyone is sleeping";
		}else if (sleep==0&&away==0) {
			return "Everyone is awake";
		}
		sleep_str= sleep+" are sleeping,";
		awake_str = awake + " are awake,";
		away_str = away +" are away.";
		
		
		return sleep_str+awake_str+away_str;

		
	}
	
	String house_state()
	{
		Query mode = new Query(new Compound("house", new Term[] {new Variable("X")}));
		//mode.open();
		if(mode.hasSolution())
		{
			Hashtable[] solutions = mode.allSolutions();
			String current_mode = solutions[0].get("X").toString();
			return "CURRENT MODE: "+current_mode;

		}
		else return "NO" ;

		
	}
	public String getStatus()
	{
		String persona=comboBox_fam.getSelectedItem().toString(); 
		Query q= new Query(new Compound("family",new Term[]{new Atom(persona),new Variable("_"),new Variable("Stat")}));
		q.hasSolution();
		String stat= ((Hashtable[]) q.allSolutions())[0].get("Stat").toString();
		//System.out.println(stat);
		return stat;
	}
	public String getLoc(String persona)
	{
		Query q= new Query(new Compound("family",new Term[]{new Atom(persona),new Variable("Stat"),new Variable("_")}));
		q.hasSolution();
		String stat= ((Hashtable[]) q.allSolutions())[0].get("Stat").toString();
		//System.out.println(stat);
		return stat;
	}
	public ArrayList<String> getFactsRoom(String room)
	{
		//Temperatura, luz, consumo total
		ArrayList<String> resp = new ArrayList<String>(); 
		 int sum=0;
		Query q= new Query(new Compound("room",new Term[]{new Atom(room),new Variable("Temp"),new Variable("S")}));
		resp.add(((Hashtable[]) q.allSolutions())[0].get("Temp").toString());
		resp.add(((Hashtable[]) q.allSolutions())[0].get("S").toString());
		q.close();
		
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
		resp.add(Integer.toString(sum));
		//System.out.println(resp);
		return resp;
	}
	
	public void UpdateAll()
	{
		
		Query q = new Query(new Compound("house", new Term[]{new Variable("X")}));
		if(q.hasSolution())
		lblCurrentMode.setText("CURRENT MODE: "+((Hashtable[]) q.allSolutions())[0].get("X").toString());
		//System.out.println("--------------------------------");
		//getFactsRoom("kitchen");
		lblAllLightsAre.setText(Integer.toString(new Apliances_menu().getON_apliances())+" Apliances ON");
		lblAllDoorsAre.setText(Integer.toString(new Doors_Window().getOPEN_doors())+" doors OPEN");
		if (getStatus().equals(new String("awake"))) {
			btnDormir.setText("Dormir");
		} else {
			btnDormir.setText("Despertar");
		}
		lblCurrentLocation.setText("Location: "+getLoc(comboBox_fam.getSelectedItem().toString()));
		btnSala.setPerson(comboBox_fam.getSelectedItem().toString());
		btnCocina.setPerson(comboBox_fam.getSelectedItem().toString());
		btnComedor.setPerson(comboBox_fam.getSelectedItem().toString());
		btnHabP.setPerson(comboBox_fam.getSelectedItem().toString());
		btnHab1.setPerson(comboBox_fam.getSelectedItem().toString());
		btnHab2.setPerson(comboBox_fam.getSelectedItem().toString());
		btnPatio.setPerson(comboBox_fam.getSelectedItem().toString());
		btnMarquesina.setPerson(comboBox_fam.getSelectedItem().toString());
		btnAlarm.setColor();
		
		
		btnSala.setIcon(null);
		btnCocina.setIcon(null);
		btnComedor.setIcon(null);
		btnHabP.setIcon(null);
		btnHab1.setIcon(null);
		btnHab2.setIcon(null);
		btnPatio.setIcon(null);
		btnMarquesina.setIcon(null);
		
		setTimeSeason();
		
		for (int i = 0; i<comboBox_fam.getItemCount(); i++) {
			if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("living_room"))){
				btnSala.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("backyard"))) {
				btnPatio.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("room_2"))) {
				btnHab2.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("principal_room"))) {
				btnHabP.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("dining_room"))) {
				btnComedor.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("room_1"))) {
				btnHab1.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("kitchen"))) {
				btnCocina.setIcon(icon_human);
			}else if (getLoc(comboBox_fam.getItemAt(i)).equals(new String("marquesina"))) {
				btnMarquesina.setIcon(icon_human);
			}					
		}
		Query solar = new Query(new Compound("setOutput",new Term[]{new Variable("_")}));
		solar.hasSolution();
		//solar.close();
		UpdateSensors();
		UpdateLights();
	}
	public void UpdateSensors()
	{
		Query q1= new Query(new Compound("state",
				new Term[]{new Variable("Place"),new Variable("State")}));
		q1.hasSolution();
		Hashtable[] ht1= q1.allSolutions();
		for(int i =0; i<ht1.length;i++)
		{
			//System.out.println(" yee "+ ht1[i].get("Place").toString());
			String motion="no_motion";
			Query q2=new Query(new Compound("family",
					new Term[]{new Variable("_"),new Atom(ht1[i].get("Place").toString()),new Variable("_")}));
			q2.hasSolution();
			if(q2.allSolutions().length>0)
			{
				//System.out.println(" yaaaaaaaaaaaaa");
				motion="motion";
			}
			Query q3=new Query(new Compound("switchSensor",
					new Term[]{new Atom(ht1[i].get("Place").toString()),new Atom(motion)}));
			q3.hasSolution();
		}
	}
	
	public void SalirLlegar()
	{
		Query q1= new Query(new Compound("salirLlegar",
				new Term[]{new Atom(comboBox_fam.getSelectedItem().toString())}));
		q1.hasSolution();
		//Hashtable[] ht1= q1.allSolutions();
		UpdateAll();
	}
	
	public void UpdateLights()
	{
		Query q = new Query(new Compound("open", new Term[]{new Variable("_")}));
		q.hasSolution();
		//System.out.println(" yeeaahh");System.out.println(" yessp");
		Query q2 = new Query(new Compound("closex", new Term[]{new Variable("_")}));q2.hasSolution();
		//System.out.println(b);
		btnSala.updateBtn();
		btnCocina.updateBtn();
		btnComedor.updateBtn();
		btnHabP.updateBtn();
		btnHab1.updateBtn();
		btnHab2.updateBtn();
		btnPatio.updateBtn();
		btnMarquesina.updateBtn();
	}
	
	
	public void setTimeSeason()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		int Dia=cal.get(Calendar.DAY_OF_MONTH),
		Mes =cal.get(Calendar.MONTH),
		hora=cal.get(Calendar.HOUR),
		Mins=cal.get(Calendar.MINUTE);
		if(!(cal.get(Calendar.AM_PM) == 0))
			hora+=12;

		Query q1= new Query(new Compound("settime",
				new Term[]{new jpl.Integer(hora),new jpl.Integer(Mins),new jpl.Integer(Dia),new jpl.Integer(Mes)}));
		q1.hasSolution();
		Query q2= new Query(new Compound("setseason",
				new Term[]{new jpl.Integer(29)}));
		q2.hasSolution();
		
	}
	
}
