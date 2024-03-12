package sim1;

import java.util.Arrays;

import util.Point;

public class World
{
	protected boolean iterationFinished = false;
    private final int width;
    private final int height;

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
    	
    	if (!isInside(pos))
    		return false;
    	
    	if (x == 1 || x == width -1 || y == 1 || y == height -1)
    		return true;
    	else
    		return false;
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
    	System.out.println("Zit nu in step methode.");
    	System.out.println(iterationFinished);
    	
    	this.iterationFinished = true;
    	for (int i = 0 ; i < populationB.length ; i ++) {
    		System.out.println("In eerste iteratie B.");
        	if (!this.isLimPos(populationB[i].getPosition()))
        		System.out.println("If 1 geslaagd.");
        		populationB[i].performAction(this);
        		iterationFinished = false;
        	}
    	
        for (int i = 0 ; i < populationA.length ; i ++) {
        	System.out.println("In tweede iteratie A.");
        	if (!this.isLimPos(populationA[i].getPosition()))
        		System.out.println("If 2 geslaagd.");
        		populationA[i].performAction(this);
        		iterationFinished = false;
        	}
    }
    

}
    


