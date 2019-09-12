package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Simulation;

public class Gui extends JFrame{
	private JTextField arriveMin=new JTextField("Minimum arriving interval");
	private JTextField arriveMax=new JTextField("Maximum arriving interval");
	private JTextField proccessMin=new JTextField("Minimum processing time");
	private JTextField proccessMax=new JTextField("Maximum processing time");
	private JTextField nrOfQueues=new JTextField("Number of queues");
	private JTextField simulationInterval=new JTextField("Simulation time");
	private JButton startSimulation=new JButton("Start");
	private JPanel panel=new JPanel();
	int nrClients[]=new int[100];
	JLabel queue[][]=new JLabel[30][100];
	
	public Gui() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,400);
		panel.setLayout(null);
		arriveMin.setBounds(20, 20, 300, 30);
		panel.add(arriveMin);
		arriveMax.setBounds(20, 60, 300, 30);
		panel.add(arriveMax);
		proccessMin.setBounds(20, 100, 300, 30);
		panel.add(proccessMin);
		proccessMax.setBounds(20, 140, 300, 30);
		panel.add(proccessMax);
		nrOfQueues.setBounds(20, 180, 300, 30);
		panel.add(nrOfQueues);
		simulationInterval.setBounds(20, 220,300, 30);
		panel.add(simulationInterval);
		startSimulation.setBounds(20, 260, 300, 60);
		panel.add(startSimulation);
		Gui thisGui=this;
		startSimulation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Simulation sim=new Simulation(Integer.parseInt(arriveMin.getText()), Integer.parseInt(arriveMax.getText()), Integer.parseInt(proccessMin.getText()),
						Integer.parseInt(proccessMax.getText()), Integer.parseInt(nrOfQueues.getText()),Integer.parseInt(simulationInterval.getText()),thisGui);
				Thread t=new Thread(sim);
				t.start();
			}
			
		});
		
		this.setContentPane(panel);
		this.setVisible(true);
	}
	
	public void addClient(int reg)
	{
		queue[reg][nrClients[reg]]=new JLabel("Client");
		queue[reg][nrClients[reg]].setBounds(350+50*reg,80+30*nrClients[reg],40,20);
		this.add(queue[reg][nrClients[reg]]);
		nrClients[reg]++;
		this.revalidate();
		this.repaint();
	}
	public void removeClient(int reg) 
	{
		nrClients[reg]--;
		this.remove(queue[reg][nrClients[reg]]);
		this.revalidate();
		this.repaint();
	}
	

}
