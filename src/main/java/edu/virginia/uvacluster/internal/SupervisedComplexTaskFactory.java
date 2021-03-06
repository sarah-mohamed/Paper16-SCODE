package edu.virginia.uvacluster.internal;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.task.AbstractNetworkTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.TaskIterator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

//Initiates user input, which appends other tasks to iterator (because of control)...
public class SupervisedComplexTaskFactory implements TaskFactory{

	private final InputTask inputTask;
	CyApplicationManager appManager;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public SupervisedComplexTaskFactory(InputTask inputTask, CyApplicationManager appManager) {
		
		this.inputTask = inputTask;
		this.appManager = appManager;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		
	    
		TaskIterator programTasks = new TaskIterator();
		
		CyNetwork network = appManager.getCurrentNetwork();
		
		InputTask userInput = inputTask;
		TrainingTask train = new TrainingTask(network, userInput);
		SearchTask search = new SearchTask(network, userInput, train);
		
		//programTasks.append(userInput);
		programTasks.append(train);
		programTasks.append(search);
		
		return programTasks;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return true;
	}

//	@Override
//	public boolean isReady() {	
////		CyNetwork network = appManager.getCurrentNetwork();
////		System.out.println("The network is " + network.getSUID());
////		JDialog dialog = new JDialog();
////	    dialog.add(new JLabel("The network id is " + network.getSUID()));
////	    dialog.pack();
////	    dialog.setVisible(true);
//		//logger.debug("The network is " + network.getSUID());
////		if (network != null) {
////			return true;
////		}
//		return true;
//	}
	
	
}