package pack_smart_home;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

public class MarqDialog extends JFrame {
	public MarqDialog() {

		setUndecorated(true);
		
		setSize(250, 250);
		setLocationRelativeTo(null);
		JButton btnBombillo = new JButton("Encender Luces");
		btnBombillo.setBorder(null);
		btnBombillo.setBackground(Color.DARK_GRAY);
		btnBombillo.setForeground(Color.WHITE);
		btnBombillo.setFont(new Font("HP Simplified Light", Font.PLAIN, 19));
		btnBombillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		
		JButton btnCarro = new JButton("Traer Vehiculo");
		btnCarro.setBorder(null);
		btnCarro.setBackground(Color.DARK_GRAY);
		btnCarro.setForeground(Color.WHITE);
		btnCarro.setFont(new Font("HP Simplified Light", Font.PLAIN, 19));
		btnCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		getContentPane().add(btnCarro);
		getContentPane().add(btnBombillo);
		//pack();
		setVisible(true);
	}

}
