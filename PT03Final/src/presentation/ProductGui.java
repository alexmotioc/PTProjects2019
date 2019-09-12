package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.*;

import bll.ProductBLL;
import model.Product;

public class ProductGui extends JFrame{
	private JPanel panel=new JPanel();
	private JButton add=new JButton("ADD");
	private JButton remove=new JButton("REMOVE");
	private JButton edit=new JButton("EDIT");
	private JScrollPane tableFin;
	private JTable table;
	public ProductGui() {
		this.setSize(500,500);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductBLL cb=new ProductBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				int price =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 2)));
				int stock =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 3)));
				String category= (String) table.getModel().getValueAt(table.getSelectedRow(), 4);
				Product c=new Product(id,name,price,stock,category);
				cb.insertProduct(c);
				updateTable();
			}
			
		});
		
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductBLL cb=new ProductBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				
				cb.deleteProduct(id);
				updateTable();
			}
			
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductBLL cb=new ProductBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				int price =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 2)));
				int stock =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 3)));
				String category= (String) table.getModel().getValueAt(table.getSelectedRow(), 4);
				Product c=new Product(id,name,price,stock,category);
				cb.deleteProduct(id);
				cb.insertProduct(c);
				updateTable();
			}
			
		});
		
		panel.add(add);
		panel.add(remove);
		panel.add(edit);
		createTable();
		this.setContentPane(panel);
		this.setVisible(true);
	}
	private void createTable() {
		
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
		table = new JTable(data,columnNames);
		tableFin=new JScrollPane(table);
		panel.add(tableFin);
	}
	
	private void updateTable() {
		panel.remove(tableFin);
		createTable();
		panel.revalidate();
	}
}
