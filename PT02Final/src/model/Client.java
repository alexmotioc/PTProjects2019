package model;

public class Client {
	private int arrivalTime;
	private int finishTime;
	private int serviceTime;
	private int index;
	
	public Client(int arrivalTime, int serviceTime,int index) {
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
		this.index=index;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}
	public int getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	

}
