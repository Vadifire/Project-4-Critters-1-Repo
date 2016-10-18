/* CRITTERS Critter1 (Cedric)
 * EE422C Project 4 submission by
 * <Ahsan Khan>
 * <ajk2723>
 * <16445>
 * <Cedric Debelle>
 * <cfd363>
 * <16445>
 * Slip days used: <0>
 * Fall 2016
 */

package assignment4;

/*
 * Critter2 has a genetic preference on how strong it feels it needs to be to fight, 
 * and how often it decides to move. As Critter2 reproduces, natural selection
 * should influence the genes of Critter2 in a positive direction given the environment.
 */
public class Critter2 extends Critter{

	
	private int moveChance;
	private int energyToFight; //The minimum energy Critter2 feels comfortable fighting with

	boolean hasWalked;
	@Override
	public String toString() { return "2"; }
	
	
	public Critter2(){
		energyToFight = Params.start_energy/2;
		moveChance = 50;
		hasWalked = false;
	}
	
	@Override
	/**
	 * This method is intended to be called every worldTimeStep. When Critter2 first
	 * spawns, moves 10 times. If Critter2 finds that it is more likely to find food
	 * than a threat, it will have a preference to move. It also reproduces when it has
	 * more than Params.start_energy.
	 */
	public void doTimeStep() {
		
		int roll = Critter.getRandomInt(100); //roll = 0-99
		if (roll < moveChance)
			walk(Critter.getRandomInt(8));
		
		if (getEnergy() > Params.start_energy) { //Reproduces if Critter2 is doing well.
			Critter2 child = new Critter2();
			child.energyToFight = energyToFight -10 + Critter.getRandomInt(20); //+/- 10 energyToFight from parent
			child.moveChance = moveChance -10 + Critter.getRandomInt(20); //+/- 10 moveChance from parent
			if (child.moveChance > 100)
				child.moveChance = 100;
			else if (child.moveChance < 0)
				child.moveChance = 0;
			reproduce(child, Critter.getRandomInt(8));
		}
		
	}
	
	@Override
	/**
	 * Critter2 will always fight Algae or if it is forced to (has already moved).
	 * Critter2 will choose to run if its energy is lower than "energyToFight"
	 */
	public boolean fight(String opponent) { 
		if (opponent.equals("@") || opponent.equals("1")){
			return true; // Not afraid to take on Algae or Scaredy cats (Critter1)
		}
		else{
			if (hasWalked){
				return true; //No choice but to fight, cannot run.
			}
			else{
				if (getEnergy() > energyToFight){ //We feel strong, lets fight.
					return true;
				}
				else{
					run(Critter.getRandomInt(8)); //Feel weak, take our chances running.
					return false;
				}
			}
		}
	}

	public static void runStats(java.util.List<Critter> critter2s) {
		int totalEnergyToFight = 0;
		int totalMoveChance = 0;
		for (Object obj : critter2s) {
			Critter2 c2 = (Critter2) obj;
			totalEnergyToFight+=c2.energyToFight;
			totalMoveChance+=c2.moveChance;
		}
		System.out.println(critter2s.size() + 
				" total Critter2s, average energyToFight: "+(totalEnergyToFight/critter2s.size())+
				", average moveChance: "+(totalMoveChance/critter2s.size()));
	}


}
