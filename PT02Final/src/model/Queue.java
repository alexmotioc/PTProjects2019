package model;

import java.util.LinkedList;

import gui.Gui;

public class Queue extends Thread{
	private LinkedList<Client> queue;
	private int index;
	private Gui gui;
	public Queue(int index, Gui gui) {
		this.index=index;
		this.gui=gui;
		queue=new LinkedList<Client>();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void addClient(Client c) {
		queue.add(c);
	}

	public void removeClient(Client c) {
		queue.remove(c);
	}

	public LinkedList<Client> getQueue() {
		return queue;
	}

	public void setQueue(LinkedList<Client> queue) {
		this.queue = queue;
	}

	@Override
	public synchronized void run() {
		while(true) {
			if(!queue.isEmpty()) {
				Client c=queue.getFirst();
				if(c.getIndex()==-1) //poison pill
				{
					this.interrupt(); //if we find the poison pill, we know the simulation time is over
				}
				try {
					this.wait(c.getServiceTime()*1000);
					System.out.println("Clientul " + c.getIndex() + " a parasit coada " + index);
					gui.removeClient(index);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				queue.remove();
				
			} else
				try {
					this.wait(100); //we verify more times per second if a client has entered the queue, because wait's are not simultaneous
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public int getQueueSize() {
		return queue.size();
	}



}
