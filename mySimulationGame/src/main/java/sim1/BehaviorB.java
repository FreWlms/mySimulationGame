package sim1;


import util.Orientation;
import util.Point;
import util.RandomUtil;


public class BehaviorB
{

	/**
	 * See overview in assignment
	 */
    public void applyBehavior(World world, CreatureB creature)
    {
    	Point currentPosition = creature.getPosition();
    	Orientation orientation = creature.getOrientation();
    	
    	Point targetPosition = currentPosition.move(orientation.toVector());
    	
    	if (world.isFree(targetPosition)) {
    		creature.moveForward(world);}
    	else {
    		if (RandomUtil.bool()) {
    				creature.turnClockwise();}
    			
    		else {
    			creature.turnCounterclockwise();}} 	
    }
}