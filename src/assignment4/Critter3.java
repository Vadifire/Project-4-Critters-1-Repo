package assignment4;

public class Critter3 extends Critter {

	/*
	 * Critter 3 is desperate to be on top of the world.
	 * They'll make it to the top or die trying. Unfortunately,
	 * they don't realize that the world wraps around at the top.
	 * They don't particularly care for fighting, but if they come
	 * across anyone that shares their goal (ie. Another Critter 3),
	 * the claws come out.
	 */
	
	
	@Override
	public String toString() {
		return "3";
	}

	@Override
	public void doTimeStep() {
		int direction = 2;
		int sideStep = (Critter.getRandomInt(1) == 0) ? -1 : 1;
		this.walk(direction + sideStep);
	}

	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("@") || opponent.equals(this.toString())) return true;
		else this.run(2);
		return false;
	}
	
	public static void runStats(java.util.List<Critter> critters) {
		System.out.println("There are: " + critters.size() + " Critter 3s vying for a spot at the top.");
	}

}
