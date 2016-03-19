package pack_smart_home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Panel_Solar extends JPanel{
	float angulo;
	float current_energy;
	String rate;
	/*final public ImageIcon closed_image =  new ImageIcon(getClass().getResource("/resources/panel.png"));
	Image image = closed_image.getImage();
	BufferedImage bi = (BufferedImage) image;*/
	
	public JLabel lblPanel=new JLabel();
	
	public Panel_Solar(float Angulo,Float CE,String Rate) {
		setBorder(new LineBorder(new Color(204, 204, 204)));
		angulo=Angulo;
		current_energy=CE;
		rate=Rate;
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblRate = new JLabel("Condition: "+rate);
		lblRate.setFont(new Font("Roboto Lt", Font.PLAIN, 25));
		lblRate.setBounds(10, 45, 295, 31);
		add(lblRate);
		
		JLabel lblProduction = new JLabel("Current Production: "+Float.toString(current_energy));
		lblProduction.setFont(new Font("Roboto Lt", Font.PLAIN, 25));
		lblProduction.setBounds(10, 75, 394, 36);
		add(lblProduction);
		
		lblPanel = new JLabel("PANEL");
		lblPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanel.setFont(new Font("Roboto Lt", Font.PLAIN, 24));
		lblPanel.setBounds(267, 11, 146, 31);
		add(lblPanel);
		//add(new MyComponent());
		
		JLabel lblRot = new JLabel("Angle: "+Float.toString(Angulo));
		lblRot.setFont(new Font("Roboto Lt", Font.PLAIN, 22));
		lblRot.setBounds(10, 105, 249, 30);
		add(lblRot);
		setVisible(true);
	}
	


}
