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
 * Critter2 tries to make intelligent decisions about whether or not walk
 * base on how frequently Critter2 runs into food vs threats. It also only
 * decides to fight if it has to (has already walked) or if it determines it is
 * strong enough to fight. This metric is tracked in "energyToFight" and is
 * a genetic trait.
 */
public class Critter2 extends Critter{

	
	private int foodFound, threatsFound, totalSteps;
	private int energyToFight; //The minimum energy Critter2 feels comfortable fighting with

	boolean hasWalked;
	@Override
	public String toString() { return "2"; }
	
	
	public Critter2(){
		energyToFight = Params.start_energy/2;
		hasWalked = false;
		foodFound = threatsFound = totalSteps = 0;
	}
	
	@Override
	public void doTimeStep() {
		if (totalSteps < 10){ //Early on in life, Critter 2 will explore the world
			hasWalked = true;
			totalSteps++;
			walk(Critter.getRandomInt(8));
		}
		else{ //Can now make more informed decisions
			double threatFrequency = threatsFound/((double)totalSteps);
			double foodFrequency = foodFound/((double)totalSteps);
			if (foodFrequency > threatFrequency){ //Food is more likely to be countered than a threat
				hasWalked = true;
				totalSteps++;
				walk(Critter.getRandomInt(8));
			}
		}
		
		
		if (getEnergy() >= Params.min_reproduce_energy+energyToFight) { //Reproduce if can and will be left strong enough to fight.
			Critter2 child = new Critter2();
			child.energyToFight = energyToFight -10 + Critter.getRandomInt(20); //+/- 10 energyToFight from parent
			reproduce(child, Critter.getRandomInt(8));
		}
		
	}

	public boolean fight(String opponent) { 
		if (opponent.equals("@") || opponent.equals("S")){
			foodFound++; 
			return true; // Not afraid to take on Algae or Scaredy cats (Critter1)
		}
		else{
			threatsFound++;
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
		for (Object obj : critter2s) {
			Critter2 c2 = (Critter2) obj;
			totalEnergyToFight+=c2.energyToFight;
		}
		System.out.println(critter2s.size() + 
				" total Critter2s	average energyToFight req of "+(totalEnergyToFight/critter2s.size()));
	}


}
