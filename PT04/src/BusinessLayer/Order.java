package BusinessLayer;

import java.util.Date;

public class Order {
	private int orderId;
	private Date date;
	private int table;
	public Order(int orderId, Date date, int table) {
		this.orderId = orderId;
		this.date = date;
		this.table = table;
	}
	@Override
	public int hashCode() {
		return orderId*orderId*3+table*13;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (orderId != other.orderId)
			return false;
		if (table != other.table)
			return false;
		return true;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", date=" + date + ", table=" + table + "]";
	}
	
}
