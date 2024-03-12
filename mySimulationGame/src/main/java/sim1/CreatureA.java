package sim1;

import util.Orientation;
import util.Point;
import util.Vector;

public class CreatureA
{

    private Point position;
    private Orientation orientation;
    private final BehaviorA behavior;
    /**
     * @representationObject
     */
    private Chromosome chrom;


    public CreatureA(BehaviorA behavior, Point position, Orientation orientation, Chromosome chrom)
    {

        this.behavior = behavior;
        this.position = position;
        this.orientation = orientation;
        this.chrom = chrom;
    }


    public Point getPosition()
    {
        return this.position;
    }


    public Orientation getOrientation() { return this.orientation; }
    

    public BehaviorA getBehavior() {
    	return this.behavior;
    }
    

    public Chromosome getChromosome() {
    	return chrom;
    }

    /**
     * The orientation remains unchanged.
     */
    public void moveForward(World world, Vector drift) {
    	Point targetPosition = position.move(orientation.toVector().plus(drift));
    	if (world.isFree(targetPosition))
    		position = targetPosition;
    }

    
    public void turnClockwise() {this.orientation = this.orientation.turnClockwise(1);}
    

    public void turnCounterclockwise(){this.orientation = this.orientation.turnCounterclockwise(1);}

    /**
     * LEGIT
     * applies behavior of `this`
     */
    public void performAction(World world)
    {
        this.behavior.applyBehavior(world, this);
    }
    
    /**
     * true if same position and orient. and chromosome
     */
    public boolean isEqual(CreatureA other) {
    	return false;
    }
    
    /**
     * @creates | result
     */
    public CreatureA giveCopy() {
    	return null;
    }
}
