package sim1;

import util.Orientation;
import util.Point;

public class CreatureB
{

    private Point position;

    private Orientation orientation;

    private final BehaviorB behavior;


    public CreatureB(BehaviorB behavior, Point position, Orientation orientation)
    {

        this.behavior = behavior;
        this.position = position;
        this.orientation = orientation;
    }


    public Point getPosition() {return this.position;}


    public Orientation getOrientation() { return this.orientation; }
    

    /**
     * Changes the position of `this` if `world` is free at the target pos.
     * The orientation remains unchanged in any case.
     * 
	 */
    public void moveForward(World world) {
    	Point targetPosition = this.position.move(orientation.toVector());
    	if (world.isFree(targetPosition))
    		this.position = targetPosition;
    }

    /**
     * @post | getOrientation().isEqual(old(getOrientation().turnClockwise(1)))
     */
    public void turnClockwise() {this.orientation = this.orientation.turnClockwise(1);}


    public void turnCounterclockwise() {this.orientation = this.orientation.turnCounterclockwise(1);}

    /**
     * LEGIT
     * applies behavior from `this`
     */
    public void performAction(World world)
    {
        this.behavior.applyBehavior(world, this);
    }

    /**
     * LEGIT
     * true if same position and orient.
     */
    public boolean isEqual(CreatureB other) {
    	return (other != null) && (this.position.equals(other.getPosition())) && (this.orientation.isEqual(other.getOrientation()));
    }
    
    /**
     * @creates | result
     */
    public CreatureB giveCopy() {
    	return null;
    }
    
}
