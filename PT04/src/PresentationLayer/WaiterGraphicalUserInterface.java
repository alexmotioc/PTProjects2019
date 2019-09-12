package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

public class WaiterGraphicalUserInterface extends JFrame{
	private JPanel panel;
	private JTable table1;
	private JTable table2;
	private JScrollPane table1Pane;
	private JScrollPane table2Pane;
	private Restaurant restaurant;
	private ArrayList<MenuItem> menuItems;
	private HashMap<Order,ArrayList<MenuItem>> orders;
	
	public WaiterGraphicalUserInterface(Restaurant restaurant) {
		this.restaurant=restaurant;
		this.setTitle("Waiter");
		menuItems=restaurant.getProducts();
		orders=restaurant.getOrders();
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel=new JPanel();
		drawTables();
		drawComponents();
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public void drawTables() {
		String[] columnNames= {"id","name","price","weight"};
		Object[][] data=new Object[menuItems.size()][4];
		for(int i=0;i<menuItems.size();i++) {
			data[i][0]=menuItems.get(i).getId();
			data[i][1]=menuItems.get(i).getName();
			data[i][2]=menuItems.get(i).computePrice();
			data[i][3]=menuItems.get(i).computeWeight();
		}
		table1=new JTable(data,columnNames);
		data=new Object[orders.size()+1][3];
		int i=0;
		for(Order o:orders.keySet()) {
			data[i][0]=o.getOrderId();
			data[i][1]=o.getDate();
			data[i][2]=o.getTable();
			i++;
		}
		String[] columnNames2= {"orderId","date","table"};
		table2=new JTable(data,columnNames2);
		table1Pane=new JScrollPane(table1);
		table2Pane=new JScrollPane(table2);
		panel.add(table1Pane);
		panel.add(table2Pane);
	}
	
	public void drawComponents() {
		JButton add=new JButton("add");
		JButton generateBill=new JButton("generate bill");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<MenuItem> orderItems=new ArrayList<MenuItem>();
				for(int i=0;i<table1.getSelectedRowCount();i++) {
					int id=Integer.valueOf(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRows()[i],0)));
					orderItems.add(restaurant.getProductById(id));
				}
				int orderId=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(table2.getSelectedRow(), 0)));
				Date date=Date.valueOf((String) table2.getModel().getValueAt(table2.getSelectedRow(), 1));
				int table=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(table2.getSelectedRow(), 2)));
				Order o=new Order(orderId,date,table);
				restaurant.createOrder(o,orderItems);
				tableRefresh();
			}
			
		});
		
		generateBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int orderId=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(table2.getSelectedRow(), 0)));
				Date date=(Date) table2.getModel().getValueAt(table2.getSelectedRow(), 1);
				int table=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(table2.getSelectedRow(), 2)));
				Order o=new Order(orderId,date,table);
				restaurant.generateBill(o);
			}
			
		});
		panel.add(add);
		panel.add(generateBill);
	}
	
	public void tableRefresh() {
		panel.removeAll();
		menuItems=restaurant.getProducts();
		orders=restaurant.getOrders();
		drawTables();
		drawComponents();
		panel.revalidate();
	}

}
