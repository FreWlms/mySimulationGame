package sim1;


import util.Orientation;
import util.Point;
import util.RandomUtil;

/**
 * @immutable
 *
 */
public class BehaviorA
{

	/**
	 * @inspects | world
	 * @mutates | creature
	 */
    public void applyBehavior(World world, CreatureA creature)
    {
 
        var drift = computeFavoriteOrientation(creature.getChromosome()).toVector();
        
    	Point currentPosition = creature.getPosition();
    	Orientation orientation = creature.getOrientation();
    	
    	Point targetPosition = currentPosition.move(orientation.toVector().plus(drift));
    	
    	if (world.isFree(targetPosition)) {
    		creature.moveForward(world, drift);}
    	else {
    		if (RandomUtil.bool()) {
    				creature.turnClockwise();}
    			
    		else {
    			creature.turnCounterclockwise();}} 
        
    }
    
	/**
	 * LEGIT
	 * 
	 * The fav. orientation of a creature depends on its 3 first genes.
	 * 
	 */
    private static Orientation computeFavoriteOrientation(Chromosome chrom) {
    	
    	int mid = Constants.GENE_MIN + ((Constants.GENE_MAX - Constants.GENE_MIN)/2);
    	
    	int bit0 = (chrom.getGene(0) <= mid) ? 0 : 1;
    	int bit1 = (chrom.getGene(1) <= mid) ? 0 : 1;
    	int bit2 = (chrom.getGene(2) <= mid) ? 0 : 1;
    	
    	
    	int res = (bit2 << 2) | (bit1 << 1) | bit0;
    	if (! (0 <= res && res  <= 7)) {
    		throw new ArithmeticException("Average abs. gene value not in the expected range");
    	}
    	
    	return Orientation.orientations().get(res);
    	
    }
}