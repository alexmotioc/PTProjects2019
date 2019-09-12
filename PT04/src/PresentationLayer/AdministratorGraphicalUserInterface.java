package PresentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import DataLayer.RestaurantSerializator;

public class AdministratorGraphicalUserInterface extends JFrame{

	private JTable table1;
	private JTable table2;
	private JScrollPane table1Pane;
	private JScrollPane table2Pane;
	private JPanel panel=new JPanel();
	private ArrayList<MenuItem> menuItems;
	private Restaurant restaurant;
	public AdministratorGraphicalUserInterface(Restaurant restaurant) {
		this.setSize(800,800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.restaurant=restaurant;
		this.setTitle("Administrator");
		drawComponents();
		menuItems=restaurant.getProducts();
		drawTables();
		this.setContentPane(panel);
		this.setVisible(true);
	}
	public void drawTables() {
		String[] columnNames= {"id","name","price","weight"};
		Object[][] data=new Object[menuItems.size()+1][4];
		for(int i=0;i<menuItems.size();i++) {
			data[i][0]=menuItems.get(i).getId();
			data[i][1]=menuItems.get(i).getName();
			data[i][2]=menuItems.get(i).computePrice();
			data[i][3]=menuItems.get(i).computeWeight();
		}
		table1=new JTable(data,columnNames);
		data=new Object[10][1];
		String[] columnNames2= {"id"};
		table2=new JTable(data,columnNames2);
		table1Pane=new JScrollPane(table1);
		table2Pane=new JScrollPane(table2);
		panel.add(table1Pane);
		panel.add(table2Pane);
	}

	public void drawComponents() {
		JButton add=new JButton("Add");
		JButton remove=new JButton("Remove");
		JButton edit=new JButton("Edit");
		panel.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0;
				ArrayList<MenuItem> ingredients=new ArrayList<MenuItem>();
				while(table2.getModel().getValueAt(i, 0)!=null) {
						int id=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(i, 0)));
						for(MenuItem menuItem:menuItems) {
							if(menuItem.getId()==id) {
								ingredients.add(menuItem);
								break;
							}
						}
						i++;
				}
				int id=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0)));
				String name=(String)table1.getModel().getValueAt(table1.getSelectedRow(), 1);
				int price=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 2)));
				int weight=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 3)));
				if(ingredients.isEmpty()) {
					BaseProduct menuItem=new BaseProduct(id,name,price,weight);
					restaurant.createMenuItem(menuItem);
				}
				else {
					CompositeProduct menuItem=new CompositeProduct(id,name,price,weight);
					for(MenuItem ing:ingredients) {
						menuItem.addIngredient(ing);
					}
					restaurant.createMenuItem(menuItem);
				}
				tableRefresh();
			}
			
		});
		panel.add(remove);
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0)));
				restaurant.deleteMenuItem(id);
				tableRefresh();
			}
			
		});
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i=0;
				ArrayList<MenuItem> ingredients=new ArrayList<MenuItem>();
				while(table2.getModel().getValueAt(i, 0)!=null) {
						int id=Integer.parseInt(String.valueOf(table2.getModel().getValueAt(i, 0)));
						for(MenuItem menuItem:menuItems) {
							if(menuItem.getId()==id) {
								ingredients.add(menuItem);
								break;
							}
						}
						i++;
				}
				int id=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 0)));
				String name=(String)table1.getModel().getValueAt(table1.getSelectedRow(), 1);
				int price=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 2)));
				int weight=Integer.parseInt(String.valueOf(table1.getModel().getValueAt(table1.getSelectedRow(), 3)));
				if(ingredients.isEmpty()) {
					BaseProduct menuItem=new BaseProduct(id,name,price,weight);
					restaurant.editMenuItem(menuItem);
				}
				else {
					CompositeProduct menuItem=new CompositeProduct(id,name,price,weight);
					for(MenuItem ing:ingredients) {
						menuItem.addIngredient(ing);
					}
					restaurant.editMenuItem(menuItem);
				}
				tableRefresh();
			}
			
		});
		panel.add(edit);
		
	}
	
	public void tableRefresh() {
		panel.removeAll();
		menuItems=restaurant.getProducts();
		drawComponents();
		drawTables();
		panel.revalidate();
	}

}
