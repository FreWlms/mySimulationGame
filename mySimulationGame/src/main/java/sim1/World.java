package sim1;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import util.Point;

public class World
{
    private final int width;
    private final int height;
//	protected boolean iterationFinished = false;

    /**
     * @representationObject
     * @representationObjects
     */
    private final CreatureA[] populationA;

    /**
     * @representationObject
     * @representationObjects
     */
    private final CreatureB[] populationB;


    public World(int width, int height, CreatureA[] popA, CreatureB[] popB)
    {
        this.width = width;
        this.height = height;
        this.populationA = popA;
        this.populationB = popB;
    }


    public int getWidth() {
    	return this.width;
    }


    public int getHeight() {
    	return this.height;
    }


    public CreatureA[] getPopulationA() {
        return populationA;
    }


    public CreatureB[] getPopulationB() {
        return populationB;
    }

    /**
     * @pre | position != null
     */
    public boolean isInside(Point position){
    	return Point.isWithin(position, width, height);
    }
    

    
    /**
     * @pre | pos != null
     * Returns true if pos is 1 unit away from a wall (and inside the world)
     */
    public boolean isLimPos(Point pos) {
    	int x = pos.getX();
    	int y = pos.getY();
    	
    	System.out.println(pos);
    	System.out.println(isInside(pos));
    	
    	if (isInside(pos)) {
    		if (x == 2 || x == width -2 || y == 2 || y == height -2)
    			return true;
    		else
    			return false;}
    	else
    		return true;
    	
    }

    
    /**
     * LEGIT 
     * @pre | array1 != null && array2 != null
     * @pre | array1.length == array2.length
     */
    public static boolean areEqualCreatureAArrays(CreatureA[] array1, CreatureA[] array2) {
    	boolean res = true;
    	for (int i = 0 ; i < array1.length ; i++) {
    		res = res && array1[i].isEqual(array2[i]);
    	}
    	return res;
    }
    
    
    /**
     * LEGIT 
     * @pre | array1 != null && array2 != null
     * @pre | array1.length == array2.length
     */
    public static boolean areEqualCreatureBArrays(CreatureB[] array1, CreatureB[] array2) {
    	boolean res = true;
    	for (int i = 0 ; i < array1.length ; i++) {
    		res = res && array1[i].isEqual(array2[i]);
    	}
    	return res;
    }
    

    /**
     * true if position is inside the world and no creature sits there
     * 
     * @pre | position != null
     */
    public boolean isFree(Point position) {
        for (CreatureA creature : populationA) {
            if (creature.getPosition().equals(position)) {
                return false;
            }
        }
        for (CreatureB creature : populationB) {
            if (creature.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Performs the action of each creature
     */
    public void step()
    {	
 //   	System.out.println(iterationFinished);
    	
 //   	this.iterationFinished = true;
    	for (int i = 0 ; i < populationB.length ; i ++) {
        	if (!this.isLimPos(populationB[i].getPosition()))
        		populationB[i].performAction(this);
 //       		iterationFinished = false;
        	}
    	
        for (int i = 0 ; i < populationA.length ; i ++) {
        	if (!this.isLimPos(populationA[i].getPosition()))
        		populationA[i].performAction(this);
 //      		iterationFinished = false;
        }
    }
    

}
    


