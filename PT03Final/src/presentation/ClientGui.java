package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.*;

import bll.ClientBLL;
import model.Client;

public class ClientGui extends JFrame{
	private JPanel panel=new JPanel();
	private JButton add=new JButton("ADD");
	private JButton remove=new JButton("REMOVE");
	private JButton edit=new JButton("EDIT");
	private JScrollPane tableFin;
	private JTable table;
	public ClientGui() {
		this.setSize(500,500);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientBLL cb=new ClientBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				int age =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 2)));
				String address= (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				System.out.println(address);
				Client c=new Client(id,name,age,address);
				cb.insertClient(c);
				updateTable();
			}
			
		});
		
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientBLL cb=new ClientBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				
				cb.deleteClient(id);
				updateTable();
			}
			
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientBLL cb=new ClientBLL();
				int id=Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 0)));
				String name = (String) table.getModel().getValueAt(table.getSelectedRow(), 1);
				int age =Integer.parseInt(String.valueOf( table.getModel().getValueAt(table.getSelectedRow(), 2)));
				String address= (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				System.out.println(address);
				Client c=new Client(id,name,age,address);
				cb.deleteClient(id);
				cb.insertClient(c);
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
		for(j=0;j<cb.findClients().get(0).getClass().getDeclaredFields().length;j++) { //create a new line where we can add a new client
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
