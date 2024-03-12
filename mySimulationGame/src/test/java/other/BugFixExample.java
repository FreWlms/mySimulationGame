package other;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sim1.Chromosome;
import sim1.Constants;
import util.Color;

/**
 * This class contains an example of a setup method for unit tests
 * As well as 2 examples of flaw-detecting tests.
 * 
 * 
 */
class BugFixExample {
	
	
	int someField;
	
	@BeforeEach
	/**
	 * the above annotation means that the present method is rerun before each test begins.
	 */
	void setup() {
		someField = 6;
	}

	@Test
	/**
	 * The Color constructor *does not* initially have defensive programming
	 * (which is a bug, according to the assignment)
	 * 
	 * 
	 * The present test asserts instead that this constructor *does have* defensive programming.
	 * Hence it should succeed on a fixed version of Color() and fail on its initial version. 
	 * 
	 * Typically this test would go into a class called ColorTest.java.
	 */
	void ColorConstrIsDefensive() {
		assertThrows(IllegalArgumentException.class, () ->
			new Color(-1, 0, 0));
	}
	
	
	@Test
	/**
	 * The initial implementation of the Chromosome constructor
	 * directly stores the reference given as input in its weights field.
	 * This is a case of representation exposure. 
	 * 
	 * The present test asserts instead that the `weights` array reference is correctly encapsulated by the Chromosome constructor.
	 * 
	 * The present test fails on the initial implementation and succeeds on a fixed implementation.
	 * 
	 * Typically this test would go into a ChromosomeTest.java class.
	 */
	void chromosomeEncapsIn() {
		int[] weights = new int[Constants.CHROM_SIZE];
		for (int i = 0 ; i < weights.length ; i++) {
			weights[i] = Constants.GENE_MIN;
		}
		
		Chromosome chrom = new Chromosome(weights);
		
		weights[0] = Constants.GENE_MAX;
		
		assertNotEquals( Constants.GENE_MAX , chrom.getGene(0));
	}

}
