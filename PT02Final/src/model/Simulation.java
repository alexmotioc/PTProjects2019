package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import gui.Gui;

public class Simulation implements Runnable{
	private int minArrival;
	private int maxArrival;
	private int minService;
	private int maxService;
	private int nrOfQueues;
	private int simulationInterval;
	private ArrayList<Queue> queues;
	private LinkedList<Client> generatedQueue;
	private Gui gui;
	public Simulation(int minArrival, int maxArrival, int minService, int maxService, int nrOfQueues,
			int simulationInterval, Gui gui) {
		this.minArrival = minArrival;
		this.maxArrival = maxArrival;
		this.minService = minService;
		this.maxService = maxService;
		this.nrOfQueues = nrOfQueues;
		this.simulationInterval = simulationInterval;
		this.gui=gui;
		generatedQueue=new LinkedList<Client>();
		generateClients();
		queues=new ArrayList<Queue>();
		for(int i=0;i<nrOfQueues;i++) {
			Queue q=new Queue(i+1,gui);
			queues.add(q);
			Thread t=new Thread(q);
			t.start();
		}
	}
	
	public void generateClients() {
		int arriveTime=0;
		int serviceTime=0;
		int i=1;
		Random rand=new Random();
		while(arriveTime<=simulationInterval) {
			arriveTime+=minArrival + (Math.abs(rand.nextInt()) % (maxArrival-minArrival+1));
			serviceTime=minService + (Math.abs(rand.nextInt()) % (maxService-minService+1));
			Client c=new Client(arriveTime,serviceTime,i++);
			generatedQueue.add(c);
		}
	}
	
	public Queue selectQueue() {
		Queue min=queues.get(0);
		for(Queue q:queues) {
			if(q.getQueueSize()<min.getQueueSize())
				min=q;
		}
		return min;
	}
	

	@Override
	public synchronized void run() {
		int time=0;
		Queue selectedQueue;
		while(time<=simulationInterval) {
			System.out.println("Time: " + time);
			if(generatedQueue.isEmpty()==false) {
				if(generatedQueue.peek().getArrivalTime()==time) {
					selectedQueue=selectQueue();
					selectedQueue.addClient(generatedQueue.getFirst());
					gui.addClient(selectedQueue.getIndex());
					System.out.println("A ajuns clientul " + generatedQueue.getFirst().getIndex() + " la coada " + selectedQueue.getIndex());
					generatedQueue.remove();
				}
				time++;
				try {
					this.wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(int i=0;i<nrOfQueues;i++) {
			queues.get(i).addClient(new Client(0,0,-1));
		}
		
	}
	
	

}
