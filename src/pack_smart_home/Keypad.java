package pack_smart_home;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Keypad extends JDialog {

public boolean is_correct=false;
String numberString = "";

JTextField jtf = new JTextField();
JButton b1 = new JButton("1");
JButton b2 = new JButton("2");
JButton b3 = new JButton("3");
JButton b4 = new JButton("4");
JButton b5 = new JButton("5");
JButton b6 = new JButton("6");
JButton b7 = new JButton("7");
JButton b8 = new JButton("8");
JButton b9 = new JButton("9");
JButton b0 = new JButton("0");
JButton bclear = new JButton("Clear");
JButton benter = new JButton("Enter");



public boolean is_valid(String pass_code)
{
	if (pass_code.equals("1234".toString())) {
		return true;
	}
	else
		return false;
}

public Keypad() {
	
	setModal(true);
	setSize(885, 750);
	
	setUndecorated(true);
	setLocationRelativeTo(null);
    JPanel panel1 = new JPanel(new GridLayout(4, 3));
    panel1.setAlignmentY(CENTER_ALIGNMENT);
    b1.setForeground(new Color(51, 51, 51));
    b1.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b1.setBackground(new Color(204, 204, 204));

    panel1.add(b1);
    b2.setForeground(new Color(51, 51, 51));
    b2.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b2.setBackground(new Color(204, 204, 204));
    panel1.add(b2);
    b3.setForeground(new Color(51, 51, 51));
    b3.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b3.setBackground(new Color(204, 204, 204));
    panel1.add(b3);
    b4.setForeground(new Color(51, 51, 51));
    b4.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b4.setBackground(new Color(204, 204, 204));
    panel1.add(b4);
    b5.setForeground(new Color(51, 51, 51));
    b5.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b5.setBackground(new Color(204, 204, 204));
    panel1.add(b5);
    b6.setForeground(new Color(51, 51, 51));
    b6.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b6.setBackground(new Color(204, 204, 204));
    panel1.add(b6);
    b7.setForeground(new Color(51, 51, 51));
    b7.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b7.setBackground(new Color(204, 204, 204));
    panel1.add(b7);
    b8.setForeground(new Color(51, 51, 51));
    b8.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b8.setBackground(new Color(204, 204, 204));
    panel1.add(b8);
    b9.setForeground(new Color(51, 51, 51));
    b9.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b9.setBackground(new Color(204, 204, 204));
    panel1.add(b9);
    bclear.setForeground(new Color(51, 51, 51));
    bclear.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    bclear.setBackground(new Color(204, 204, 204));
    panel1.add(bclear);
    b0.setForeground(new Color(51, 51, 51));
    b0.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    b0.setBackground(new Color(204, 204, 204));
    panel1.add(b0);
    benter.setForeground(new Color(51, 51, 51));
    benter.setFont(new Font("Roboto Bk", Font.PLAIN, 55));
    benter.setBackground(new Color(204, 204, 204));
    panel1.add(benter);


    ButtonListener listener = new ButtonListener();
    
    b1.addActionListener(listener);
    b2.addActionListener(listener);
    b3.addActionListener(listener);
    b4.addActionListener(listener);
    b5.addActionListener(listener);
    b6.addActionListener(listener);
    b7.addActionListener(listener);
    b8.addActionListener(listener);
    b9.addActionListener(listener);
    b0.addActionListener(listener);
    bclear.addActionListener(listener);
    benter.addActionListener(listener);

    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panel1, BorderLayout.CENTER);
    jtf.setForeground(new Color(51, 51, 51));
    jtf.setEditable(false);
    jtf.setFont(new Font("Roboto Bk", Font.PLAIN, 55));


    getContentPane().add(jtf, BorderLayout.NORTH);
    
    bclear.setPreferredSize(new Dimension(300,80));
    jtf.setHorizontalAlignment(SwingConstants.CENTER);
    jtf.setPreferredSize(new Dimension(300, 80));
    setVisible(true);

}


class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == b1&&jtf.getDocument().getLength()<4) {
            numberString += "1";
            jtf.setText(numberString);
        } else if (e.getSource() == b2&&jtf.getDocument().getLength()<4) {
            numberString += "2";
            jtf.setText(numberString);
        } else if (e.getSource() == b3&&jtf.getDocument().getLength()<4) {
            numberString += "3";
            jtf.setText(numberString);
        } else if (e.getSource() == b4&&jtf.getDocument().getLength()<4) {
            numberString += "4";
            jtf.setText(numberString);
        } else if (e.getSource() == b5&&jtf.getDocument().getLength()<4) {
            numberString += "5";
            jtf.setText(numberString);
        } else if (e.getSource() == b6&&jtf.getDocument().getLength()<4) {
            numberString += "6";
            jtf.setText(numberString);
        } else if (e.getSource() == b7&&jtf.getDocument().getLength()<4) {
            numberString += "7";
            jtf.setText(numberString);
        } else if (e.getSource() == b8&&jtf.getDocument().getLength()<4) {
            numberString += "8";
            jtf.setText(numberString);
        } else if (e.getSource() == b9&&jtf.getDocument().getLength()<4) {
            numberString += "9";
            jtf.setText(numberString);
        } else if (e.getSource() == b0&&jtf.getDocument().getLength()<4) {
            numberString += "0";
            jtf.setText(numberString);
        } else if (e.getSource() == bclear) {
        	numberString="";
            jtf.setText(numberString);
        } else if (e.getSource() == benter) {
        	if (is_valid(numberString)) {
               is_correct=true;
        		dispose();   
			}
        	else {
        		is_correct=false;
				jtf.setText("ERROR");
				numberString="";
			}
        	
        }
        

        
    }

}


public boolean is_this_correct()
{
	return is_correct;
}





}