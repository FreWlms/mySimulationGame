package sim1;


import java.util.ArrayList;

import util.Orientation;
import util.Point;
import util.RandomUtil;
import util.Vector;

public class Simulation {
	
	/**
	 * To remain efficient this reference can freely be accessed by the client
	 */
	private World world;

    private final int populationSize;

    /**
     * Creates a simulation having a getWorld() with:
     * - A square simulation field of side `size`
     * - A population size (resp. population A size) equal to `populationSize` (resp. `numA`)
     * - Randomly chosen orientations for all creatures
     * - Random positions for all creatures, within a central square whose points (x,y) are
     *   such that size/4 <= x < size/2 + size/4 (same for y)
     * - Randomly assigned chromosomes for creatures of kind A
     *    
     */
    public Simulation(int size, int populationSize, int numA)
    {
    	this.populationSize = populationSize;
    	this.world = createRandWorldWith(size, populationSize, numA, populationSize-numA, Chromosome.createRandom(numA));
    }
    
    public void runIteration() {
    	System.out.println("Nu eerste keer in while loop.");
    	System.out.println(world.iterationFinished);
    	while (!world.iterationFinished){
    		System.out.println("Eerste keer step methode uitvoeren.");
    		world.step();
    	}
    }


    public int getPopulationSize() {
    	return populationSize;
    }
    


    /**
     * Creates a World with:
     * - A square simulation field of side `size`
     * - A population size (resp. population A size) equal to `popuSize` (resp. `numA`)
     * - A population B size equal to `numB`
     * - Randomly chosen orientations for all creatures
     * - Random positions for all creatures, within a central square whose points (x,y) are
     *   such that size/4 <= x < size/2 + size/4 (same for y)
     * - Chromosomes for creatures of kind A as in `chromsA`
     */
    public static World createRandWorldWith(int size, int popuSize, int numA, int numB, Chromosome[] chromsA) {
    	if (size <= 0 || popuSize <= 0 || numA <= 0 || numB <= 0 || chromsA == null || popuSize != numA + numB) {
    		throw new IllegalArgumentException();}
    	
    	CreatureA[] populationA = new CreatureA[numA];
    	for (int i = 0; i < numA; i++) {
    		int x = RandomUtil.integer(size/4, size-size/4);
    		int y = RandomUtil.integer(size/4, size-size/4);
    		Point randomPointInSquare = new Point(x, y);
    		
    		BehaviorA behaviorA = new BehaviorA();
    		
    		Orientation orientation = Orientation.createRandom();
    		
    		int chromIndex = RandomUtil.integer(chromsA.length);
    		Chromosome chrom = chromsA[chromIndex];
    		
    		populationA[i] = new CreatureA(behaviorA, randomPointInSquare, orientation, chrom);	
    	}
    	
    	CreatureB[] populationB = new CreatureB[numB];
    	for (int i = 0; i <  numB; i++) {
    		int x = RandomUtil.integer(size/4, size-size/4);
    		int y = RandomUtil.integer(size/4, size-size/4);
    		Point randomPointInSquare = new Point(x, y);
    		
    		BehaviorB behaviorB = new BehaviorB();
    		
    		Orientation orientation = Orientation.createRandom();
    		
    		populationB[i] = new CreatureB(behaviorB, randomPointInSquare, orientation);
    	}
    	
    	
    	return new World(size, size, populationA, populationB);

    }


    public World getWorld() {
    	return world;
/*
    	CreatureA[] popA = world.getPopulationA();
    	CreatureB[] popB = world.getPopulationB();
    	return new World(world.getWidth(), world.getHeight(), popA, popB);
*/
    }


