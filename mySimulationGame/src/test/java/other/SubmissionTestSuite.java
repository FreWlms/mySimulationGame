package other;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import sim1.BehaviorA;
import sim1.BehaviorB;
import sim1.Chromosome;
import sim1.Constants;
import sim1.CreatureA;
import sim1.CreatureB;
import sim1.Simulation;
import sim1.World;
import util.Color;
import util.Orientation;
import util.Point;

class SubmissionTestSuite {

	/**
	 * @pre | n < width * height
	 * @pre | 0 < n
	 * gives n distinct Point positions within width, height.
	 * if beg is true, result gives "the first" n positions (at the top of the field)
	 * Else, result gives "the last" n positions (at the bot. of the field)
	 */
	static Point[] rows(int n, int width, int height, boolean beg) {
		Point[] res = new Point[n];
		if (beg) {
			for (int i = 0 ; i < n ; i++) {
				res[i] = new Point(i % width, i / width);
			}
		}
		else {
			for (int i = 0 ; i < n ; i ++) {
				res[i] = new Point((width - i - 1) % width, height - (i / width) - 1);
			}
		}

		return res;
	}
	
	/**
	 * Contants.POPU_SIZE - Constants.INIT_CREAT_A creatures of kind B positioned
	 * at the top of the field
	 * All have a north orientation
	 */
	static CreatureB[] givePopB() {
		CreatureB[] popB = new CreatureB[Constants.POPU_SIZE - Constants.INIT_CREAT_A];
		Point[] pos = rows(popB.length, Constants.WSIZE, Constants.WSIZE, true);
		for (int i = 0 ; i < popB.length ; i++) {
			popB[i] = new CreatureB(new BehaviorB(), pos[i], Orientation.north());
		}
		return popB;
	}
	
	/**
	 * `Constants.INIT_CREAT_A` creatures of kind A positioned at the
	 * bottom of the field.
	 * All have a north orientation
	 * @creates | result
	 */
	static CreatureA[] givePopA() {
		CreatureA[] popA = new CreatureA[Constants.INIT_CREAT_A];
		Point[] pos = rows(popA.length, Constants.WSIZE, Constants.WSIZE, false);
		for (int i = 0 ; i < popA.length ; i++) {
			popA[i] = new CreatureA(new BehaviorA(), pos[i], Orientation.north(), Chromosome.createRandom());
		}
		return popA;
		
	}
	
	
	
	
	
	@Test
	void mkColor() {
		Color c = new Color(100,100,100);
	}
	
	@Test
	void mkChromosome() {
		int[] weights = new int[Constants.CHROM_SIZE];
		Chromosome c = new Chromosome(weights);
	}
	
	@Test
	void mkCreatureB() {
		CreatureB creatB = new CreatureB(
				new BehaviorB(),
				new Point(0,0),
				Orientation.east());
	}
	
	@Test
	void mkCreatureA() {
		CreatureA creatA = new CreatureA(
				new BehaviorA(),
				new Point(0,0),
				Orientation.east(),
				Chromosome.createRandom());
	}
	
	@Test
	void mkSimulation() {
		Simulation s = new Simulation(
				Constants.WSIZE,
				Constants.POPU_SIZE,
				Constants.INIT_CREAT_A);
	}
	
	@Test
	void mkWorld() {
		World w = new World(Constants.WSIZE, Constants.WSIZE, givePopA(), givePopB());
	}
	
	@Test
	void stepWorld() {
		
		World w = new World(Constants.WSIZE, Constants.WSIZE, givePopA(), givePopB());
		
		CreatureB[] popB = w.getPopulationB();
		CreatureB[] popBbis = new CreatureB[popB.length];
		for (int i = 0 ; i < popB.length ; i++) {
			popBbis[i] = new CreatureB(new BehaviorB(), popB[i].getPosition(), popB[i].getOrientation());
		}
		
		w.step();
		
		assertFalse(
			World.areEqualCreatureBArrays(popBbis, w.getPopulationB()) );
		
		
	}
	
	
	@Test
	void nextGenSimulation() {
		Simulation s = new Simulation(
				Constants.WSIZE,
				Constants.POPU_SIZE,
				Constants.INIT_CREAT_A);
		
		s.nextGeneration();
	}
	
	
	
	
	
	
}
