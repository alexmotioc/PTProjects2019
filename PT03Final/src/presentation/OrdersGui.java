package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;

public class OrdersGui extends JFrame{
	private static int orders=1;
	private JPanel panel=new JPanel();
	private JTable table1;
	private JTable table2;
	private JScrollPane tableFin1;
	private JScrollPane tableFin2;
	private JTextField quantity=new JTextField("Quantity");
	private JButton createOrder=new JButton("Add Order");
	public OrdersGui() {
		System.out.println("Test");
		this.setSize(1500,800);
		JFrame frame=this;
		panel.add(quantity);
		panel.add(createOrder);
		createTable1();
		createTable2();
		createOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderBLL cb=new OrderBLL();
				ProductBLL pb=new ProductBLL();
				ClientBLL cbb=new ClientBLL();
				int idClient = Integer.parseInt(String.valueOf( table1.getModel().getValueAt(table1.getSelectedRow(), 0)));
				int idProduct = Integer.parseInt(String.valueOf( table2.getModel().getValueAt(table2.getSelectedRow(), 0)));
				int q = Integer.parseInt(quantity.getText());

				Product p=pb.findByID(idProduct);
				Orders o=new Orders(idClient,idProduct,q);
				if(q>p.getStock())
					JOptionPane.showMessageDialog(frame, "Not enought stock");
				else {
				cb.insertOrder(o);
				pb.deleteProduct(idProduct);
				p.setStock(p.getStock()-q);
				pb.insertProduct(p);
				List<String> lines = Arrays.asList(cbb.findByID(idClient).toString(), pb.findByID(idProduct).toString());
				Path file = Paths.get("Order"+idProduct + "_" + idClient +".txt");
				try {
					Files.write(file, lines, Charset.forName("UTF-8"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateTable();
				}
			}
			
		});
		this.setContentPane(panel);
		this.setVisible(true);
		
	}

private void createTable1() {
		
		ClientBLL cb=new ClientBLL();
		Object data[][]=new Object[cb.findClients().size()+1][cb.findClients().get(0).getClass().getDeclaredFields().length];
		String[] columnNames=new String[cb.findClients().get(0).getClass().getDeclaredFields().length];
		int i=0;
		for(Field field: cb.findClients().get(0).getClass().getDeclaredFields()) {
			columnNames[i++]=field.getName();
		}
		
		int j=0;
		i=0;
		System.out.println(cb.findClients().size());
		for(Object o:cb.findClients())
		{
			j=0;
			for(Field field: o.getClass().getDeclaredFields()) {
				try {
					field.setAccessible(true);
					Object value;
					value=field.get(o);
					data[i][j++]=value;
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
		for(j=0;j<cb.findClients().get(0).getClass().getDeclaredFields().length;j++) {
			data[i][j]="";
		}
		table1 = new JTable(data,columnNames);
		tableFin1=new JScrollPane(table1);
		panel.add(tableFin1);
	}

private void createTable2() {
	
	ProductBLL cb=new ProductBLL();
	Object data[][]=new Object[cb.findProducts().size()+1][cb.findProducts().get(0).getClass().getDeclaredFields().length];
	String[] columnNames=new String[cb.findProducts().get(0).getClass().getDeclaredFields().length];
	int i=0;
	for(Field field: cb.findProducts().get(0).getClass().getDeclaredFields()) {
		columnNames[i++]=field.getName();
	}
	
	int j=0;
	i=0;
	System.out.println(cb.findProducts().size());
	for(Object o:cb.findProducts())
	{
		j=0;
		for(Field field: o.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				Object value;
				value=field.get(o);
				data[i][j++]=value;
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		i++;
	}
	for(j=0;j<cb.findProducts().get(0).getClass().getDeclaredFields().length;j++) {
		data[i][j]="";
	}
	table2 = new JTable(data,columnNames);
	tableFin2=new JScrollPane(table2);
	panel.add(tableFin2);
}

private void updateTable() {
	panel.remove(tableFin1);
	panel.remove(tableFin2);
	createTable1();
	createTable2();
	panel.revalidate();
}

	
}