    /**
     * Replaces getWorld() with a new one. The latter is populated according to the same ratio
     * CreatureA/(CreatureA + CreatureB) of creatures of kind A,B that survived.
     * 
     * The DNA of CreatureA's of the new world  is derived from the DNA
     * of CreatureA's that survived. Specifically, Each new chromosome
     * is obtained by crossing over some surviving parent chromosomes and by maybe
     * applying a random mutation.
     * 
     * 
     */
    public void nextGeneration()
    {
    	//Formula from assignment
    	//nextCountA = (int) Math.floor( ((double) survA / (survA + survB)) * populationSize );
    	
    	int survA = countSurvivingCreatureA();
    	int survB = countSurvivingCreatureB();
    	
    	int nextCountA = (int) Math.floor((double) survA / (survA + survB) * populationSize);
    	int nextCountB = (int) Math.floor((double) survB / (survA + survB) * populationSize);
    	
    	CreatureA[] nextGenA = new CreatureA[nextCountA];
    	
    	ArrayList<Chromosome> survivingDNA = selectSurvivingDNA();
    	
    	Chromosome[] offspringA = computeOffspring(survivingDNA, nextCountA);
    	
    	for (int i = 0; i < nextCountA; i++) {
    		int x = RandomUtil.integer(world.getWidth()/4, world.getWidth()-world.getWidth()/4);
    		int y = RandomUtil.integer(world.getHeight()/4, world.getHeight()-world.getHeight()/4);
    		Point randomPointInSquare = new Point(x, y);
    		
    		BehaviorA behaviorA = new BehaviorA();
    		
    		Orientation orientation = Orientation.createRandom();
    		
    		nextGenA[i] = new CreatureA(behaviorA, randomPointInSquare, orientation, offspringA[i]);
    		}
    	
    	CreatureB[] nextGenB = new CreatureB[nextCountB];
    	for (int i = 0; i < nextCountB ; i++) {
    		int x = RandomUtil.integer(world.getWidth()/4, world.getWidth()-world.getWidth()/4);
    		int y = RandomUtil.integer(world.getHeight()/4, world.getHeight()-world.getHeight()/4);
    		Point randomPointInSquare = new Point(x, y);
    		
    		BehaviorB behaviorB = new BehaviorB();
    		
    		Orientation orientation = Orientation.createRandom();
    		
    		nextGenB[i] = new CreatureB(behaviorB, randomPointInSquare, orientation);
    		}
    	
    		this.world = new World(world.getWidth(), world.getHeight(), nextGenA, nextGenB);
    		
    		System.out.println("Ik ga nu iteration runnen.");
 
    	
    }
    
    
    /**
     * Returns the number of CreatureB that survive (see this.survives)
     */
    private int countSurvivingCreatureB() {
    	int count = 0;
    	for (CreatureB creature : world.getPopulationB()) {
    		if (this.survives(creature.getPosition()))
    			count++;
    	}
    	
    	return count;
    }
    
    /**
     * Returns the number of CreatureA that survive (see this.survives)
     */
    private int countSurvivingCreatureA() {
    	int count = 0;
    	for (CreatureA creature : world.getPopulationA()) {
    		if (this.survives(creature.getPosition()))
    			count++;
    	}
 
    	return count;
    }
    
    
    /**
     * The list of Chromosome's of creatures of kind A that survive (according to this.survives)
     */
    private ArrayList<Chromosome> selectSurvivingDNA()
    {
        ArrayList<Chromosome> result = new ArrayList<Chromosome>();
        //result.add(something)
       
        for (CreatureA creature : world.getPopulationA()) {
        	Point endPosition = creature.getPosition();
        	if (this.survives(endPosition))
        		result.add(creature.getChromosome());
        }

        return result;
    }
    
    /**

     * Returns an array of `count` Chromosomes.
     * 
     * Each such "offpsring" chromosome is obtained as a (1) random crossover (see Chromosome.crossover)
     * of 2 chromosomes called "parents" randomly picked in the input list.
     * and (2) some offpsring chromosomes are further subject to a mutation
     * 
     * if parentGeneration is empty, the result array consists of `count` random chromosomes. 

     */
    private Chromosome[] computeOffspring(ArrayList<Chromosome> parentGeneration, int count)
    {
    	
//        if ( RandomUtil.integer(100) < Constants.MUT_RATE )
//        {
//            offspring.randomlyMutate();
//        }
    	
    	Chromosome[] offspring = new Chromosome[count];
    	for (int i = 0; i < count; i++) {
    		int parent1Index = RandomUtil.integer(parentGeneration.size());
    		int parent2Index = RandomUtil.integer(parentGeneration.size());
    		
    		while (parent1Index == parent2Index) {
    			parent2Index = RandomUtil.integer(parentGeneration.size());
    		}
    		
    		Chromosome offspringChromosome = parentGeneration.get(parent1Index).crossover(parentGeneration.get(parent2Index), RandomUtil.integer(Constants.CHROM_SIZE));
    		
    		if (RandomUtil.integer(100) < Constants.MUT_RATE) 
    			offspringChromosome.randomlyMutate();
    		
    		offspring[i] = offspringChromosome;
    	}
    	
    	return offspring;
    }
    
    /**
     * LEGIT
     * 
     * @pre | pos != null
     * Survive zone = Lower right ninth of the field 
     */
    public boolean survives(Point pos) {
    	return isInSouthEastZone(pos);
    }
    
    /**
     * LEGIT 
     * 
     * @param pos
     * @return
     */
    private boolean isInSouthEastZone(Point pos) {
    	return (pos.getX() >= 2 * world.getWidth() / 3) &&
    			(pos.getY() >= 2* world.getHeight() / 3);
    }
    
}
