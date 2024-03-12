package sim1;

import java.util.Arrays;
import java.util.stream.IntStream;

import util.RandomUtil;


public class Chromosome
{

    /**
     * @representationObject
     */
    private int[] weights;


	public Chromosome(int[] weights) {
		this.weights = weights;
	}

	/**
	 * LEGIT
	 * Gives 1 randomly generated chromosome
	 * @creates | result
	 */
	public static Chromosome createRandom()
    {
        int[] genes =
        		IntStream.generate(() -> RandomUtil.integer(Constants.GENE_MIN, Constants.GENE_MAX + 1))
        		.limit(Constants.CHROM_SIZE).toArray();

        return new Chromosome(genes);
    }

	/**
	 * Gives `count` randomly generated chromosomes
	 */
    public static Chromosome[] createRandom(int count){
    	Chromosome[] ChromCount = new Chromosome[count];
    	
    	for (int i = 0; i < count; i++) {
    		Chromosome newChromosome = Chromosome.createRandom();
    		ChromCount[i] = newChromosome;
    	}
    	
    	return ChromCount;
    }

    /**
     * index should be 0 <= index < Cst.CHROM_SIZE
     */
    public int getGene(int index){return weights[index];}
    

    public void setGene(int index, int val) {
       	if (index < 0 || index >= Constants.CHROM_SIZE)
    		throw new IllegalArgumentException();
       	this.weights[index] = val;
    	
    }

    /**
     * Returns a Chromosome whose weights are derived from `this` and `other` weights
     *  
     * 
     * @post The result starts with the first `index` genes from `this`
     * and finishes with (0 <=) genes picked in `other`.  
     * 
     */
    public Chromosome crossover(Chromosome other, int index)
    {
        int[] offspringGenes = new int[Constants.CHROM_SIZE];

        for ( int i = 0; i < index ; i++ )
        {
            offspringGenes[i] = this.getGene(i);
        }

        for ( int i = index ; i < Constants.CHROM_SIZE; i++ )
        {
            offspringGenes[i] = other.getGene(i);
        }

        return new Chromosome(offspringGenes);
    }

    /**
     * Gene #index is set to gene + delta if that modification remains within gene bounds.
     * 
     */
    public void mutate(int index, int delta) {
    	
    	if (index < 0 || index > Constants.CHROM_SIZE )
    		throw new IllegalArgumentException();
    	
    	int newVal = this.weights[index] + delta;
    	
    	if (newVal < Constants.GENE_MIN || newVal > Constants.GENE_MAX)
    	
    	this.weights[index] = newVal;
    	}


    public void randomlyMutate()
    {
        var index = RandomUtil.integer(weights.length);
        var delta = RandomUtil.integer(-Constants.GENE_DELTA, Constants.GENE_DELTA);

        this.mutate(index, delta);
    }
    

    public Chromosome giveCopy() {
    	int[] copy = this.weights.clone();
    	return new Chromosome(copy);
    }
    
    /**
     * LEGIT
     */
    public boolean isEqual(Chromosome other) {
    	boolean res = (other != null);
    	for (int i = 0 ; i < weights.length ; i ++) {
    		res = res && (weights[i] == other.getGene(i));
    	}
    	return res;
    }
}
