package pack_smart_home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import jpl.Atom;
import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

public class panel_window extends JFrame implements ActionListener{
	
	Query INI;
	JPanel central_panel= new JPanel();
	JLabel label;
    ImageIcon icon;
    int angle = 90;
    

	public panel_window() {
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		setSize(650, 630);
		getContentPane().setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("SOLAR PANELS");
		lblNewLabel.setFont(new Font("Roboto Lt", Font.PLAIN, 28));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setPreferredSize(new Dimension(0,70));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		central_panel.setBackground(new Color(255, 255, 255));
		central_panel.setPreferredSize(new Dimension(40, 70));
		getContentPane().add(central_panel,BorderLayout.CENTER);
		central_panel.setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(new TestPane() ,BorderLayout.EAST);
		INI = new Query(new Compound("consult", new Term[] {new Atom("final.pl")}));
		INI.hasSolution();
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(255, 255, 255));
		//getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Roboto Lt", Font.PLAIN, 28));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setPreferredSize(new Dimension(40,70));
		//btnNewButton.setBounds(10, 11, 60, 31);
		getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		Query q= new Query(new Compound("solar",
				new Term[]{new Variable("Name"),new Variable("_"),new Variable("_")}));
		q.hasSolution();
		int i=0;
		
		for(Hashtable ht: q.allSolutions())
		{
			
			//System.out.println("jojojoo");
			ht.get("Name").toString();
			String angle = getAngle();
			//System.out.println("Sun ioutput = "+outp());
			Panel_Solar panel = new Panel_Solar(Float.parseFloat((angle.compareTo("night")==0?"-180":angle)),Float.valueOf(getEnergy()), getConsumptionRate());
			if (i%2==0) {
				panel.setBackground(new Color(240,240,240));
			}
			//panel.setPreferredSize(new Dimension(600,200));
			panel.lblPanel.setText("PANEL #"+ i );
			central_panel.add(panel);
			i++;
		}
		

		
	}

	public String getAngle()
	{
		Query q = new Query(new Compound("calculateAngle",new Term[]{new Variable("X")}));
		if(q.hasSolution())
		{
			Hashtable ht = q.allSolutions()[0];
			return ht.get("X").toString();
		}
		return "0";
		
	}
	public String getEnergy()
	{
		Query q = new Query(new Compound("output",new Term[]{new Variable("X")}));
		if(q.hasSolution())
		{
			Hashtable ht = q.allSolutions()[0];
			return ht.get("X").toString();
		}
		return "-1";
	}

	public String getConsumption()
	{
		Query q = new Query(new Compound("energyConsumption",new Term[]{new Variable("X")}));
		if(q.hasSolution())
		{
			Hashtable ht = q.allSolutions()[0];
			return ht.get("X").toString();
		}
		return "0";
	}
	
	public String getConsumptionRate()
	{
		Query q = new Query(new Compound("consumptionRate",new Term[]{new Variable("X")}));
		q.hasSolution();
		Hashtable ht = q.allSolutions()[0];
		//System.out.println(ht.get("X").toString());
		return ht.get("X").toString();
	}

	private void rotateIcon() {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        int type = BufferedImage.TYPE_INT_RGB;  // other options, see api
        BufferedImage image = new BufferedImage(h, w, type);
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(icon.getImage(), at, label);
        g2.dispose();
        icon = new ImageIcon(image);
    }
 
    private JLabel getCenter() {
        URL url = getClass().getResource("images/cougar.jpg");
        icon = new ImageIcon(url);
        label = new JLabel(icon, JLabel.CENTER);
        return label;
    }
 
    private JPanel getUIPanel() {
        String[] ids = { "ccw", "cw" };
        JPanel panel = new JPanel();
        for(int j = 0; j < ids.length; j++) {
            JButton button = new JButton(ids[j]);
            button.setActionCommand(ids[j].toUpperCase());
            button.addActionListener(this);
            panel.add(button);
        }
        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

class TestPane extends JPanel {

    private CrossShape prop= new CrossShape(50, 50);

    private double angle;

    public TestPane() {
        prop = new CrossShape(50, 50);
        Timer timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 5;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        AffineTransform current = g2d.getTransform();

        int x = 50;
        int y = (getHeight() - prop.getBounds().height) / 2;

        AffineTransform at = new AffineTransform();
        at.translate(x, y);
        at.rotate(Math.toRadians(angle), prop.getBounds().width / 2, prop.getBounds().height / 2);
        g2d.setTransform(at);
        g2d.setColor(Color.BLACK);
        g2d.draw(prop);

        // Reset...
        // Equally, you could dispose of the g2d and create a new copy
        g2d.setTransform(current);

        x = getWidth() - 50 - prop.getBounds().width;
        y = (getHeight() - prop.getBounds().height) / 2;

        at = new AffineTransform();
        at.translate(x, y);
        at.rotate(Math.toRadians(-angle), prop.getBounds().width / 2, prop.getBounds().height / 2);
        g2d.setTransform(at);
        g2d.setColor(Color.BLACK);
        g2d.draw(prop);
  
        g2d.dispose();
    }

}

class CrossShape extends Path2D.Double {

    public CrossShape(int width, int height) {

        moveTo(width/2, 0);
        lineTo(width/2, height);
        //moveTo(width, 0);
        //lineTo(0, height);

    }

}