package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import dao.ClientDAO;
import model.Client;

public class ClientBLL {
	

	public ClientBLL() {
	}

	public ArrayList<Client> findClients() {
		ArrayList<Client> st = ClientDAO.find();
		
		return st;
	}
	
	public void insertClient(Client client) {
		ClientDAO.insert(client);
	}
	
	public void deleteClient(int id) {
		ClientDAO.deleteById(id);
	}
	
	public Client findByID(int id) {
		return ClientDAO.findById(id);
	}

}
