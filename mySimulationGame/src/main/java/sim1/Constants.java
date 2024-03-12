package sim1;

/**
 * @immutable
 * LEGIT
 * 
 * A class gathering constants
 */
public class Constants {
	
	private Constants() {}
	
	//DNA related
	public static int CHROM_SIZE = 6; // should be >=3
	public static int GENE_MIN = 0;
	public static int GENE_MAX = 1000; //min and max should be different.
	public static int GENE_DELTA = 200; //typical "mutation" value. should be < (GENE_MAX - GENE_MIN)/2
	public static int MUT_RATE = 20; // Mutation rate. 0<= MUT_RATE <= 100
	
	
	//general purpose numbers
	public static int WSIZE = 300; //length of side of (square) world
	public static int POPU_SIZE = 1000;
	public static int INIT_CREAT_A = POPU_SIZE / 8;
	
	
	public final static int DEFAULT_FRAME_RATE = 300;
}
