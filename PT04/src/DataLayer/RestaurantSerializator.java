package DataLayer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import BusinessLayer.MenuItem;

public class RestaurantSerializator {
	public void Serialize(ArrayList<MenuItem> menuItems) {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("menuItems.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(menuItems);
	         out.close();
	         fileOut.close();
	         //System.out.printf("Serialized data is saved in /tmp/menuItems.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public ArrayList<MenuItem> Deserialize() {
		ArrayList<MenuItem> menuItems=null;
		 try {
	         FileInputStream fileIn = new FileInputStream("menuItems.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         menuItems = (ArrayList<MenuItem>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	      }
		return menuItems;
	}

}
