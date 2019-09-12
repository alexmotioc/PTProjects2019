package Main;

import BusinessLayer.Restaurant;
import PresentationLayer.AdministratorGraphicalUserInterface;
import PresentationLayer.WaiterGraphicalUserInterface;

public class Main {

	public static void main(String[] args) {
		Restaurant restaurant=new Restaurant();
		AdministratorGraphicalUserInterface adminGui=new AdministratorGraphicalUserInterface(restaurant);
		WaiterGraphicalUserInterface waiterGui=new WaiterGraphicalUserInterface(restaurant);
	}

}
