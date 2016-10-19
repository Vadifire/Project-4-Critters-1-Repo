/* CRITTERS Controller.java
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

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

public class Controller {

	public boolean quit;
	Scanner keyboard;

	/**
	 * Creates a new Controller class with the given Scanner
	 * @param kb the Scanner from which commands will be read
	 */
	public Controller(Scanner kb) {
		quit = false;
		keyboard = kb;
	}

	/**
	 * Prompts the user for a command through Scanner 
	 * Handles the various commands
	 */
	public void promptInput() {
		System.out.print("critters>");
		String input = keyboard.nextLine();
		boolean commandFound = true;

		try {
			String[] commands = input.split(" ");

			for (String command : commands) {
				command.trim();
			}
			
			if (commands[0].equals("quit")){
				if (commands.length > 1)
					throw new IllegalArgumentException();
				quit = true;
			}
			else if (commands[0].equals("show")){
				if (commands.length > 1)
					throw new IllegalArgumentException();
				Critter.displayWorld();
			}
			else if (commands[0].equals("seed")){
				if (commands.length > 2)
					throw new IllegalArgumentException();
				Critter.setSeed(Long.parseLong(commands[1]));
			}
			else if (commands[0].equals("stats")){
				if(commands.length > 2) throw new IllegalArgumentException();
				List<Critter> instances = Critter.getInstances(commands[1]);
				
				String myPackage = Critter.class.getPackage().toString().split(" ")[1];
				
				Class<?> c = Class.forName(myPackage+"."+commands[1]);
				Class<?>[] types = { List.class };
				c.getMethod("runStats", types).invoke(c, instances);
			}
			
			else if (commands[0].equals("energy")){
				if(commands.length > 2) throw new IllegalArgumentException();
				List<Critter> instances = Critter.getInstances(commands[1]);
				int totalEnergy = 0;
				for (Critter c : instances){
					totalEnergy+=c.getEnergy();
				}
				System.out.println("Total system energy for "+instances.size()+" "+commands[1]+"s: "+totalEnergy);
			}
			
			else if (commands[0].equals("make")){
				int count = 1;
				if(commands.length > 3) throw new IllegalArgumentException();
				if (commands.length > 2) {
					count = Integer.parseInt(commands[2]);
				}
				while (count > 0) {
					count--;
					Critter.makeCritter(commands[1]);
				}
			}
			else if (commands[0].equals("step")){
				int count = 1;
				if(commands.length > 2) throw new IllegalArgumentException();
				if (commands.length > 1) {
					count = Integer.parseInt(commands[1]);
				}
				while (count > 0) {
					count--;
					Critter.worldTimeStep();
				}
			}
			else{
				commandFound = false;
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			if (commandFound == true)
				System.out.println("error processing: " + input);
			else
				System.out.println("invalid command: " + input);
		} catch (Error e){
			System.out.println("error processing: " + input);
		}
	}

}
