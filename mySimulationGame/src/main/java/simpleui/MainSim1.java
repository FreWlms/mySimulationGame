package simpleui;

import sim1.Constants;
import sim1.Simulation;

/**
 * LEGIT
 */
public class MainSim1
{

    public static void main(String[] args)
    {

    	//remark on coordinates:
    	//If a creature sits at (x,y) it is displayed as follows:
    	//- (0,0) is the top left point, 
    	//- (x,0) is x (simulation) units to the right of (0,0)
    	//- (x,y) is y (simulation) units below the latter
    	//- Besides, 1 simulation unit = 2 pixels (swing)

    	Simulation sim = new Simulation(Constants.WSIZE, Constants.POPU_SIZE, Constants.INIT_CREAT_A);
    	SimLifeWindow.create("Iteration 1", Constants.WSIZE, Constants.WSIZE, sim);
        }
}