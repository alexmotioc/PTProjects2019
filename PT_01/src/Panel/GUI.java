package Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import polynomial.*;

public class GUI {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel p1 = new JLabel("P1:");
	JLabel p2 = new JLabel("P2:");
	JLabel p3 = new JLabel("P3:");
	JTextField poly1 = new JTextField();
	JTextField poly2 = new JTextField();
	JTextField poly3 = new JTextField();
	JButton add = new JButton("+");
	JButton sub = new JButton("-");
	JButton mul = new JButton("*");
	JButton div = new JButton("/");
	JButton integ = new JButton("integrate");
	JButton deriv = new JButton("derivate");
	JButton clear = new JButton("clear");
	
	public GUI() {
		frame.setSize(400,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cand se inchide fereastra se opreste programul
		panel.setLayout(null); // daca ii null pot da valori pentru coordonatele componentelor
		p1.setBounds(10,10,20,25);
		p2.setBounds(10,50,20,25);
		p3.setBounds(10,90,20,25);
		poly1.setBounds(40,10,300,25);
		poly2.setBounds(40,50,300,25);
		poly3.setBounds(40,90,300,25);
		add.setBounds(35,200,60,60);
		sub.setBounds(125,200,60,60);
		mul.setBounds(215,200,60,60);
		div.setBounds(305,200,60,60);
		deriv.setBounds(30,280,100,60);
		integ.setBounds(150,280,100,60);
		clear.setBounds(270,280,100,60);
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial p2=parse(poly2.getText());
					Polynomial res=new Polynomial();
					res=res.add(p1, p2);
					poly3.setText(res.toString());
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial p2=parse(poly2.getText());
					Polynomial res=new Polynomial();
					res=res.sub(p1, p2);
					poly3.setText(res.toString());
				}
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		mul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial p2=parse(poly2.getText());
					Polynomial res=new Polynomial();
					res=res.mul(p1, p2);
					poly3.setText(res.toString());
				}
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		div.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial p2=parse(poly2.getText());
					ArrayList<Polynomial> res;
					res=p1.divide(p1, p2);
					poly3.setText("Q:" + res.get(0).toString() + " R:" + res.get(1).toString());
				}
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		deriv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial res=new Polynomial();
					res=res.derive(p1);
					poly3.setText(res.toString());
				}
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		
		integ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					Polynomial p1=parse(poly1.getText());
					Polynomial res=new Polynomial();
					res=res.integrate(p1);
					poly3.setText(res.toString());
				}
				catch(Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Invalid polynomial format");
				}
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				poly1.setText("");
				poly2.setText("");
				poly3.setText("");
			}
		});
		
		panel.add(p1);
		panel.add(p2);
		panel.add(p3);
		panel.add(poly1);
		panel.add(poly2);
		panel.add(poly3);
		panel.add(add);
		panel.add(sub);
		panel.add(mul);
		panel.add(div);
		panel.add(deriv);
		panel.add(integ);
		panel.add(clear);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
	
	public Polynomial parse(String polynomStr) throws Exception {
		Polynomial result=new Polynomial();
		polynomStr = polynomStr.replaceAll("-", "+-");
		String[] monomials = polynomStr.split("(\\+)");
		for(int i=0;i<monomials.length;i++)
		{
			//System.out.println(monomials[i]);
			if(monomials[i].matches("(-)?(\\d+)x\\^(\\d+)"))
			{
				String[] parts=monomials[i].split("x\\^");
				result.addMonomial(new Monomial(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])));
			}
			else
				if(monomials[i].matches("(-)?(\\d+)x"))
				{
					String[] parts=monomials[i].split("x");
					result.addMonomial(new Monomial(Integer.parseInt(parts[0]),1));
				}
				else
					if(monomials[i].matches("x\\^(\\d+)"))
					{
						String[] parts=monomials[i].split("x\\^");
						result.addMonomial(new Monomial(1,Integer.parseInt(parts[1]))); //check here if parsing goes wrong
					}
					else
						if(monomials[i].matches("(-)?(\\d+)"))
						{
							result.addMonomial(new Monomial(Integer.parseInt(monomials[i]),0));
						}
						else
							if(monomials[i].equals("x"))
							{
								result.addMonomial(new Monomial(1,1));
							}
							else
								if(monomials[i].equals("-x"))
								{
									result.addMonomial(new Monomial(-1,1));
								}
								else
									throw new Exception("Format gresit");
		}
		return result;
	}
	
}
