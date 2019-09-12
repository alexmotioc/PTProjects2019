package BusinessLayer;

import java.util.ArrayList;

public interface IRestaurantProcessing {
	
	/*
	 * @inv wellFormed
	 * @pre menuItem!=null
	 * @post products contains menuItem
	 */
	public void createMenuItem(MenuItem menuItem);
	
	/*
	 * @inv wellFormed
	 * @pre products contains menuItem with id=id
	 * @post products does not contain menuItem with id=id
	 */
	public void deleteMenuItem(int id);
	
	/*
	 * @inv wellFormed
	 * @pre menuItem!=null
	 * @pre products contains menuItem with id=id
	 */
	public void editMenuItem(MenuItem menuItem);
	
	/*
	 * @inv wellFormed
	 * @pre order!=null
	 * @pre items!=null
	 * @post orders contain key order
	 */
	public void createOrder(Order order,ArrayList<MenuItem> items);
	
	/*
	 * @inv wellFormed
	 * @pre order!=null
	 * @pre orders contain key order
	 * @post price>=0
	 */
	public int computePriceForOrder(Order order);
	
	/*
	 * @inv wellFormed
	 * @pre order!=null
	 * @pre orders contains key order
	 */
	public void generateBill(Order order);

}
