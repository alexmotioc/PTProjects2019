package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Gui extends JFrame{
	
	public Gui() {
		this.setSize(300,300);
		JPanel panel=new JPanel();
		JButton clientBut=new JButton("Clients");
		clientBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientGui g=new ClientGui();
			}
			
		});
		JButton productBut=new JButton("Product");
		productBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductGui g=new ProductGui();
			}
		});
		JButton orderBut=new JButton("Orders");
		orderBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrdersGui g=new OrdersGui();
			}
		});
		panel.add(clientBut);
		panel.add(productBut);
		panel.add(orderBut);
		this.setContentPane(panel);
		this.setVisible(true);
	}

}
